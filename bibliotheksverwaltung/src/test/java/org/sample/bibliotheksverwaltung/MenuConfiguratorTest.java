package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import application.DBHandler;
import application.FrontendHandler;
import application.Menu;
import application.MenuAction;
import application.MenuConfigurator;
import application.QuitAppAction;
import domain.Book;
import domain.BookCopy;
import domain.Employee;
import domain.Lending;
import domain.User;

/**
 * Test für die MenuConfigurator-Klasse
 *
 * Folgt denke ich den ATRIP-Regeln:
 * - Automatic: Der Test läuft automatisch ohne manuelle Eingriffe
 * - Thorough: Testet verschiedene Szenarien (Mitarbeiter und regulärer Benutzer)
 * - Repeatable: Der Test führt bei jedem Durchlauf zum gleichen Ergebnis
 * - Independent: Der Test ist unabhängig von anderen Tests
 * - Professional: Der Test ist klar strukturiert und wartbar
 */
public class MenuConfiguratorTest {

    private MenuConfigurator menuConfiguratorForUser;
    private MenuConfigurator menuConfiguratorForEmployee;
    private String dbPath;
    private FrontendHandler frontendMock;
    private DBHandler<Book> bookDBMock;
    private DBHandler<BookCopy> bookCopyDBMock;
    private DBHandler<Lending> lendingDBMock;
    private DBHandler<User> userDBMock;
    private User userMock;
    private Employee employeeMock;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        // Mock-Objekte erstellen
        dbPath = "test.db";
        frontendMock = mock(FrontendHandler.class);
        bookDBMock = mock(DBHandler.class);
        bookCopyDBMock = mock(DBHandler.class);
        lendingDBMock = mock(DBHandler.class);
        userDBMock = mock(DBHandler.class);

        // Mock-Benutzer erstellen
        userMock = mock(User.class);
        employeeMock = mock(Employee.class);

        // MenuConfigurator für User erstellen
        when(frontendMock.getUser()).thenReturn(userMock);
        menuConfiguratorForUser = new MenuConfigurator(dbPath, frontendMock,
                                                        bookDBMock, bookCopyDBMock,
                                                        lendingDBMock, userDBMock);

        // MenuConfigurator für Employee erstellen
        when(frontendMock.getUser()).thenReturn(employeeMock);
        menuConfiguratorForEmployee = new MenuConfigurator(dbPath, frontendMock,
                                                            bookDBMock, bookCopyDBMock,
                                                            lendingDBMock, userDBMock);
    }

    /**
     * Test, dass das Menü für einen Mitarbeiter die richtigen Menüpunkte enthält
     */
    @Test
    public void testConfigureMenuForEmployee() {
        // Konfiguriere Menü für Mitarbeiter
        Menu menu = menuConfiguratorForEmployee.configureMenu();

        // Überprüfe Menüstruktur - Menü sollte 4 Einträge haben (3 Spezifische + QuitAppAction)
        String menuDescriptions = menu.getAllDescriptions();
        assertTrue(menuDescriptions.contains("List all Books"));
        assertTrue(menuDescriptions.contains("List books with given title"));
        assertTrue(menuDescriptions.contains("List all lendings"));
        assertTrue(menuDescriptions.contains("Quit app"));

        // Zähle die Anzahl der Zeilen, um zu überprüfen, ob es 4 Menüoptionen gibt
        String[] lines = menuDescriptions.split("\n");
        assertEquals(4, lines.length);
    }

    /**
     * Test, dass das Menü für einen regulären Benutzer die richtigen Menüpunkte enthält
     */
    @Test
    public void testConfigureMenuForUser() {
        // Konfiguriere Menü für Benutzer
        Menu menu = menuConfiguratorForUser.configureMenu();

        // Überprüfe Menüstruktur - Menü sollte 6 Einträge haben (5 Spezifische + QuitAppAction)
        String menuDescriptions = menu.getAllDescriptions();
        assertTrue(menuDescriptions.contains("List all Books"));
        assertTrue(menuDescriptions.contains("Lend A Book by ID"));
        assertTrue(menuDescriptions.contains("Return a loan Book by Lending ID"));
        assertTrue(menuDescriptions.contains("List books with given title"));
        assertTrue(menuDescriptions.contains("List all your lendings"));
        assertTrue(menuDescriptions.contains("Quit app"));

        // Zähle die Anzahl der Zeilen, um zu überprüfen, ob es 6 Menüoptionen gibt
        String[] lines = menuDescriptions.split("\n");
        assertEquals(6, lines.length);
    }

    /**
     * Test, dass die konfigurierten MenuAction-Objekte korrekt erstellt werden
     */
    @Test
    public void testMenuActionsAreCorrectlyCreated() {
        // Erstelle eine Menü-Simulation, die den registerAction-Aufruf protokolliert
        Menu menuMock = mock(Menu.class);

        // Erstelle einen Mocking-Wrapper für die configureMenu-Methode
        MenuConfigurator spyConfiguratorForEmployee = spy(menuConfiguratorForEmployee);
        doReturn(menuMock).when(spyConfiguratorForEmployee).configureMenu();

        // Rufe die Methode auf
        spyConfiguratorForEmployee.configureMenu();

        // Überprüfe, ob registerAction mit den richtigen Aktionstypen aufgerufen wurde
        ArgumentCaptor<MenuAction> actionCaptor = ArgumentCaptor.forClass(MenuAction.class);
        verify(menuMock, atLeast(4)).registerAction(actionCaptor.capture());

        // Extrahiere die registrierten Aktionen und überprüfe ihre Typen
        List<MenuAction> capturedActions = actionCaptor.getAllValues();

        // Überprüfe ob die QuitAppAction dabei ist
        boolean hasQuitAction = false;
        for (MenuAction action : capturedActions) {
            if (action instanceof QuitAppAction) {
                hasQuitAction = true;
                break;
            }
        }
        assertTrue("Das Menü sollte eine QuitAppAction enthalten", hasQuitAction);
    }
}
package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import application.DBHandler;
import application.FrontendHandler;
import application.ListAllLendings;
import application.ListBookByTitleAction;
import application.ListBooksAction;
import application.LendBookAction;
import application.ListUserLendings;
import application.Menu;
import application.MenuAction;
import application.MenuConfigurator;
import application.QuitAppAction;
import application.ReturnLendingAction;
import domain.Book;
import domain.BookCopy;
import domain.Employee;
import domain.Lending;
import domain.User;

/**
 * Test für die MenuConfigurator-Klasse
 *
 * Folgt den ATRIP-Regeln:
 * - Automatic: Der Test läuft automatisch ohne manuelle Eingriffe
 * - Thorough: Testet verschiedene Szenarien (Mitarbeiter und regulärer Benutzer)
 * - Repeatable: Der Test führt bei jedem Durchlauf zum gleichen Ergebnis
 * - Independent: Der Test ist unabhängig von anderen Tests
 * - Professional: Der Test ist klar strukturiert und wartbar
 */
public class MenuConfiguratorTest {

    // Konstanten für Testfälle
    private static final String TEST_DB_PATH = "test.db";
    
    // Zu testende Objekte
    private MenuConfigurator menuConfiguratorForUser;
    private MenuConfigurator menuConfiguratorForEmployee;
    
    // Mock-Objekte
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
        frontendMock = mock(FrontendHandler.class);
        bookDBMock = mock(DBHandler.class);
        bookCopyDBMock = mock(DBHandler.class);
        lendingDBMock = mock(DBHandler.class);
        userDBMock = mock(DBHandler.class);

        // Mock-Benutzer erstellen
        userMock = mock(User.class);
        employeeMock = mock(Employee.class);

        // Standardverhalten für Mocks konfigurieren
        when(employeeMock.getUserLevel()).thenReturn(1);
        
        // MenuConfigurator für User erstellen
        when(frontendMock.getUser()).thenReturn(userMock);
        menuConfiguratorForUser = new MenuConfigurator(TEST_DB_PATH, frontendMock,
                                                      bookDBMock, bookCopyDBMock,
                                                      lendingDBMock, userDBMock);

        // MenuConfigurator für Employee erstellen
        when(frontendMock.getUser()).thenReturn(employeeMock);
        menuConfiguratorForEmployee = new MenuConfigurator(TEST_DB_PATH, frontendMock,
                                                           bookDBMock, bookCopyDBMock,
                                                           lendingDBMock, userDBMock);
    }

    /**
     * Test, dass das Menü für einen Mitarbeiter die richtigen Menüpunkte enthält
     */
    @Test
    public void testConfigureMenuForEmployee() {
        // Mitarbeiter-Kontext sicherstellen
        when(frontendMock.getUser()).thenReturn(employeeMock);
        
        // Konfiguriere Menü für Mitarbeiter
        Menu menu = menuConfiguratorForEmployee.configureMenu();

        // Überprüfe Menüstruktur - Menü sollte 4 Einträge haben (3 Spezifische + QuitAppAction)
        String menuDescriptions = menu.getAllDescriptions();
        
        // Prüfe auf erwartete Menüeinträge
        assertTrue("Menü sollte 'List all Books' enthalten", 
                  menuDescriptions.contains("List all Books"));
        assertTrue("Menü sollte 'List books with given title' enthalten", 
                  menuDescriptions.contains("List books with given title"));
        assertTrue("Menü sollte 'List all lendings' enthalten", 
                  menuDescriptions.contains("List all lendings"));
        assertTrue("Menü sollte 'Quit app' enthalten", 
                  menuDescriptions.contains("Quit app"));

        // Zählt die Anzahl der Zeilen, um zu überprüfen, ob es 4 Menüoptionen gibt
        String[] lines = menuDescriptions.split("\n");
        assertEquals("Mitarbeitermenü sollte 4 Einträge haben", 4, lines.length);
    }

    /**
     * Test, dass das Menü für einen regulären Benutzer die richtigen Menüpunkte enthält
     */
    @Test
    public void testConfigureMenuForUser() {
        // Benutzerkontext sicherstellen
        when(frontendMock.getUser()).thenReturn(userMock);
        
        // Konfiguriere Menü für Benutzer
        Menu menu = menuConfiguratorForUser.configureMenu();

        // Überprüfe Menüstruktur - Menü sollte 6 Einträge haben (5 Spezifische + QuitAppAction)
        String menuDescriptions = menu.getAllDescriptions();
        
        // Prüfe auf erwartete Menüeinträge
        assertTrue("Menü sollte 'List all Books' enthalten", 
                  menuDescriptions.contains("List all Books"));
        assertTrue("Menü sollte 'Lend A Book by ID' enthalten", 
                  menuDescriptions.contains("Lend A Book by ID"));
        assertTrue("Menü sollte 'Return a loan Book by Lending ID' enthalten", 
                  menuDescriptions.contains("Return a loan Book by Lending ID"));
        assertTrue("Menü sollte 'List books with given title' enthalten", 
                  menuDescriptions.contains("List books with given title"));
        assertTrue("Menü sollte 'List all your lendings' enthalten", 
                  menuDescriptions.contains("List all your lendings"));
        assertTrue("Menü sollte 'Quit app' enthalten", 
                  menuDescriptions.contains("Quit app"));

        // Zähle die Anzahl der Zeilen, um zu überprüfen, ob es 6 Menüoptionen gibt
        String[] lines = menuDescriptions.split("\n");
        assertEquals("Benutzermenü sollte 6 Einträge haben", 6, lines.length);
    }

    /**
     * Test, dass die konfigurierten MenuAction-Objekte korrekt erstellt werden
     */
    @Test
    public void testMenuActionsAreCorrectlyCreated() {
        // 1. Test für Mitarbeiter-Menü
        // Mitarbeiterkontext sicherstellen
        when(frontendMock.getUser()).thenReturn(employeeMock);
        
        // Menü für Mitarbeiter konfigurieren
        Menu employeeMenu = menuConfiguratorForEmployee.configureMenu();
        
        // Menü-Beschreibungen holen und analysieren
        String employeeMenuDesc = employeeMenu.getAllDescriptions();
        assertTrue("Mitarbeitermenü sollte 'List all Books' enthalten", 
                  employeeMenuDesc.contains("List all Books"));
        assertTrue("Mitarbeitermenü sollte 'List books with given title' enthalten", 
                  employeeMenuDesc.contains("List books with given title"));
        assertTrue("Mitarbeitermenü sollte 'List all lendings' enthalten", 
                  employeeMenuDesc.contains("List all lendings"));
        assertTrue("Mitarbeitermenü sollte 'Quit app' enthalten", 
                  employeeMenuDesc.contains("Quit app"));
        
        // 2. Test für Benutzer-Menü
        // Benutzerkontext sicherstellen
        when(frontendMock.getUser()).thenReturn(userMock);
        
        // Menü für Benutzer konfigurieren
        Menu userMenu = menuConfiguratorForUser.configureMenu();
        
        // Menü-Beschreibungen holen und analysieren
        String userMenuDesc = userMenu.getAllDescriptions();
        assertTrue("Benutzermenü sollte 'List all Books' enthalten", 
                  userMenuDesc.contains("List all Books"));
        assertTrue("Benutzermenü sollte 'Lend A Book by ID' enthalten", 
                  userMenuDesc.contains("Lend A Book by ID"));
        assertTrue("Benutzermenü sollte 'Return a loan Book by Lending ID' enthalten", 
                  userMenuDesc.contains("Return a loan Book by Lending ID"));
        assertTrue("Benutzermenü sollte 'List books with given title' enthalten", 
                  userMenuDesc.contains("List books with given title"));
        assertTrue("Benutzermenü sollte 'List all your lendings' enthalten", 
                  userMenuDesc.contains("List all your lendings"));
        assertTrue("Benutzermenü sollte 'Quit app' enthalten", 
                  userMenuDesc.contains("Quit app"));
        
        // Sicherstellen, dass die Anzahl der Menüeinträge korrekt ist
        String[] employeeLines = employeeMenuDesc.split("\n");
        assertEquals("Mitarbeitermenü sollte 4 Einträge haben", 4, employeeLines.length);
        
        String[] userLines = userMenuDesc.split("\n");
        assertEquals("Benutzermenü sollte 6 Einträge haben", 6, userLines.length);
    }
}
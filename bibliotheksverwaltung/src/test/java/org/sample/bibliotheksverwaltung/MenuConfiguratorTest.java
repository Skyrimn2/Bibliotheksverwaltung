package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import application.DBHandler;
import application.FrontendHandler;
import application.IDisplayableFactory;
import application.ListAllLendings;
import application.ListBookByTitleAction;
import application.ListBooksAction;
import application.LendBookAction;
import application.ListUserLendings;
import application.Menu;
import application.MenuConfigurator;
import application.QuitAppAction;
import application.ReturnLendingAction;
import domain.Book;
import domain.BookCopy;
import domain.Employee;
import domain.Lending;
import domain.User;
import domain.UserInterface;


public class MenuConfiguratorTest {

    // Konstanten für Testfälle
    private static final String TEST_DB_PATH = "test.db";
    
    // Zu testende Objekte
    private MenuConfigurator menuConfigurator;
    
    // Mock-Objekte
    private FrontendHandler frontendMock;
    private DBHandler<Book> bookDBMock;
    private DBHandler<BookCopy> bookCopyDBMock;
    private DBHandler<Lending> lendingDBMock;
    private DBHandler<User> userDBMock;
    private IDisplayableFactory displayableFactoryMock;
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
        displayableFactoryMock = mock(IDisplayableFactory.class);

        // Mock-Benutzer erstellen
        userMock = mock(User.class);
        employeeMock = mock(Employee.class);

        // MenuConfigurator erstellen
        menuConfigurator = new MenuConfigurator(TEST_DB_PATH, frontendMock,
                                               bookDBMock, bookCopyDBMock,
                                               lendingDBMock, userDBMock,
                                               displayableFactoryMock);
    }

    
    @Test
    public void testConfigureMenuForEmployee() {
        // Mitarbeiter-Kontext einrichten
        when(frontendMock.getUser()).thenReturn(employeeMock);
        
        // Menü konfigurieren
        Menu menu = menuConfigurator.configureMenu();
        
        // Menüpunkte abrufen
        List<String> menuItems = menu.getAllDescriptions();
        
        // Prüfen, ob die richtigen Menüpunkte für Mitarbeiter vorhanden sind
        assertEquals(4, menuItems.size()); // 3 spezifische Aktionen + Beenden
        assertTrue(menuItems.contains("List all Books"));
        assertTrue(menuItems.contains("List books with given title"));
        assertTrue(menuItems.contains("List all lendings"));
        assertTrue(menuItems.contains("Quit app"));
    }

    
    @Test
    public void testConfigureMenuForUser() {
        // Benutzer-Kontext einrichten
        when(frontendMock.getUser()).thenReturn(userMock);
        
        // Menü konfigurieren
        Menu menu = menuConfigurator.configureMenu();
        
        // Menüpunkte abrufen
        List<String> menuItems = menu.getAllDescriptions();
        
        // Prüfen, ob die richtigen Menüpunkte für Benutzer vorhanden sind
        assertEquals(6, menuItems.size()); // 5 spezifische Aktionen + Beenden
        assertTrue(menuItems.contains("List all Books"));
        assertTrue(menuItems.contains("Lend A Book by ID"));
        assertTrue(menuItems.contains("Return a loan Book by Lending ID"));
        assertTrue(menuItems.contains("List books with given title"));
        assertTrue(menuItems.contains("List all your lendings"));
        assertTrue(menuItems.contains("Quit app"));
    }

    
    @Test
    public void testConfigureMenuForUnknownUserType() {
        // Einen Benutzer erstellen, der weder Employee noch User ist
        UserInterface unknownUserMock = mock(UserInterface.class);
        when(frontendMock.getUser()).thenReturn(unknownUserMock);
        
        // Menü konfigurieren
        Menu menu = menuConfigurator.configureMenu();
        
        // Menüpunkte abrufen
        List<String> menuItems = menu.getAllDescriptions();
        
        // Prüfen, ob nur die Beenden-Option vorhanden ist
        assertEquals(1, menuItems.size());
        assertTrue(menuItems.contains("Quit app"));
    }

    
    @Test
    public void testAddEmployeeActions() {
        // Ein Mock-Menü erstellen
        Menu menuMock = mock(Menu.class);
        
        // Eine Unterklasse von MenuConfigurator erstellen, die addEmployeeActions öffentlich macht
        class TestableMenuConfigurator extends MenuConfigurator {
            public TestableMenuConfigurator() {
                super(TEST_DB_PATH, frontendMock, bookDBMock, bookCopyDBMock, 
                      lendingDBMock, userDBMock, displayableFactoryMock);
            }
            
            public void publicAddEmployeeActions(Menu menu) {
                addEmployeeActions(menu);
            }
        }
        
        TestableMenuConfigurator configurator = new TestableMenuConfigurator();
        configurator.publicAddEmployeeActions(menuMock);
        
        // Überprüfen, dass die richtigen Aktionen hinzugefügt wurden
        verify(menuMock, times(3)).registerAction(any());
        verify(menuMock).registerAction(isA(ListBooksAction.class));
        verify(menuMock).registerAction(isA(ListBookByTitleAction.class));
        verify(menuMock).registerAction(isA(ListAllLendings.class));
    }

    
    @Test
    public void testAddUserActions() {
        // Ein Mock-Menü erstellen
        Menu menuMock = mock(Menu.class);
        
        // Eine Unterklasse von MenuConfigurator erstellen, die addUserActions öffentlich macht
        class TestableMenuConfigurator extends MenuConfigurator {
            public TestableMenuConfigurator() {
                super(TEST_DB_PATH, frontendMock, bookDBMock, bookCopyDBMock, 
                      lendingDBMock, userDBMock, displayableFactoryMock);
            }
            
            public void publicAddUserActions(Menu menu) {
                addUserActions(menu);
            }
        }
        
        TestableMenuConfigurator configurator = new TestableMenuConfigurator();
        configurator.publicAddUserActions(menuMock);
        
        // Überprüfen, dass die richtigen Aktionen hinzugefügt wurden
        verify(menuMock, times(5)).registerAction(any());
        verify(menuMock).registerAction(isA(ListBooksAction.class));
        verify(menuMock).registerAction(isA(LendBookAction.class));
        verify(menuMock).registerAction(isA(ReturnLendingAction.class));
        verify(menuMock).registerAction(isA(ListBookByTitleAction.class));
        verify(menuMock).registerAction(isA(ListUserLendings.class));
    }

    
    @Test
    public void testConfigureMenuForNullUser() {
        // Null-Benutzer einrichten
        when(frontendMock.getUser()).thenReturn(null);
        
        // Menü konfigurieren
        Menu menu = menuConfigurator.configureMenu();
        
        // Menüpunkte abrufen
        List<String> menuItems = menu.getAllDescriptions();
        
        // Prüfen, ob nur die Beenden-Option vorhanden ist
        assertEquals(1, menuItems.size());
        assertTrue(menuItems.contains("Quit app"));
    }
}
package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import application.DBHandler;
import application.FrontendHandler;
import application.Menu;
import application.MenuConfigurator;
import domain.Book;
import domain.BookCopy;
import domain.Employee;
import domain.Lending;
import domain.User;

public class MenuConfiguratorTest {

    private static final String TEST_DB_PATH = "test.db";
    
    // Zu testende Klasse
    private MenuConfigurator menuConfiguratorForUser;
    private MenuConfigurator menuConfiguratorForEmployee;
    
    // Mocks
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
        // Mocks erstellen
        frontendMock = mock(FrontendHandler.class);
        bookDBMock = mock(DBHandler.class);
        bookCopyDBMock = mock(DBHandler.class);
        lendingDBMock = mock(DBHandler.class);
        userDBMock = mock(DBHandler.class);

        // Mock-Benutzer erstellen
        userMock = mock(User.class);
        employeeMock = mock(Employee.class);

        // Standardverhalten für Mocks
        when(userMock.getUserLevel()).thenReturn(0);
        when(employeeMock.getUserLevel()).thenReturn(1);
        
        // MenuConfigurator für User
        when(frontendMock.getUser()).thenReturn(userMock);
        menuConfiguratorForUser = new MenuConfigurator(TEST_DB_PATH, frontendMock,
                                                      bookDBMock, bookCopyDBMock,
                                                      lendingDBMock, userDBMock);

        // MenuConfigurator für Employee
        menuConfiguratorForEmployee = new MenuConfigurator(TEST_DB_PATH, frontendMock,
                                                           bookDBMock, bookCopyDBMock,
                                                           lendingDBMock, userDBMock);
    }

    @Test
    public void testConfigureMenuForEmployee() {
        // Mitarbeiter-Kontext sicherstellen
        when(frontendMock.getUser()).thenReturn(employeeMock);
        
        // Menü für Mitarbeiter konfigurieren
        Menu menu = menuConfiguratorForEmployee.configureMenu();

        // Menü-Beschreibungen holen
        List<String> descriptions = menu.getAllDescriptions();
        
        // Prüfungen für Mitarbeitermenü
        assertTrue("Menü sollte 'List all Books' enthalten", 
                  descriptions.contains("List all Books"));
        assertTrue("Menü sollte 'List books with given title' enthalten", 
                  descriptions.contains("List books with given title"));
        assertTrue("Menü sollte 'List all lendings' enthalten", 
                  descriptions.contains("List all lendings"));
        assertTrue("Menü sollte 'Quit app' enthalten", 
                  descriptions.contains("Quit app"));

        // Erwartete Anzahl an Menüeinträgen
        assertEquals("Mitarbeitermenü sollte 4 Einträge haben", 4, descriptions.size());
    }

    @Test
    public void testConfigureMenuForUser() {
        // Benutzerkontext sicherstellen
        when(frontendMock.getUser()).thenReturn(userMock);
        
        // Menü für Benutzer konfigurieren
        Menu menu = menuConfiguratorForUser.configureMenu();

        // Menü-Beschreibungen holen
        List<String> descriptions = menu.getAllDescriptions();
        
        // Prüfungen für Benutzermenü
        assertTrue("Menü sollte 'List all Books' enthalten", 
                  descriptions.contains("List all Books"));
        assertTrue("Menü sollte 'Lend A Book by ID' enthalten", 
                  descriptions.contains("Lend A Book by ID"));
        assertTrue("Menü sollte 'Return a loan Book by Lending ID' enthalten", 
                  descriptions.contains("Return a loan Book by Lending ID"));
        assertTrue("Menü sollte 'List books with given title' enthalten", 
                  descriptions.contains("List books with given title"));
        assertTrue("Menü sollte 'List all your lendings' enthalten", 
                  descriptions.contains("List all your lendings"));
        assertTrue("Menü sollte 'Quit app' enthalten", 
                  descriptions.contains("Quit app"));

        // Erwartete Anzahl an Menüeinträgen
        assertEquals("Benutzermenü sollte 6 Einträge haben", 6, descriptions.size());
    }
    
    @Test
    public void testCorrectActionsForEmployee() {
        // Mitarbeiterkontext sicherstellen
        when(frontendMock.getUser()).thenReturn(employeeMock);
        
        // Menü für Mitarbeiter konfigurieren
        Menu menu = menuConfiguratorForEmployee.configureMenu();
        
        // Menü-Beschreibungen holen
        List<String> descriptions = menu.getAllDescriptions();
        
        // Inhalt und Reihenfolge der Menüpunkte prüfen
        assertEquals("List all Books", descriptions.get(0));
        assertEquals("List books with given title", descriptions.get(1));
        assertEquals("List all lendings", descriptions.get(2));
        assertEquals("Quit app", descriptions.get(3));
    }
    
    @Test
    public void testCorrectActionsForUser() {
        // Benutzerkontext sicherstellen
        when(frontendMock.getUser()).thenReturn(userMock);
        
        // Menü für Benutzer konfigurieren
        Menu menu = menuConfiguratorForUser.configureMenu();
        
        // Menü-Beschreibungen holen
        List<String> descriptions = menu.getAllDescriptions();
        
        // Inhalt und Reihenfolge der Menüpunkte prüfen
        assertEquals("List all Books", descriptions.get(0));
        assertEquals("Lend A Book by ID", descriptions.get(1));
        assertEquals("Return a loan Book by Lending ID", descriptions.get(2));
        assertEquals("List books with given title", descriptions.get(3));
        assertEquals("List all your lendings", descriptions.get(4));
        assertEquals("Quit app", descriptions.get(5));
    }
}
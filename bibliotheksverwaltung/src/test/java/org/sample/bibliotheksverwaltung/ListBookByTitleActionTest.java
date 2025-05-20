package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import application.DBHandler;
import application.FrontendHandler;
import application.IDisplayableFactory;
import application.ListBookByTitleAction;
import domain.Book;
import domain.BookCategory;
import domain.Displayable;


public class ListBookByTitleActionTest {

    // Zu testende Objekte
    private ListBookByTitleAction listBookByTitleAction;
    
    // Mock-Objekte
    private DBHandler<Book> dbMock;
    private FrontendHandler frontendMock;
    private IDisplayableFactory displayableFactoryMock;
    
    // Testdaten
    private final String TEST_TITLE = "Der Herr der Ringe";
    private final Book testBook1 = new Book("Der Herr der Ringe", "J.R.R. Tolkien", 1, 3, BookCategory.FICTION, 5);
    private final Book testBook2 = new Book("Der Herr der Ringe: Die zwei Türme", "J.R.R. Tolkien", 2, 1, BookCategory.FICTION, 3);
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        // Mock-Objekte erstellen
        dbMock = mock(DBHandler.class);
        frontendMock = mock(FrontendHandler.class);
        displayableFactoryMock = mock(IDisplayableFactory.class);
        
        // Standardverhalten für Mocks konfigurieren
        when(frontendMock.readString()).thenReturn(TEST_TITLE);
        
        // DisplayableFactory-Mock konfigurieren
        when(displayableFactoryMock.createDisplayableBook(any(Book.class)))
            .thenReturn(mock(Displayable.class));
        
        // Zu testende Klasse initialisieren
        listBookByTitleAction = new ListBookByTitleAction(dbMock, frontendMock, displayableFactoryMock);
    }

   
    @Test
    public void testGetDescription() {
        assertEquals("List books with given title", listBookByTitleAction.getDescription());
    }


    @Test
    public void testExecuteActionBooksFound() {
        // Vorbereitung: Bücher mit passendem Titel
        List<Book> books = Arrays.asList(testBook1, testBook2);
        when(dbMock.getItemsByString(eq("Title"), anyString())).thenReturn(books);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        verify(frontendMock).showMessage(contains("Input Title"));
        verify(frontendMock).readString();
        verify(dbMock).getItemsByString("Title", TEST_TITLE);
        verify(displayableFactoryMock, times(2)).createDisplayableBook(any(Book.class));
        
        // Prüfen, dass die Ergebnisliste korrekt an das Frontend übergeben wurde
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock).showResultList(displayablesCaptor.capture());
        
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(2, capturedDisplayables.size());
    }

    
    @Test
    public void testExecuteActionNoBooksFound() {
        // Vorbereitung: Keine Bücher mit passendem Titel
        when(dbMock.getItemsByString(eq("Title"), anyString())).thenReturn(new ArrayList<>());
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        verify(frontendMock).readString();
        verify(dbMock).getItemsByString("Title", TEST_TITLE);
        verify(displayableFactoryMock, never()).createDisplayableBook(any(Book.class));
        
        // Prüfen, dass eine leere Liste an das Frontend übergeben wurde
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock).showResultList(displayablesCaptor.capture());
        
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertTrue(capturedDisplayables.isEmpty());
    }

    
    @Test
    public void testExecuteActionDatabaseReturnsNull() {
        // Vorbereitung: Datenbankabfrage gibt null zurück
        when(dbMock.getItemsByString(eq("Title"), anyString())).thenReturn(null);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        verify(frontendMock).readString();
        verify(dbMock).getItemsByString("Title", TEST_TITLE);
        verify(frontendMock).showMessage(contains("Keine Bücher gefunden"));
        verify(displayableFactoryMock, never()).createDisplayableBook(any(Book.class));
        
        // Prüfen, dass eine leere Liste an das Frontend übergeben wurde
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock).showResultList(displayablesCaptor.capture());
        
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertTrue(capturedDisplayables.isEmpty());
    }

    
    @Test(expected = RuntimeException.class)
    public void testExecuteActionWithReadStringException() {
        // Vorbereitung: readString wirft eine Exception
        when(frontendMock.readString()).thenThrow(new RuntimeException("Test-Exception"));
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Die Exception sollte durchgereicht werden
    }

    
    @Test(expected = RuntimeException.class)
    public void testExecuteActionWithDatabaseException() {
        // Vorbereitung: getItemsByString wirft eine Exception
        when(dbMock.getItemsByString(anyString(), anyString())).thenThrow(new RuntimeException("DB-Exception"));
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Die Exception sollte durchgereicht werden
    }

    
    @Test(expected = RuntimeException.class)
    public void testExecuteActionWithShowResultListException() {
        // Vorbereitung: Bücher mit passendem Titel
        List<Book> books = Arrays.asList(testBook1, testBook2);
        when(dbMock.getItemsByString(eq("Title"), anyString())).thenReturn(books);
        
        // showResultList wirft eine Exception
        doThrow(new RuntimeException("Frontend-Exception")).when(frontendMock).showResultList(any());
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Die Exception sollte durchgereicht werden
    }
}
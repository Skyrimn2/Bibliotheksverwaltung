package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import adapter.DisplayableBook;
import application.DBHandler;
import application.DatabaseException;
import application.FrontendHandler;
import application.ListBookByTitleAction;
import domain.Book;
import domain.BookCategory;
import domain.Displayable;

public class ListBookByTitleActionTest {

    // Zu testende Klasse
    private ListBookByTitleAction listBookByTitleAction;
    
    // Mocks
    private DBHandler<Book> bookDBMock;
    private FrontendHandler frontendMock;
    
    // Testdaten
    private final String TEST_TITLE = "Der Herr der Ringe";
    private final Book testBook1;
    private final Book testBook2;
    
    public ListBookByTitleActionTest() {
        // Testdaten initialisieren
        testBook1 = new Book("Der Herr der Ringe", "J.R.R. Tolkien", 1, 3, BookCategory.FICTION, 5);
        testBook2 = new Book("Der Herr der Ringe: Die zwei Türme", "J.R.R. Tolkien", 2, 1, BookCategory.FICTION, 3);
    }
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws DatabaseException {
        // Mock-Objekte erstellen
        bookDBMock = mock(DBHandler.class);
        frontendMock = mock(FrontendHandler.class);
        
        // Zu testende Klasse initialisieren
        listBookByTitleAction = new ListBookByTitleAction(bookDBMock, frontendMock);
        
        // Standardverhalten für Mocks konfigurieren
        when(frontendMock.readString()).thenReturn(TEST_TITLE);
    }

    @Test
    public void testGetDescription() {
        String description = listBookByTitleAction.getDescription();
        assertEquals("List books with given title", description);
    }

    @Test
    public void testExecuteActionBooksFound() throws DatabaseException {
        // Bücher mit passendem Titel vorbereiten
        List<Book> books = Arrays.asList(testBook1, testBook2);
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(books);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Prüfungen
        verify(frontendMock).readString();
        verify(bookDBMock).getItemsByString("Title", TEST_TITLE);
        
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock).showResultList(displayablesCaptor.capture());
        
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(2, capturedDisplayables.size());
        
        // Überprüfen, dass es sich um DisplayableBook-Objekte handelt
        assert(capturedDisplayables.get(0) instanceof DisplayableBook);
        assert(capturedDisplayables.get(1) instanceof DisplayableBook);
    }

    @Test
    public void testExecuteActionNoBooksFound() throws DatabaseException {
        // Keine Bücher mit passendem Titel
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(new ArrayList<>());
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Prüfungen
        verify(frontendMock).readString();
        verify(bookDBMock).getItemsByString("Title", TEST_TITLE);
        
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock).showResultList(displayablesCaptor.capture());
        
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(0, capturedDisplayables.size());
    }

    @Test
    public void testExecuteActionDatabaseReturnsNull() throws DatabaseException {
        // Datenbankabfrage gibt null zurück
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(null);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Prüfungen
        verify(frontendMock).readString();
        verify(bookDBMock).getItemsByString("Title", TEST_TITLE);
        
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock).showResultList(displayablesCaptor.capture());
        
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(0, capturedDisplayables.size());
        
        verify(frontendMock).showMessage("Keine Bücher gefunden oder Datenbankfehler aufgetreten.");
    }
    
    @Test
    public void testExecuteActionDatabaseException() throws DatabaseException {
        // Datenbankabfrage wirft Exception
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenThrow(new DatabaseException("Datenbankfehler"));
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Prüfungen
        verify(frontendMock).readString();
        verify(bookDBMock).getItemsByString("Title", TEST_TITLE);
        verify(frontendMock).showMessage(contains("Datenbankfehler:"));
    }
    
    @Test
    public void testExecuteActionWithEmptyTitle() throws DatabaseException {
        // Eingabe ist leerer String
        when(frontendMock.readString()).thenReturn("");
        
        // Bücher trotz leerem Titel
        List<Book> books = Arrays.asList(testBook1, testBook2);
        when(bookDBMock.getItemsByString(eq("Title"), eq(""))).thenReturn(books);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Prüfungen
        verify(frontendMock).readString();
        verify(bookDBMock).getItemsByString("Title", "");
        
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock).showResultList(displayablesCaptor.capture());
        
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(2, capturedDisplayables.size());
    }
}
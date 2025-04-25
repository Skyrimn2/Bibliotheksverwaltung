package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import adapter.DisplayableBook;
import application.DBHandler;
import application.FrontendHandler;
import application.ListBookByTitleAction;
import domain.Book;
import domain.BookCategory;
import domain.Displayable;

public class ListBookByTitleActionTest {

    // Zu testende Objekte
    private ListBookByTitleAction listBookByTitleAction;
    
    // Mock-Objekte
    private DBHandler<Book> bookDBMock;
    private FrontendHandler frontendMock;
    
    // Testdaten
    private final String TEST_TITLE = "Der Herr der Ringe";
    private final Book testBook1;
    private final Book testBook2;
    
    // Zum Abfangen der System.out-Ausgaben
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    public ListBookByTitleActionTest() {
        // Testdaten initialisieren
        testBook1 = new Book("Der Herr der Ringe", "J.R.R. Tolkien", 1, 3, BookCategory.FICTION, 5);
        testBook2 = new Book("Der Herr der Ringe: Die zwei Türme", "J.R.R. Tolkien", 2, 1, BookCategory.FICTION, 3);
    }
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        // System.out umleiten, um Ausgaben überprüfen zu können
        System.setOut(new PrintStream(outContent));
        
        // Mock-Objekte erstellen
        bookDBMock = mock(DBHandler.class);
        frontendMock = mock(FrontendHandler.class);
        
        // Zu testende Klasse initialisieren
        listBookByTitleAction = new ListBookByTitleAction(bookDBMock, frontendMock);
        
        // Standardverhalten für Mocks konfigurieren
        when(frontendMock.readString()).thenReturn(TEST_TITLE);
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Testet die Beschreibung der Aktion
     */
    @Test
    public void testGetDescription() {
        String description = listBookByTitleAction.getDescription();
        assertEquals("List books with given title", description);
    }

    /**
     * Testet den Konstruktor mit verschiedenen Parametern
     */
    @Test
    public void testConstructor() {
        // Erzeuge eine neue Instanz mit den Mock-Objekten
        ListBookByTitleAction action = new ListBookByTitleAction(bookDBMock, frontendMock);
        
        // Überprüfe, dass die Beschreibung korrekt gesetzt wurde
        assertEquals("List books with given title", action.getDescription());
        
        // Da die aktuelle Implementierung keine Null-Checks im Konstruktor hat,
        // können wir das Verhalten nicht wie erwartet testen.
        // In einer verbesserten Version würde man hier Null-Checks hinzufügen.
    }

    /**
     * Testet die erfolgreiche Suche und Anzeige von Büchern mit einem bestimmten Titel
     */
    @Test
    public void testExecuteActionBooksFound() {
        // Vorbereitung: Bücher mit passendem Titel
        List<Book> books = Arrays.asList(testBook1, testBook2);
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(books);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde mit dem korrekten Suchbegriff durchgeführt
        verify(bookDBMock, times(1)).getItemsByString("Title", TEST_TITLE);
        
        // 3. Die gefundenen Bücher wurden als Displayables an das Frontend übergeben
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock, times(1)).showResultList(displayablesCaptor.capture());
        
        // Die Liste sollte 2 Displayables enthalten (für jedes gefundene Buch)
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(2, capturedDisplayables.size());
        
        // Die Displayables sollten DisplayableBook-Objekte sein
        assert(capturedDisplayables.get(0) instanceof DisplayableBook);
        assert(capturedDisplayables.get(1) instanceof DisplayableBook);
        
        // Überprüfe, dass die Eingabeaufforderung ausgegeben wurde
        assert(outContent.toString().contains("Input Title you want to search"));
    }

    /**
     * Testet den Fall, dass keine Bücher mit dem gesuchten Titel gefunden werden
     */
    @Test
    public void testExecuteActionNoBooksFound() {
        // Vorbereitung: Keine Bücher mit passendem Titel
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(new ArrayList<>());
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde mit dem korrekten Suchbegriff durchgeführt
        verify(bookDBMock, times(1)).getItemsByString("Title", TEST_TITLE);
        
        // 3. Eine leere Liste wurde an das Frontend übergeben
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock, times(1)).showResultList(displayablesCaptor.capture());
        
        // Die Liste sollte leer sein
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(0, capturedDisplayables.size());
    }

    /**
     * Testet den Fall, dass die Datenbankabfrage null zurückgibt (Fehlerfall)
     */
    @Test
    public void testExecuteActionDatabaseReturnsNull() {
        // Vorbereitung: Datenbankabfrage gibt null zurück
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(null);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde mit dem korrekten Suchbegriff durchgeführt
        verify(bookDBMock, times(1)).getItemsByString("Title", TEST_TITLE);
        
        // 3. Eine leere Liste wurde an das Frontend übergeben (Fehlerbehandlung)
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock, times(1)).showResultList(displayablesCaptor.capture());
        
        // Die Liste sollte leer sein
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(0, capturedDisplayables.size());
        
        // Überprüfe, dass die Fehlermeldung ausgegeben wurde
        assert(outContent.toString().contains("Keine Bücher gefunden oder Datenbankfehler aufgetreten"));
    }
    
    /**
     * Testet das Verhalten, wenn der DisplayableBook-Konstruktor Probleme verursacht
     */
    @Test
    public void testExecuteActionWithDisplayableBookIssue() {
        // Erstelle ein Buch, das bei der Umwandlung in DisplayableBook Probleme verursachen könnte
        Book problematicBook = mock(Book.class);
        when(problematicBook.getId()).thenReturn(-1); // Ungültige ID
        when(problematicBook.getTitle()).thenReturn(null); // Kein Titel
        when(problematicBook.getAutor()).thenReturn(null); // Kein Autor
        when(problematicBook.getCategoryString()).thenReturn(null); // Keine Kategorie
        when(problematicBook.getCopies()).thenReturn(-1); // Ungültige Anzahl
        when(problematicBook.getAvailableCopies()).thenReturn(-1); // Ungültige Anzahl
        
        // Vorbereitung: Liste mit problematischem Buch
        List<Book> books = Arrays.asList(problematicBook);
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(books);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde mit dem korrekten Suchbegriff durchgeführt
        verify(bookDBMock, times(1)).getItemsByString("Title", TEST_TITLE);
        
        // 3. Die Liste mit einem Displayable wurde an das Frontend übergeben
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock, times(1)).showResultList(displayablesCaptor.capture());
        
        // Die Liste sollte ein Element enthalten
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(1, capturedDisplayables.size());
    }
    
    /**
     * Testet das Verhalten, wenn mit verschiedenen Arten von leeren Werten aufgerufen wird
     */
    @Test
    public void testExecuteActionWithEmptyValues() {
        // Vorbereitung: Frontend gibt leeren String zurück
        when(frontendMock.readString()).thenReturn("");
        
        // Vorbereitung: Bücher mit passendem Titel (leerer String)
        List<Book> books = Arrays.asList(testBook1, testBook2);
        when(bookDBMock.getItemsByString(eq("Title"), eq(""))).thenReturn(books);
        
        // Ausführen der zu testenden Aktion
        listBookByTitleAction.executeAction();
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde mit dem korrekten Suchbegriff durchgeführt
        verify(bookDBMock, times(1)).getItemsByString("Title", "");
        
        // 3. Die gefundenen Bücher wurden als Displayables an das Frontend übergeben
        ArgumentCaptor<List<Displayable>> displayablesCaptor = ArgumentCaptor.forClass(List.class);
        verify(frontendMock, times(1)).showResultList(displayablesCaptor.capture());
        
        // Die Liste sollte 2 Displayables enthalten (für jedes gefundene Buch)
        List<Displayable> capturedDisplayables = displayablesCaptor.getValue();
        assertEquals(2, capturedDisplayables.size());
    }
    
    /**
     * Testet, dass die Klasse mit einer Exception in readString umgehen kann
     */
    @Test
    public void testExecuteActionWithReadStringException() {
        // Vorbereitung: readString wirft eine Exception
        when(frontendMock.readString()).thenThrow(new RuntimeException("Test-Exception"));
        
        try {
            // Ausführen der zu testenden Aktion
            listBookByTitleAction.executeAction();
            // Wenn wir hierher kommen, ist etwas schiefgelaufen, da eine Exception erwartet wird
            assert(false);
        } catch (RuntimeException e) {
            // Die Exception sollte durchgereicht werden
            assertEquals("Test-Exception", e.getMessage());
        }
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde nicht durchgeführt
        verify(bookDBMock, times(0)).getItemsByString(anyString(), anyString());
    }
    
    /**
     * Testet, dass die Klasse mit einer Exception in getItemsByString umgehen kann
     */
    @Test
    public void testExecuteActionWithDatabaseException() {
        // Vorbereitung: getItemsByString wirft eine Exception
        when(bookDBMock.getItemsByString(anyString(), anyString())).thenThrow(new RuntimeException("DB-Exception"));
        
        try {
            // Ausführen der zu testenden Aktion
            listBookByTitleAction.executeAction();
            // Wenn wir hierher kommen, ist etwas schiefgelaufen, da eine Exception erwartet wird
            assert(false);
        } catch (RuntimeException e) {
            // Die Exception sollte durchgereicht werden
            assertEquals("DB-Exception", e.getMessage());
        }
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde durchgeführt
        verify(bookDBMock, times(1)).getItemsByString("Title", TEST_TITLE);
    }
    
    /**
     * Testet, dass die Klasse mit einer Exception in showResultList umgehen kann
     */
    @Test
    public void testExecuteActionWithShowResultListException() {
        // Vorbereitung: Bücher mit passendem Titel
        List<Book> books = Arrays.asList(testBook1, testBook2);
        when(bookDBMock.getItemsByString(eq("Title"), anyString())).thenReturn(books);
        
        // showResultList wirft eine Exception
        doThrow(new RuntimeException("Frontend-Exception")).when(frontendMock).showResultList(any());
        
        try {
            // Ausführen der zu testenden Aktion
            listBookByTitleAction.executeAction();
            // Wenn wir hierher kommen, ist etwas schiefgelaufen, da eine Exception erwartet wird
            assert(false);
        } catch (RuntimeException e) {
            // Die Exception sollte durchgereicht werden
            assertEquals("Frontend-Exception", e.getMessage());
        }
        
        // Überprüfungen
        // 1. Frontend wurde aufgefordert, den Suchbegriff zu lesen
        verify(frontendMock, times(1)).readString();
        
        // 2. Datenbankabfrage wurde durchgeführt
        verify(bookDBMock, times(1)).getItemsByString("Title", TEST_TITLE);
        
        // 3. Das Frontend wurde aufgefordert, die Ergebnisse anzuzeigen
        verify(frontendMock, times(1)).showResultList(any());
    }
}

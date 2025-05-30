package org.sample.bibliotheksverwaltung;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import application.DBHandler;
import application.FrontendHandler;
import application.LendBookAction;
import domain.Book;
import domain.BookCategory;
import domain.BookCopy;
import domain.Lending;
import domain.User;
import domain.UserInterface;


public class LendBookActionTest {

    // Zu testende Objekte
    private LendBookAction lendBookAction;
    
    // Mock-Objekte
    private DBHandler<Book> bookDBMock;
    private DBHandler<BookCopy> copyDBMock;
    private DBHandler<Lending> lendingDBMock;
    private DBHandler<User> userDBMock;
    private FrontendHandler frontendMock;
    private UserInterface userInterface;
    private User user;
    private Book book;
    private List<BookCopy> bookCopies;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        // Mock-Objekte erstellen
        bookDBMock = mock(DBHandler.class);
        copyDBMock = mock(DBHandler.class);
        lendingDBMock = mock(DBHandler.class);
        userDBMock = mock(DBHandler.class);
        frontendMock = mock(FrontendHandler.class);
        user = new User("testuser", 42);
        userInterface = user;
        book = new Book("Testbook", "Testauthor", 101, 1, BookCategory.FICTION, 2);
        
        // LendBookAction initialisieren
        		
        lendBookAction = new LendBookAction(bookDBMock, copyDBMock, lendingDBMock, userDBMock, frontendMock);
        
        // Standardverhalten für Mocks konfigurieren
        when(frontendMock.getUser()).thenReturn(userInterface);
        when(userDBMock.loadItemByID(42)).thenReturn(user);
    }

    /**
     * Testet den erfolgreichen Ausleihvorgang eines Buches
     */
    @Test
    public void testExecuteActionSuccessful() {
        // Vorbereitung: Buch mit verfügbaren Exemplaren
        int bookId = 101;
        book = new Book("Testbook", "Testauthor", 101, 1, BookCategory.FICTION, 2);
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookDBMock.loadItemByID(bookId)).thenReturn(book);
        
        // Vorbereitung: Buchexemplare
        BookCopy copy1 = new BookCopy(book, 1001, true);
        BookCopy copy2 = new BookCopy(book, 1002, true);
        bookCopies = new ArrayList<>();
        bookCopies.add(copy1);
        bookCopies.add(copy2);
        
        when(copyDBMock.getItemsByString(eq("BookID"), anyString())).thenReturn(bookCopies);
        
        // Ausführen der zu testenden Aktion
        lendBookAction.executeAction();
        
        // Überprüfungen
        // 1. Verfügbarkeit des Exemplars wurde auf false gesetzt
        assertEquals(copy1.isAvailable(), false);
        
        // 2. Das Exemplar wurde in der Datenbank aktualisiert
        verify(copyDBMock).updateItemByID(copy1, 1001);
        
        // 3. Eine neue Ausleihe wurde in der Datenbank gespeichert
        ArgumentCaptor<Lending> lendingCaptor = ArgumentCaptor.forClass(Lending.class);
        verify(lendingDBMock).saveItem(lendingCaptor.capture());
        
        Lending capturedLending = lendingCaptor.getValue();
        assertEquals(user, capturedLending.getUser());
        assertEquals(copy1, capturedLending.getBookCopy());
    }

    /**
     * Testet den Fall, dass kein Exemplar verfügbar ist
     */
    @Test
    public void testExecuteActionNoAvailableCopies() {
        // Vorbereitung: Buch ohne verfügbare Exemplare
        int bookId = 102;
        book = new Book("Testbook", "Testauthor", 102, 0, BookCategory.FICTION, 0);
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookDBMock.loadItemByID(bookId)).thenReturn(book);
        
        // Ausführen der zu testenden Aktion
        lendBookAction.executeAction();
        
        // Überprüfungen
        // Es sollte keine Interaktion mit copyDB und lendingDB stattfinden
        verify(copyDBMock, never()).getItemsByString(anyString(), anyString());
        verify(copyDBMock, never()).updateItemByID(any(BookCopy.class), anyInt());
        verify(lendingDBMock, never()).saveItem(any(Lending.class));
    }
    
    /**
     * Testet den Fall, dass nicht das erste Exemplar, sondern ein nachfolgendes verfügbar ist
     */
    @Test
    public void testExecuteActionSecondCopyAvailable() {
        // Vorbereitung: Buch mit verfügbaren Exemplaren
    	int bookId = 103;
    	book = new Book("Testbook", "Testauthor", 103, 1, BookCategory.FICTION, 2);
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookDBMock.loadItemByID(bookId)).thenReturn(book);
        
        // Vorbereitung: Buchexemplare (erstes nicht verfügbar, zweites verfügbar)
        BookCopy copy1 = new BookCopy(book, 1001, false);
        BookCopy copy2 = new BookCopy(book, 1002, true);
        bookCopies = new ArrayList<>();
        bookCopies.add(copy1);
        bookCopies.add(copy2);
        
        when(copyDBMock.getItemsByString(eq("BookID"), anyString())).thenReturn(bookCopies);
        
        // Ausführen der zu testenden Aktion
        lendBookAction.executeAction();
        
        // Überprüfungen
        // 1. Verfügbarkeit des zweiten Exemplars wurde auf false gesetzt
        assertEquals(copy2.isAvailable(), false);
        
        // 2. Das zweite Exemplar wurde in der Datenbank aktualisiert
        verify(copyDBMock).updateItemByID(copy2, 1002);
        
        // 3. Eine neue Ausleihe wurde in der Datenbank gespeichert
        ArgumentCaptor<Lending> lendingCaptor = ArgumentCaptor.forClass(Lending.class);
        verify(lendingDBMock).saveItem(lendingCaptor.capture());
        
        Lending capturedLending = lendingCaptor.getValue();
        assertEquals(user, capturedLending.getUser());
        assertEquals(copy2, capturedLending.getBookCopy());
    }
}
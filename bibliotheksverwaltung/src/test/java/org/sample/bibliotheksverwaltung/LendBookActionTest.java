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
    private UserInterface userInterfaceMock;
    private User userMock;
    private Book bookMock;
    private List<BookCopy> bookCopiesMock;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        // Mock-Objekte erstellen
        bookDBMock = mock(DBHandler.class);
        copyDBMock = mock(DBHandler.class);
        lendingDBMock = mock(DBHandler.class);
        userDBMock = mock(DBHandler.class);
        frontendMock = mock(FrontendHandler.class);
        userInterfaceMock = mock(UserInterface.class);
        userMock = mock(User.class);
        bookMock = mock(Book.class);
        
        // LendBookAction initialisieren
        lendBookAction = new LendBookAction(bookDBMock, copyDBMock, lendingDBMock, userDBMock, frontendMock);
        
        // Standardverhalten für Mocks konfigurieren
        when(frontendMock.getUser()).thenReturn(userInterfaceMock);
        when(userInterfaceMock.getID()).thenReturn(42);
        when(userDBMock.loadItemByID(42)).thenReturn(userMock);
    }

    /**
     * Testet den erfolgreichen Ausleihvorgang eines Buches
     */
    @Test
    public void testExecuteActionSuccessful() {
        // Vorbereitung: Buch mit verfügbaren Exemplaren
        int bookId = 101;
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookMock.getId()).thenReturn(bookId);
        when(bookMock.getAvailableCopies()).thenReturn(2); // Es gibt 2 verfügbare Exemplare
        when(bookDBMock.loadItemByID(bookId)).thenReturn(bookMock);
        
        // Vorbereitung: Buchexemplare
        BookCopy copy1 = mock(BookCopy.class);
        BookCopy copy2 = mock(BookCopy.class);
        bookCopiesMock = new ArrayList<>();
        bookCopiesMock.add(copy1);
        bookCopiesMock.add(copy2);
        
        when(copy1.isAvailable()).thenReturn(true);
        when(copy1.getCopyID()).thenReturn(1001);
        when(copy1.getBook()).thenReturn(bookMock);
        when(copyDBMock.getItemsByString(eq("BookID"), anyString())).thenReturn(bookCopiesMock);
        
        // Ausführen der zu testenden Aktion
        lendBookAction.executeAction();
        
        // Überprüfungen
        // 1. Verfügbarkeit des Exemplars wurde auf false gesetzt
        verify(copy1).setAvailability(false);
        
        // 2. Das Exemplar wurde in der Datenbank aktualisiert
        verify(copyDBMock).updateItemByID(copy1, 1001);
        
        // 3. Eine neue Ausleihe wurde in der Datenbank gespeichert
        ArgumentCaptor<Lending> lendingCaptor = ArgumentCaptor.forClass(Lending.class);
        verify(lendingDBMock).saveItem(lendingCaptor.capture());
        
        Lending capturedLending = lendingCaptor.getValue();
        assertEquals(userMock, capturedLending.getUser());
        assertEquals(copy1, capturedLending.getBookCopy());
    }

    /**
     * Testet den Fall, dass kein Exemplar verfügbar ist
     */
    @Test
    public void testExecuteActionNoAvailableCopies() {
        // Vorbereitung: Buch ohne verfügbare Exemplare
        int bookId = 102;
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookMock.getAvailableCopies()).thenReturn(0); // Keine verfügbaren Exemplare
        when(bookDBMock.loadItemByID(bookId)).thenReturn(bookMock);
        
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
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookMock.getId()).thenReturn(bookId);
        when(bookMock.getAvailableCopies()).thenReturn(1); // Es gibt 1 verfügbares Exemplar
        when(bookDBMock.loadItemByID(bookId)).thenReturn(bookMock);
        
        // Vorbereitung: Buchexemplare (erstes nicht verfügbar, zweites verfügbar)
        BookCopy copy1 = mock(BookCopy.class);
        BookCopy copy2 = mock(BookCopy.class);
        bookCopiesMock = new ArrayList<>();
        bookCopiesMock.add(copy1);
        bookCopiesMock.add(copy2);
        
        when(copy1.isAvailable()).thenReturn(false); // Erstes Exemplar nicht verfügbar
        when(copy2.isAvailable()).thenReturn(true);  // Zweites Exemplar verfügbar
        when(copy2.getCopyID()).thenReturn(1002);
        when(copy2.getBook()).thenReturn(bookMock);
        when(copyDBMock.getItemsByString(eq("BookID"), anyString())).thenReturn(bookCopiesMock);
        
        // Ausführen der zu testenden Aktion
        lendBookAction.executeAction();
        
        // Überprüfungen
        // 1. Verfügbarkeit des zweiten Exemplars wurde auf false gesetzt
        verify(copy2).setAvailability(false);
        
        // 2. Das zweite Exemplar wurde in der Datenbank aktualisiert
        verify(copyDBMock).updateItemByID(copy2, 1002);
        
        // 3. Eine neue Ausleihe wurde in der Datenbank gespeichert
        ArgumentCaptor<Lending> lendingCaptor = ArgumentCaptor.forClass(Lending.class);
        verify(lendingDBMock).saveItem(lendingCaptor.capture());
        
        Lending capturedLending = lendingCaptor.getValue();
        assertEquals(userMock, capturedLending.getUser());
        assertEquals(copy2, capturedLending.getBookCopy());
    }
}
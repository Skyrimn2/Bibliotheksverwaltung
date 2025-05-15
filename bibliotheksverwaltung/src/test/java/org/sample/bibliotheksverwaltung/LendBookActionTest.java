package org.sample.bibliotheksverwaltung;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import application.DBHandler;
import application.DatabaseException;
import application.FrontendHandler;
import application.LendBookAction;
import domain.Book;
import domain.BookCopy;
import domain.Lending;
import domain.User;
import domain.UserInterface;

public class LendBookActionTest {

    private LendBookAction lendBookAction;
    
    // Mocks
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
    public void setUp() throws DatabaseException {
        // Mocks erstellen
        bookDBMock = mock(DBHandler.class);
        copyDBMock = mock(DBHandler.class);
        lendingDBMock = mock(DBHandler.class);
        userDBMock = mock(DBHandler.class);
        frontendMock = mock(FrontendHandler.class);
        userInterfaceMock = mock(UserInterface.class);
        userMock = mock(User.class);
        bookMock = mock(Book.class);
        
        // Action initialisieren
        lendBookAction = new LendBookAction(bookDBMock, copyDBMock, lendingDBMock, userDBMock, frontendMock);
        
        // Basis-Mock-Verhalten konfigurieren
        when(frontendMock.getUser()).thenReturn(userInterfaceMock);
        when(userInterfaceMock.getID()).thenReturn(42);
        when(userDBMock.loadItemByID(42)).thenReturn(userMock);
    }

    @Test
    public void testExecuteActionSuccessful() throws DatabaseException {
        // Buch mit verfügbaren Exemplaren vorbereiten
        int bookId = 101;
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookMock.getId()).thenReturn(bookId);
        when(bookMock.getAvailableCopies()).thenReturn(2);
        when(bookDBMock.loadItemByID(bookId)).thenReturn(bookMock);
        
        // Buchexemplare vorbereiten
        BookCopy copy1 = mock(BookCopy.class);
        BookCopy copy2 = mock(BookCopy.class);
        bookCopiesMock = new ArrayList<>();
        bookCopiesMock.add(copy1);
        bookCopiesMock.add(copy2);
        
        when(copy1.isAvailable()).thenReturn(true);
        when(copy1.getCopyID()).thenReturn(1001);
        when(copy1.getBook()).thenReturn(bookMock);
        when(copyDBMock.getItemsByString(eq("BookID"), anyString())).thenReturn(bookCopiesMock);
        
        // Ausführen der Aktion
        lendBookAction.executeAction();
        
        // Prüfungen
        verify(copy1).setAvailability(false);
        verify(copyDBMock).updateItemByID(copy1, 1001);
        
        ArgumentCaptor<Lending> lendingCaptor = ArgumentCaptor.forClass(Lending.class);
        verify(lendingDBMock).saveItem(lendingCaptor.capture());
        
        Lending capturedLending = lendingCaptor.getValue();
        assertEquals(userMock, capturedLending.getUser());
        assertEquals(copy1, capturedLending.getBookCopy());
        
        verify(frontendMock).showMessage("Book successfully borrowed.");
    }

    @Test
    public void testExecuteActionNoAvailableCopies() throws DatabaseException {
        // Buch ohne verfügbare Exemplare
        int bookId = 102;
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookMock.getAvailableCopies()).thenReturn(0);
        when(bookDBMock.loadItemByID(bookId)).thenReturn(bookMock);
        
        // Ausführen der Aktion
        lendBookAction.executeAction();
        
        // Prüfungen
        verify(copyDBMock, never()).getItemsByString(anyString(), anyString());
        verify(copyDBMock, never()).updateItemByID(any(BookCopy.class), anyInt());
        verify(lendingDBMock, never()).saveItem(any(Lending.class));
        verify(frontendMock).showMessage("No Copy available.");
    }
    
    @Test
    public void testExecuteActionSecondCopyAvailable() throws DatabaseException {
        // Buch mit verfügbaren Exemplaren
        int bookId = 103;
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookMock.getId()).thenReturn(bookId);
        when(bookMock.getAvailableCopies()).thenReturn(1);
        when(bookDBMock.loadItemByID(bookId)).thenReturn(bookMock);
        
        // Buchexemplare vorbereiten (erstes nicht verfügbar, zweites verfügbar)
        BookCopy copy1 = mock(BookCopy.class);
        BookCopy copy2 = mock(BookCopy.class);
        bookCopiesMock = new ArrayList<>();
        bookCopiesMock.add(copy1);
        bookCopiesMock.add(copy2);
        
        when(copy1.isAvailable()).thenReturn(false);
        when(copy2.isAvailable()).thenReturn(true);
        when(copy2.getCopyID()).thenReturn(1002);
        when(copy2.getBook()).thenReturn(bookMock);
        when(copyDBMock.getItemsByString(eq("BookID"), anyString())).thenReturn(bookCopiesMock);
        
        // Ausführen der Aktion
        lendBookAction.executeAction();
        
        // Prüfungen
        verify(copy2).setAvailability(false);
        verify(copyDBMock).updateItemByID(copy2, 1002);
        
        ArgumentCaptor<Lending> lendingCaptor = ArgumentCaptor.forClass(Lending.class);
        verify(lendingDBMock).saveItem(lendingCaptor.capture());
        
        Lending capturedLending = lendingCaptor.getValue();
        assertEquals(userMock, capturedLending.getUser());
        assertEquals(copy2, capturedLending.getBookCopy());
    }
    
    @Test
    public void testExecuteActionBookNotFound() throws DatabaseException {
        // Nicht existierendes Buch
        int bookId = 999;
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookDBMock.loadItemByID(bookId)).thenReturn(null);
        
        // Ausführen der Aktion
        lendBookAction.executeAction();
        
        // Prüfungen
        verify(frontendMock).showMessage("Book with ID " + bookId + " not found.");
        verify(copyDBMock, never()).getItemsByString(anyString(), anyString());
        verify(lendingDBMock, never()).saveItem(any(Lending.class));
    }
    
    @Test
    public void testExecuteActionDatabaseException() throws DatabaseException {
        // Test für Datenbankfehler bei der Buchabfrage
        int bookId = 104;
        when(frontendMock.readMenuOption()).thenReturn(bookId);
        when(bookDBMock.loadItemByID(bookId)).thenThrow(new DatabaseException("Datenbankfehler"));
        
        // Ausführen der Aktion
        lendBookAction.executeAction();
        
        // Prüfungen
        verify(frontendMock).showMessage(contains("Database error:"));
        verify(copyDBMock, never()).getItemsByString(anyString(), anyString());
        verify(lendingDBMock, never()).saveItem(any(Lending.class));
    }
}
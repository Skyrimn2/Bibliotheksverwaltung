package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Book;
import domain.BookCategory;

public class BookTest {

    private Book book;

    @Before
    public void setUp() {
        book = new Book("Der Herr der Ringe", "J.R.R. Tolkien", 1, 5, BookCategory.FICTION, 10);
    }

    /**
     * #Requirement: Bücher müssen Titel und Autor korrekt speichern
     */
    @Test
    public void testGetTitleAndAuthor() {
        assertEquals("Der Herr der Ringe", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAutor());
    }

    /**
     * #Requirement: Bücher müssen eine eindeutige ID haben
     */
    @Test
    public void testBookId() {
        assertEquals(1, book.getId());

        book.setId(42);
        assertEquals(42, book.getId());
    }

    /**
     * #Requirement: Bücher müssen ihre Verfügbarkeit korrekt angeben
     */
    @Test
    public void testBookAvailability() {
        assertEquals(5, book.getAvailableCopies());
        assertEquals(10, book.getCopies());
    }

    /**
     * #Requirement: Bücher müssen ihre Kategorie als lesbaren String ausgeben können
     */
    @Test
    public void testBookCategoryString() {
        assertEquals("fiction", book.getCategoryString());

        Book scienceBook = new Book("Kosmos", "Carl Sagan", 2, 3, BookCategory.SCIENCE, 5);
        assertEquals("science", scienceBook.getCategoryString());

        Book historyBook = new Book("Geschichte Europas", "Ein Historiker", 3, 2, BookCategory.HISTORY, 6);
        assertEquals("history", historyBook.getCategoryString());
    }
}
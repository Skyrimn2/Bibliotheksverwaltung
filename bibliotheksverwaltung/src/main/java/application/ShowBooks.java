package application;

import java.util.List;
import domain.Book;

public class ShowBooks {
    private BookDBHandler bookDBHandler;
    private FrontendHandler frontendHandler;

    public ShowBooks(BookDBHandler bookDBHandler, FrontendHandler frontendHandler) {
        this.bookDBHandler = bookDBHandler;
        this.frontendHandler = frontendHandler;
    }

    public void showBook(int bookID) {
        Book book = bookDBHandler.loadBook(bookID);
        frontendHandler.showBook(book);
    }

    public void showAllBooks() {
        for (Book book : bookDBHandler.loadAllBooks()) {
            frontendHandler.showBook(book);
        }
    }

    public void showAllavailableBooks() {
        List<Book> availableBooks = bookDBHandler.loadAvailableBooks();
        for (Book book : availableBooks) {
            frontendHandler.showBook(book);
        }
    }



}

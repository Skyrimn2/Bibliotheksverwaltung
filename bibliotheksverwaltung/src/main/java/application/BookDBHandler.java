package application;

import java.util.List;
import domain.Book;

public interface BookDBHandler {
    public void saveBook(Book buch);
    public Book loadBook(int id);
    public void deleteBook(int id);
    public void updateBook(Book buch);
    public List<Book> loadAllBooks();
    public List<Book> loadAvailableBooks();
}

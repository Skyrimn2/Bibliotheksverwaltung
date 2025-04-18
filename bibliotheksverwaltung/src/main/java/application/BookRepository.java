package application;

import java.util.List;

import domain.Book;

public interface BookRepository {
    List<Book> allBooks();
    Book bookByID(int id);
    void saveBook(Book buch);
    void deleteBook(Book buch);
}
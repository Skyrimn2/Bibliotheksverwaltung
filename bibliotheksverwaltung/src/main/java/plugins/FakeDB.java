package plugins;

import java.util.List;
import java.util.stream.Collectors;

import application.BookDBHandler;
import domain.Book;

public class FakeDB implements BookDBHandler{

    @Override
    public void saveBook(domain.Book buch) {
        System.out.println("Fake Buch gespeichert");
    }

    @Override
    public Book loadBook(int id) {
        System.out.println("Fake Buch geladen");
        return new Book("Java ist toll", "John Doe", 1);
    }

    @Override
    public void deleteBook(int id) {
        System.out.println("Fake Buch gelöscht");
    }

    @Override
    public void updateBook(Book buch) {
        System.out.println("Fake Buch aktualisiert");
    }

    @Override
    public List<Book> loadAllBooks() {
        System.out.println("Fake Alle Bücher geladen");
        return List.of(new Book("Java ist toll", "John Doe", 1), new Book("Python ist toll", "John Doe", 2));
    }

    @Override
    public List<Book> loadAvailableBooks() {
        System.out.println("Fake Verfügbare Bücher geladen");
        return loadAllBooks().stream()
            // Fake Logik momentan
            .filter(buch -> buch.getId() % 2 != 0)
            // logik für die verfügbaren Bücher
            .collect(Collectors.toList());
    }

    public Book getBuchById(int id) {
        List<Book> alleBuecher = loadAllBooks();
        for (Book buch : alleBuecher) {
            if (buch.getId() == id) {
                return buch;
            }
        }
        return null;
    }
}

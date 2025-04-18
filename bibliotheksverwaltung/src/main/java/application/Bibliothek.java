package application;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;
// import java.util.Collection;
import domain.Lending;
import domain.Book;
import domain.User;
import domain.Staffmember;
import domain.LibraryLocation;


//Too Long Class. Muss gefixt werden.
//Klasse mischt verschiedene Aufgaben -> Aufteilen in kleine Kalssen

public class Library {
    private List<Lending> lendings;
    private List<Book> books;
    private List<User> user;
    private List<Staffmember> librarian;
    private List<LibraryLocation> libraryLocation;

    public Library() {
        lendings = new ArrayList<>();
        books = new ArrayList<>();
        user = new ArrayList<>();
        this.librarian = new ArrayList<>();
        this.libraryLocation = new ArrayList<>();
        this.user = new ArrayList<>();
    }

    public void lendBook(User user, Book book) {
        if (books.contains(book)) {
            boolean hasAlreadyLend = lendings.stream()
                    .anyMatch(len -> len.getUser().equals(user) && len.getBook().equals(book));
            if (!hasAlreadyLend) {
                Lending lending = new Lending(user, book, LocalDate.now(), 123); // Aktuelles Datum setzen
                lendings.add(lending);
                System.out.println(user.getName() + " hat das Buch \"" + book.getTitle() + "\" ausgeliehen.");
            } else {
                System.out.println(user.getName() + " hat das Buch \"" + book.getTitle() + "\" bereits ausgeliehen.");
            }
        } else {
            System.out.println("Das Buch \"" + book.getTitle() + "\" ist nicht verfügbar.");
        }
    }

    public void returnBook(User user, Book book) {
        Lending lending = lendings.stream()
                .filter(len -> len.getUser().equals(user) && len.getBook().equals(book))
                .findFirst()
                .orElse(null);
        if (lending != null) {
            lendings.remove(lending);
            System.out.println(user.getName() + " hat das Buch \"" + book.getTitle() + "\" zurückgegeben.");
        } else {
            System.out.println("Das Buch \"" + book.getTitle() + "\" wurde von " + user.getName() + " nicht ausgeliehen.");
        }
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!isLoan(book)) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    private boolean isLoan(Book buch) {
        for (Lending lending : lendings) {
            if (lending.getBook().equals(buch)) {
                return true;
            }
        }
        return false;
    }
    
    public void addBook(Book buch) {
        books.add(buch);
        System.out.println("Buch hinzugefügt: " + buch.getTitle());
    }

    public void removeBook(Book buch) {
        books.remove(buch);
    }

    public void registerUser(User user) {
        this.user.add(user);
        System.out.println("Benutzer registriert: " + user.getName());
    }

    public void addLibraryLocation(LibraryLocation location) {
        libraryLocation.add(location);
    }

    public List<LibraryLocation> getLibraryLocation() {
        return libraryLocation;
    }

    public void removeLibraryLocation(LibraryLocation location) {
        libraryLocation.remove(location);
    }

    public void addLibraryLocationUsingInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bitte geben Sie den Namen des neuen Bibliothekars ein:");
            String name = scanner.nextLine();
            System.out.println("Bitte geben Sie die E-Mail-Adresse des neuen Bibliothekars ein:");
            String email = scanner.nextLine();

            Employee librarian = new Employee(name, email);
            
            this.librarian.add(librarian);
            System.out.println("Neuer Bibliothekar erfolgreich hinzugefügt.");
        }
    }

    public List<User> getUserList() {
        return user;
    }
    
}
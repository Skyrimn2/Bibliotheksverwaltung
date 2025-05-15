package application;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import domain.Book;
import domain.BookCopy;
import domain.Lending;
import domain.User;
import domain.UserInterface;

public class LendBookAction implements MenuAction {

    private String description = "Lend A Book by ID";
    private DBHandler<Book> bookDB;
    private DBHandler<BookCopy> copyDB;
    private DBHandler<Lending> lendingDB;
    private DBHandler<User> userDB;
    private FrontendHandler frontend;

    public LendBookAction(DBHandler<Book> bookDB, DBHandler<BookCopy> copyDB, DBHandler<Lending> lendingDB, DBHandler<User> userDB, FrontendHandler frontend) {
        super();
        this.bookDB = bookDB;
        this.copyDB = copyDB;
        this.lendingDB = lendingDB;
        this.userDB = userDB;
        this.frontend = frontend;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void executeAction() {
        try {
            frontend.showMessage("Enter book ID to lend the book:\t");
            int bookID = frontend.readMenuOption();
            Book book = bookDB.loadItemByID(bookID);
            
            // Check if book exists
            if (book == null) {
                frontend.showMessage("Book with ID " + bookID + " not found.");
                return;
            }
            
            // Check if copies are available
            if (book.getAvailableCopies() <= 0) {
                frontend.showMessage("No Copy available.");
                return;
            }

            // Get all copies of the book
            List<BookCopy> copies = copyDB.getItemsByString("BookID", String.valueOf(book.getId()));
            
            if (copies == null || copies.isEmpty()) {
                frontend.showMessage("No copies found for this book.");
                return;
            }

            // Find an available copy
            BookCopy copyToLend = null;
            for (BookCopy copy : copies) {
                if (copy.isAvailable()) {
                    copyToLend = copy;
                    break;
                }
            }
            
            // Double-check we got an available copy
            if (copyToLend == null) {
                frontend.showMessage("No available copies found.");
                return;
            }

            // Set the copy as unavailable
            copyToLend.setAvailability(false);
            int lendCopyID = copyToLend.getCopyID();
            
            // Update the copy in database
            copyDB.updateItemByID(copyToLend, lendCopyID);

            // Get the user
            UserInterface user_interface = frontend.getUser();
            User user = userDB.loadItemByID(user_interface.getID());
            
            if (user == null) {
                frontend.showMessage("User information not found.");
                // Revert changes to copy availability
                copyToLend.setAvailability(true);
                copyDB.updateItemByID(copyToLend, lendCopyID);
                return;
            }

            // Create and save the lending record
            Lending lending = new Lending(user, copyToLend, Timestamp.valueOf(LocalDateTime.now()));
            lendingDB.saveItem(lending);
            
            frontend.showMessage("Book successfully borrowed.");
        } catch (DatabaseException e) {
            frontend.showMessage("Database error: " + e.getMessage());
        }
    }
}
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
		frontend.showMessage("Enter book ID to lend the book:\t");
		int bookID = frontend.readMenuOption();
		Book book = bookDB.loadItemByID(bookID);
		if (book.getAvailableCopies() <= 0) {
			System.out.println("No Copy available.");
			return;
		}

		List<BookCopy> copies = copyDB.getItemsByString("BookID", String.valueOf(book.getId()));

		int i = 0;

		while(!copies.get(i).isAvailable()) {
			i++;
		}

		BookCopy copyToLend = copies.get(i);

		copyToLend.setAvailability(false);

		int lendCopyID = copyToLend.getCopyID();

		copyDB.updateItemByID(copyToLend, lendCopyID);

		UserInterface user_interface = frontend.getUser();

		User user = userDB.loadItemByID(user_interface.getID());

		Lending lending = new Lending(user, copyToLend, Timestamp.valueOf(LocalDateTime.now()));

		lendingDB.saveItem(lending);

	}

}

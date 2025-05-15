package application;

import java.util.ArrayList;
import java.util.List;

import adapter.DisplayableBook;
import domain.Book;
import domain.Displayable;

public class ListBooksAction implements MenuAction{

	private final String description = "List all Books";
	private DBHandler<Book> bookDB;
	private FrontendHandler frontend;

	public ListBooksAction(DBHandler<Book> bookDB, FrontendHandler frontend) {
		super();
		this.bookDB = bookDB;
		this.frontend = frontend;
	}

	@Override
	public void executeAction() {
	    try {
	        List<Book> allBooks = bookDB.loadAllOfItem();
	        List<Displayable> disps = new ArrayList<>();
	        for (Book b: allBooks) {
	            disps.add(new DisplayableBook(b));
	        }
	        frontend.showResultList(disps);
	    } catch (DatabaseException e) {
	        frontend.showMessage("Database error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	@Override
	public String getDescription() {
	    return description;
	}
}

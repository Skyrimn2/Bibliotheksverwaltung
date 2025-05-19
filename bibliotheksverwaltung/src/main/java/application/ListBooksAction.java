package application;

import java.util.ArrayList;
import java.util.List;

import adapter.DisplayableBook;
import domain.Book;
import domain.Displayable;
import plugins.ConsoleFrontend;

public class ListBooksAction implements MenuAction{

	private final String description = "List all Books";
	private DBHandler<Book> bookDB;
	private ConsoleFrontend frontend;

	public ListBooksAction(DBHandler<Book> bookDB, ConsoleFrontend frontend) {
		super();
		this.bookDB = bookDB;
		this.frontend = frontend;
	}

	@Override
	public void executeAction() {
		List<Book> allBooks = bookDB.loadAllOfItem();
		List<Displayable> disps = new ArrayList<>();
		for (Book b: allBooks) {
			disps.add(new DisplayableBook(b));
		}
		frontend.showResultList(disps);
	}
	@Override
	public String getDescription() {
		return description;
	}
}

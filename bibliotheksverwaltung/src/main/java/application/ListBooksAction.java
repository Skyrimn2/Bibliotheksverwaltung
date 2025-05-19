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
	private IDisplayableFactory displayableFactory;

	public ListBooksAction(DBHandler<Book> bookDB, FrontendHandler frontend, IDisplayableFactory displayableFactory) {
		super();
		this.bookDB = bookDB;
		this.frontend = frontend;
		this.displayableFactory = displayableFactory;
	}

	@Override
	public void executeAction() {
		List<Book> allBooks = bookDB.loadAllOfItem();
		List<Displayable> disps = new ArrayList<>();
		for (Book b: allBooks) {
			disps.add(displayableFactory.createDisplayableBook(b));
		}
		frontend.showResultList(disps);
	}
	@Override
	public String getDescription() {
		return description;
	}
}

package application;

import java.util.ArrayList;
import java.util.List;

import adapter.DisplayableBook;
import domain.Book;
import domain.Displayable;

public class ListBooksAction implements MenuAction{
	
	private final String description = "Alle Bücher auflisten";
	private DBHandler<Book> bookDB;
	private FrontendHandler frontend;
	
	public ListBooksAction(DBHandler<Book> bookDB, FrontendHandler frontend) {
		super();
		this.bookDB = bookDB;
		this.frontend = frontend;
	}
	
	@Override
	public void executeAction() {
		List<Book> allBooks = bookDB.loadAllOfItem();
		List<Displayable> disps = new ArrayList<Displayable>();
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

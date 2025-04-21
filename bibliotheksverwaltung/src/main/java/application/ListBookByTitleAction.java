package application;

import java.util.ArrayList;
import java.util.List;

import adapter.DisplayableBook;
import domain.Book;
import domain.Displayable;

public class ListBookByTitleAction implements MenuAction {

	private String description = "List books with given title";
	private DBHandler<Book> db;
	private FrontendHandler frontend;
	
	public ListBookByTitleAction(DBHandler<Book> db, FrontendHandler frontend) {
		this.db = db;
		this.frontend = frontend;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void executeAction() {
		System.out.println("Input Title you want to search:\t\t");
		String title = frontend.readString();
		List<Book> books = db.getItemsByString("Title", title);
		
		List<Displayable> disps = new ArrayList<Displayable>();
		for (Book b: books) {
			disps.add(new DisplayableBook(b));
		}
		
		frontend.showResultList(disps);
	}

}

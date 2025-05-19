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
	private IDisplayableFactory displayableFactory;

	public ListBookByTitleAction(DBHandler<Book> db, FrontendHandler frontend, IDisplayableFactory displayableFactory) {
		this.db = db;
		this.frontend = frontend;
		this.displayableFactory = displayableFactory;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void executeAction() {
		frontend.showMessage("Input Title you want to search:\t\t");
		String title = frontend.readString();
		List<Book> books = db.getItemsByString("Title", title);
		
		// Null-Prüfung hinzugefügt, um NullPointerException zu vermeiden
		List<Displayable> disps = new ArrayList<>();
		if (books != null) {
			for (Book b: books) {
				disps.add(displayableFactory.createDisplayableBook(b));
			}
		} else {
			// Logging für den Fall, dass die Datenbank null zurückgibt
			frontend.showMessage("Keine Bücher gefunden oder Datenbankfehler aufgetreten.");
		}

		frontend.showResultList(disps);
	}

}
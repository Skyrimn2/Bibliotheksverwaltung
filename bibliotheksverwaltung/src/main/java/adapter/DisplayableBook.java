package adapter;

import domain.Book;
import domain.Displayable;

public class DisplayableBook implements Displayable {

	private final Book book;

	public DisplayableBook (Book book) {
		super();
		this.book = book;
	}

	@Override
	public String getDisplayText() {
		return " ID:\t\t" + book.getId() + "\n title:\t\t" + book.getTitle() +  "\n author:\t" + book.getAutor() + "\n category:\t" + book.getCategoryString() + "\n copies:\t\t" + book.getCopies() + "\n available copies:\t" + book.getAvailableCopies() + "\n";
	}
}

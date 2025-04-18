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
		return book.getTitle() +  "\n" + book.getAutor() + "\n";
	}
}

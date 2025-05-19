package adapter;

import application.IDisplayableFactory;
import domain.Book;
import domain.Displayable;
import domain.Lending;

public class DisplayableFactory implements IDisplayableFactory {

	public DisplayableFactory() {
		super();
	}

	@Override
	public Displayable createDisplayableBook(Book book) {
		return new DisplayableBook(book);
	}

	@Override
	public Displayable createDisplayableLending(Lending lending) {
		return new DisplayableLending(lending);
	}

	@Override
	public Displayable createDisplayableLendingsPerUser(Lending lending) {
		return new DisplayableLendingsPerUser(lending);
	}

}

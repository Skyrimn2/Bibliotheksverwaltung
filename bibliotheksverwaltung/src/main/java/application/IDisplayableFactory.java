package application;

import domain.Book;
import domain.Displayable;
import domain.Lending;

public interface IDisplayableFactory {
	public Displayable createDisplayableBook(Book book);
	public Displayable createDisplayableLending(Lending lending);
	public Displayable createDisplayableLendingsPerUser(Lending lending);
	
}

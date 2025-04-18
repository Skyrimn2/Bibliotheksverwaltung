package plugins;

import java.util.ArrayList;
import java.util.List;

import application.DBHandler;
import domain.Book;

public class FakeBookDB implements DBHandler<Book> {

	@Override
	public Book loadItemByID(int id) {
		return new Book("Test", "Test", id)
	}

	@Override
	public void saveItem(Book item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemByID(Book item, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> loadAllOfItem() {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Test", "Test", 3121));
		books.add(new Book("Test1", "Test1", 324));
		return books;
	}


}

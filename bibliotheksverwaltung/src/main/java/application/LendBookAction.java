package application;

import domain.Book;

public class LendBookAction implements MenuAction {

	private String description = "Lend A Book by ID";
	private DBHandler<Book> db;
	private FrontendHandler frontend;
	
	public LendBookAction(DBHandler<Book> db, FrontendHandler frontend) {
		super();
		this.db = db;
		this.frontend = frontend;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void executeAction() {
		System.out.println("Enter book ID to lend the book:\t");
		int BookID = frontend.readMenuOption();
		
	}

}

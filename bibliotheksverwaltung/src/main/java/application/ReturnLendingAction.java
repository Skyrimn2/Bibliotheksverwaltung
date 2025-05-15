package application;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import domain.BookCopy;
import domain.Lending;

public class ReturnLendingAction implements MenuAction {

	private String description = "Return a loan Book by Lending ID";
	private DBHandler<Lending> lendingDB;
	private DBHandler<BookCopy> copyDB;
	private FrontendHandler frontend;

	public ReturnLendingAction(DBHandler<Lending> lendingDB, DBHandler<BookCopy> copyDB, FrontendHandler frontend) {
		super();
		this.lendingDB = lendingDB;
		this.copyDB = copyDB;
		this.frontend = frontend;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void executeAction() {
	    try {
	        frontend.showMessage("Enter lending ID");

	        int id = frontend.readMenuOption();
	        Lending lending = lendingDB.loadItemByID(id);

	        if (lending == null) {
	            frontend.showMessage("No such lending\n");
	            return;
	        }
	        
	        if (lending.getReturnDate() != null) {
	            frontend.showMessage("Lending is already returned\n");
	            return;
	        }

	        BookCopy copy = copyDB.loadItemByID(lending.getBookCopy().getCopyID());

	        lending.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
	        System.out.println(lending.getReturnDate().toString());
	        copy.setAvailability(true);

	        lendingDB.updateItemByID(lending, id);
	        copyDB.updateItemByID(copy, copy.getCopyID());

	        frontend.showMessage("Book returned\n");
	    } catch (DatabaseException e) {
	        frontend.showMessage("Database error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

}

package domain;

import java.sql.Timestamp;

public class Lending {
    private int lendingID;
    private User user;
    private BookCopy book;
    private Timestamp lendingDate;
    private Timestamp returnDate;
    
    public Lending(User user, BookCopy book, Timestamp lendingDate, int lendingID) {
        this.user = user;
        this.book = book;
        this.lendingDate = lendingDate;
        this.lendingID = lendingID;
        this.returnDate = null;
    }
    public Lending(User user, BookCopy book, Timestamp lendingDate) {
        this.user = user;
        this.book = book;
        this.lendingDate = lendingDate;
        this.returnDate = null;
    }
    
    public User getUser() {
    	return this.user;
    }
    
    public BookCopy getBookCopy() {
    	return this.book;
    }
    
    public Timestamp getLendingDate() {
    	return this.lendingDate;
    }
    
    public void setReturnDate(Timestamp date) {
    	this.returnDate = date;
    }

	public Timestamp getReturnDate() {
		return this.returnDate;
	}

}
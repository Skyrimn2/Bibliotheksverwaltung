package domain;

public class BookCopy {
    private Book book;
    private int copyID;
    private boolean isAvailable;

    public BookCopy(Book book, int copyID) {
        this.book = book;
        this.copyID = copyID;
    }
    public BookCopy(Book book, int copyID, boolean isAvailable) {
        this.book = book;
        this.copyID = copyID;
        this.isAvailable = isAvailable;
    }
    
    public Book getBook() {
        return this.book;
    }

    public int getCopyID() {
        return this.copyID;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }

    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }
    
    public boolean isAvailable() {
    	return this.isAvailable;
    }
    
    public void setAvailability(boolean isAvailable) {
    	this.isAvailable = isAvailable;
    }

}
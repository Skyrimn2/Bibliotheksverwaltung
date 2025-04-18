package domain;

public class BookCopy {
    private Book book;
    private int copyID;

    public BookCopy(Book book, int copyID) {
        this.book = book;
        this.copyID = copyID;
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

}
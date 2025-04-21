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

}
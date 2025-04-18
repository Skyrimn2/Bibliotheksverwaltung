package domain;

import java.time.LocalDate;

public class Lending {
    private int lendingID;
    private User user;
    private Book book;
    private LocalDate lendingDate;
    private LocalDate returnDate;
    
    public Lending(User user, Book book, LocalDate lendingDate, int lendingID) {
        this.user = user;
        this.book = book;
        this.lendingDate = lendingDate;
        this.lendingID = lendingID;
        this.returnDate = null;
    }

}
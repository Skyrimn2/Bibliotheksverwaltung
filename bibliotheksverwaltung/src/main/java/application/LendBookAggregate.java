package application;

import java.util.List;
import domain.Lending;
import domain.Book;

public class LendBookAggregate {
    private Book book;
    private List<Lending> lendings;

    public LendBookAggregate(Book buch, List<Lending> lendings) {
        this.book = buch;
        this.lendings = lendings;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book buch) {
        this.book = buch;
    }

    public List<Lending> getLending() {
        return lendings;
    }

    public void setLending(List<Lending> lendings) {
        this.lendings = lendings;
    }

}
package application;

import domain.Book;

public interface FrontendHandler {
    public void showBook(Book buch);
    public void showMenu();
    public int readMenuOption();
}

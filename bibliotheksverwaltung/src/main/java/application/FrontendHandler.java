package application;

import java.util.List;

import domain.Book;
import domain.Displayable;

public interface FrontendHandler {
    public void showBook(Book buch);
    public void showMenu(Menu menu);
    public int readMenuOption();
    public void showResult(Displayable disp);
    public void showResultList(List<Displayable> disps);
    public boolean loginView();
    public String readString();
}

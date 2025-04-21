package application;

import java.util.List;

import domain.Book;
import domain.Displayable;
import domain.Employee;
import domain.User;
import domain.UserInterface;

public interface FrontendHandler {
    public void showBook(Book buch);
    public void showMenu(Menu menu);
    public int readMenuOption();
    public void showResult(Displayable disp);
    public void showResultList(List<Displayable> disps);
    public boolean loginView();
    public String readString();
    public void setUser(User user);
    public void setUser(Employee emp);
    public void deleteUser();
    public UserInterface getUser();
}

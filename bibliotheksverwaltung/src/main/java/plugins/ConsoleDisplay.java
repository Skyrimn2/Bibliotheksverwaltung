package plugins;

import java.util.List;

import application.Menu;
import domain.Book;
import domain.Displayable;

/**
 * Handles console display operations - separated from ConsoleFrontend
 * Follows Single Responsibility Principle
 */
public class ConsoleDisplay {

    public void showMenu(Menu menu) {
        List<String> menuItems = menu.getAllDescriptions();
        int i = 0;
        for(String action : menuItems) {
            System.out.println(String.valueOf(i) + "\t\t" + action);
            i++;
        }
    }

    public void showResultList(List<Displayable> disps) {
        for (Displayable disp : disps) {
            System.out.println(disp.getDisplayText());
        }
    }
    
    public void showResult(Displayable disp) {
        System.out.println(disp.getDisplayText());
    }

    public void showBook(Book buch) {
        // Implementation for showing single book
        System.out.println("Book: " + buch.getTitle());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
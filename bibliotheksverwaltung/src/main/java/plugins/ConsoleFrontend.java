package plugins;

import java.util.List;

import adapter.Frontend;
import application.AuthenticationController;
import application.EmployeeAuthenticationStrategy;
import application.Menu;
import application.UserAuthenticationStrategy;
import domain.Book;
import domain.Displayable;
import domain.Employee;
import domain.User;
import domain.UserInterface;

/**
 * Refactored ConsoleFrontend - now follows Single Responsibility Principle
 * Delegates responsibilities to specialized components
 */
public class ConsoleFrontend extends Frontend {

    private String dbPath;
    private ConsoleDisplay display;
    private ConsoleInputHandler inputHandler;
    private AuthenticationController authController;

    public ConsoleFrontend(String dbpath) {
        super();
        this.dbPath = dbpath;
        this.display = new ConsoleDisplay();
        this.inputHandler = new ConsoleInputHandler();
        this.authController = new AuthenticationController(display, inputHandler);
        
        // Initialize authentication strategies
        initializeAuthenticationStrategies();
    }
    
    private void initializeAuthenticationStrategies() {
        UserAuthenticationStrategy userStrategy = new UserAuthenticationStrategy(new UserDB(dbPath));
        EmployeeAuthenticationStrategy employeeStrategy = new EmployeeAuthenticationStrategy(new EmployeeDB(dbPath));
        
        authController.registerStrategies(userStrategy, employeeStrategy);
    }

    @Override
    public void showMenu(Menu menu) {
        display.showMenu(menu);
    }

    @Override
    public int readMenuOption() {
        return inputHandler.readMenuOption();
    }

    @Override
    public void showResultList(List<Displayable> disps) {
        display.showResultList(disps);
    }
    
    @Override
    public void showResult(Displayable disp) {
        display.showResult(disp);
    }

    @Override
    public boolean loginView() {
        UserInterface user = authController.handleLogin();
        if (user != null) {
            this.setUser(user);
            return true;
        }
        return false;
    }

    @Override
    public String readString() {
        return inputHandler.readString();
    }

    @Override
    public void showBook(Book buch) {
        display.showBook(buch);
    }

    @Override
    public void setUser(User user) {
        UserInterface u = new User(user.getName(), user.getID());
        this.user = u;
    }

    @Override
    public void setUser(Employee emp) {
        UserInterface u = new Employee(emp.getName(), emp.getID());
        this.user = u;
    }

    @Override
    public void setUser(UserInterface user) {
        this.user = user;
    }

    @Override
    public void deleteUser() {
        this.user = null;
    }

    @Override
    public void showMessage(String message) {
        display.showMessage(message);
    }
}

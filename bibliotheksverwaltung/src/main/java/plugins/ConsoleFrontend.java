package plugins;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import adapter.Frontend;
import application.Authentication;
import application.DBHandler;
import application.DatabaseException;
import application.EmployeeAuthentication;
import application.EmployeeRegistration;
import application.LoginHandler;
import application.Menu;
import application.Registration;
import application.UserAuthentication;
import application.UserRegistration;
import domain.Book;
import domain.Displayable;
import domain.Employee;
import domain.User;
import domain.UserInterface;

public class ConsoleFrontend extends Frontend {

    private String dbPath;

    public ConsoleFrontend(String dbpath) {
        super();
        this.dbPath = dbpath;
    }

    @Override
    public void showMenu(Menu menu) {
        List<String> menuItems = menu.getAllDescriptions();
        int i = 0;
        for(String action : menuItems) {
            System.out.println(String.valueOf(i) + "\t\t" + action);
            i++;
        }
    }

    @Override
    public int readMenuOption() {
        Scanner scanner = new Scanner(System.in);
        int selection = -1;

        while (true) {
            try {
                selection = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        return selection;
    }

    @Override
    public void showResultList(List<Displayable> disps) {
        for (Displayable disp : disps) {
            System.out.println(disp.getDisplayText());
        }
    }
    
    @Override
    public void showResult(Displayable disp) {
        disp.getDisplayText();
    }

    @Override
    public boolean loginView() {
        displayLoginOptions();
        int selection = this.readMenuOption();
        
        LoginHandler handler = createLoginHandler(selection);
        if (handler == null) {
            return false;
        }
        
        String username = readCredentialInput("username");
        String password = readCredentialInput("password");
        
        boolean success = handler.handleLogin(username, password);
        
        if (!success) {
            handleLoginFailure();
        }
        
        return success;
    }

    private LoginHandler createLoginHandler(int selection) {
        switch (selection) {
            case 0:
                return new UserLoginHandler(dbPath, this);
            case 1:
                return (LoginHandler) new UserRegistrationHandler(dbPath, this);
            case 2:
                return new EmployeeLoginHandler(dbPath, this);
            case 3:
                return (LoginHandler) new EmployeeRegistrationHandler(dbPath, this);
            default:
                return null;
        }
    }

    private void displayLoginOptions() {
        System.out.println("Please select:\n0\t\tlogin with User\n1\t\tregister User\n2\t\tlogin as employee\n3\t\tregister employee");
    }

    private boolean handleUserLogin() {
        String username = readCredentialInput("username");
        String password = readCredentialInput("password");
        
        DBHandler<User> db = new UserDB(this.dbPath);
        UserInterface user = null;
        
        try {
            user = db.getItemByString("name", username);
        } catch (DatabaseException e) {
            System.err.println("Datenbankfehler beim Login: " + e.getMessage());
            return false;
        }
        
        if (user == null) {
            return false;
        }
        
        this.setUser(user);
        Authentication userAuth = new UserAuthentication(db);
        return userAuth.authenticate(username, password);
    }

    private boolean handleUserRegistration() {
        String username = readCredentialInput("username");
        String password = readCredentialInput("password");
        
        DBHandler<User> db = new UserDB(this.dbPath);
        UserInterface user = null;
        
        try {
            user = db.getItemByString("name", username);
            
            if (user != null) {
                System.out.println("Benutzer existiert bereits. Registrierung nicht möglich.");
                return false;
            }
        } catch (DatabaseException e) {
            System.err.println("Datenbankfehler bei der Registrierung: " + e.getMessage());
            return false;
        }
        
        UserRegistration userReg = new UserRegistration(db);
        boolean success = userReg.register(username, password);
		if (success) {
		    try {
		        user = db.getItemByString("name", username);
		        if (user != null) {
		            this.setUser(user);
		        } else {
		            System.err.println("Benutzer wurde registriert, konnte aber nicht geladen werden.");
		        }
		    } catch (DatabaseException e) {
		        System.err.println("Datenbankfehler beim Laden des neuen Benutzers: " + e.getMessage());
		    }
		}
		return success;
    }

    private boolean handleEmployeeLogin() {
        String username = readCredentialInput("username");
        String password = readCredentialInput("password");
        
        DBHandler<Employee> db = new EmployeeDB(this.dbPath);
        UserInterface user = null;
        
        try {
            user = db.getItemByString("name", username);
        } catch (DatabaseException e) {
            System.err.println("Datenbankfehler beim Login: " + e.getMessage());
            return false;
        }
        
        if (user == null) {
            return false;
        }
        
        this.setUser(user);
        Authentication empAuth = new EmployeeAuthentication(db);
        return empAuth.authenticate(username, password);
    }

    private boolean handleEmployeeRegistration() {
        String username = readCredentialInput("username");
        String password = readCredentialInput("password");
        
        DBHandler<Employee> db = new EmployeeDB(this.dbPath);
        UserInterface user = null;
        
        try {
            user = db.getItemByString("name", username);
            
            if (user != null) {
                System.out.println("Mitarbeiter existiert bereits. Registrierung nicht möglich.");
                return false;
            }
        } catch (DatabaseException e) {
            System.err.println("Datenbankfehler bei der Registrierung: " + e.getMessage());
            return false;
        }
        
        EmployeeRegistration empReg = new EmployeeRegistration(db);
        // Cast zu Registration, da EmployeeRegistration das Registration-Interface implementiert
		Registration registration = (Registration) empReg;
		boolean success = registration.register(username, password);
		
		if (success) {
		    try {
		        user = db.getItemByString("name", username);
		        if (user != null) {
		            this.setUser(user);
		        } else {
		            System.err.println("Mitarbeiter wurde registriert, konnte aber nicht geladen werden.");
		        }
		    } catch (DatabaseException e) {
		        System.err.println("Datenbankfehler beim Laden des neuen Mitarbeiters: " + e.getMessage());
		    }
		}
		return success;
    }

    private void handleLoginFailure() {
        System.out.println("Wrong username or password. Try again.\n\n");
        this.deleteUser();
        this.loginView();
    }

    @Override
    public String readString() {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();
        scanner.nextLine();
        return value;
    }

    private String readCredentialInput(String credentialType) {
        System.out.println("Input " + credentialType + ":\t\t");
        return this.readString();
    }

    @Override
    public void showBook(Book buch) {
        // Implementierung hinzufügen
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
        System.out.println(message);
    }
}
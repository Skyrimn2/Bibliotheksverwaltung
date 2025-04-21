package plugins;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import adapter.EmployeeAuthentication;
import adapter.EmployeeRegistration;
import adapter.Frontend;
import adapter.UserAuthentication;
import adapter.UserRegistration;
import application.Authentication;
import application.DBHandler;
import application.FrontendHandler;
import application.Menu;
import application.Registration;
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
    	System.out.println(menu.getAllDescriptions());
    	System.out.print("\n"+"Please enter a number: ");
    	
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
		System.out.println("Please select:\n0\t\tlogin with User\n1\t\tregister User\n2\t\tlogin as employee\n3\t\tregister employee");
		
		int selection = this.readMenuOption();
		boolean state = false;
		
		System.out.println("Input username:\t\t");
		String username = this.readString();
		System.out.println("Input password:\t\t");
		String password = this.readString();
		
		switch (selection) {
		case 0:
			DBHandler<User> db = new UserDB(this.dbPath);
			User user = db.getItemByString("name", username);
			this.setUser(user);
			Authentication UserAuth = new UserAuthentication(db);
			state = UserAuth.authenticate(username, password);
			break;
			
		case 1:
			DBHandler<User> db1 = new UserDB(this.dbPath);
			User user1 = db1.getItemByString("name", username);
			this.setUser(user1);
			Registration UserReg = new UserRegistration(db1);
			state = UserReg.register(username, password);
			break;

		case 2:
			DBHandler<Employee> db2 = new EmployeeDB(this.dbPath);
			Employee emp = db2.getItemByString("name", username);
			this.setUser(emp);
			Authentication EmpAuth = new EmployeeAuthentication(db2);
			state = EmpAuth.authenticate(username, password);
			break;
			
		case 3:
			DBHandler<Employee> db3 = new EmployeeDB(this.dbPath);
			Employee emp1 = db3.getItemByString("name", username);
			this.setUser(emp1);
			Registration EmpReg = new EmployeeRegistration(db3);
			state = EmpReg.register(username, password);
			
		default:
			break;
		}
		if (state == false) {
			System.out.println("Wrong username or password. Try again.\n\n");
			this.deleteUser();
			state = this.loginView();	
		}
		return state;
	}
	
	@Override
	public String readString() {
		Scanner scanner = new Scanner(System.in);
    	String value = scanner.next();
    	scanner.nextLine();
    	return value;
	}

	@Override
	public void showBook(Book buch) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setUser(User user) {
		UserInterface u = new User(user.getName(), user.getID(), user.getMembership());
		this.user = u;
	}
	
	@Override
	public void setUser(Employee emp) {
		UserInterface u = new Employee(emp.getName(), emp.getID());
		this.user = u;
	}
	
	@Override
	public void deleteUser() {
		this.user = null;
	}
	
}

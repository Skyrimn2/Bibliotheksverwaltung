package plugins;

import java.util.List;
import java.util.Scanner;

import adapter.EmployeeAuthentication;
import adapter.EmployeeRegistration;
import adapter.Frontend;
import adapter.UserAuthentication;
import adapter.UserRegistration;
import application.Authentication;
import application.FrontendHandler;
import application.Menu;
import application.Registration;
import domain.Displayable;

public class ConsoleFrontend extends Frontend {
	
	private String dbPath;
	
	public ConsoleFrontend(String dbpath) {
		super();
		this.dbPath = dbpath;
	}
	
    @Override
    public void showBook(domain.Book book) {
        System.out.println("Buchtitel: " + book.getTitle());
        System.out.println("Buchautor: " + book.getAutor());
        System.out.println("Verfügbar: " + (book.isAvailable() ? "Ja" : "Nein"));
    }
    
    @Override
    public void showMenu(Menu menu) {
    	System.out.println(menu.getAllDescriptions());
    	
    }
    
    @Override
    public int readMenuOption() {
		Scanner scanner = new Scanner(System.in);
    	int selection = scanner.nextInt();
        scanner.nextLine();
    	
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
			userLevel = 1;
			Authentication UserAuth = new UserAuthentication(new UserDB(this.dbPath));
			state = UserAuth.authenticate(username, password);
			break;
			
		case 1:
			userLevel = 1;
			Registration UserReg = new UserRegistration(new UserDB(this.dbPath));
			state = UserReg.register(username, password);
			break;

		case 2:
			userLevel = 2;
			Authentication EmpAuth = new EmployeeAuthentication(new EmployeeDB(this.dbPath));
			state = EmpAuth.authenticate(username, password);
			break;
			
		case 3:
			userLevel = 2;
			Registration EmpReg = new EmployeeRegistration(new EmployeeDB(this.dbPath));
			state = EmpReg.register(username, password);
			
		default:
			break;
		}
		if (state == false) {
			System.out.println("Wrong username or password. Try again.\n\n");
			state = this.loginView();
		}
		return state;
	}
	
	public String readString() {
		Scanner scanner = new Scanner(System.in);
    	String value = scanner.next();
    	scanner.nextLine();
    	return value;
	}
}

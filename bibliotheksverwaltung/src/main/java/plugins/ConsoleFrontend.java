package plugins;

import java.util.List;
import java.util.Scanner;

import application.FrontendHandler;
import application.Menu;
import domain.Displayable;

public class ConsoleFrontend implements FrontendHandler {
	
	public ConsoleFrontend() {
		super();
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
}

package main;

import java.util.Scanner;

import application.*;
import plugins.*;

public class Bibliotheksverwaltung {

    public static void main(String[] args) {
    	
    	System.out.println("Please enter DB Path:");
    	Scanner sc = new Scanner(System.in);
    	String dbPath = sc.next();
            
        FrontendHandler frontend = new ConsoleFrontend(dbPath);
        frontend.loginView();        
        
        MenuConfigurator menuconfig = new MenuConfigurator(dbPath, frontend, new BookDB(dbPath), new BookCopyDB(dbPath), new LendingDB(dbPath), new UserDB(dbPath));
        Menu menu = menuconfig.configureMenu();
        
        while (true) {
        	frontend.showMenu(menu);
        	int option = frontend.readMenuOption();
        	menu.executeAction(option);        	
        }

    }
}
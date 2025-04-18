package main;

import java.util.Scanner;

import application.*;
import domain.*;
import plugins.*;

public class Bibliotheksverwaltung {

    public static void main(String[] args) {
    	
    	System.out.println("Please enter DB Path:");
    	Scanner sc = new Scanner(System.in);
    	String dbPath = sc.next();
    	sc.close();
            
        FrontendHandler frontend = new ConsoleFrontend();
        Menu menu = new Menu();
        menu.registerAction(new ListBooksAction(new BookDB(dbPath), frontend));
        
        
        frontend.showMenu(menu);
    }
}
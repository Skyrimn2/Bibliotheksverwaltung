package main;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;

import application.*;
import domain.*;
import plugins.*;

public class Bibliotheksverwaltung {

    public static void main(String[] args) {
 

    	
    	System.out.println("Please enter DB Path:");
    	Scanner sc = new Scanner(System.in);
    	String dbPath = sc.next();
            
        FrontendHandler frontend = new ConsoleFrontend(dbPath);
        Menu menu = new Menu();
        menu.registerAction(new ListBooksAction(new BookDB(dbPath), frontend));
        menu.registerAction(new ListBookByTitleAction(new BookDB(dbPath), frontend));
        menu.registerAction(new LendBookAction(new BookDB(dbPath), new BookCopyDB(dbPath), new LendingDB(dbPath), new UserDB(dbPath), frontend));
        menu.registerAction(new ReturnLendingAction(new LendingDB(dbPath), new BookCopyDB(dbPath), frontend));
        menu.registerAction(new ListUserLendings(new LendingDB(dbPath), frontend));
        menu.registerAction(new ListAllLendings(new LendingDB(dbPath), frontend));
        
        menu.registerAction(new QuitAppAction());
        
        frontend.loginView();        
        while (true) {
        	frontend.showMenu(menu);
        	int option = frontend.readMenuOption();
        	menu.executeAction(option);        	
        }

    }
}
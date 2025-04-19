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
        
        frontend.loginView();        
        
        frontend.showMenu(menu);
        int option = frontend.readMenuOption();
        
       menu.executeAction(option);
       
       sc.close();
    }
}
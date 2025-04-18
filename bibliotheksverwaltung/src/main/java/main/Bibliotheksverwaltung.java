package main;

import application.*;
import domain.*;
import plugins.*;

public class Bibliotheksverwaltung {

    public static void main(String[] args) {
            
        FrontendHandler frontend = new ConsoleFrontend();
        Menu menu = new Menu();
        menu.registerAction(new ListBooksAction(new FakeBookDB(), frontend));
        
        
        frontend.showMenu(menu);
    }
}
package main;

import application.*;
import domain.*;
import plugins.*;

public class Bibliotheksverwaltung {

    public static void main(String[] args) {
            
        FrontendHandler frontend = new ConsoleFrontend();
        
        
        frontend.showMenu();
    }
}
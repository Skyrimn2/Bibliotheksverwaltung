package plugins;

import java.util.Scanner;

import application.FrontendHandler;

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
    public void showMenu() {
    	System.out.println("*********************************************");
        System.out.println("|   Willkommen zur Bibliotheksverwaltung!   |");
        System.out.println("*********************************************");

        System.out.println("Bitte wählen Sie eine Aktion aus:");
        System.out.println("1.  Alle Bücher anzeigen");
        System.out.println("2.  Benutzer registrieren");
        System.out.println("3.  Verfügbare Bücher anzeigen");
        System.out.println("4.  Buch ausleihen");
        System.out.println("5.  Buch zurückgeben");
        System.out.println("6.  Bibliotheksstandort hinzufügen");
        System.out.println("7.  Bibliothekar erstellen");
        System.out.println("8.  Mitgliedschaft erstellen");
        System.out.println("9.  Gebühren festlegen");
        System.out.println("10. Ausleihstatus überprüfen");
        System.out.println("11. Veranstaltungskategorie erstellen");
        System.out.println("12. Bibliotheksangebot erstellen");
        System.out.println("13. Ereignis erstellen");
        System.out.println("14. Ausgeliehene Bücher eines Benutzers anzeigen");
        System.out.println("15. Beenden");
        System.out.print("\n Ihre Auswahl: ");
    }
    
    @Override
    public int readMenuOption() {
    	Scanner scanner = new Scanner(System.in);
    	 int selection = scanner.nextInt();
         scanner.nextLine();
    	
    	return selection;
    }
}

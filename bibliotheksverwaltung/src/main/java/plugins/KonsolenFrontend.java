package plugins;

import application.FrontendHandler;

public class KonsolenFrontend implements FrontendHandler {
	
	public KonsolenFrontend() {
		super();
	}
	
    @Override
    public void zeigeBuch(domain.Buch buch) {
        System.out.println("Buchtitel: " + buch.getTitel());
        System.out.println("Buchautor: " + buch.getAutor());
        System.out.println("Verfügbar: " + (buch.istVerfuegbar() ? "Ja" : "Nein"));
    }
    
    @Override
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
    
}

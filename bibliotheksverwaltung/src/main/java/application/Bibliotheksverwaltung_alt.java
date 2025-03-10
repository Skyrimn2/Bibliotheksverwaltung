// package application;

// import java.util.List;
// // import java.time.LocalDateTime;
// // import java.time.LocalDate;
// import java.util.Scanner;

// import domain.*;
// import plugins.*;
// // import application.*;
// // import adapters.*;
// // import domain.Bewertung;



// public class Bibliotheksverwaltung_alt {
//         private final BenutzerAuthentifizierung authentifizierung;
//         private List<Bewertung> bewertungen;

//         public List<Bewertung> getBewertungen() {
//             return bewertungen;
//         }

//     public Bibliotheksverwaltung_alt(BenutzerAuthentifizierung authentifizierung) {
//         this.authentifizierung = authentifizierung;
//     }

//     private BewertungManager bewertungManager;
    
//     public Bibliotheksverwaltung_alt(BenutzerAuthentifizierung authentifizierung, List<Bewertung> bewertungen) {
//         this.authentifizierung = authentifizierung;
//         this.bewertungen = bewertungen;
//         this.bewertungManager = new BewertungManager(bewertungen);
//     }

//     public void alleBewertungenAnzeigen() {
//         bewertungManager.alleBewertungenAnzeigen();
//     }

//     public double durchschnittlicheBewertung() {
//         return bewertungManager.durchschnittlicheBewertung();
//     }


//     public static void start() {   // Ausführungen beim Starten der Anwendung
//         BenutzerAuthentifizierung authentifizierung = new EinfacheBenutzerAuthentifizierung();
//         Bibliotheksverwaltung bibliotheksverwaltung = new Bibliotheksverwaltung(authentifizierung);
//         String benutzername = "admin";
//         String passwort = "geheim";
        
//         if (bibliotheksverwaltung.authentifizierung.authentifizieren(benutzername, passwort)) {
//             System.out.println("Benutzer erfolgreich authentifiziert.");
//         } else {
//             System.out.println("Fehler bei der Authentifizierung.");
//         }


//         // Bibliothek bibliothek = new Bibliothek();

//         // Scanner scanner = new Scanner(System.in);

//         // Benutzer benutzer1 = new Benutzer("Max Mustermann", "abc", 123456, null);
        
//     }

//     public static void auswahl() {
//         Scanner scanner = new Scanner(System.in);
//         int auswahl;
//         do {
//             System.out.println("*********************************************");
//             System.out.println("|   Willkommen zur Bibliotheksverwaltung!   |");
//             System.out.println("*********************************************");

//             System.out.println("Bitte wählen Sie eine Aktion aus:");
//             System.out.println("1.  Alle Bücher anzeigen");
//             System.out.println("2.  Benutzer registrieren");
//             System.out.println("3.  Verfügbare Bücher anzeigen");
//             // System.out.println("4.  Buch ausleihen");
//             // System.out.println("5.  Buch zurückgeben");
//             // System.out.println("6.  Bibliotheksstandort hinzufügen");
//             // System.out.println("7.  Bibliothekar erstellen");
//             // System.out.println("8.  Mitgliedschaft erstellen");
//             // System.out.println("9.  Gebühren festlegen");
//             // System.out.println("10. Ausleihstatus überprüfen");
//             // System.out.println("11. Veranstaltungskategorie erstellen");
//             // System.out.println("12. Bibliotheksangebot erstellen");
//             // System.out.println("13. Ereignis erstellen");
//             // System.out.println("14. Ausgeliehene Bücher eines Benutzers anzeigen");
//             System.out.println("15. Beenden");
//             System.out.print("\n Ihre Auswahl: ");
//             auswahl = scanner.nextInt();
//             switch (auswahl) {
//                 case 1:
//                     BucherAnzeigen bucherAnzeigen = new BucherAnzeigen(new FakeDB(), new KonsolenFrontend());
//                     bucherAnzeigen.zeigealleBuecher();
//                     break;
//                 case 2:
//                     BenutzerRegistrierung benutzerRegistrierung = new BenutzerRegistrierung();
//                     benutzerRegistrierung.registriereBenutzer();
//                     break;
//                 case 3:
//                     System.out.println("\nVerfügbare Bücher:");
//                     BucherAnzeigen verfuegbareBucherAnzeigen = new BucherAnzeigen(new FakeDB(), new KonsolenFrontend());
//                     verfuegbareBucherAnzeigen.zeigeVerfuegbareBuecher();
//                     break;
                    
//             //         case 4:
//             //             // Aktion: Buch ausleihen
//             //             // Hier implementierst du die Logik für das Ausleihen eines Buches
//             //             break;
//             //         case 5:
//             //             Benutzer aktiverBenutzer = benutzer1;
//             //             List<Buch> ausgelieheneBuecherBenutzer = aktiverBenutzer.getAusgelieheneBuecher();
//             //             if (ausgelieheneBuecherBenutzer.isEmpty()) {
//             //                 System.out.println("Sie haben keine Bücher ausgeliehen.");
//             //             } else {
//             //                 System.out.println("Folgende Bücher haben Sie ausgeliehen:");
//             //                 for (int i = 0; i < ausgelieheneBuecherBenutzer.size(); i++) {
//             //                     System.out.println((i+1) + ". " + ausgelieheneBuecherBenutzer.get(i).getTitel());
//             //                 }
//             //                 System.out.println("Bitte geben Sie die Nummer des Buches ein, das Sie zurückgeben möchten:");
//             //                 int buchNummer = Integer.parseInt(scanner.nextLine());
//             //                 if (buchNummer >= 1 && buchNummer <= ausgelieheneBuecherBenutzer.size()) {
//             //                     Buch buchZurueckgeben = ausgelieheneBuecherBenutzer.get(buchNummer - 1);
//             //                     bibliothek.buchRueckgabe(aktiverBenutzer, buchZurueckgeben);
//             //                 } else {
//             //                     System.out.println("Ungültige Buchnummer.");
//             //                 }
//             //             }
//             //             break;
//             //         case 6:
//             //             System.out.println("Bitte geben Sie den Namen des neuen Bibliotheksstandorts ein:");
//             //             String standortName = scanner.nextLine();
//             //             System.out.println("Bitte geben Sie die Adresse des neuen Bibliotheksstandorts ein:");
//             //             String standortAdresse = scanner.nextLine();
//             //             System.out.println("Bitte geben Sie die Öffnungszeiten des neuen Bibliotheksstandorts ein:");
//             //             String standortOeffnungszeiten = scanner.nextLine();
//             //             Bibliotheksstandort neuerStandort = new Bibliotheksstandort(standortName, standortAdresse, standortOeffnungszeiten);
//             //             bibliothek.bibliotheksstandortHinzufuegen(neuerStandort);
//             //             System.out.println("Der Bibliotheksstandort \"" + standortName + "\" wurde erfolgreich hinzugefügt.");
//             //             break;
//             //         case 7:
//             //             bibliothek.bibliothekarHinzufuegenMitInput();
//             //             break;
//             //         case 8:
//             //             // Aktion: Mitgliedschaft erstellen
//             //             // Hier implementierst du die Logik für das Erstellen einer Mitgliedschaft
//             //             break;
//             //         case 9:
//             //             // Aktion: Gebühren festlegen
//             //             // Hier implementierst du die Logik für das Festlegen von Gebühren
//             //             break;
//             //         case 10:
//             //             // Aktion: Ausleihstatus überprüfen
//             //             // Hier implementierst du die Logik für das Überprüfen des Ausleihstatus
//             //             break;
//             //         case 11:
//             //             // Aktion: Veranstaltungskategorie erstellen
//             //             // Hier implementierst du die Logik für das Erstellen einer Veranstaltungskategorie
//             //             break;
//             //         case 12:
//             //             // Aktion: Bibliotheksangebot erstellen
//             //             // Hier implementierst du die Logik für das Erstellen eines Bibliotheksangebots
//             //             break;
//             //         case 13:
//             //             // Aktion: Ereignis erstellen
//             //             // Hier implementierst du die Logik für das Erstellen eines Ereignisses
//             //             break;
//             //         case 14:
//             //             System.out.println("\nAusgeliehene Bücher von " + benutzer1.getName() + ":");
//             //             List<Buch> ausgelieheneBuecher = benutzer1.getAusgelieheneBuecher();
//             //             for (Buch buch : ausgelieheneBuecher) {
//             //                 System.out.println(buch.getTitel() + " - " + buch.getAutor());
//             //             }
//             //             break;
//                     case 15:
//                         // Aktion: Beenden
//                         System.out.println("Vielen Dank für die Nutzung der Bibliotheksverwaltung. Auf Wiedersehen!");
//                         break;
//                     default:
//                         System.out.println("Ungültige Auswahl. Bitte geben Sie eine gültige Option ein.");
//                 }
//             } while (auswahl != 15);

//         // // Bücher hinzufügen
//         // Buch buch1 = new Buch("Java ist toll", "John Doe");
//         // Buch buch2 = new Buch("Python für Anfänger", "Jane Smith");
//         // bibliothek.buchHinzufuegen(buch1);
//         // bibliothek.buchHinzufuegen(buch2);
        

//         // // Benutzer registrieren
//         // Benutzer benutzer2 = new Benutzer("Anna Schmidt", 789012);
//         // bibliothek.benutzerRegistrieren(benutzer1);
//         // bibliothek.benutzerRegistrieren(benutzer2);

//         // // Bücher ausleihen und zurückgeben
//         // bibliothek.buchAusleihen(benutzer1, buch1);
//         // bibliothek.buchAusleihen(benutzer2, buch2);
//         // bibliothek.buchRueckgabe(benutzer1, buch1);

//         // // Kunden erstellen
//         // Kunde kunde1 = new Kunde("Max Mustermann", "max@example.com");
//         // Kunde kunde2 = new Kunde("Erika Mustermann", "erika@example.com");
        
//         // // Bewertungen erstellen
//         // Bewertung bewertung1 = new Bewertung(buch1, benutzer1, 5, "Tolles Buch!");
//         // Bewertung bewertung2 = new Bewertung(buch2, benutzer2, 4, "Sehr hilfreich!");
        
//         // // Bibliotheksstandorte erstellen
//         // Bibliotheksstandort standort1 = new Bibliotheksstandort("Hauptbibliothek", "Hauptstraße 1", "Mo-Fr: 9-18 Uhr");
//         // Bibliotheksstandort standort2 = new Bibliotheksstandort("Nebenstelle", "Nebenstraße 2", "Mo-Do: 10-16 Uhr");
        
//         // // Ereignisse erstellen
//         // Ereignis ereignis1 = new Ereignis("Lesung", LocalDateTime.now(), "Hauptbibliothek", "Lesung mit einem berühmten Autor");
//         // Ereignis ereignis2 = new Ereignis("Workshop", LocalDateTime.now().plusDays(7), "Nebenstelle", "Java-Workshop für Anfänger");    

//         // // Bibleothekar hinzufügen
//         // Mitarbeiter bibliothekar = new Mitarbeiter("Hans Müller", "1234");

//         // Mitgliedschaft mitgliedschaft1 = new Mitgliedschaft(benutzer1, LocalDate.now(), LocalDate.now().plusYears(1));
//         // Mitgliedschaft mitgliedschaft2 = new Mitgliedschaft(benutzer2, LocalDate.now(), LocalDate.now().plusYears(2));
            
//         // // Gebühren festlegen
//         // Gebuehren gebuehren = new Gebuehren(2.0, 0.5);
            
//         // // Ausleihstatus überprüfen
//         // Ausleihstatus status = Ausleihstatus.Ausgeliehen;
            
//         // // Veranstaltungskategorie erstellen
//         // Veranstaltungskategorie veranstaltungskategorie = new Veranstaltungskategorie("Lesung");
            
//         // // Bibliotheksangebot erstellen
//         // Bibliotheksangebot bibliotheksangebot = new Bibliotheksangebot("PC-Nutzung", "Freie Nutzung der PCs in der Bibliothek");
    

//          scanner.close();
//     }


// }

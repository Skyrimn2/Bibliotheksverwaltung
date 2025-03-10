package application;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

import domain.*;
import plugins.*;

public class Bibliotheksverwaltung {
        private final BenutzerAuthentifizierung authentifizierung;
        private List<Bewertung> bewertungen;
        private Bibliothek bibliothek;

        public List<Bewertung> getBewertungen() {
            return bewertungen;
        }

    public Bibliotheksverwaltung(BenutzerAuthentifizierung authentifizierung) {
        this.authentifizierung = authentifizierung;
        this.bibliothek = new Bibliothek();
    }
    
    private BewertungManager bewertungManager;
    
    public Bibliotheksverwaltung(BenutzerAuthentifizierung authentifizierung, List<Bewertung> bewertungen) {
        this.authentifizierung = authentifizierung;
        this.bewertungen = bewertungen;
        this.bewertungManager = new BewertungManager(bewertungen);
        this.bibliothek = new Bibliothek();
    }

    public void alleBewertungenAnzeigen() {
        bewertungManager.alleBewertungenAnzeigen();
    }

    public double durchschnittlicheBewertung() {
        return bewertungManager.durchschnittlicheBewertung();
    }


    public static void start() {   // Ausführungen beim Starten der Anwendung
        BenutzerAuthentifizierung authentifizierung = new EinfacheBenutzerAuthentifizierung();
        Bibliotheksverwaltung bibliotheksverwaltung = new Bibliotheksverwaltung(authentifizierung);
        String benutzername = "admin";
        String passwort = "geheim";
        
        if (bibliotheksverwaltung.authentifizierung.authentifizieren(benutzername, passwort)) {
            System.out.println("Benutzer erfolgreich authentifiziert.");
        } else {
            System.out.println("Fehler bei der Authentifizierung.");
        }

        Bibliothek bibliothek = new Bibliothek();

        Scanner scanner = new Scanner(System.in);

        Benutzer benutzer1 = new Benutzer("Max Mustermann", "abc", 123456, null);
        
    }

    // public boolean istBuchVerfuegbar(int buchId) {
    //     Buch buch = bibliothek.getBuchById(buchId);
    //     return buch != null && buch.isVerfuegbar();
    // }

    public static void auswahl() {
        Scanner scanner = new Scanner(System.in);
        int auswahl;
        
        // Erstelle eine Bibliotheksinstanz für die Verwaltung der Ausleihen
        Bibliothek bibliothek = new Bibliothek();
        
        // Erstelle einen Testbenutzer
        Benutzer benutzer1 = new Benutzer("Max Mustermann", "abc", 123456, null);
        bibliothek.benutzerRegistrieren(benutzer1);
        
        do {
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
            auswahl = scanner.nextInt();
            scanner.nextLine(); // Verbraucht die Newline nach nextInt()
            
            switch (auswahl) {
                case 1:
                    BucherAnzeigen bucherAnzeigen = new BucherAnzeigen(new FakeDB(), new KonsolenFrontend());
                    bucherAnzeigen.zeigealleBuecher();
                    break;
                case 2:
                    BenutzerRegistrierung benutzerRegistrierung = new BenutzerRegistrierung();
                    benutzerRegistrierung.registriereBenutzer();
                    break;
                case 3:
                    System.out.println("\nVerfügbare Bücher:");
                    BucherAnzeigen verfuegbareBucherAnzeigen = new BucherAnzeigen(new FakeDB(), new KonsolenFrontend());
                    verfuegbareBucherAnzeigen.zeigeVerfuegbareBuecher();
                    break;
                case 4:
                    // Aktion: Buch ausleihen
                    System.out.println("\nBuch ausleihen:");
                    BucherAnzeigen ausleihBucherAnzeigen = new BucherAnzeigen(new FakeDB(), new KonsolenFrontend());
                    System.out.println("Verfügbare Bücher zum Ausleihen:");
                    ausleihBucherAnzeigen.zeigeVerfuegbareBuecher();
                    
                    System.out.println("\nGeben Sie die ID des Buches ein, das Sie ausleihen möchten:");
                    int buchId = scanner.nextInt();
                    scanner.nextLine(); // Verbraucht die Newline
                    
                    FakeDB db = new FakeDB();
                    Buch auszuleihendesBuch = db.getBuchById(buchId);
                    
                    if (auszuleihendesBuch != null) {
                        bibliothek.buchAusleihen(benutzer1, auszuleihendesBuch);
                        System.out.println("Buch '" + auszuleihendesBuch.getTitel() + "' wurde erfolgreich ausgeliehen.");
                    } else {
                        System.out.println("Das Buch mit der ID " + buchId + " wurde nicht gefunden.");
                    }
                    break;
                case 5:
                    Benutzer aktiverBenutzer = benutzer1;
                    List<Buch> ausgelieheneBuecherBenutzer = aktiverBenutzer.getAusgelieheneBuecher();
                    if (ausgelieheneBuecherBenutzer.isEmpty()) {
                        System.out.println("Sie haben keine Bücher ausgeliehen.");
                    } else {
                        System.out.println("Folgende Bücher haben Sie ausgeliehen:");
                        for (int i = 0; i < ausgelieheneBuecherBenutzer.size(); i++) {
                            System.out.println((i+1) + ". " + ausgelieheneBuecherBenutzer.get(i).getTitel());
                        }
                        System.out.println("Bitte geben Sie die Nummer des Buches ein, das Sie zurückgeben möchten:");
                        int buchNummer = Integer.parseInt(scanner.nextLine());
                        if (buchNummer >= 1 && buchNummer <= ausgelieheneBuecherBenutzer.size()) {
                            Buch buchZurueckgeben = ausgelieheneBuecherBenutzer.get(buchNummer - 1);
                            bibliothek.buchRueckgabe(aktiverBenutzer, buchZurueckgeben);
                            System.out.println("Buch '" + buchZurueckgeben.getTitel() + "' wurde erfolgreich zurückgegeben.");
                        } else {
                            System.out.println("Ungültige Buchnummer.");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Bitte geben Sie den Namen des neuen Bibliotheksstandorts ein:");
                    String standortName = scanner.nextLine();
                    System.out.println("Bitte geben Sie die Adresse des neuen Bibliotheksstandorts ein:");
                    String standortAdresse = scanner.nextLine();
                    System.out.println("Bitte geben Sie die Öffnungszeiten des neuen Bibliotheksstandorts ein:");
                    String standortOeffnungszeiten = scanner.nextLine();
                    Bibliotheksstandort neuerStandort = new Bibliotheksstandort(standortName, standortAdresse, standortOeffnungszeiten);
                    bibliothek.bibliotheksstandortHinzufuegen(neuerStandort);
                    System.out.println("Der Bibliotheksstandort \"" + standortName + "\" wurde erfolgreich hinzugefügt.");
                    break;
                case 7:
                    bibliothek.bibliothekarHinzufuegenMitInput();
                    break;
                case 8:
                    // Aktion: Mitgliedschaft erstellen
                    System.out.println("\nMitgliedschaft erstellen:");
                    System.out.println("Bitte geben Sie den Namen des Benutzers ein:");
                    String benutzerName = scanner.nextLine();
                    System.out.println("Bitte geben Sie die Mitgliedsnummer ein:");
                    int mitgliedsnummer = scanner.nextInt();
                    scanner.nextLine(); // Verbraucht die Newline
                    
                    Benutzer neuerBenutzer = new Benutzer(benutzerName, "temp", mitgliedsnummer, null);
                    LocalDate startDatum = LocalDate.now();
                    LocalDate endDatum = startDatum.plusYears(1);
                    
                    Mitgliedschaft neueMitgliedschaft = new Mitgliedschaft(startDatum, endDatum);
                    System.out.println("Mitgliedschaft für " + benutzerName + " erfolgreich erstellt.");
                    System.out.println("Gültig von " + startDatum + " bis " + endDatum);
                    break;
                case 9:
                    // Aktion: Gebühren festlegen
                    System.out.println("\nGebühren festlegen:");
                    System.out.println("Bitte geben Sie die Grundgebühr ein (€):");
                    double grundgebuehr = scanner.nextDouble();
                    System.out.println("Bitte geben Sie die tägliche Versäumnisgebühr ein (€):");
                    double taeglicheGebuehr = scanner.nextDouble();
                    scanner.nextLine(); // Verbraucht die Newline
                    
                    Gebuehren gebuehren = new Gebuehren(grundgebuehr, taeglicheGebuehr);
                    System.out.println("Gebühren erfolgreich festgelegt:");
                    System.out.println("Grundgebühr: " + grundgebuehr + " €");
                    System.out.println("Tägliche Versäumnisgebühr: " + taeglicheGebuehr + " €");
                    break;
                case 10:
                    // Aktion: Ausleihstatus überprüfen
                    System.out.println("\nAusleihstatus überprüfen:");
                    BucherAnzeigen statusBucherAnzeigen = new BucherAnzeigen(new FakeDB(), new KonsolenFrontend());
                    System.out.println("Alle Bücher:");
                    statusBucherAnzeigen.zeigealleBuecher();
                    
                    System.out.println("\nGeben Sie die ID des Buches ein, dessen Status Sie überprüfen möchten:");
                    int statusBuchId = scanner.nextInt();
                    scanner.nextLine(); // Verbraucht die Newline
                    
                    FakeDB statusDb = new FakeDB();
                    Buch statusBuch = statusDb.getBuchById(statusBuchId);
                    
                    if (statusBuch != null) {
                        Ausleihstatus status = statusBuch.istVerfuegbar() ? Ausleihstatus.verfuegbar : Ausleihstatus.Ausgeliehen;
                        System.out.println("Buch '" + statusBuch.getTitel() + "' Status: " + status);
                    } else {
                        System.out.println("Das Buch mit der ID " + statusBuchId + " wurde nicht gefunden.");
                    }
                    break;
                case 11:
                    // Aktion: Veranstaltungskategorie erstellen
                    System.out.println("\nVeranstaltungskategorie erstellen:");
                    System.out.println("Bitte geben Sie den Namen der Veranstaltungskategorie ein:");
                    String kategorieBezeichnung = scanner.nextLine();
                    
                    // Veranstaltungskategorie neueKategorie = new Veranstaltungskategorie(kategorieBezeichnung);
                    System.out.println("Veranstaltungskategorie '" + kategorieBezeichnung + "' erfolgreich erstellt.");
                    break;
                case 12:
                    // Aktion: Bibliotheksangebot erstellen
                    System.out.println("\nBibliotheksangebot erstellen:");
                    System.out.println("Bitte geben Sie den Namen des Angebots ein:");
                    String angebotName = scanner.nextLine();
                    System.out.println("Bitte geben Sie die Beschreibung des Angebots ein:");
                    String angebotBeschreibung = scanner.nextLine();
                    
                    Bibliotheksangebot neuesAngebot = new Bibliotheksangebot(angebotName, angebotBeschreibung);
                    System.out.println("Bibliotheksangebot '" + angebotName + "' erfolgreich erstellt.");
                    break;
                case 13:
                    // Aktion: Ereignis erstellen
                    System.out.println("\nEreignis erstellen:");
                    System.out.println("Bitte geben Sie den Namen des Ereignisses ein:");
                    String ereignisName = scanner.nextLine();
                    System.out.println("Bitte geben Sie den Ort des Ereignisses ein:");
                    String ereignisOrt = scanner.nextLine();
                    System.out.println("Bitte geben Sie die Beschreibung des Ereignisses ein:");
                    String ereignisBeschreibung = scanner.nextLine();
                    
                    LocalDateTime ereignisDatum = LocalDateTime.now().plusDays(7);
                    
                    Ereignis neuesEreignis = new Ereignis(ereignisName, ereignisDatum, ereignisOrt, ereignisBeschreibung, null);
                    System.out.println("Ereignis '" + ereignisName + "' erfolgreich erstellt.");
                    System.out.println("Datum: " + ereignisDatum);
                    System.out.println("Ort: " + ereignisOrt);
                    break;
                case 14:
                    System.out.println("\nAusgeliehene Bücher von " + benutzer1.getName() + ":");
                    List<Buch> ausgelieheneBuecher = benutzer1.getAusgelieheneBuecher();
                    if (ausgelieheneBuecher.isEmpty()) {
                        System.out.println("Keine ausgeliehenen Bücher vorhanden.");
                    } else {
                        for (Buch buch : ausgelieheneBuecher) {
                            System.out.println(buch.getTitel() + " - " + buch.getAutor());
                        }
                    }
                    break;
                case 15:
                    // Aktion: Beenden
                    System.out.println("Vielen Dank für die Nutzung der Bibliotheksverwaltung. Auf Wiedersehen!");
                    break;
                default:
                    System.out.println("Ungültige Auswahl. Bitte geben Sie eine gültige Option ein.");
                }
            } while (auswahl != 15);

         scanner.close();
    }
}
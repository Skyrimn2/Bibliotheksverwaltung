package application;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Collection;
import domain.Ausleihe;
import domain.Buch;
import domain.Benutzer;
import domain.Mitarbeiter;
import domain.Bibliotheksstandort;

public class Bibliothek {
    private List<Ausleihe> ausleihen;
    private List<Buch> buecher;
    private List<Benutzer> benutzer;
    private List<Mitarbeiter> bibliothekare;
    private List<Bibliotheksstandort> bibliotheksstandorte;

    public Bibliothek() {
        ausleihen = new ArrayList<>();
        buecher = new ArrayList<>();
        benutzer = new ArrayList<>();
        this.bibliothekare = new ArrayList<>();
        this.bibliotheksstandorte = new ArrayList<>();
        this.benutzer = new ArrayList<>();
    }

    public void buchAusleihen(Benutzer benutzer, Buch buch) {
        if (buecher.contains(buch)) {
            boolean hatBereitsAusgeliehen = ausleihen.stream()
                    .anyMatch(ausleihe -> ausleihe.getBenutzer().equals(benutzer) && ausleihe.getBuch().equals(buch));
            if (!hatBereitsAusgeliehen) {
                Ausleihe ausleihe = new Ausleihe(benutzer, buch, LocalDate.now(), 123); // Aktuelles Datum setzen
                ausleihen.add(ausleihe);
                System.out.println(benutzer.getName() + " hat das Buch \"" + buch.getTitel() + "\" ausgeliehen.");
            } else {
                System.out.println(benutzer.getName() + " hat das Buch \"" + buch.getTitel() + "\" bereits ausgeliehen.");
            }
        } else {
            System.out.println("Das Buch \"" + buch.getTitel() + "\" ist nicht verfügbar.");
        }
    }

    public void buchRueckgabe(Benutzer benutzer, Buch buch) {
        Ausleihe ausleihe = ausleihen.stream()
                .filter(ausl -> ausl.getBenutzer().equals(benutzer) && ausl.getBuch().equals(buch))
                .findFirst()
                .orElse(null);
        if (ausleihe != null) {
            ausleihen.remove(ausleihe);
            System.out.println(benutzer.getName() + " hat das Buch \"" + buch.getTitel() + "\" zurückgegeben.");
        } else {
            System.out.println("Das Buch \"" + buch.getTitel() + "\" wurde von " + benutzer.getName() + " nicht ausgeliehen.");
        }
    }

    public List<Buch> getVerfuegbareBuecher() {
        List<Buch> verfuegbare = new ArrayList<>();
        for (Buch buch : buecher) {
            if (!istAusgeliehen(buch)) {
                verfuegbare.add(buch);
            }
        }
        return verfuegbare;
    }

    private boolean istAusgeliehen(Buch buch) {
        for (Ausleihe ausleihe : ausleihen) {
            if (ausleihe.getBuch().equals(buch)) {
                return true;
            }
        }
        return false;
    }
    
    public void buchHinzufuegen(Buch buch) {
        buecher.add(buch);
        System.out.println("Buch hinzugefügt: " + buch.getTitel());
    }

    public void buchEntfernen(Buch buch) {
        buecher.remove(buch);
    }

    public void benutzerRegistrieren(Benutzer benutzer) {
        this.benutzer.add(benutzer);
        System.out.println("Benutzer registriert: " + benutzer.getName());
    }

    public void bibliotheksstandortHinzufuegen(Bibliotheksstandort standort) {
        bibliotheksstandorte.add(standort);
    }

    public List<Bibliotheksstandort> getBibliotheksstandorte() {
        return bibliotheksstandorte;
    }

    public void bibliotheksstandortEntfernen(Bibliotheksstandort standort) {
        bibliotheksstandorte.remove(standort);
    }

    public void bibliothekarHinzufuegenMitInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Namen des neuen Bibliothekars ein:");
        String name = scanner.nextLine();
        System.out.println("Bitte geben Sie die E-Mail-Adresse des neuen Bibliothekars ein:");
        String email = scanner.nextLine();

        Mitarbeiter bibliothekar = new Mitarbeiter(name, email);
        
        this.bibliothekare.add(bibliothekar);
        System.out.println("Neuer Bibliothekar erfolgreich hinzugefügt.");
    }

    public List<Benutzer> getBenutzerListe() {
        return benutzer;
    }
    
}
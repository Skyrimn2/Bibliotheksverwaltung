package application;

import java.util.List;

import domain.Bewertung;


//Was ist der Sinn von der Klasse? Warum Bewertungen in einer neuen Liste speichern? Die sind doch in dem Buch-Objekt schon gespeichert.
public class BewertungManager {
    
    private List<Bewertung> bewertungen;
    public BewertungManager(List<Bewertung> bewertungen) {
        this.bewertungen = bewertungen;
    }
    
    public void bewertungHinzufuegen(Bewertung bewertung) {
        bewertungen.add(bewertung);
    }

    public double durchschnittlicheBewertung() {
        if (bewertungen.isEmpty()) {
            return 0.0;
        }
        int summeSterne = 0;
        for (Bewertung bewertung : bewertungen) {
            summeSterne += bewertung.getSterne();
        }
        return (double) summeSterne / bewertungen.size();
    }

    public void alleBewertungenAnzeigen() {
        for (Bewertung bewertung : bewertungen) {
            System.out.println(bewertung);
        }
    }

}

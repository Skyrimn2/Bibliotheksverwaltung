package application;

import java.util.List;

import domain.Rating;


//Was ist der Sinn von der Klasse? Warum Bewertungen in einer neuen Liste speichern? Die sind doch in dem Buch-Objekt schon gespeichert.
public class BewertungManager {
    
    private List<Rating> bewertungen;
    public BewertungManager(List<Rating> bewertungen) {
        this.bewertungen = bewertungen;
    }
    
    public void bewertungHinzufuegen(Rating bewertung) {
        bewertungen.add(bewertung);
    }

    public double durchschnittlicheBewertung() {
        if (bewertungen.isEmpty()) {
            return 0.0;
        }
        int summeSterne = 0;
        for (Rating bewertung : bewertungen) {
            summeSterne += bewertung.getSterne();
        }
        return (double) summeSterne / bewertungen.size();
    }

    public void alleBewertungenAnzeigen() {
        for (Rating bewertung : bewertungen) {
            System.out.println(bewertung);
        }
    }

}

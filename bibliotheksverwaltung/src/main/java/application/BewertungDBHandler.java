package application;

import domain.Bewertung;

public interface BewertungDBHandler {
    public void speichereBewertung(Bewertung bewertung);
    public Bewertung ladeBewertung(int bewertungsID);
    public void loescheBewertung(int bewertungsID);
    public void aktualisiereBewertung(Bewertung bewertung);
}

package domain;

public class Bewertung {
    private Buch buch;
    private Benutzer benutzer;
    private int bewertungId;
    private String kommentar;
    private int sterne;

    public Bewertung(Buch buch, Benutzer benutzer, int bewertungId, String kommentar, int sterne) {
        this.buch = buch;
        this.benutzer = benutzer;
        this.bewertungId = bewertungId;
        this.kommentar = kommentar;
        this.sterne = sterne;
    }

    public Buch getBuch() {
        return this.buch;
    }

    public Benutzer getBenutzer() {
        return this.benutzer;
    }

    public int getBewertungId() {
        return this.bewertungId;
    }

    public String getKommentar() {
        return this.kommentar;
    }
    
    public int getSterne() {
        return sterne;
    }
    
    public void setSterne(int sterne) {
        if (sterne < 0 || sterne > 5 ){
            throw new IllegalArgumentException("Die Anzahl der Sterne muss zwischen 0 und 5 liegen.");
        }
        this.sterne = sterne;
    }
    
    public void setBuch(Buch buch) {
        this.buch = buch;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public void setBewertungId(int bewertungId) {
        this.bewertungId = bewertungId;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
package domain;

public class Bewertung {
    private User benutzer;
    private int bewertungId;
    private String kommentar;
    private int sterne;

    public Bewertung(User benutzer, int bewertungId, String kommentar, int sterne) {
        this.benutzer = benutzer;
        this.bewertungId = bewertungId;
        this.kommentar = kommentar;
        this.sterne = sterne;
    }

    public User getBenutzer() {
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

    public void setBenutzer(User benutzer) {
        this.benutzer = benutzer;
    }

    public void setBewertungId(int bewertungId) {
        this.bewertungId = bewertungId;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
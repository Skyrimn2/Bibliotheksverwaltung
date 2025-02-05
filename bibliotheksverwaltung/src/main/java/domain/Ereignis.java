package domain;

import java.time.LocalDateTime;

public class Ereignis {
    private String name;
    private LocalDateTime zeitpunkt;
    private String raum;
    private Bibliotheksstandort bibliotheksstandort;
    private String beschreibung;
    
    public Ereignis(String name, LocalDateTime zeitpunkt, String raum, String beschreibung, Bibliotheksstandort bibliotheksstandort) {
        this.name = name;
        this.zeitpunkt = zeitpunkt;
        this.raum = raum;
        this.beschreibung = beschreibung;
        this.bibliotheksstandort = bibliotheksstandort;
    }
    
    public String getName() {
        return this.name;
    }

    public LocalDateTime getZeitpunkt() {
        return this.zeitpunkt;
    }

    public String getRaum() {
        return this.raum;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setZeitpunkt(LocalDateTime zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Bibliotheksstandort getBibliotheksstandort() {
        return this.bibliotheksstandort;
    }

    public void setBibliotheksstandort(Bibliotheksstandort bibliotheksstandort) {
        this.bibliotheksstandort = bibliotheksstandort;
    }

}
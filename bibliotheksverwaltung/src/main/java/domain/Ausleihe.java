package domain;

import java.time.LocalDate;

public class Ausleihe {
    private int ausleihID;
    private Benutzer benutzer;
    private Buch buch;
    private LocalDate ausleihDatum;
    private LocalDate rueckgabeDatum;
    
    public Ausleihe(Benutzer benutzer, Buch buch, LocalDate ausleihDatum, int ausleihID) {
        this.benutzer = benutzer;
        this.buch = buch;
        this.ausleihDatum = ausleihDatum;
        this.ausleihID = ausleihID;
        this.rueckgabeDatum = null;
    }
    public Benutzer getBenutzer() {
        return benutzer;
    }
    public Buch getBuch() {
        return buch;
    }
    public LocalDate getAusleihDatum() {
        return ausleihDatum;
    }
    public LocalDate getRueckgabeDatum() {
        return rueckgabeDatum;
    }
    public void setRueckgabeDatum(LocalDate rueckgabeDatum) {
        this.rueckgabeDatum = rueckgabeDatum;
    }

    public int getAusleihID() {
        return ausleihID;
    }

    public void setAusleihID(int ausleihID) {
        this.ausleihID = ausleihID;
    }
}
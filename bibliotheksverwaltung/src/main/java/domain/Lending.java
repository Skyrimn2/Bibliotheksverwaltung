package domain;

import java.time.LocalDate;

public class Lending {
    private int ausleihID;
    private User benutzer;
    private Book buch;
    private LocalDate ausleihDatum;
    private LocalDate rueckgabeDatum;
    
    public Lending(User benutzer, Book buch, LocalDate ausleihDatum, int ausleihID) {
        this.benutzer = benutzer;
        this.buch = buch;
        this.ausleihDatum = ausleihDatum;
        this.ausleihID = ausleihID;
        this.rueckgabeDatum = null;
    }
    public User getUser() {
        return benutzer;
    }
    public Book getBook() {
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
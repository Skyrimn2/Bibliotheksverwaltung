package domain;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.List;

public class Buch {
    private int id;
    private String titel;
    private String autor;
    private boolean verfuegbar;
    private List<Bewertung> bewertungen;

    public Buch(String titel, String autor, int id) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.verfuegbar = true;
        this.bewertungen = new ArrayList();
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public boolean istVerfuegbar() {
        return verfuegbar;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    public void bewertungHinzufuegen(Bewertung bewertung) {
        this.bewertungen.add(bewertung);
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Bewertung> getBewertungen() {
        return bewertungen;
    }

}
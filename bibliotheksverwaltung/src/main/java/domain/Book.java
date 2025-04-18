package domain;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String titel;
    private String autor;
    private boolean verfuegbar;
    private List<Rating> bewertungen;
    private BookCategory kategorie;

    public Book(String titel, String autor, int id) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.verfuegbar = true;
        this.bewertungen = new ArrayList<Rating>();
    }

    public String getTitle() {
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

    public void bewertungHinzufuegen(Rating bewertung) {
        this.bewertungen.add(bewertung);
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Rating> getBewertungen() {
        return bewertungen;
    }

}
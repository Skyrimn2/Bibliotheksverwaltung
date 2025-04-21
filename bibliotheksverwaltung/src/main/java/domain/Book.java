package domain;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String titel;
    private String autor;
    private int availableCopies;
    private BookCategory category;

    public Book(String titel, String autor, int id) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
    }
    
    public Book(String titel, String autor, int id, int available, BookCategory category) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.availableCopies = available;
        this.category = category;
    }

    public String getTitle() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }


    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
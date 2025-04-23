package domain;

public class Book {
    private int id;
    private String titel;
    private String autor;
    private int availableCopies;
    private int copies;
    private BookCategory category;

    public Book(String titel, String autor, int id) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
    }

    public Book(String titel, String autor, int id, int available, BookCategory category, int copies) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.availableCopies = available;
        this.category = category;
        this.copies = copies;
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

    public int getAvailableCopies() {
    	return this.availableCopies;
    }

    public String getCategoryString() {
    	return category.name().replace("_", " ").toLowerCase();
    }

    public int getCopies() {
    	return this.copies;
    }

}
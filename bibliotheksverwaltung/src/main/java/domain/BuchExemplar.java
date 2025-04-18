package domain;

public class BuchExemplar {
    private Book buch;
    private int exemplarId;

    public BuchExemplar(Book buch, int exemplarId) {
        this.buch = buch;
        this.exemplarId = exemplarId;
    }
    
    public Book getBuch() {
        return this.buch;
    }

    public int getExemplarId() {
        return this.exemplarId;
    }
    
    public void setBuch(Book buch) {
        this.buch = buch;
    }

    public void setExemplarId(int exemplarId) {
        this.exemplarId = exemplarId;
    }

}
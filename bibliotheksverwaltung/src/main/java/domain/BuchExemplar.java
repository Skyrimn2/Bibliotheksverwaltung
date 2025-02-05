package domain;

public class BuchExemplar {
    private Buch buch;
    private int exemplarId;

    public BuchExemplar(Buch buch, int exemplarId) {
        this.buch = buch;
        this.exemplarId = exemplarId;
    }
    
    public Buch getBuch() {
        return this.buch;
    }

    public int getExemplarId() {
        return this.exemplarId;
    }
    
    public void setBuch(Buch buch) {
        this.buch = buch;
    }

    public void setExemplarId(int exemplarId) {
        this.exemplarId = exemplarId;
    }

}
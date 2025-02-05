package domain;

public class Mitarbeiter {
    private String name;
    private String mitarbeiterID;

    public Mitarbeiter(String name, String mitarbeiterID) {
        this.name = name;
        this.mitarbeiterID = mitarbeiterID;
    }
    
    public String getName() {
        return this.name;
    }

    public String getMitarbeiterID() {
        return this.mitarbeiterID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMitarbeiterID(String mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

}
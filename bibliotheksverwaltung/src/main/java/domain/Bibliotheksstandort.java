package domain;

public class Bibliotheksstandort {
    private String name;
    private Adresse adresse;
    
    public Bibliotheksstandort(String name, Adresse adresse, String oeffnungszeiten) {
        this.name = name;
        this.adresse = adresse;
    }
    
    public String getName() {
        return this.name;
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
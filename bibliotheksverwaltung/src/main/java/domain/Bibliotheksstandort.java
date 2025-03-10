package domain;

public class Bibliotheksstandort {
    private String name;
    private String adresse;
    private String oeffnungszeiten;
    
    public Bibliotheksstandort(String name, String adresse, String oeffnungszeiten) {
        this.name = name;
        this.adresse = adresse;
        this.oeffnungszeiten = oeffnungszeiten;
    }
    
    // public String getName() {
    //     return this.name;
    // }

    // public Adresse getAdresse() {
    //     return this.adresse;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


public String getName() {
    return name;
}

public String getAdresse() {
    return adresse;
}

public String getOeffnungszeiten() {
    return oeffnungszeiten;
}
}
package domain;

public class LibraryLocation {
    private String name;
    private Adresse adresse;
    private String oeffnungszeiten;
    
    public LibraryLocation(String name, Adresse adresse, String oeffnungszeiten) {
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

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


	public String getName() {
	    return name;
	}
	
	public Adresse getAdresse() {
	    return adresse;
	}
	
	public String getOeffnungszeiten() {
	    return oeffnungszeiten;
}
}
package domain;

public class LibraryLocation {
    private String name;
    private Adress adress;
    private String openingHours;
    
    public LibraryLocation(String name, Adress adress, String openingHours) {
        this.name = name;
        this.adress = adress;
        this.openingHours = openingHours;
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


}
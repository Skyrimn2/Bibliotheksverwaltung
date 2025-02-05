package domain;

import java.util.List;

public class Abteilung {
    private String name;
    private List<Mitarbeiter> mitarbeiterListe;
    public Abteilung(String name, List<Mitarbeiter> mitarbeiterListe) {
        this.name = name;
        this.mitarbeiterListe = mitarbeiterListe;
    }
    // Getter methods
    public String getName() {
        return this.name;
    }
    public List<Mitarbeiter> getMitarbeiterListe() {
        return this.mitarbeiterListe;
    }
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    public void setMitarbeiterListe(List<Mitarbeiter> mitarbeiterListe) {
        this.mitarbeiterListe = mitarbeiterListe;
    }
    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiterListe.add(mitarbeiter);
    }
}
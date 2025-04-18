package domain;

import java.util.List;

public class Abteilung {
    private String name;
    private List<Employee> mitarbeiterListe;
    public Abteilung(String name, List<Employee> mitarbeiterListe) {
        this.name = name;
        this.mitarbeiterListe = mitarbeiterListe;
    }
    // Getter methods
    public String getName() {
        return this.name;
    }
    public List<Employee> getMitarbeiterListe() {
        return this.mitarbeiterListe;
    }
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    public void setMitarbeiterListe(List<Employee> mitarbeiterListe) {
        this.mitarbeiterListe = mitarbeiterListe;
    }
    public void addMitarbeiter(Employee mitarbeiter) {
        this.mitarbeiterListe.add(mitarbeiter);
    }
}
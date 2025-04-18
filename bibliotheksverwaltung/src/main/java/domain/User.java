package domain;

import java.util.List;

public class User {
    private String name;
    private String benutzerPasswort;
    private int benutzerID;
    private List<Buch> ausgelieheneBuecher;
    private Mitgliedschaft mitgliedschaft;

    public User(String name, String benutzerPasswort, int benutzerID, Mitgliedschaft mitgliedschaft) {
        this.name = name;
        this.benutzerID = benutzerID;
        this.benutzerPasswort = benutzerPasswort;
        this.mitgliedschaft = mitgliedschaft;
    }

    public String getName() {
        return name;
    }

    public void buchAusleihen(Buch buch) {
        ausgelieheneBuecher.add(buch);
    }

    public void buchRueckgabe(Buch buch) {
        ausgelieheneBuecher.remove(buch);
    }

    public List<Buch> getAusgelieheneBuecher() {
        return ausgelieheneBuecher;
    }

    public int getbenutzerID() {
        return benutzerID;
    }

    public String getPassworthash() {
        return benutzerPasswort;
    }

    public void setPassworthash(String benutzerPasswort) {
        this.benutzerPasswort = benutzerPasswort;
    }

    public Mitgliedschaft getMitgliedschaft() {
        return mitgliedschaft;
    }

    public void setMitgliedschaft(Mitgliedschaft mitgliedschaft) {
        this.mitgliedschaft = mitgliedschaft;
    }

}
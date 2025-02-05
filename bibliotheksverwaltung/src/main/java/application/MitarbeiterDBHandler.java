package application;

import domain.Mitarbeiter;

public interface MitarbeiterDBHandler {
    public void speichereMitarbeiter(Mitarbeiter mitarbeiter);
    public Mitarbeiter ladeMitarbeiter(int mitarbeiterID);
    public void loescheMitarbeiter(int mitarbeiterID);
    public void aktualisiereMitarbeiter(Mitarbeiter mitarbeiter);
}

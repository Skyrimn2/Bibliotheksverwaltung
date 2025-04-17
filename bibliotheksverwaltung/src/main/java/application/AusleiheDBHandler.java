package application;

import domain.Ausleihe;

public interface AusleiheDBHandler {
    public void speichereAusleihe(Ausleihe ausleihe);
    public Ausleihe ladeAusleihe(int ausleihID);
    public void loescheAusleihe(int ausleihID);
    public void aktualisiereAusleihe(Ausleihe ausleihe);
}
    
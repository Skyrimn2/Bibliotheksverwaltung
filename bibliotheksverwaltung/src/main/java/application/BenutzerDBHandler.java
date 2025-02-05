package application;

import domain.Benutzer;

public interface BenutzerDBHandler {
    public void speichereBenutzer(Benutzer benutzer);

    public Benutzer ladeBenutzer(int benutzerID);
    
    public void loescheBenutzer(int benutzerID);
    
    public void aktualisiereBenutzer(Benutzer benutzer);
}

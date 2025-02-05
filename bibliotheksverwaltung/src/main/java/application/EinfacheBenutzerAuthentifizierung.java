package application;

public class EinfacheBenutzerAuthentifizierung implements BenutzerAuthentifizierung {
    // Harte Koodierung der Benutzerdaten nur für Demonstartionszwecke
    private static final String KORREKTER_BENUTZERNAME = "admin";
    private static final String KORREKTES_PASSWORT = "geheim";

    @Override
    public boolean authentifizieren(String benutzername, String passwort) {
        return benutzername.equals(KORREKTER_BENUTZERNAME) && passwort.equals(KORREKTES_PASSWORT);
    }
}
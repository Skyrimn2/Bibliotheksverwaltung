package application;

public interface BenutzerAuthentifizierung {
    boolean authentifizieren(String benutzername, String passwort);
}
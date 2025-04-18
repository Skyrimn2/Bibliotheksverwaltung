package application;

import domain.User;

public interface UserDBHandler {
    public void saveUser(User benutzer);

    public User saveUser(int benutzerID);
    
    public void loescheBenutzer(int benutzerID);
    
    public void aktualisiereBenutzer(User benutzer);
}

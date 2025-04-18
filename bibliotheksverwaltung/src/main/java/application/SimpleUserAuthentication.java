package application;

public class SimpleUserAuthentication implements UserAuthentication {
    // Harte Koodierung der Benutzerdaten nur für Demonstartionszwecke
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "secret";

    @Override
    public boolean authenticate(String username, String password) {
        return username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD);
    }
}
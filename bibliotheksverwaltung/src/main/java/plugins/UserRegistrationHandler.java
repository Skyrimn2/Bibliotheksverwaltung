package plugins;

import application.DBHandler;
import application.DatabaseException;
import application.ItemNotFoundException;
import application.Registration;
import application.RegistrationHandler;
import application.UserRegistration;
import domain.User;
import domain.UserInterface;

public class UserRegistrationHandler implements RegistrationHandler {
    private final String dbPath;
    private final ConsoleFrontend frontend;

    public UserRegistrationHandler(String dbPath, ConsoleFrontend frontend) {
        this.dbPath = dbPath;
        this.frontend = frontend;
    }

    @Override
    public boolean handleRegistration(String username, String password) {
        try {
            DBHandler<User> db = new UserDB(this.dbPath);
            UserInterface user = null;
            
            try {
                user = db.getItemByString("name", username);
                if (user != null) {
                    return false; // User already exists
                }
            } catch (ItemNotFoundException e) {
             
            }
            
            Registration userReg = new UserRegistration(db);
            boolean success = userReg.register(username, password);
            
            if (success) {
                try {
                    user = db.getItemByString("name", username);
                    this.frontend.setUser(user);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            
            return success;
        } catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
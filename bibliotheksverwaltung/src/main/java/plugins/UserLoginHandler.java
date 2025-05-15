package plugins;

import application.Authentication;
import application.DBHandler;
import application.DatabaseException;
import application.ItemNotFoundException;
import application.LoginHandler;
import application.UserAuthentication;
import domain.User;
import domain.UserInterface;

public class UserLoginHandler implements LoginHandler {
    private final String dbPath;
    private final ConsoleFrontend frontend;

    public UserLoginHandler(String dbPath, ConsoleFrontend frontend) {
        this.dbPath = dbPath;
        this.frontend = frontend;
    }

    @Override
    public boolean handleLogin(String username, String password) {
        try {
            DBHandler<User> db = new UserDB(this.dbPath);
            UserInterface user = null;
            
            try {
                user = db.getItemByString("name", username);
            } catch (ItemNotFoundException e) {
                // User not found
                return false;
            }
            
            if (user == null) {
                return false;
            }
            
            this.frontend.setUser(user);
            Authentication userAuth = new UserAuthentication(db);
            return userAuth.authenticate(username, password);
        } catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }
}

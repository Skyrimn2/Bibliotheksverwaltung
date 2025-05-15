package plugins;

import application.Authentication;
import application.DBHandler;
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
        DBHandler<User> db = new UserDB(this.dbPath);
        UserInterface user = db.getItemByString("name", username);
        
        if (user == null) {
            return false;
        }
        
        this.frontend.setUser(user);
        Authentication userAuth = new UserAuthentication(db);
        return userAuth.authenticate(username, password);
    }
}

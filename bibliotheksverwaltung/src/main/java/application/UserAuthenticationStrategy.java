package application;

import domain.User;
import domain.UserInterface;

/**
 * Concrete strategy for user authentication and registration
 */
public class UserAuthenticationStrategy implements AuthenticationStrategy {
    
    private final DBHandler<User> userDB;
    private final Authentication userAuth;
    private final Registration userReg;
    
    public UserAuthenticationStrategy(DBHandler<User> userDB) {
        this.userDB = userDB;
        this.userAuth = new UserAuthentication(userDB);
        this.userReg = new UserRegistration(userDB);
    }

    @Override
    public UserInterface authenticate(String username, String password) {
        try {
            User user = userDB.getItemByString("name", username);
            if (user == null) {
                return null;
            }
            
            boolean success = userAuth.authenticate(username, password);
            if (success) {
                return new User(user.getName(), user.getID());
            }
            return null;
        } catch (Exception e) {
            System.err.println("Database error during authentication: " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserInterface register(String username, String password) {
        try {
            User existingUser = userDB.getItemByString("name", username);
            if (existingUser != null) {
                System.out.println("User already exists!");
                return null;
            }
            
            boolean success = userReg.register(username, password);
            if (success) {
                User newUser = userDB.getItemByString("name", username);
                return new User(newUser.getName(), newUser.getID());
            }
            return null;
        } catch (Exception e) {
            System.err.println("Database error during registration: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String getDisplayName() {
        return "User";
    }
}
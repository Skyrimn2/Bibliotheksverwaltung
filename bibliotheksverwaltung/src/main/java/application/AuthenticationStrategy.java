package application;

import domain.UserInterface;

/**
 * Strategy interface for different authentication types
 * Replaces switch statement with polymorphism
 */
public interface AuthenticationStrategy {
    
    /**
     * Authenticates a user with given credentials
     * @param username the username
     * @param password the password
     * @return the authenticated user interface or null if authentication fails
     */
    UserInterface authenticate(String username, String password);
    
    /**
     * Registers a new user with given credentials
     * @param username the username
     * @param password the password
     * @return the registered user interface or null if registration fails
     */
    UserInterface register(String username, String password);
    
    /**
     * Gets the display name for this authentication type
     * @return display name
     */
    String getDisplayName();
}
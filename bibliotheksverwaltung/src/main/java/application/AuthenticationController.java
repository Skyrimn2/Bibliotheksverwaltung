package application;

import java.util.HashMap;
import java.util.Map;

import domain.UserInterface;
import plugins.ConsoleDisplay;
import plugins.ConsoleInputHandler;

/**
 * Controller that handles authentication logic using Strategy Pattern
 * Replaces complex switch statement with polymorphic behavior
 */
public class AuthenticationController {
    
    private final Map<Integer, AuthenticationStrategy> loginStrategies;
    private final Map<Integer, AuthenticationStrategy> registerStrategies;
    private final ConsoleDisplay display;
    private final ConsoleInputHandler inputHandler;
    
    public AuthenticationController(ConsoleDisplay display, ConsoleInputHandler inputHandler) {
        this.display = display;
        this.inputHandler = inputHandler;
        this.loginStrategies = new HashMap<>();
        this.registerStrategies = new HashMap<>();
    }
    
    /**
     * Registers authentication strategies
     */
    public void registerStrategies(AuthenticationStrategy userStrategy, 
                                 AuthenticationStrategy employeeStrategy) {
        // Login strategies
        loginStrategies.put(0, userStrategy);
        loginStrategies.put(2, employeeStrategy);
        
        // Registration strategies  
        registerStrategies.put(1, userStrategy);
        registerStrategies.put(3, employeeStrategy);
    }
    
    /**
     * Handles the login process using polymorphism instead of switch statements
     */
    public UserInterface handleLogin() {
        while (true) {
            display.showMessage("Please select:");
            display.showMessage("0\t\tlogin with User");
            display.showMessage("1\t\tregister User");
            display.showMessage("2\t\tlogin as employee");
            display.showMessage("3\t\tregister employee");
            
            int selection = inputHandler.readMenuOption();
            
            display.showMessage("Input username:\t\t");
            String username = inputHandler.readString();
            display.showMessage("Input password:\t\t");
            String password = inputHandler.readString();
            
            UserInterface user = processAuthentication(selection, username, password);
            
            if (user != null) {
                return user;
            } else {
                display.showMessage("Wrong username or password. Try again.\n");
            }
        }
    }
    
    /**
     * Processes authentication using strategy pattern
     */
    private UserInterface processAuthentication(int selection, String username, String password) {
        // Check if it's a login operation
        if (loginStrategies.containsKey(selection)) {
            AuthenticationStrategy strategy = loginStrategies.get(selection);
            return strategy.authenticate(username, password);
        }
        
        // Check if it's a registration operation
        if (registerStrategies.containsKey(selection)) {
            AuthenticationStrategy strategy = registerStrategies.get(selection);
            return strategy.register(username, password);
        }
        
        display.showMessage("Invalid selection. Please try again.");
        return null;
    }
}
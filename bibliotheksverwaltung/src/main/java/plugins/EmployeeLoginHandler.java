package plugins;

import application.Authentication;
import application.DBHandler;
import application.EmployeeAuthentication;
import application.LoginHandler;
import domain.Employee;
import domain.UserInterface;

public class EmployeeLoginHandler implements LoginHandler {
    private final String dbPath;
    private final ConsoleFrontend frontend;

    public EmployeeLoginHandler(String dbPath, ConsoleFrontend frontend) {
        this.dbPath = dbPath;
        this.frontend = frontend;
    }

    @Override
    public boolean handleLogin(String username, String password) {
        DBHandler<Employee> db = new EmployeeDB(this.dbPath);
        UserInterface user = db.getItemByString("name", username);
        
        if (user == null) {
            return false;
        }
        
        this.frontend.setUser(user);
        Authentication empAuth = new EmployeeAuthentication(db);
        return empAuth.authenticate(username, password);
    }
}
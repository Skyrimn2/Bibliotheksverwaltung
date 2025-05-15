package plugins;

import application.DBHandler;
import application.EmployeeRegistration;
import application.Registration;
import application.RegistrationHandler;
import application.DatabaseException;
import application.ItemNotFoundException;
import domain.Employee;
import domain.UserInterface;

public class EmployeeRegistrationHandler implements RegistrationHandler {
    private final String dbPath;
    private final ConsoleFrontend frontend;

    public EmployeeRegistrationHandler(String dbPath, ConsoleFrontend frontend) {
        this.dbPath = dbPath;
        this.frontend = frontend;
    }

    @Override
    public boolean handleRegistration(String username, String password) {
        try {
            DBHandler<Employee> db = new EmployeeDB(this.dbPath);
            UserInterface user = null;
            
            try {
                user = db.getItemByString("name", username);
                if (user != null) {
                    return false;
                }
            } catch (ItemNotFoundException e) {
            }
            
            Registration empReg = (Registration) new EmployeeRegistration(db);
            boolean success = empReg.register(username, password);
            
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
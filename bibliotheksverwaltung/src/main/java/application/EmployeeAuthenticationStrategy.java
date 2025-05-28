package application;

import domain.Employee;
import domain.UserInterface;

/**
 * Concrete strategy for employee authentication and registration
 */
public class EmployeeAuthenticationStrategy implements AuthenticationStrategy {
    
    private final DBHandler<Employee> employeeDB;
    private final Authentication empAuth;
    private final Registration empReg;
    
    public EmployeeAuthenticationStrategy(DBHandler<Employee> employeeDB) {
        this.employeeDB = employeeDB;
        this.empAuth = new EmployeeAuthentication(employeeDB);
        this.empReg = (Registration) new EmployeeRegistration(employeeDB);
    }

    @Override
    public UserInterface authenticate(String username, String password) {
        try {
            Employee employee = employeeDB.getItemByString("name", username);
            if (employee == null) {
                return null;
            }
            
            boolean success = empAuth.authenticate(username, password);
            if (success) {
                return new Employee(employee.getName(), employee.getID());
            }
            return null;
        } catch (Exception e) {
            System.err.println("Database error during employee authentication: " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserInterface register(String username, String password) {
        try {
            Employee existingEmployee = employeeDB.getItemByString("name", username);
            if (existingEmployee != null) {
                System.out.println("Employee already exists!");
                return null;
            }
            
            boolean success = empReg.register(username, password);
            if (success) {
                Employee newEmployee = employeeDB.getItemByString("name", username);
                return new Employee(newEmployee.getName(), newEmployee.getID());
            }
            return null;
        } catch (Exception e) {
            System.err.println("Database error during employee registration: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String getDisplayName() {
        return "Employee";
    }
}
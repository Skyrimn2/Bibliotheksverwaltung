package plugins;

import application.DBHandler;
import application.EmployeeRegistration;
import application.Registration;
import application.RegistrationHandler;
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
        DBHandler<Employee> db = new EmployeeDB(this.dbPath);
        UserInterface user = db.getItemByString("name", username);
        
        if (user != null) {
            return false; // Employee already exists
        }
        
        Registration empReg = new EmployeeRegistration(db);
        boolean success = empReg.register(username, password);
        
        if (success) {
            // After successful registration, retrieve the newly created employee
            user = db.getItemByString("name", username);
            this.frontend.setUser(user);
        }
        
        return success;
    }
}
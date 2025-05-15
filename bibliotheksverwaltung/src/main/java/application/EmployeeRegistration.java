package application;

import java.util.Arrays;

import domain.Employee;

public class EmployeeRegistration implements Authentication {

    private DBHandler<Employee> db;

    public EmployeeRegistration(DBHandler<Employee> db) {
        super();
        this.db = db;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Employee emp_db;
        try {
            emp_db = db.getItemByString("name", username);
        } catch (DatabaseException e) {
            System.err.println("Datenbankfehler bei der Authentifizierung: " + e.getMessage());
            return false;
        }
        
        if(emp_db == null) {
            return false;
        }
        
        byte[] salt = emp_db.getSalt();
        byte[] password_hash = this.hashPassword(password, salt);
        byte[] db_password_hash = null;

        db_password_hash = emp_db.getPassword();

        if (Arrays.equals(password_hash, db_password_hash) && db_password_hash != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
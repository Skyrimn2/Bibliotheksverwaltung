package adapter;

import application.DBHandler;
import domain.Employee;

public class EmployeeRegistration extends BaseRegistration<Employee> {
    
    public EmployeeRegistration(DBHandler<Employee> db) {
        super(db);
    }
    
    @Override
    protected Employee createEntity(String username, byte[] passwordHash, byte[] salt) {
        return new Employee(username, passwordHash, 0, salt);
    }
}
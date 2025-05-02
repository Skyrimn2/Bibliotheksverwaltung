package adapter;

import application.DBHandler;
import domain.Employee;

public class EmployeeAuthentication extends BaseAuthentication<Employee> {
    
    public EmployeeAuthentication(DBHandler<Employee> db) {
        super(db);
    }
    
    @Override
    protected byte[] getSalt(Employee entity) {
        return entity.getSalt();
    }
    
    @Override
    protected byte[] getPassword(Employee entity) {
        return entity.getPassword();
    }
}
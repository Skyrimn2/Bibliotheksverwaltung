package application;

import domain.Employee;

public interface EmployeeDBHandler {
    public void saveEmployee(Employee employee);
    public Employee loadEmployee(int employeeID);
    public void deleteEmployee(int employeeID);
    public void updateEmployee(Employee employee);
}

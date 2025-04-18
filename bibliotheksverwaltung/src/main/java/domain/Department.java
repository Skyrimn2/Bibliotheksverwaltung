package domain;

import java.util.List;

public class Department {
    private String name;
    private List<Employee> employeeList;
    public Department(String name, List<Employee> employeeList) {
        this.name = name;
        this.employeeList = employeeList;
    }
    // Getter methods
    public String getName() {
        return this.name;
    }
    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }
}
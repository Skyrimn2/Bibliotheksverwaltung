package domain;

public class Employee {
    private String name;
    private String employeeID;

    public Employee(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
    }
    
    public String getName() {
        return this.name;
    }

    public String getEmployeeID() {
        return this.employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

}
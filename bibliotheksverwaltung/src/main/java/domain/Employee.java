package domain;

public class Employee {
    private String name;
    private byte[] password;
    private int employeeID;
    private byte[] password_salt;

    public Employee(String name, byte[] password, int employeeID) {
        this.name = name;
        this.employeeID = employeeID;
        this.password = password;
    }
    
    public Employee(String name, byte[] password, int employeeID, byte[] salt) {
        this.name = name;
        this.employeeID = employeeID;
        this.password = password;
        this.password_salt = salt;
    }
    
    public byte[] getPassword() {
    	return this.password;
    }

	public String getName() {
		return this.name;
	}
	
	public byte[] getSalt() {
		return this.password_salt;
	}


}
package domain;

public class Employee implements UserInterface{
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

    public Employee(String name, int employeeID) {
        this.name = name;
        this.employeeID = employeeID;
    }


    public byte[] getPassword() {
    	return this.password;
    }

    @Override
	public String getName() {
		return this.name;
	}

	public byte[] getSalt() {
		return this.password_salt;
	}

	@Override
	public int getUserLevel() {
		return 1;
	}

	@Override
	public int getID() {
		return this.employeeID;
	}

}
package application;

import domain.Employee;

public class EmployeeRegistration implements Registration {

	private DBHandler<Employee> db;

	public EmployeeRegistration(DBHandler<Employee> db){
		super();
		this.db = db;
	}

	@Override
	public boolean register(String username, String password) {
		byte[] salt = this.generateSalt();
		byte[] password_hash = this.hashPassword(password, salt);
		Employee emp = new Employee(username, password_hash, 0, salt);

		db.saveItem(emp);

		return true;

	}

}

package adapter;



import application.DBHandler;
import application.Registration;
import domain.User;

public class UserRegistration implements Registration {

	private DBHandler<User> db;
	
	public UserRegistration(DBHandler<User> db){
		super();
		this.db = db;
	}
	
	@Override
	public boolean register(String username, String password) {
		byte[] password_hash = this.hashPassword(password);
		User user = new User(username, password_hash, 0, null);
		
		db.saveItem(user);
		
		return true;
		
	}

}

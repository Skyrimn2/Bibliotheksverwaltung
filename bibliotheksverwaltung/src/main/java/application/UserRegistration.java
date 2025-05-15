package application;



import domain.User;

public class UserRegistration implements Registration {

	private DBHandler<User> db;

	public UserRegistration(DBHandler<User> db){
		super();
		this.db = db;
	}

	@Override
	public boolean register(String username, String password) {
	    try {
	        byte[] salt = this.generateSalt();
	        byte[] password_hash = this.hashPassword(password, salt);
	        User user = new User(username, password_hash, salt);

	        db.saveItem(user);

	        return true;
	    } catch (DatabaseException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}

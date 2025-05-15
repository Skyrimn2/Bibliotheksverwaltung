package application;


import java.util.Arrays;

import domain.User;


public class UserAuthentication implements Authentication {

	DBHandler<User> db;

	public UserAuthentication(DBHandler<User> db) {
		super();
		this.db = db;
	}

	@Override
	public boolean authenticate(String username, String password) {
	    try {
	        User user_db = db.getItemByString("name", username);
	        if(user_db == null) {
	            return false;
	        }
	        byte[] salt = user_db.getSalt();
	        byte[] password_hash = this.hashPassword(password, salt);
	        byte[] db_password_hash = null;

	        db_password_hash = user_db.getPassword();

	        if (Arrays.equals(password_hash, db_password_hash) && db_password_hash != null) {
	            return true;
	        }
	        else {
	            return false;
	        }
	    } catch (DatabaseException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}

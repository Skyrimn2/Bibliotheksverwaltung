package adapter;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import application.Authentication;
import application.DBHandler;
import domain.User;


public class UserAuthentication implements Authentication {
	
	DBHandler<User> db;
	
	public UserAuthentication(DBHandler<User> db) {
		super();
		this.db = db;
	}

	@Override
	public boolean authenticate(String username, String password) {
		User user_db = db.getItemByString("name", username);
		if(user_db == null) {
			return false;
		}
		byte[] password_hash = null;
		byte[] db_password_hash = null;
		
		password_hash = this.hashPassword(password);
		db_password_hash = user_db.getPassword();

		
		if (Arrays.equals(password_hash, db_password_hash) && db_password_hash != null) {
			return true;
		}
		else {
			return false;
		}
		
	}

}

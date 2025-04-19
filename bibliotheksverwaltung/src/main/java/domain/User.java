package domain;

import java.util.List;

public class User {
    private String name;
    private byte[] password;
    private int userID;
    private List<Book> loanBooks;
    private Membership membership;
    private byte[] password_salt;

    public User(String name, byte[] password, int userID, Membership mempership) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.membership = mempership;
    }
    
    public User(String name, byte[] password, int userID, Membership mempership, byte[] salt) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.membership = mempership;
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
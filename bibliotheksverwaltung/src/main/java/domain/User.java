package domain;

import java.util.List;

public class User implements UserInterface{
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
    
    public User(String name, int userID, Membership membership) {
    	this.name = name;
    	this.userID = userID;
    	this.membership = membership;
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
	public int getID() {
		return this.userID;
	}

	@Override
	public int getUserLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Membership getMembership() {
		return this.membership;
	}

}
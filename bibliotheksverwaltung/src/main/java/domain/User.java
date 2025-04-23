package domain;

import java.util.List;

public class User implements UserInterface{
    private String name;
    private byte[] password;
    private int userID;
    private List<Book> loanBooks;
    private byte[] password_salt;

    public User(String name, byte[] password, int userID) {
        this.name = name;
        this.userID = userID;
        this.password = password;
    }

    public User(String name, byte[] password, int userID, byte[] salt) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.password_salt = salt;
    }

    public User(String name, byte[] password, byte[] salt) {
        this.name = name;
        this.password = password;
        this.password_salt = salt;
    }

    public User(String name, int userID) {
    	this.name = name;
    	this.userID = userID;
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


}
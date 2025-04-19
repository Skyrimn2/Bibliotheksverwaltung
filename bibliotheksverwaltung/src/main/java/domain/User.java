package domain;

import java.util.List;

public class User {
    private String name;
    private byte[] password;
    private int userID;
    private List<Book> loanBooks;
    private Membership membership;

    public User(String name, byte[] password, int userID, Membership mempership) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.membership = mempership;
    }
    
    public byte[] getPassword() {
    	return this.password;
    }


}
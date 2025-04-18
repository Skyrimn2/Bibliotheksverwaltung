package domain;

import java.util.List;

public class User {
    private String name;
    private String password;
    private int userID;
    private List<Book> loanBooks;
    private Mempership membership;

    public User(String name, String password, int userID, Mempership mempership) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.membership = mempership;
    }


}
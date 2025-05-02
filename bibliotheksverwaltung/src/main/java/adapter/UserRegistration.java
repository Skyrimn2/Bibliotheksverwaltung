package adapter;

import application.DBHandler;
import domain.User;

public class UserRegistration extends BaseRegistration<User> {
    
    public UserRegistration(DBHandler<User> db) {
        super(db);
    }
    
    @Override
    protected User createEntity(String username, byte[] passwordHash, byte[] salt) {
        return new User(username, passwordHash, salt);
    }
}
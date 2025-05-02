package adapter;

import application.DBHandler;
import domain.User;

public class UserAuthentication extends BaseAuthentication<User> {
    
    public UserAuthentication(DBHandler<User> db) {
        super(db);
    }
    
    @Override
    protected byte[] getSalt(User entity) {
        return entity.getSalt();
    }
    
    @Override
    protected byte[] getPassword(User entity) {
        return entity.getPassword();
    }
}
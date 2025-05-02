package adapter;

import java.util.Arrays;

import application.Authentication;
import application.DBHandler;
import application.SecurityUtils;
import domain.UserInterface;

public abstract class BaseAuthentication<T extends UserInterface> implements Authentication {
    
    protected DBHandler<T> db;
    
    public BaseAuthentication(DBHandler<T> db) {
        this.db = db;
    }
    
    @Override
    public boolean authenticate(String username, String password) {
        T entity = db.getItemByString("name", username);
        if (entity == null) {
            return false;
        }
        
        byte[] salt = getSalt(entity);
        if (salt == null) {
            return false;
        }
        
        byte[] passwordHash = SecurityUtils.hashPassword(password, salt);
        byte[] dbPasswordHash = getPassword(entity);
        
        if (dbPasswordHash == null) {
            return false;
        }
        
        return Arrays.equals(passwordHash, dbPasswordHash);
    }
    
    protected abstract byte[] getSalt(T entity);
    
    protected abstract byte[] getPassword(T entity);
}
package adapter;

import application.DBHandler;
import application.Registration;
import application.SecurityUtils;
import domain.UserInterface;

public abstract class BaseRegistration<T extends UserInterface> implements Registration {
    
    protected DBHandler<T> db;
    
    public BaseRegistration(DBHandler<T> db) {
        this.db = db;
    }
    
    @Override
    public boolean register(String username, String password) {
        // Eingabevalidierung
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        
        // Prüfen, ob der Benutzername bereits existiert
        T existingEntity = db.getItemByString("name", username);
        if (existingEntity != null) {
            return false;
        }
        
        // Benutzer erstellen und speichern
        byte[] salt = SecurityUtils.generateSalt();
        byte[] passwordHash = SecurityUtils.hashPassword(password, salt);
        
        T entity = createEntity(username, passwordHash, salt);
        db.saveItem(entity);
        
        return true;
    }
    
    protected abstract T createEntity(String username, byte[] passwordHash, byte[] salt);
}
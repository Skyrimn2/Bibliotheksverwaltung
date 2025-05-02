package application;

public interface Authentication {

    boolean authenticate(String username, String password);
    
    @Deprecated
    default byte[] hashPassword(String password, byte[] salt) {
        return SecurityUtils.hashPassword(password, salt);
    }
    
    @Deprecated
    default byte[] generateSalt() {
        return SecurityUtils.generateSalt();
    }
}
package application;

public interface Authentication {
    boolean authenticate(String username, String password);
}
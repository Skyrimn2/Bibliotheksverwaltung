package application;

public class ItemNotFoundException extends DatabaseException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
package application;

import java.util.List;

public interface DBHandler<T> {
    T loadItemByID(int id) throws DatabaseException;
    void saveItem(T item) throws DatabaseException;
    void updateItemByID(T item, int id) throws DatabaseException;
    List<T> loadAllOfItem() throws DatabaseException;
    T getItemByString(String column, String value) throws DatabaseException;
    List<T> getItemsByString(String column, String value) throws DatabaseException;
}
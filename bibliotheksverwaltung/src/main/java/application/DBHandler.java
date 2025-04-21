package application;

import java.util.List;

public interface DBHandler<T> {

	
	public T loadItemByID(int id);
	public void saveItem(T item);
	public void updateItemByID(T item, int id);
	public List<T> loadAllOfItem();
	public T getItemByString(String column, String value);
	public List<T> getItemsByString(String column, String value);
}

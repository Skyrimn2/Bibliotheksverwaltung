package application;

import java.util.List;

public interface DBHandler<T> {

	public T loadItem();
	public T saveItem();
	public T updateItem();
	public List<T> loadAllOfItem();
}

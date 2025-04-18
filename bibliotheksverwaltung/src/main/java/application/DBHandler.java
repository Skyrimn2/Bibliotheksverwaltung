package application;

public interface DBHandler<T> {

	public T loadItem();
	public T saveItem();
	public T updateItem();
	public T loadAllOfItem();
}

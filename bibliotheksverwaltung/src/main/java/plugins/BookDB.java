package plugins;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import application.DBHandler;
import domain.Book;

public class BookDB implements DBHandler<Book> {

	String dbPath;
	
	public BookDB(String dbPath) {
		super();
		this.dbPath = dbPath;
	}
	
	private Connection conn() throws SQLException {
		
		String db = "jdbc:sqlite:";
		String connectionstring = db + this.dbPath;
		
		Connection conn = DriverManager.getConnection(connectionstring);
		return conn;
	}
	
	@Override
	public Book loadItemByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveItem(Book item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemByID(Book item, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> loadAllOfItem() {
		
		List<Book> books = new ArrayList<Book>();
		try {	
			Connection conn = this.conn();
			
			String sql = "SELECT * FROM Books;";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
						
			while(result.next()) {
				int ID = result.getInt("BookID");
				String title = result.getString("Titel");
				String author = result.getString("Autor");
				
				books.add(new Book(title, author, ID));
			}
			
			conn.close();
			
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
			return books;
		}
	}

	
}

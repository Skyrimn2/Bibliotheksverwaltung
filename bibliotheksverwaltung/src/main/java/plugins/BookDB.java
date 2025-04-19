package plugins;

import java.util.ArrayList;
import java.util.List;

import adapter.DBHandlerConnection;

import java.sql.*;

import application.DBHandler;
import domain.Book;

public class BookDB extends DBHandlerConnection<Book> {

	public BookDB(String indbPath) {
		super(indbPath);
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
				String title = result.getString("Title");
				String author = result.getString("Author");
				
				books.add(new Book(title, author, ID));
			}
			
			conn.close();
			
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
			return books;
		}
	}


	@Override
	public Book getItemByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

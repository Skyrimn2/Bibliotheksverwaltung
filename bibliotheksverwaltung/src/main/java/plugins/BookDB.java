package plugins;

import java.util.ArrayList;
import java.util.List;

import adapter.DBHandlerConnection;

import java.sql.*;

import application.DBHandler;
import domain.Book;
import domain.BookCategory;
import domain.User;

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
				
				books.add(new Book(result.getString("Title"), result.getString("Author"), result.getInt("BookID"), this.getAvailableCopies(conn, result.getInt("BookID")), BookCategory.valueOf(result.getString("Category")), this.getCopies(conn, result.getInt("BookID"))));
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
		if (!column.equals("Title") && !column.equals("title") && !column.equals("Author") && !column.equals("Author")) {
			throw new IllegalArgumentException("Ungültiger Spaltenname");
		}
		
		try {
			String sql = "SELECT * FROM BOOKS WHERE " + column + " = ?";

			
			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			ResultSet result = pstmt.executeQuery();
			
			if (!result.next()) {
				return null;
			}

			
			Book book = new Book(result.getString("Title"), result.getString("Author"), result.getInt("BookID"), this.getAvailableCopies(conn, result.getInt("BookID")), BookCategory.valueOf(result.getString("Category")), this.getCopies(conn, result.getInt("BookID")));
			conn.close();
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}


	@Override
	public List<Book> getItemsByString(String column, String value) {
		if (!column.equals("Title") && !column.equals("title") && !column.equals("Author") && !column.equals("Author")) {
			throw new IllegalArgumentException("Ungültiger Spaltenname");
		}
		
		try {
			String sql = "SELECT * FROM BOOKS WHERE " + column + " LIKE ?";

			
			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + value + "%");
			ResultSet result = pstmt.executeQuery();
			
			List<Book> books = new ArrayList<Book>();

			while (result.next()) {	
				Book book = new Book(result.getString("Title"), result.getString("Author"), result.getInt("BookID"), this.getAvailableCopies(conn, result.getInt("BookID")), BookCategory.valueOf(result.getString("Category")), this.getCopies(conn, result.getInt("BookID")));
				books.add(book);
			}

			conn.close();
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private int getAvailableCopies(Connection conn, int BookID) {
		try {
			String sql = "SELECT COUNT(*) as available FROM BOOKCOPIES WHERE BookID = ? AND IsAvailable = 1";

			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BookID);
			ResultSet result = pstmt.executeQuery();
			
			return result.getInt("available");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;	
		
	}
	
	private int getCopies(Connection conn, int BookID) {
		try {
			String sql = "SELECT COUNT(*) as available FROM BOOKCOPIES WHERE BookID = ?";

			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BookID);
			ResultSet result = pstmt.executeQuery();
			
			return result.getInt("available");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;	
		
	}

	
}

package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adapter.DBHandlerConnection;
import domain.Book;
import domain.BookCopy;

public class BookCopyDB extends DBHandlerConnection<BookCopy> {

	public BookCopyDB(String indbPath) {
		super(indbPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BookCopy loadItemByID(int id) {
		try {
			String sql = "SELECT * FROM BOOKCOPIES WHERE CopyID = ?";

			
			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			
			if (!result.next()) {
				return null;
			}

			
			BookCopy copy = new BookCopy(this.getBook(result.getInt("BookID")), result.getInt("CopyID"));
			conn.close();
			return copy;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveItem(BookCopy item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemByID(BookCopy item, int id) {
	    String sql = "UPDATE BOOKCOPIES SET BookID = ?, IsAvailable = ? WHERE CopyID = ?";
	    
	    try (Connection conn = this.conn(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, item.getBook().getId());
	        pstmt.setBoolean(2, item.isAvailable());
	        pstmt.setInt(3, id);
	        
	        pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public List<BookCopy> loadAllOfItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookCopy getItemByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCopy> getItemsByString(String column, String value) {
	    if (!column.equals("BookID")) {
	        throw new IllegalArgumentException("Ungültiger Spaltenname. Nur 'BookID' erlaubt.");
	    }
	    
	    try {
	        String sql = "SELECT * FROM BOOKCOPIES WHERE " + column + " = ?";

	        Connection conn = this.conn();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, Integer.parseInt(value)); 
	        ResultSet result = pstmt.executeQuery();

	        List<BookCopy> copies = new ArrayList<>();
	        
	        while (result.next()) {
	            BookCopy copy = new BookCopy(this.getBook(result.getInt("BookID")), result.getInt("CopyID"), result.getBoolean("IsAvailable"));
	            copies.add(copy);
	        }

	        conn.close();
	        return copies;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	
	private Book getBook(int id) {
		BookDB bookDB = new BookDB(dbPath);
		return bookDB.loadItemByID(id);
	}

}

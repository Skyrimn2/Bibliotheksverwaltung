package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = "SELECT * FROM BOOKS WHERE CopyID = ?";

			
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}
	
	private Book getBook(int id) {
		BookDB bookDB = new BookDB(dbPath);
		return bookDB.loadItemByID(id);
	}

}

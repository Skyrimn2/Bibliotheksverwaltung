package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import adapter.DBHandlerConnection;
import domain.Book;
import domain.BookCategory;
import domain.BookCopy;
import domain.Lending;
import domain.User;

public class LendingDB extends DBHandlerConnection<Lending> {

	public LendingDB(String dbPath) {
		super(dbPath);
	}

	@Override
	public Lending loadItemByID(int id) {
		try {
			String sql = "SELECT * FROM LENDINGS WHERE LendingID = ?";

			
			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			
			if (!result.next()) {
				return null;
			}

			
			Lending lending = new Lending(this.getUser(result.getInt("UserID")), this.getBookBopy(result.getInt("CopyID")), result.getTimestamp("LendingDate"), result.getInt("LendingID"));
			conn.close();
			return lending;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveItem(Lending item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemByID(Lending item, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lending> loadAllOfItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lending getItemByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lending> getItemsByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private User getUser(int id) {
		UserDB userDB = new UserDB(this.dbPath);
		return userDB.loadItemByID(id);
	}
	
	private BookCopy getBookBopy(int id) {
		BookCopyDB copyDB = new BookCopyDB(dbPath);
		return copyDB.loadItemByID(id);
	}

}

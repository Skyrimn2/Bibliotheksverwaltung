package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import adapter.DBHandlerConnection;
import application.DatabaseException;
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
	        String sql = "SELECT * FROM LENDING WHERE LendingID = ?";
	        Connection conn = this.conn();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        ResultSet result = pstmt.executeQuery();

	        if (!result.next()) {
	            conn.close();
	            return null;
	        }

	        User user = getUser(result.getInt("UserID"));
	        BookCopy copy = getBookBopy(result.getInt("CopyID"));
	        Timestamp lendingDate = result.getTimestamp("LendingDate");
	        Timestamp returnDate = result.getTimestamp("ReturnDate");
	        int lendingID = result.getInt("LendingID");

	        conn.close();
	        return new Lending(user, copy, lendingDate, lendingID, returnDate);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (DatabaseException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void saveItem(Lending item) {
	    String sql = "INSERT INTO LENDING (UserID, CopyID, LendingDate, ReturnDate) VALUES (?, ?, ?, ?)";

	    try (Connection conn = this.conn(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, item.getUser().getID());
	        pstmt.setInt(2, item.getBookCopy().getCopyID());
	        pstmt.setTimestamp(3, item.getLendingDate());

	        if (item.getReturnDate() != null) {
	            pstmt.setTimestamp(4, item.getReturnDate());
	        } else {
	            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
	        }

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (DatabaseException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateItemByID(Lending item, int id) {
	    String sql = "UPDATE LENDING SET UserID = ?, CopyID = ?, LendingDate = ?, ReturnDate = ? WHERE LendingID = ?";

	    try (Connection conn = this.conn(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, item.getUser().getID());
	        pstmt.setInt(2, item.getBookCopy().getCopyID());
	        pstmt.setTimestamp(3, item.getLendingDate());

	        if (item.getReturnDate() != null) {
	            pstmt.setTimestamp(4, item.getReturnDate());
	        } else {
	            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
	        }

	        pstmt.setInt(5, id);

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (DatabaseException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Lending> loadAllOfItem() {
	    List<Lending> lendings = new java.util.ArrayList<>();

	    String sql = "SELECT * FROM LENDING";

	    try (Connection conn = this.conn(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        ResultSet result = pstmt.executeQuery();

	        while (result.next()) {
	            User user = getUser(result.getInt("UserID"));
	            BookCopy copy = getBookBopy(result.getInt("CopyID"));
	            Timestamp lendingDate = result.getTimestamp("LendingDate");
	            Timestamp returnDate = result.getTimestamp("ReturnDate");
	            int lendingID = result.getInt("LendingID");

	            lendings.add(new Lending(user, copy, lendingDate, lendingID, returnDate));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (DatabaseException e) {
	        e.printStackTrace();
	    }

	    return lendings;
	}

	@Override
	public Lending getItemByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lending> getItemsByString(String column, String value) {
	    List<Lending> lendings = new java.util.ArrayList<>();

	    List<String> allowedColumns = List.of("UserID", "CopyID", "userID", "copyID");

	    if (!allowedColumns.contains(column)) {
	        System.err.println("Invalid column name: " + column);
	        return lendings;
	    }

	    String sql = "SELECT * FROM LENDING WHERE " + column + " = ?";

	    try (Connection conn = this.conn(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, value);
	        ResultSet result = pstmt.executeQuery();

	        while (result.next()) {
	            User user = getUser(result.getInt("UserID"));
	            BookCopy copy = getBookBopy(result.getInt("CopyID"));
	            Timestamp lendingDate = result.getTimestamp("LendingDate");
	            Timestamp returnDate = result.getTimestamp("ReturnDate");
	            int lendingID = result.getInt("LendingID");

	            lendings.add(new Lending(user, copy, lendingDate, lendingID, returnDate));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (DatabaseException e) {
	        e.printStackTrace();
	    }

	    return lendings;
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

package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import adapter.DBHandlerConnection;
import application.DatabaseException;
import application.ItemNotFoundException;
import domain.User;

public class UserDB extends DBHandlerConnection<User>{

	public UserDB(String dbPath) {
		super(dbPath);
	}

	@Override
	public User loadItemByID(int id) {
		try {

			String sql = "SELECT * FROM USERS WHERE ID = ?";

			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();

			if (!result.next()) {
				return null;
			}


			User user = new User(result.getString("Name"), result.getBytes("Password"), result.getInt("ID"));
			conn.close();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveItem(User item) {
		try {

			String sql = "INSERT INTO USERS(name, password, salt) VALUES (?, ?, ?)";

			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getName());
			pstmt.setBytes(2, item.getPassword());
			pstmt.setBytes(3, item.getSalt());
			pstmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateItemByID(User item, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> loadAllOfItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getItemByString(String column, String value) throws DatabaseException {
	    try {
	        if (!column.equals("name") && !column.equals("id") && !column.equals("membership_id")) {
	            throw new IllegalArgumentException("Ungültiger Spaltenname");
	        }

	        String sql = "SELECT * FROM USERS WHERE " + column + " = ?";

	        Connection conn = this.conn();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, value);
	        ResultSet result = pstmt.executeQuery();

	        if (!result.next()) {
	            throw new ItemNotFoundException("Kein Benutzer mit " + column + " = " + value + " gefunden");
	        }

	        User user = new User(
	            result.getString("Name"), 
	            result.getBytes("Password"), 
	            result.getInt("ID"), 
	            result.getBytes("salt")
	        );
	        conn.close();
	        return user;
	    } catch (SQLException e) {
	        throw new DatabaseException("Fehler beim Suchen des Benutzers: " + e.getMessage(), e);
	    }
	}

	@Override
	public List<User> getItemsByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

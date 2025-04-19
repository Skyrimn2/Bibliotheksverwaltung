package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import adapter.DBHandlerConnection;
import domain.Membership;
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

			
			User user = new User(result.getString("Name"), result.getBytes("Password"), result.getInt("ID"), this.readMembership(conn, result.getInt("membership_id")));
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
		// TODO Auto-generated method stub
		
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
	
	private Membership readMembership(Connection conn, int id) {
		String sql = "SELECT * FROM MEMBERSHIP WHERE ID = ?";
		
		try {
			Membership mem;
			PreparedStatement pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Membership(result.getTimestamp("StartDate"), result.getTimestamp("EndDate"), result.getInt("ID"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getItemByString(String column, String value) {
		try {
			
			String sql = "SELECT * FROM USERS WHERE ? = ?";
			
			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, column);
			pstmt.setString(2, value);
			ResultSet result = pstmt.executeQuery();
			
			if (!result.next()) {
				return null;
			}

			
			User user = new User(result.getString("Name"), result.getBytes("Password"), result.getInt("ID"), this.readMembership(conn, result.getInt("membership_id")));
			conn.close();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

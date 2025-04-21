package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import adapter.DBHandlerConnection;
import domain.Membership;
import domain.User;

public class MembershipDB extends DBHandlerConnection<Membership> {

	public MembershipDB(String indbPath) {
		super(indbPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Membership loadItemByID(int id) {
		try {
			
			String sql = "SLECT * FROM MEMBERSHIP WHERE ID = ?";
			
			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(0, Integer.toString(id));
			ResultSet result = pstmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			
			Membership mem = new Membership(result.getTimestamp("StartDate"), result.getTimestamp("EndDate"), result.getInt("ID"));
			return mem;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveItem(Membership item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemByID(Membership item, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Membership> loadAllOfItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Membership getItemByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Membership> getItemsByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}

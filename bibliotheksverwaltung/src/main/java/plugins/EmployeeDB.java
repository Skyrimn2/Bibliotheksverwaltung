package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import adapter.DBHandlerConnection;
import domain.Employee;

public class EmployeeDB extends DBHandlerConnection<Employee> {

	public EmployeeDB(String indbPath) {
		super(indbPath);
	}

	@Override
	public Employee loadItemByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveItem(Employee item) {
		try {

			String sql = "INSERT INTO EMPLOYEES(name, password, salt) VALUES (?, ?, ?)";

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
	public void updateItemByID(Employee item, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> loadAllOfItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getItemByString(String column, String value) {
		try {

			if (!column.equals("name") && !column.equals("id")) {
		        throw new IllegalArgumentException("Ungültiger Spaltenname");
		    }

		    String sql = "SELECT * FROM USERS WHERE " + column + " = ?";

			Connection conn = this.conn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			ResultSet result = pstmt.executeQuery();

			if (!result.next()) {
				return null;
			}


			Employee emp = new Employee(result.getString("name"), result.getBytes("password"), result.getInt("id"), result.getBytes("salt"));
			conn.close();
			return emp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getItemsByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}

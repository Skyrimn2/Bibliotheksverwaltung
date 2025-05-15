package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adapter.DBHandlerConnection;
import application.DatabaseException;
import domain.Employee;

public class EmployeeDB extends DBHandlerConnection<Employee> {

    public EmployeeDB(String indbPath) {
        super(indbPath);
    }

    @Override
    public Employee loadItemByID(int id) {
        try {
            String sql = "SELECT * FROM EMPLOYEES WHERE id = ?";
            Connection conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            if (!result.next()) {
                conn.close();
                return null;
            }

            Employee emp = new Employee(
                result.getString("name"),
                result.getBytes("password"),
                result.getInt("id"),
                result.getBytes("salt")
            );
            conn.close();
            return emp;
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
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
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItemByID(Employee item, int id) {
        try {
            String sql = "UPDATE EMPLOYEES SET name = ?, password = ?, salt = ? WHERE id = ?";
            Connection conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setBytes(2, item.getPassword());
            pstmt.setBytes(3, item.getSalt());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> loadAllOfItem() {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EMPLOYEES";
            Connection conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                Employee emp = new Employee(
                    result.getString("name"),
                    result.getBytes("password"),
                    result.getInt("id"),
                    result.getBytes("salt")
                );
                employees.add(emp);
            }
            conn.close();
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee getItemByString(String column, String value) {
        try {
            if (!column.equals("name") && !column.equals("id")) {
                throw new IllegalArgumentException("Ungültiger Spaltenname");
            }

            String sql = "SELECT * FROM EMPLOYEES WHERE " + column + " = ?";
            Connection conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, value);
            ResultSet result = pstmt.executeQuery();

            if (!result.next()) {
                conn.close();
                return null;
            }

            Employee emp = new Employee(
                result.getString("name"),
                result.getBytes("password"),
                result.getInt("id"),
                result.getBytes("salt")
            );
            conn.close();
            return emp;
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getItemsByString(String column, String value) {
        List<Employee> employees = new ArrayList<>();
        try {
            if (!column.equals("name") && !column.equals("id")) {
                throw new IllegalArgumentException("Ungültiger Spaltenname");
            }

            String sql = "SELECT * FROM EMPLOYEES WHERE " + column + " LIKE ?";
            Connection conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + value + "%");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                Employee emp = new Employee(
                    result.getString("name"),
                    result.getBytes("password"),
                    result.getInt("id"),
                    result.getBytes("salt")
                );
                employees.add(emp);
            }
            conn.close();
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
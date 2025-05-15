package plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adapter.DBHandlerConnection;
import application.DatabaseException;
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
                conn.close();
                return null;
            }

            User user = new User(
                result.getString("Name"), 
                result.getBytes("Password"), 
                result.getInt("ID"), 
                result.getBytes("salt")
            );
            conn.close();
            return user;
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveItem(User item) throws DatabaseException {
        Connection conn = null;
        try {
            String sql = "INSERT INTO USERS(name, password, salt) VALUES (?, ?, ?)";

            conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setBytes(2, item.getPassword());
            pstmt.setBytes(3, item.getSalt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Speichern des Benutzers: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateItemByID(User item, int id) throws DatabaseException {
        Connection conn = null;
        try {
            String sql = "UPDATE USERS SET name = ?, password = ?, salt = ? WHERE id = ?";
            
            conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setBytes(2, item.getPassword());
            pstmt.setBytes(3, item.getSalt());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Aktualisieren des Benutzers: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> loadAllOfItem() throws DatabaseException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            String sql = "SELECT * FROM USERS";
            
            conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            
            while (result.next()) {
                User user = new User(
                    result.getString("Name"), 
                    result.getBytes("Password"), 
                    result.getInt("ID"), 
                    result.getBytes("salt")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Laden der Benutzer: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public User getItemByString(String column, String value) throws DatabaseException {
        Connection conn = null;
        try {
            if (!column.equals("name") && !column.equals("id") && !column.equals("membership_id")) {
                throw new IllegalArgumentException("Ungültiger Spaltenname");
            }

            String sql = "SELECT * FROM USERS WHERE " + column + " = ?";

            conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, value);
            ResultSet result = pstmt.executeQuery();

            if (!result.next()) {
                return null;
            }

            User user = new User(
                result.getString("Name"), 
                result.getBytes("Password"), 
                result.getInt("ID"), 
                result.getBytes("salt")
            );
            return user;
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Suchen des Benutzers: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getItemsByString(String column, String value) throws DatabaseException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            if (!column.equals("name") && !column.equals("id") && !column.equals("membership_id")) {
                throw new IllegalArgumentException("Ungültiger Spaltenname");
            }

            String sql = "SELECT * FROM USERS WHERE " + column + " LIKE ?";

            conn = this.conn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + value + "%");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                User user = new User(
                    result.getString("Name"), 
                    result.getBytes("Password"), 
                    result.getInt("ID"), 
                    result.getBytes("salt")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Suchen der Benutzer: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }
}
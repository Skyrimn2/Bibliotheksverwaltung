package adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    
    private DBUtils() {
        // Private Konstruktor, um Instanziierung zu verhindern
    }
    
    public static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static ResultSet executeQuery(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        return stmt.executeQuery();
    }
    
    public static int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            
            return stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
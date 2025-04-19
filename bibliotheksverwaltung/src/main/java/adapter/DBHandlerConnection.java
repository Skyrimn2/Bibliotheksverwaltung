package adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.DBHandler;

public abstract class DBHandlerConnection<T> implements DBHandler<T> {

	protected String dbPath;
	
	public DBHandlerConnection(String indbPath) {
		super();
		this.dbPath = indbPath;
	}
	
	protected Connection conn() throws SQLException {
		
		String db = "jdbc:sqlite:";
		String connectionstring = db + this.dbPath;
		
		Connection conn = DriverManager.getConnection(connectionstring);
		return conn;
	}
}

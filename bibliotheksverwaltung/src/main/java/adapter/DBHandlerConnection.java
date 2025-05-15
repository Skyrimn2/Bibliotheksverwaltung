package adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.DBHandler;
import application.DatabaseException;

public abstract class DBHandlerConnection<T> implements DBHandler<T> {

	protected String dbPath;

	public DBHandlerConnection(String indbPath) {
		super();
		this.dbPath = indbPath;
	}

	protected Connection conn() throws DatabaseException {
	    try {
	        String db = "jdbc:sqlite:";
	        String connectionstring = db + this.dbPath;

	        return DriverManager.getConnection(connectionstring);
	    } catch (SQLException e) {
	        throw new DatabaseException("Verbindung zur Datenbank konnte nicht hergestellt werden: " + e.getMessage(), e);
	    }
	}
}

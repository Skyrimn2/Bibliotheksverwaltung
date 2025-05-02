package adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.DBHandler;

public abstract class DBHandlerConnection<T> implements DBHandler<T> {

    protected String dbPath;

    public DBHandlerConnection(String indbPath) {
        if (indbPath == null || indbPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Datenbankpfad darf nicht leer sein");
        }
        this.dbPath = indbPath;
    }

    /**
     * Stellt eine Verbindung zur Datenbank her
     * @return Die Datenbankverbindung
     * @throws SQLException Bei Verbindungsfehlern
     */
    protected Connection conn() throws SQLException {
        String db = "jdbc:sqlite:";
        String connectionstring = db + this.dbPath;

        return DriverManager.getConnection(connectionstring);
    }
    
    @Override
    public List<T> loadAllOfItem() {
        return new ArrayList<>();
    }
    
    @Override
    public List<T> getItemsByString(String column, String value) {
        return new ArrayList<>();
    }
}
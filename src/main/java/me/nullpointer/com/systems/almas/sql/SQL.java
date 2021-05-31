package me.nullpointer.com.systems.almas.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {
    public SQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:plugins/PAtlantis/storage.db");
            Statement stmt = c.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS Almas (Player TEXT, Almas INTEGER)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Paid (Player TEXT, Paid TEXT)");
            c.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:plugins/PAtlantis/storage.db");
    }
}

package me.nullpointer.com.systems.almas.utils;

import me.nullpointer.com.PAtlantis;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Almas {
    public static HashMap<Player, Integer> almas = new HashMap<>();

    public static int getAlmas(Player p) throws SQLException {
        int i = 0;
        if (almas.containsKey(p))
            return almas.get(p);
        Connection c = PAtlantis.sql.getNewConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Almas FROM Almas WHERE Player='" + p.getName() + "'");
        if (rs.next()) {
            i = Integer.parseInt(rs.getString("Almas"));
            almas.put(p, i);
        } else {
            almas.put(p, 0);
        }
        c.close();
        stmt.close();
        rs.close();
        return almas.get(p);
    }

    public static void setAlmas(Player p, int almas) throws SQLException {
        Connection c = PAtlantis.sql.getNewConnection();
        Statement stmt = c.createStatement();
        Statement stmt1 = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Almas FROM Almas WHERE Player='" + p.getName() + "'");
        if (rs.next()) {
            stmt1.executeUpdate("UPDATE Almas SET Almas='" + almas + "' WHERE Player='" + p.getName() + "'");
        } else {
            stmt1.execute("INSERT INTO Almas (Player, Almas) VALUES ('" + p.getName() + "','" + almas + "');");
        }
        c.close();
        stmt.close();
        stmt1.close();
        rs.close();
        Almas.almas.put(p, almas);
    }

    public static void addAlmas(Player p, int almas) throws SQLException {
        Connection c = PAtlantis.sql.getNewConnection();
        Statement stmt = c.createStatement();
        Statement stmt1 = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Almas FROM Almas WHERE Player='" + p.getName() + "'");
        if (rs.next()) {
            int i = Integer.parseInt(rs.getString("Almas"));
            int d = almas + i;
            Almas.almas.put(p, d);
            stmt1.executeUpdate("UPDATE Almas SET Almas='" + d + "' WHERE Player='" + p.getName() + "'");
        } else {
            stmt1.execute("INSERT INTO Almas (Player, Almas) VALUES ('" + p.getName() + "','" + almas + "');");
            Almas.almas.put(p, almas);
        }
        c.close();
        stmt.close();
        stmt1.close();
        rs.close();
    }
}
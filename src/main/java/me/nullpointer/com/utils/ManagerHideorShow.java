package me.nullpointer.com.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ManagerHideorShow {

    public static ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    public boolean temPlayer(Player p) {
        return players.contains(p);
    }
}

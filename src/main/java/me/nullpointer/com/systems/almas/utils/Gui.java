package me.nullpointer.com.systems.almas.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Gui {

    public static Inventory inv;

    public static Inventory ossos_opcoes;

    public static void open(Player p) {
        p.openInventory(inv);
    }

    public static void load() {
        inv = Bukkit.createInventory(null, 27, "Mercado Negro");

    }
}

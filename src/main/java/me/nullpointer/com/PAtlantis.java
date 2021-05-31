package me.nullpointer.com;

import me.nullpointer.com.commands.AdvertisementCommand;
import me.nullpointer.com.commands.HideCommand;
import me.nullpointer.com.events.PlayerListener;
import me.nullpointer.com.systems.almas.core.ItensLoader;
import me.nullpointer.com.systems.almas.sql.SQL;
import me.nullpointer.com.systems.almas.utils.Gui;
import me.nullpointer.com.utils.ConfigAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PAtlantis extends JavaPlugin {

    public static HashMap<ItemStack, String> item = new HashMap<>();
    public static SQL sql;

    private static PAtlantis plugin;

    public static PAtlantis getInstance() {
        return plugin;
    }

    public static PAtlantis getPlugin() {
        return JavaPlugin.getPlugin(PAtlantis.class);
    }

    public static ConfigAPI itens;

    @Override
    public void onEnable() {
        plugin = this;
        sql = new SQL();
        itens = new ConfigAPI(this, "itens.yml");
        itens.saveDefaultConfig();
        commandsHandler();
        eventsHandler();
        Gui.load();
        ItensLoader.loadAll();
    }

    @Override
    public void onDisable() {
    }

    public void commandsHandler() {
        registerCommand("anunciotela", new AdvertisementCommand());
        registerCommand("esconder", new HideCommand());
    }

    public void eventsHandler() {
        registrarEventos(new PlayerListener());
    }


    public void registrarEventos(Listener classEvents) {
        Bukkit.getPluginManager().registerEvents(classEvents, this);
    }

    public void registerCommand(String name, CommandExecutor a) {
        getInstance().getCommand(name).setExecutor(a);
    }

}

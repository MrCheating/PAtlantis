package me.nullpointer.com;

import me.nullpointer.com.commands.AdvertisementCommand;
import me.nullpointer.com.commands.HideCommand;
import me.nullpointer.com.events.PlayerListener;
import me.nullpointer.com.utils.EnchantGlow;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PAtlantis extends JavaPlugin {

    private static PAtlantis plugin;

    public static PAtlantis getInstance() {
        return plugin;
    }

    public static PAtlantis getPlugin() {
        return JavaPlugin.getPlugin(PAtlantis.class);
    }

    @Override
    public void onEnable() {
        plugin = this;
        commandsHandler();
        eventsHandler();
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

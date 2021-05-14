package me.nullpointer.com;

import me.nullpointer.com.commands.AdvertisementCommand;
import me.nullpointer.com.commands.HideCommand;
import me.nullpointer.com.events.PlayerListener;
import me.nullpointer.com.events.bot.GuildMessageReceived;
import me.nullpointer.com.utils.ConfigAPI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class PAtlantis extends JavaPlugin {

    private static PAtlantis plugin;

    public static PAtlantis getInstance() {
        return plugin;
    }

    public static PAtlantis getPlugin() {
        return JavaPlugin.getPlugin(PAtlantis.class);
    }

    public static ConfigAPI botconfig;

    @Override
    public void onEnable() {
        botconfig = new ConfigAPI(this, "botconfig.yml");
        plugin = this;
        commandsHandler();
        eventsHandler();
        botconfig.saveDefaultConfig();
        new GuildMessageReceived(this);
    }

    @Override
    public void onDisable() {
        botconfig.saveConfig();
    }

    public void commandsHandler() {
        registerCommand("anunciotela", new AdvertisementCommand());
        registerCommand("esconder", new HideCommand());
    }

    public void eventsHandler() {
        registrarEventos(new PlayerListener());
    }

    public void onStartBot() {
        try {
            JDA jda = JDABuilder.createDefault(botconfig.getString("TOKEN"))
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[!] O BOT NÃO FOI LIGADO.");
        }
    }

    public void registrarEventos(Listener classEvents) {
        Bukkit.getPluginManager().registerEvents(classEvents, this);
    }

    public void registerCommand(String name, CommandExecutor a) {
        getInstance().getCommand(name).setExecutor(a);
    }

}

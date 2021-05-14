package me.nullpointer.com.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigAPI extends YamlConfiguration {

    private final File file;
    private final JavaPlugin plugin;
    private final String name;

    public ConfigAPI(JavaPlugin plugin, String name) {
        file = new File((this.plugin = plugin).getDataFolder(), this.name = name);
    }

    public void reloadConfig() {
        try {
            load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        plugin.saveResource(name, false);
        reloadConfig();
    }
}
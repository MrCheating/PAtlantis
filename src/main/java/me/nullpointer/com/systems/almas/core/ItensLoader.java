package me.nullpointer.com.systems.almas.core;

import me.nullpointer.com.PAtlantis;
import me.nullpointer.com.systems.almas.utils.EnchantLibrary;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItensLoader {

    public static Inventory gui;


    public static void loadAll() {
        gui = Bukkit.createInventory(null, 54, "Itens");
        FileConfiguration c = PAtlantis.itens;
        for (String path : PAtlantis.itens.getConfigurationSection("Itens").getKeys(false)) {
            try {
                int id = c.getInt("Itens." + path + ".id");
                int data = c.getInt("Itens." + path + ".data");
                int custo = c.getInt("Itens." + path + ".custo");
                int amount = c.getInt("Itens." + path + ".quantidade");
                int slot = c.getInt("Itens." + path + ".slot");
                String enchants = c.getString("Itens." + path + ".enchants");
                ItemStack item = new ItemStack(id, amount, (short) data);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(c.getString("Itens." + path + ".nome").replaceAll("&", "§"));
                meta.setLore(Arrays.asList("", "§7Custo: §8✟§7" + custo, ""));
                if (!enchants.equalsIgnoreCase("nenhum"))
                    if (enchants.contains(",")) {
                        byte b;
                        int i;
                        String[] arrayOfString;
                        for (i = (arrayOfString = enchants.split(",")).length, b = 0; b < i; ) {
                            String en = arrayOfString[b];
                            int level = Integer.parseInt(en.split(":")[1]);
                            Enchantment e = EnchantLibrary.getTranslateEnchant(en.split(":")[0]);
                            if (e != null)
                                meta.addEnchant(e, level, true);
                            b++;
                        }
                    } else {
                        int level = Integer.parseInt(enchants.split(":")[1]);
                        Enchantment e = EnchantLibrary.getTranslateEnchant(enchants.split(":")[0]);
                        if (e != null)
                            meta.addEnchant(e, level, true);
                    }
                item.setItemMeta(meta);
                gui.setItem(slot, item);
                PAtlantis.item.put(item, path);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Falha ao carregar " + path);
            }
        }
    }

}

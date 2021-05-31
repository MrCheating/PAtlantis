package me.nullpointer.com.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdvertisementCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {

        if (cmd.getName().equalsIgnoreCase("anunciotela")) {
            if (args.length == 0) {
                s.sendMessage("§c[!] Use: §f/anunciotela (tempo) (aviso).");
                return true;
            }
            if (args.length == 1) {
                s.sendMessage("§c[!] Use: §f/anunciotela (tempo) (aviso).");
                return true;
            }
            if (!s.hasPermission("atlantis.staff.*")) {
                s.sendMessage("§c[!] Você não tem permissão para executar esse comando.");
                return true;
            }
            int king = Integer.parseInt(args[0]);
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                builder.append(args[i]).append(" ");
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                TitleAPI.sendTitle(p, 20, king * 20, 20, "§bREDE ATLANTIS", builder.toString());
            }
        }

        return false;
    }
}

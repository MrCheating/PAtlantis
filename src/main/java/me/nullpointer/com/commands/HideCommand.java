package me.nullpointer.com.commands;

import me.nullpointer.com.utils.ManagerHideorShow;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)) return true;

        if(cmd.getName().equalsIgnoreCase("esconder")){
            Player p = (Player) s;
            if(ManagerHideorShow.players.contains(p)){
                p.sendMessage("§c[!] §aOs jogadores agora estão visiveis para você.");
                for(Player p2: Bukkit.getOnlinePlayers()) {
                    p.showPlayer(p2);
                    return true;
                }
                return true;
            }
            p.sendMessage("§c[!] §aOs jogadores agora não estão visiveis para você.");
            ManagerHideorShow.players.add(p);
            for(Player p1: Bukkit.getOnlinePlayers()){
                if(!p1.hasPermission("atlantis.staff.*")){
                    p.hidePlayer(p1);
                }
            }
        }

        return false;
    }
}

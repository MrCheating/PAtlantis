package me.nullpointer.com.events;

import me.nullpointer.com.utils.ManagerHideorShow;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        for (Player p1 : Bukkit.getOnlinePlayers()) {
            if (ManagerHideorShow.players.contains(p1)) {
                if (!p.hasPermission("atlantis.staff.*")) {
                    p1.hidePlayer(p);
                }
            }
        }
    }

}

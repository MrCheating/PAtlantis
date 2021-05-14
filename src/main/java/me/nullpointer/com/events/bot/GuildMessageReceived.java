package me.nullpointer.com.events.bot;

import me.nullpointer.com.PAtlantis;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class GuildMessageReceived extends ListenerAdapter implements Listener {

    public PAtlantis plugin;
    public JDA jda;

    public GuildMessageReceived(PAtlantis plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        onStartBot();
    }

    public void onStartBot() {
        try {
            JDA jda = JDABuilder.createDefault(PAtlantis.botconfig.getString("TOKEN"))
                    .addEventListeners(this)
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[!] O BOT NÃO FOI LIGADO.");
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage()) return;
        String message = event.getMessage().getContentRaw();
        User user = event.getAuthor();
        if (!event.getChannel().getId().equals(PAtlantis.botconfig.getString("IDCANAL1"))) return;
        Bukkit.broadcastMessage("[DISCORD] " + Objects.requireNonNull(event.getGuild().getMember(user)).getRoles().get(0) + user.getName() + ":§e " + message);
    }

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        TextChannel textChannel = jda.getTextChannelById(PAtlantis.botconfig.getString("IDCANAL1"));
        assert textChannel != null;
        textChannel.sendMessage("**" + e.getPlayer().getName() + ":** " + message).queue();
    }
}

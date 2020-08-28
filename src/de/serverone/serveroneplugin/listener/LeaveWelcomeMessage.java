package de.serverone.serveroneplugin.listener;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.source.util.ServerOneConfig;

public class LeaveWelcomeMessage implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
	Player spieler = event.getPlayer();
	event.setQuitMessage(randomMessage(spieler, "messages.leave"));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	Player player = event.getPlayer();
	event.setJoinMessage(randomMessage(player, "messages.join"));
    }

    private String randomMessage(Player player, String list) {
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml");
	List<String> args = (List<String>) config.getList(list);
	int rand = (int) Math.round(Math.random() * (args.size() - 1));

	String[] strings = args.get(rand).split("<p>");
	if (strings.length == 1)
	    return (ChatColor.BLUE + "<ServerOne> " + ChatColor.WHITE + strings[0] + ChatColor.GREEN
		    + player.getName());
	return (ChatColor.BLUE + "<ServerOne> " + ChatColor.WHITE + strings[0] + ChatColor.GREEN + player.getName()
		+ ChatColor.WHITE + strings[1]);
    }
}

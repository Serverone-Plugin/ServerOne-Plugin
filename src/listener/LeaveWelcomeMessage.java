package listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import util.ServerOneConfig;

public class LeaveWelcomeMessage implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	Player spieler = event.getPlayer();
		event.setQuitMessage(PlayerMoves(spieler, "messages.leave"));
		PlayerList(spieler);
    }
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
    	Player player = event.getPlayer();
		event.setJoinMessage(PlayerMoves(player, "messages.join"));
		PlayerList(player);
    }
    @SuppressWarnings("unchecked")
	private String PlayerMoves(Player player, String list) {
	    ServerOneConfig config = new ServerOneConfig("config.yml");
	    List<String> args = (List<String>) config.getList(list);    
    	int rand = (int) Math.round(Math.random()*(args.size()-1));
    	
    	String[] strings = args.get(rand).split("<p>");
    	if(strings.length == 1)
    		return (ChatColor.BLUE + "<ServerOne> " + ChatColor.WHITE + strings[0] + ChatColor.GREEN + player.getName());	
    	return (ChatColor.BLUE + "<ServerOne> " + ChatColor.WHITE + strings[0] + ChatColor.GREEN + player.getName() + ChatColor.WHITE + strings[1]);
    }
    private void PlayerList(Player spieler) {
    	spieler.setPlayerListHeader("§9Herzlich Willkommen auf §2§lServerone §r§9, " + spieler.getName() + "!§r\n§9Schau doch mal bei unserem Discord-Server vorbei: §5discord.gg/CTvRRZx§l!\n");
    	spieler.setPlayerListFooter("\n§6Viel Spaß beim Bauen!");
    }
    
}

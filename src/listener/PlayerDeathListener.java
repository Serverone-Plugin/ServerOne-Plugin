package listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import util.ServerOneConfig;

public class PlayerDeathListener implements Listener{
	@EventHandler
	public void onBlockBreak(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		ServerOneConfig config = new ServerOneConfig("config.yml");
		if(player.getWorld().getName() != config.getString("Worlds.build")) {
			
			Location loc = player.getLocation();
			String message = event.getDeathMessage() + " (" + (int) loc.getX() + " "+ (int) loc.getY() + " "+ (int) loc.getZ() + ", " + loc.getWorld().getName()+")";
			event.setDeathMessage(message);
		}
	}
}
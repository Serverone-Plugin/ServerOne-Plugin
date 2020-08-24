package de.robotix_00.serveroneplugin.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onBlockBreak(PlayerDeathEvent event) {
	Player player = event.getEntity().getPlayer();
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml");
	if (player.getWorld().getName() != config.getString("Worlds.build")) {

	    Location loc = player.getLocation();
	    String message = event.getDeathMessage() + " (" + (int) loc.getX() + " " + (int) loc.getY() + " "
		    + (int) loc.getZ() + ", " + loc.getWorld().getName() + ")";
	    event.setDeathMessage(message);
	}
    }
}
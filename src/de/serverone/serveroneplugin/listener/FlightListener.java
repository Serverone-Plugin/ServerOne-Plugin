package de.serverone.serveroneplugin.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.source.util.ServerOneConfig;

public class FlightListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

	if (event.getPlayer().getWorld().getName().equals(
		ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml").getString("Worlds.build"))) {
	    event.getPlayer().setAllowFlight(true);
	    event.getPlayer().setFlying(true);
	    
	}
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
	Player player = event.getPlayer();
	if (player.getWorld().getName().equals(
		ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml").getString("Worlds.build"))) {
	    player.setAllowFlight(true);
	    player.setFlying(true);
	} else {
	    if (player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE))
		return;
	    else {
		player.setFlying(false);
		player.setAllowFlight(false);
	    }
	}
    }

    @EventHandler
    public void onChangeGamemode(PlayerGameModeChangeEvent event) {
	if (event.getNewGameMode().equals(GameMode.SURVIVAL) || event.getNewGameMode().equals(GameMode.ADVENTURE)) {
	    Player player = event.getPlayer();
	    if (player.getWorld().getName().equals(
		    ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml").getString("Worlds.build"))) {
		player.setFlying(true);
		player.setAllowFlight(true);
	    }
	}
    }
}

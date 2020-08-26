package de.serverone.serveroneplugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.serveroneplugin.serverOneController.Warp;

public class VoidFallListener implements Listener {
    @EventHandler
    public void damageListener(EntityDamageEvent event) {
	if (!(event.getEntity() instanceof Player))
	    return;
	if (!(event.getCause().equals(DamageCause.VOID)) || event.getEntity().getLocation().getY() > 0)
	    return;
	Player player = (Player) event.getEntity();
	String world = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml").getString("Worlds.build");
	if (!player.getWorld().getName().equals(world))
	    return;
	player.sendMessage("§cUiuiui, da wärst du fast gestorben");
	player.setFallDistance(0);
	player.teleport(Warp.Spawn.getLocation(null));
    }
}

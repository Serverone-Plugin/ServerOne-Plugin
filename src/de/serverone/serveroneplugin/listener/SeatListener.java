package de.serverone.serveroneplugin.listener;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SeatListener implements Listener {
    private static HashMap<Player, Entity> map = new HashMap<>();
    
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    if (event.getItem() != null)
		return;
	    if (!((event.getClickedBlock().getBlockData() instanceof Stairs)
		    || (event.getClickedBlock().getBlockData() instanceof Slab)))
		return;
	    if (event.getBlockFace() != BlockFace.UP)
		return;
	    if(map.containsKey(player)) return;
	    
	    Location loc = event.getClickedBlock().getLocation();

	    if (!loc.add(0, 1, 0).getBlock().isPassable())
		return;
	    loc.add(0, -1, 0);
	    
	    if (map.containsKey(player)) {
		map.get(player).remove();
		map.remove(player);
	    }

	    ArmorStand mount = player.getWorld().spawn(loc.add(0.5D, -0.3D, 0.5D), ArmorStand.class);
	    mount.addPassenger(player);
	    mount.setGravity(false);
	    mount.setSmall(true);
	    mount.setInvulnerable(true);
	    mount.setVisible(false);
	    mount.addScoreboardTag("seat");
	    map.put(player, mount);
	}
    }

    @EventHandler
    public void onDismount(PlayerMoveEvent event) {
	Player player = event.getPlayer();
	if (map.containsKey(player)) {
	    map.get(player).remove();
	    map.remove(player);
	    player.teleport(player.getLocation().add(0, .5, 0));
	} else
	    return;
    }
    public static void onDisable() {
	for(Entity e : map.values()) {
	    e.remove();
	}
    }
}

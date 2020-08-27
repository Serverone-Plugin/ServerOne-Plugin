package listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SeatListener implements Listener{
	   HashMap<Player, Entity> map = new HashMap<>();
	   
		
		@EventHandler
		public void onInteract(PlayerInteractEvent event){
	    Player player = event.getPlayer();
	    if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
	    	if(event.getItem() != null) return;
	    	if(!getList().contains(event.getClickedBlock().getType())) return;
	        if(event.getBlockFace() != BlockFace.UP) return;
	    	Location loc = event.getClickedBlock().getLocation();
	    	
	    	if(!loc.add(0, 1, 0).getBlock().isPassable()) return;
	    	loc.add(0, -1, 0);
	    	
	        
	       	if(map.containsKey(player)) {
	       		map.get(player).remove();
	       		map.remove(player);
	       	}
	       	
	        AreaEffectCloud mount = player.getWorld().spawn(loc.add(0.5D, 0, 0.5D), AreaEffectCloud.class);
	        mount.addPassenger(player);
	        mount.setInvulnerable(true);
	        mount.setGravity(false);
	        mount.setRadius(0);
	        mount.setParticle(Particle.PORTAL);
	        mount.setDuration(99999);
	        mount.addScoreboardTag("seat");
	        map.put(player,  mount);
	     	}
		}
		
		@EventHandler
		public void onDismount(PlayerMoveEvent event) {
			Player player = event.getPlayer();
			if(map.containsKey(player)) {
				map.get(player).remove();
				map.remove(player);
				player.teleport(player.getLocation().add(0, .5, 0));
			} else return;
		}
		
		private List<Material> getList() {
			List<Material> list = new ArrayList<>();
			list.add(Material.ACACIA_STAIRS);
			list.add(Material.ANDESITE_STAIRS);
			list.add(Material.BIRCH_STAIRS);
			list.add(Material.BRICK_STAIRS);
			list.add(Material.COBBLESTONE_STAIRS);
			list.add(Material.DARK_OAK_STAIRS);
			list.add(Material.DARK_PRISMARINE_STAIRS);
			list.add(Material.DIORITE_STAIRS);
			list.add(Material.END_STONE_BRICK_STAIRS);
			list.add(Material.GRANITE_STAIRS);
			list.add(Material.JUNGLE_STAIRS);
			list.add(Material.MOSSY_COBBLESTONE_STAIRS);
			list.add(Material.MOSSY_STONE_BRICK_STAIRS);
			list.add(Material.NETHER_BRICK_STAIRS);
			list.add(Material.OAK_STAIRS);
			list.add(Material.POLISHED_ANDESITE_STAIRS);
			list.add(Material.POLISHED_DIORITE_STAIRS);
			list.add(Material.POLISHED_GRANITE_STAIRS);
			list.add(Material.PRISMARINE_BRICK_STAIRS);
			list.add(Material.PRISMARINE_STAIRS);
			list.add(Material.PURPUR_STAIRS);
			list.add(Material.QUARTZ_STAIRS);
			list.add(Material.RED_NETHER_BRICK_STAIRS);
			list.add(Material.RED_SANDSTONE_STAIRS);
			list.add(Material.SANDSTONE_STAIRS);
			list.add(Material.SMOOTH_QUARTZ_STAIRS);
			list.add(Material.SMOOTH_RED_SANDSTONE_STAIRS);
			list.add(Material.SMOOTH_SANDSTONE_STAIRS);
			list.add(Material.SPRUCE_STAIRS);
			list.add(Material.STONE_BRICK_STAIRS);
			list.add(Material.STONE_STAIRS);
			return list;
		}
}

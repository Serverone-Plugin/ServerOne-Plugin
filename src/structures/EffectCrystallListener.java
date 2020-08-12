package structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectCrystallListener implements Listener {
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
    	Player player = event.getPlayer();
		
		Location location = player.getLocation();
    	World welt = player.getWorld();
    	Block block1 = welt.getBlockAt(location);
    	Material mat1 = block1.getType();
    	if(mat1 == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
        	location.setY(location.getY()-1);
    		Block block2 = welt.getBlockAt(location);
    		Material mat2 = block2.getType();
    		if(mat2 == Material.REDSTONE_BLOCK) {
    	    	player.removePotionEffect(PotionEffectType.SPEED);
    			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 4));
    		} else if(mat2 == Material.DIAMOND_BLOCK) {
    			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 500, 2));
    		} else if(mat2 == Material.EMERALD_BLOCK) {
    			player.playSound(location, Sound.ENTITY_RABBIT_JUMP, 1, 1);
    			player.setFallDistance(0.0f);
    			player.setVelocity(player.getVelocity().setY(1.0));
    		}
    	}
	}
}

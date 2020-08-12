package listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class antiSpawnDestroyListener implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(event.getBlock().getType() == Material.SPAWNER) {
			if(player.isSneaking() == false) {
				event.setCancelled(true);
				player.sendMessage("§cDu musst sneaken um den Spawner abzubauen.");
			}
		}
	}
}

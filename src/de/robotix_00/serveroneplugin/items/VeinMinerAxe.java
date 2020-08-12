package de.robotix_00.serveroneplugin.items;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.robotix_00.serverone.source.util.ServerOneWorldGuard;

public class VeinMinerAxe implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Material mat = block.getType();
		String name = "";
		ItemStack item = player.getInventory().getItemInMainHand();
		if(item == null) return;
		
		try {
			name = item.getItemMeta().getDisplayName();
		}catch(Exception e) {
			return;
		}
		if(mat == Material.OAK_LOG || mat == Material.SPRUCE_LOG || mat == Material.BIRCH_LOG || mat == Material.JUNGLE_LOG || mat == Material.ACACIA_LOG || mat == Material.DARK_OAK_LOG) {
			if(name.equalsIgnoreCase("§6Lumber-Axe")) {
				int count = 0;	
				for(int i = 0; i<=15; i++) {
					if(!ServerOneWorldGuard.canBreak(block.getLocation(), player)) return;
					if(item.getType().getMaxDurability() - item.getDurability() <= i) break;
					block = block.getLocation().add(0, 1, 0).getBlock();
					if(block.getType() != mat) break;
					block.breakNaturally();
					count++;
				}
				if(player.getGameMode() != GameMode.CREATIVE) {
					item.setDurability((short) (item.getDurability()+count));
					player.getInventory().setItemInMainHand(item);
				}
				return;
			}
		}
	}
}

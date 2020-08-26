package de.serverone.serveroneplugin.items;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.robotix_00.serverone.source.util.Cooldown;
import de.robotix_00.serverone.source.util.ServerOneWorldGuard;
import de.serverone.serveroneplugin.ServerOnePlugin;

public class VeinMinerPiackaxe implements Listener{
	Cooldown switchDown = new Cooldown(5, ServerOnePlugin.getPlugin());
	@EventHandler
	public void onPlayerInterect(PlayerInteractEvent event) {
		if(switchDown.run(event.getPlayer())) return;
		Player player = event.getPlayer();
		if(!player.isSneaking()) return;
		ItemStack item;
		List<String> lore;
		String name;
		try {
			item = player.getInventory().getItemInMainHand();
			lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
			name = item.getItemMeta().getDisplayName();
		}catch(Exception e) {
			return;
		}
		if(lore == null) return;
		if(name == null) return;
		event.setCancelled(true);
		ItemMeta meta = item.getItemMeta();
		switch(lore.get(0)) {
		case "§dMode: 1x1":
			lore.set(0, "§dMode: 1x2");
			player.sendMessage("§dMode: 1x2");
			break;
		case "§dMode: 1x2":
			lore.set(0, "§dMode: 3x3");
			player.sendMessage("§dMode: 3x3");
			break;
		case "§dMode: 3x3":
			lore.set(0, "§dMode: 1x1");
			player.sendMessage("§dMode: 1x1");
			break;
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		player.getInventory().setItemInMainHand(item);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Material mat = block.getType();
		ItemStack item;
		String lore;
		try {
			item = player.getInventory().getItemInMainHand();
			if(!(item.getItemMeta().getDisplayName().equals("§6Miners Pickaxe"))) return;
			lore = item.getItemMeta().getLore().get(0);
		}catch(Exception e) {
			return;
		}
		
		if(!allowed(mat)) return;
		if(lore == null) return;
		int dur = item.getDurability();
		int maxDur = item.getType().getMaxDurability();
		
		int count = 0;
		Location blockLoc = block.getLocation();
		switch(lore){
		case "§dMode: 1x1":
			break;
		case "§dMode: 1x2":
			if(dur+2>maxDur) return;
			if(blockLoc.add(0, -1, 0).getBlock().getType() == mat) {
				if(!ServerOneWorldGuard.canBreak(blockLoc, player)) return;
				blockLoc.getBlock().breakNaturally();
				count++;
			}
			break;
		case "§dMode: 3x3":
			if(dur+27>=maxDur) return;
			blockLoc.add(-1,-1,-1);
			Location loc2 = blockLoc;
			for(int x = (int)blockLoc.getX(); x<blockLoc.getX()+3; x++) {
				for(int y = (int)blockLoc.getY(); y<blockLoc.getY()+3; y++) {
					for(int z = (int)blockLoc.getZ(); z<blockLoc.getZ()+3; z++) {
						loc2 = new Location(player.getWorld(), x, y, z);
						if(ServerOneWorldGuard.canBreak(loc2, player)) {
							if(loc2.getBlock().getType() == mat) loc2.getBlock().breakNaturally();
							count++;
							}
						}
					}
				}
			break;
		}
		if(player.getGameMode() != GameMode.CREATIVE) {
			item.setDurability((short) (item.getDurability()+count));
			player.getInventory().setItemInMainHand(item);
		}
	}
	private boolean allowed(Material mat) {
		Material[] allowedMaterials = {
				Material.SANDSTONE,
				Material.STONE,
				Material.NETHERRACK,
				Material.COBBLESTONE,
				Material.ANDESITE,
				Material.DIORITE,
				Material.GRANITE
		};
		for(Material current : allowedMaterials) {
			if(current == mat) return true;
		}
		return false;
	}
}

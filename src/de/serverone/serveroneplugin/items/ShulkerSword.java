package de.serverone.serveroneplugin.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.source.util.Cooldown;

public class ShulkerSword implements Listener{
	JavaPlugin plugin = ServerOnePlugin.getPlugin();
	Cooldown cooldown = new Cooldown(70, plugin);
	Cooldown switchDown = new Cooldown(5, plugin);

	@SuppressWarnings("deprecation")
	@EventHandler
	public void RightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if(item.getType() == Material.AIR) return;
		if(!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if(!(item.getItemMeta().getDisplayName().equals("§5§lShulkerSword"))) return;
		event.setCancelled(true);
		if(player.isSneaking() && switchDown.run(player)) {
			
			if(!item.getItemMeta().hasLore()) return;
			List<String> lores = item.getItemMeta().getLore();
			
			ItemMeta meta = item.getItemMeta();
			List<String> lore = item.getItemMeta().getLore();
			if(lore == null) return;
			switch(lores.get(0)) {
			case "§dMode: none":
				lores.set(0, "§dMode: Mobs");
				
				player.sendMessage("§dMode: Mobs");
				break;
			case "§dMode: Mobs":
				lores.set(0, "§dMode: none");
				
				player.sendMessage("§dMode: none");
				break;
			}
			meta.setLore(lores);
			item.setItemMeta(meta);
			player.getInventory().setItemInMainHand(item);
			return;
		}
		
		if(cooldown.run(player)) {
			if(item.getDurability()+50 > item.getType().getMaxDurability()) return;
			player.playSound(player.getLocation(), Sound.ENTITY_SHULKER_SHOOT, 1, 1);
			ShulkerBullet shulkerbullet = player.getWorld().spawn(player.getEyeLocation(), ShulkerBullet.class);
			shulkerbullet.setShooter(player);
			shulkerbullet.setVelocity(player.getLocation().getDirection().multiply(1));
			
			if(player.getGameMode() != GameMode.CREATIVE) item.setDurability((short) (item.getDurability()+50));
			player.getInventory().setItemInMainHand(item);
			
			List<String> lores = item.getItemMeta().getLore();
			if(lores == null) return;
			switch(lores.get(0)) {
			case "§dMode: Mobs":
		           int sightLine = 20;
		           List<Entity> entity = event.getPlayer().getNearbyEntities(sightLine, sightLine, sightLine);
		           List<Mob> living = new ArrayList<Mob>();
		           for(Entity e : entity){
		               if(e instanceof Mob){
		                   living.add((Mob) e);
		               }
		           }
		           try {
		        	   shulkerbullet.setTarget(living.get(0));
		           }catch(Exception e) {
		        	   return;
		           }
		           break;
			default:
				break;
			}
		} else player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1, 1);
	}
}

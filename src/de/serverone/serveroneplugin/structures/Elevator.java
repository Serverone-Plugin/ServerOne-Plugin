package de.serverone.serveroneplugin.structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Elevator implements Listener{
	@EventHandler
	public void onSneakEvent(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if(player.isSneaking() && onElevator(player)) {
			if(player.getLocation().getPitch() <= -80) {
				elevTpUp(player);
			} else  if(player.getLocation().getPitch() >= 80){
				elevTpDown(player);
			}
		}
	}
	public void elevTpUp(Player player) {
		Location pos = player.getLocation();
		Material mat = null;
			
		for(int i = pos.getBlockY(); i<player.getLocation().getBlockY()+19 && i<=255; i++) {
			pos.setY(pos.getY()+1);
			mat = pos.getBlock().getType();
			if(mat == Material.SEA_LANTERN) {
				pos.setY(pos.getY()+1);
				mat = pos.getBlock().getType();
				if(mat == Material.AIR || mat == Material.CAVE_AIR || mat == Material.VOID_AIR) {
					player.teleport(pos);
					break;
				}
			}
		}
	}
	public void elevTpDown(Player player) {
		Location pos = player.getLocation();
		pos.setY(pos.getY()-1);
		Material mat = null;
			
		for(int i = pos.getBlockY()-1; i>player.getLocation().getBlockY()-22 && i>0; i--) {
			pos.setY(pos.getY()-1);
			mat = pos.getBlock().getType();
			if(mat == Material.SEA_LANTERN) {
				pos.setY(pos.getY()+1);
				mat = pos.getBlock().getType();
				if(mat == Material.AIR || mat == Material.CAVE_AIR || mat == Material.VOID_AIR || mat == Material.WATER) {
					player.teleport(pos);
					break;
				}
			}
		}
	}
	public boolean onElevator(Player player) {
		Location pos = player.getLocation();
		
		if(pos.add(0, -1, 0).getBlock().getType() != Material.SEA_LANTERN) return false;
		if(pos.add(-1, 0, 0).getBlock().getType() != Material.QUARTZ_STAIRS) return false;
		if(pos.add(2, 0, 0).getBlock().getType() != Material.QUARTZ_STAIRS) return false;
		if(pos.add(-1, 0, -1).getBlock().getType() != Material.QUARTZ_STAIRS) return false;
		if(pos.add(0, 0, 2).getBlock().getType() != Material.QUARTZ_STAIRS) return false;
		return true;
	}
}
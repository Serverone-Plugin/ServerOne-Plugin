package util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import main.Main;

public class Cooldown {
	private int cooldownTime;
	private List<String> list = new ArrayList<>();
	
	
	public Cooldown(int cooldownTime) {
		this.cooldownTime = cooldownTime;
	}
	
	public boolean test(Player player) {
		if(list.contains(player.getName())) return false;
		else {
			list.add(player.getName());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				@Override
				public void run() {
					list.remove(player.getName());
				}
			}, cooldownTime);
			return true;
		}
	}
}
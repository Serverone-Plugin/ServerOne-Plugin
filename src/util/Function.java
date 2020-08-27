package util;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Function {
	public static boolean canStack(Player player, ItemStack item, int amount) {
		for(ItemStack now : player.getInventory().getStorageContents()) {
			if(now == null) 
				amount -= item.getMaxStackSize();
			else {
				if(now.isSimilar(item)) {
					amount -= now.getMaxStackSize()-now.getAmount();
				}
			}
			if(amount <= 0) return true;
		}
		return false;
	}
	
	public static int countItem(Player player, ItemStack item) {
		int counter = 0;
		if(item == null || item.getType() == Material.AIR) return 42;
		
		for(ItemStack now : player.getInventory().getContents()) {
			if(now != null) {
				if(now.isSimilar(item)) {
					counter += now.getAmount();
				}
			}
		}
		
		return counter;
	}
	
	@SuppressWarnings("unchecked")
	public static String getMessage(String configPath, String path) {
		ServerOneConfig config = new ServerOneConfig(configPath);
		List<String> sprüche = (List<String>) config.getList(path);
		return sprüche.get((int) Math.round(Math.random()*(sprüche.size()-1)));
	}
}

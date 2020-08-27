package util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;


public class SkullBuilder{
	
	private ItemStack item;
	private SkullMeta itemMeta;
	
	public SkullBuilder(Player player, int amount) {
		item = new ItemStack(Material.PLAYER_HEAD, amount);
		itemMeta = (SkullMeta) item.getItemMeta();
		itemMeta.setOwningPlayer(player);
	}
	public SkullBuilder(Player player) {
		item = new ItemStack(Material.PLAYER_HEAD);
		itemMeta = (SkullMeta) item.getItemMeta();
		itemMeta.setOwningPlayer(player);
	}
	@SuppressWarnings("deprecation")
	public SkullBuilder(String player) {
		item = new ItemStack(Material.PLAYER_HEAD);
		itemMeta = (SkullMeta) item.getItemMeta();
		//TODO besserer weg hierfür
		itemMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player));
	}
	
	public SkullBuilder setName(String name) {
		itemMeta.setDisplayName(name);
		return this;
	}
	public SkullBuilder setLore(String... lore) {
		itemMeta.setLore(Arrays.asList(lore));
		return this;
	}
	public SkullBuilder setUbreakable() {
		itemMeta.setUnbreakable(true);
		return this;
	}
	public SkullBuilder addLore(String lore) {
			if(itemMeta.hasLore()) {
				List<String> lores = itemMeta.getLore();
				lores.add(lore);
				itemMeta.setLore(lores);
			} else {
				setLore(lore);
			}
		return this;
	}
	public SkullBuilder setMenuItem() {
		addLore("§8MenuItem");
		return this;
	}
	
	public ItemStack build() {
		item.setItemMeta(itemMeta);
		return item;
	}
}

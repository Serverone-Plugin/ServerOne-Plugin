package util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemBuilder {
	
	private ItemStack item;
	private ItemMeta itemMeta;
	
	public ItemBuilder(Material material, int amount) {
		item = new ItemStack(material, amount);
		itemMeta = item.getItemMeta();
	}
	public ItemBuilder(Material material) {
		item = new ItemStack(material);
		itemMeta = item.getItemMeta();
	}
	
	public ItemBuilder setName(String name) {
		itemMeta.setDisplayName(name);
		return this;
	}
	public ItemBuilder setLore(String... lore) {
		itemMeta.setLore(Arrays.asList(lore));
		return this;
	}
	public ItemBuilder addLore(String lore) {
		if(itemMeta.hasLore()) {
				List<String> lores = itemMeta.getLore();
				lores.add(lore);
				itemMeta.setLore(lores);
			} else {
				setLore(lore);
			}
		return this;
	}
	public ItemBuilder setUnbreakable() {
		itemMeta.setUnbreakable(true);
		return this;
	}
	public ItemBuilder enchant(Enchantment enchantment, int level, boolean bool) {
		itemMeta.addEnchant(enchantment, level, bool);
		return this;
	}
	public ItemBuilder setMenuItem() {
		addLore("§8MenuItem");
		return this;
	}
	
	
	
	public ItemStack build() {
		item.setItemMeta(itemMeta);
		return item;
	}
}

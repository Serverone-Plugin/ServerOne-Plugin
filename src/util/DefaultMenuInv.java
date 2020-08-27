package util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DefaultMenuInv {
	private Inventory inv;
	
	
	private ItemStack defaultBorder = new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("§6Rand").setMenuItem().build();
	private ItemStack defaultEmpty = new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName("§7nischd").setMenuItem().build();
	
	
	public DefaultMenuInv(String name) {
		inv = Bukkit.createInventory(null, 6*9, name);
		for (int i=9; i<=53; i++) inv.setItem(i, defaultEmpty);
		for(int i = 0; i<=8; i++) inv.setItem(i, defaultBorder);
		inv.setItem(4, new ItemBuilder(Material.ARROW).setName("§6§lzurück").setMenuItem().build());
	}
	public DefaultMenuInv setBorderBar(ItemStack item) {
		defaultBorder = item;
		for(int i = 0; i<=8; i++) inv.setItem(i, item);
		
		return this;
	}
	public DefaultMenuInv setBorderAround(ItemStack item) {
		defaultBorder = item;
			
		for(int i=0; i<=8; i++) inv.setItem(i, item);
		for(int i=9; i<=36; i=i+9) inv.setItem(i, item);
		for(int i=17; i<=44; i=i+9) inv.setItem(i, item);
		for(int i=45; i<=53; i++) inv.setItem(i, item);
		return this;
	}
	public DefaultMenuInv setBorderAround() {
			
		for(int i=0; i<=8; i++) inv.setItem(i, defaultBorder);
		for(int i=9; i<=36; i=i+9) inv.setItem(i, defaultBorder);
		for(int i=17; i<=44; i=i+9) inv.setItem(i, defaultBorder);
		for(int i=45; i<=53; i++) inv.setItem(i, defaultBorder);
		return this;
	}
	public DefaultMenuInv setEmpty(ItemStack item) {
		for (int i=9; i<=53; i++) inv.setItem(i, item);
		
		return this;
	}
	public DefaultMenuInv setBarMiddle(ItemStack item) {
		inv.setItem(4, item);
		return this;
	}
	public DefaultMenuInv setBarSide(ItemStack item) {
		inv.setItem(3, item);
		inv.setItem(5, item);
		return this;
	}
	public DefaultMenuInv setBack() {
		inv.setItem(4, new ItemBuilder(Material.ARROW).setName("§6§lzurück").setMenuItem().build());
		return this;
	}
	public DefaultMenuInv setClose() {
		inv.setItem(4, new ItemBuilder(Material.SPECTRAL_ARROW).setName("§6§lschließen").setMenuItem().build());
		return this;
	}
	public Inventory build() {
		return inv;
	}
}

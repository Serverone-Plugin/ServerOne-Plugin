package universalGetter;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import util.ItemBuilder;

public class ItemGetter {
	public static ItemStack getController() {
		return new ItemBuilder(Material.COMPASS).setName("§6§lServerOneController").build();
	}
	public static ItemStack getCoin() {
		return new ItemBuilder(Material.GOLD_NUGGET).setName("§6§lServerOneTaler").build();
	}
	public static ItemStack getCoin(int amount) {
		return new ItemBuilder(Material.GOLD_NUGGET, amount).setName("§6§lServerOneTaler").build();
	}
}

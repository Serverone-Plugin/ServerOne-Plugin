package main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import util.ItemBuilder;

public class RecipeLoader {
	@SuppressWarnings("deprecation")
	public static void load(JavaPlugin plugin) {
		ItemStack bottle = new ItemBuilder(Material.COMPASS).setName("§6§lServerOneController").build();
		
		ShapedRecipe soC = new ShapedRecipe(bottle);
		
		soC.shape(" % ","%*%"," % ");
		 
		soC.setIngredient('*', Material.COMPASS);
		soC.setIngredient('%', Material.GOLD_NUGGET);
		 
		plugin.getServer().addRecipe(soC);
	}
}

package de.serverone.serveroneplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.serverone.serveroneplugin.commands.MoneyCommand;
import de.serverone.serveroneplugin.commands.SkillCommand;
import de.serverone.serveroneplugin.commands.SprachFehlerCommand;
import de.serverone.serveroneplugin.commands.VoteCommand;
import de.serverone.serveroneplugin.commands.sopCommand;
import de.serverone.serveroneplugin.commands.toggleOpCommand;
import de.serverone.serveroneplugin.items.CreditCardController;
import de.serverone.serveroneplugin.items.ShulkerSword;
import de.serverone.serveroneplugin.items.VeinMinerAxe;
import de.serverone.serveroneplugin.items.VeinMinerPiackaxe;
import de.serverone.serveroneplugin.listener.LeaveWelcomeMessage;
import de.serverone.serveroneplugin.listener.PlayerDeathListener;
import de.serverone.serveroneplugin.listener.SeatListener;
import de.serverone.serveroneplugin.listener.VoidFallListener;
import de.serverone.serveroneplugin.listener.antiSpawnDestroyListener;
import de.serverone.serveroneplugin.listener.newPlayerListener;
import de.serverone.serveroneplugin.playerShop.PlayerShopMain;
import de.serverone.serveroneplugin.playerShop.ShopRegisterListener;
import de.serverone.serveroneplugin.playerShop.SimplePlayerShop;
import de.serverone.serveroneplugin.serverShop.ShopInvController;
import de.serverone.serveroneplugin.serverShop.ShopVillagerController;
import de.serverone.serveroneplugin.serverShop.UtilVillagerController;
import de.serverone.serveroneplugin.server_one_controller.ControllerListener;
import de.serverone.serveroneplugin.server_one_controller.Warp;
import de.serverone.serveroneplugin.structures.EffectCrystallListener;
import de.serverone.serveroneplugin.structures.Elevator;
import de.serverone.source.builder.ItemBuilder;

public class Loader {
    public static void load(JavaPlugin plugin) {
	loadCommands(plugin);
	loadRecipes(plugin);
	loadEventListener(plugin);
    }

    public static void unload() {
	SeatListener.onDisable();
    }

    /* EventListener */
    private static void loadEventListener(JavaPlugin plugin) {
	PluginManager pluginManager = plugin.getServer().getPluginManager();

	// Structures
	pluginManager.registerEvents(new EffectCrystallListener(), plugin);
	pluginManager.registerEvents(new Elevator(), plugin);

	// CustomMenus
	pluginManager.registerEvents(new ControllerListener(), plugin);
	pluginManager.registerEvents(new ShopInvController(), plugin);
	pluginManager.registerEvents(new ShopVillagerController(), plugin);
	pluginManager.registerEvents(new ShopRegisterListener(), plugin);
	pluginManager.registerEvents(new SimplePlayerShop(), plugin);
	pluginManager.registerEvents(new CreditCardController(), plugin);
	pluginManager.registerEvents(new UtilVillagerController(), plugin);

	// PlayerShops
	pluginManager.registerEvents(new PlayerShopMain(), plugin);

	// Items
	pluginManager.registerEvents(new VeinMinerAxe(), plugin);
	pluginManager.registerEvents(new VeinMinerPiackaxe(), plugin);
	pluginManager.registerEvents(new ShulkerSword(), plugin);

	//
	pluginManager.registerEvents(new LeaveWelcomeMessage(), plugin);
	pluginManager.registerEvents(new PlayerDeathListener(), plugin);
	pluginManager.registerEvents(new antiSpawnDestroyListener(), plugin);
	pluginManager.registerEvents(new VoidFallListener(), plugin);
	pluginManager.registerEvents(new newPlayerListener(), plugin);

	//
	pluginManager.registerEvents(new SeatListener(), plugin);
	pluginManager.registerEvents(new Warp.WarpCancelListener(), plugin);

	for (Player player : Bukkit.getOnlinePlayers()) {
	    ControllerListener.createController(player);
	}
    }

    /* Commands */
    private static void loadCommands(JavaPlugin plugin) {
	plugin.getCommand("skill").setExecutor(new SkillCommand());
	plugin.getCommand("money").setExecutor(new MoneyCommand());
	plugin.getCommand("sop").setExecutor(new sopCommand());
	plugin.getCommand("toggleOP").setExecutor(new toggleOpCommand());

	plugin.getCommand("sprachfehler").setExecutor(new SprachFehlerCommand());
	plugin.getCommand("vote").setExecutor(new VoteCommand());
    }

    /* Recipes */
    private static void loadRecipes(JavaPlugin plugin) {
	ItemStack bottle = new ItemBuilder(Material.COMPASS).setName("§6§lServerOneController").build();

	@SuppressWarnings("deprecation")
	ShapedRecipe soC = new ShapedRecipe(bottle);

	soC.shape(" % ", "%*%", " % ");

	soC.setIngredient('*', Material.COMPASS);
	soC.setIngredient('%', Material.GOLD_NUGGET);

	plugin.getServer().addRecipe(soC);
    }
}

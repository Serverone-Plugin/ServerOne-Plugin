package de.robotix_00.serveroneplugin;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.robotix_00.serverone.source.builder.ItemBuilder;
import de.robotix_00.serveroneplugin.commands.MoneyCommand;
import de.robotix_00.serveroneplugin.commands.SkillCommand;
import de.robotix_00.serveroneplugin.commands.SprachFehlerCommand;
import de.robotix_00.serveroneplugin.commands.VoteCommand;
import de.robotix_00.serveroneplugin.commands.sopCommand;
import de.robotix_00.serveroneplugin.commands.toggleOpCommand;
import de.robotix_00.serveroneplugin.items.CreditCardController;
import de.robotix_00.serveroneplugin.items.ShulkerSword;
import de.robotix_00.serveroneplugin.items.VeinMinerAxe;
import de.robotix_00.serveroneplugin.items.VeinMinerPiackaxe;
import de.robotix_00.serveroneplugin.listener.LeaveWelcomeMessage;
import de.robotix_00.serveroneplugin.listener.PlayerDeathListener;
import de.robotix_00.serveroneplugin.listener.SeatListener;
import de.robotix_00.serveroneplugin.listener.VoidFallListener;
import de.robotix_00.serveroneplugin.listener.antiSpawnDestroyListener;
import de.robotix_00.serveroneplugin.listener.newPlayerListener;
import de.robotix_00.serveroneplugin.playerShop.PlayerShopMain;
import de.robotix_00.serveroneplugin.playerShop.ShopRegisterListener;
import de.robotix_00.serveroneplugin.playerShop.SimplePlayerShop;
import de.robotix_00.serveroneplugin.serverOneController.Controller_Listener;
import de.robotix_00.serveroneplugin.serverOneController.Warp;
import de.robotix_00.serveroneplugin.serverShop.ShopInvController;
import de.robotix_00.serveroneplugin.serverShop.ShopVillagerController;
import de.robotix_00.serveroneplugin.serverShop.UtilVillagerController;
import de.robotix_00.serveroneplugin.structures.EffectCrystallListener;
import de.robotix_00.serveroneplugin.structures.Elevator;

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
	pluginManager.registerEvents(new Controller_Listener(), plugin);
	pluginManager.registerEvents(new ShopInvController(), plugin);
	pluginManager.registerEvents(new ShopVillagerController(), plugin);
	pluginManager.registerEvents(new ShopRegisterListener(), plugin);
	pluginManager.registerEvents(new SimplePlayerShop(), plugin);
	pluginManager.registerEvents(new CreditCardController(), plugin);
	pluginManager.registerEvents(new UtilVillagerController(), plugin);
	
	//PlayerShops
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

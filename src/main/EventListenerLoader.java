package main;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import items.ShulkerSword;
import items.veinMinerAxe;
import items.veinMinerPiackaxe;
import listener.LeaveWelcomeMessage;
import listener.PlayerDeathListener;
import listener.VoidFallListener;
import listener.antiSpawnDestroyListener;
import listener.newPlayerListener;
import listener.SeatListener;
import serverOneController.Controller_Listener;
import serverShop.ShopInvController;
import serverShop.ShopVillagerController;
import structures.EffectCrystallListener;
import structures.Elevator;

public class EventListenerLoader{
	public static void load(JavaPlugin plugin) {
		PluginManager pluginManager = plugin.getServer().getPluginManager();
		
		//Structures
		pluginManager.registerEvents(new EffectCrystallListener(), plugin);
		pluginManager.registerEvents(new Elevator(), plugin);
		
		//CustomMenus
		pluginManager.registerEvents(new Controller_Listener(), plugin);
		pluginManager.registerEvents(new ShopInvController(), plugin);
		pluginManager.registerEvents(new ShopVillagerController(), plugin);
		
		//Items
		pluginManager.registerEvents(new veinMinerAxe(), plugin);
		pluginManager.registerEvents(new veinMinerPiackaxe(), plugin);
		pluginManager.registerEvents(new ShulkerSword(), plugin);
		
		//
		pluginManager.registerEvents(new LeaveWelcomeMessage(), plugin);
		pluginManager.registerEvents(new PlayerDeathListener(), plugin);
		pluginManager.registerEvents(new antiSpawnDestroyListener(), plugin);
		pluginManager.registerEvents(new VoidFallListener(), plugin);
		pluginManager.registerEvents(new newPlayerListener(), plugin);
		
		
		//
		pluginManager.registerEvents(new SeatListener(), plugin);
	}
}

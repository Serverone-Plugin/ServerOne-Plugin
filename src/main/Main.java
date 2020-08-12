package main;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	private static WorldGuardPlugin wgPlugin = null;
	
	//onEnable
	public void onEnable(){
		plugin = this;
		
		Plugin worldGurad = Main.getPlugin().getServer().getPluginManager().getPlugin("WorldGuard");
	    if (!(plugin == null || !(worldGurad instanceof WorldGuardPlugin))) wgPlugin = (WorldGuardPlugin) worldGurad;
		
		this.getLogger().info("ServerOne-Plugin geladen");
		
		//save YMLs
		this.saveResource("config.yml", false);
		this.saveResource("playerdata.yml", false);
		this.saveResource("shops.yml", false);
		
		//loading
		CommandLoader.load(this);;
		EventListenerLoader.load(this);
		RecipeLoader.load(this);
    }
	
	//onDisable
	public void onDisable(){
		this.getLogger().info("ServerOne-Plugin disabled");
    }
	
	public static Main getPlugin() {
		return plugin;
	}
	public static WorldGuardPlugin getWorldGuard() {
		return wgPlugin;
	}
}
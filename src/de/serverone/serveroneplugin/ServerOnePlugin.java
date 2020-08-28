package de.serverone.serveroneplugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.serverone.source.util.ServerOneConfig;

public class ServerOnePlugin extends JavaPlugin {

    private static ServerOnePlugin plugin;
    private static WorldGuardPlugin wgPlugin = null;
    
    static boolean worldGuardIsEnabled = false;
    
    // onEnable
    public void onEnable() {
	plugin = this;
	
	
	Plugin worldGuard = this.getServer().getPluginManager().getPlugin("WorldGuard");
	if (!(worldGuard == null || !(worldGuard instanceof WorldGuardPlugin))) {
	    wgPlugin = (WorldGuardPlugin) worldGuard;
	    worldGuardIsEnabled = true;
	}
	
	this.getLogger().info("ServerOne-Plugin geladen");
	
	ServerOneConfig.loadConfig(this, "ServerOne/ServerOnePlugin", "config.yml", "playerdata.yml", "shops.yml");
	
	
	Loader.load(this);
    }

    // onDisable
    public void onDisable() {
	this.getLogger().info("ServerOne-Plugin disabled");
    }

    public static ServerOnePlugin getPlugin() {
	return plugin;
    }

    public static WorldGuardPlugin getWorldGuard() {
	return wgPlugin;
    }
}
package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ServerOneConfig {
	File datei;
	YamlConfiguration config;
	
	public ServerOneConfig(String datapath) {
		Plugin plugin = main.Main.getPlugin();
    	datei = new File(plugin.getDataFolder(), datapath);
    	config = YamlConfiguration.loadConfiguration(datei);
	}
	
	public YamlConfiguration getConfig() {
		return config;
	}
	
	//Moneycontroll
	public int getMoney(Player player) {
		return config.getInt(player.getUniqueId()+".stats.money");
	}
	public void setMoney(Player player, int amount) {
		 config.set(player.getUniqueId()+".stats.money", amount);
	}
	public void addMoney(Player player, int amount) {
		config.set(player.getUniqueId()+".stats.money", getMoney(player)+amount);
	}
	
	
	//Getter
	public int getPlayerInt(Player player, String path) {
		return config.getInt(player.getUniqueId()+"."+path);
	}
	public String getPlayerString(Player player, String path) {
		return config.getString(player.getUniqueId()+"."+path);
	}
	public double getPlayerDouble(Player player, String path) {
		return config.getDouble(player.getUniqueId()+"."+path);
	}
	//
	public int getInt(String path) {
		return config.getInt(path);
	}
	public String getString(String path) {
		return config.getString(path);
	}
	public double getDouble(String path) {
		return config.getDouble(path);
	}
	public List<?> getList(String path) {
		return config.getList(path);
	}
	
	
	
	//Setter
	public void setPlayer(Player player, String path, int value) {
		config.set(player.getUniqueId()+"."+path, value);
	}
	public void setPlayer(Player player, String path, String value) {
		config.set(player.getUniqueId()+"."+path, value);
	}
	public void setPlayer(Player player, String path, double value) {
		config.set(player.getUniqueId()+"."+path, value);
	}
	//
	public void set(String path, int value) {
		config.set(path, value);
	}
	public void set(String path, String value) {
		config.set(path, value);
	}
	public void set(String path, double value) {
		config.set(path, value);
	}
	@SuppressWarnings("rawtypes")
	public void set(String path, List list) {
		config.set(path, list);
	}
	public void setPlayerString(Player player, String path, String value) {
		config.set(player.getUniqueId()+"."+path, value);
	}
	//
	
	
	
	//Save
	public void save() {
		try {
			config.save(datei);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("Could not save Config");
			}
	}
}

package commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetCanOpHimselfCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	if (sender instanceof ConsoleCommandSender) {
    			if(args.length == 2) {
    				Plugin plugin = main.Main.getPlugin();
    		    	File datei = new File(plugin.getDataFolder(), "playerdata.yml");
    		    	YamlConfiguration config = YamlConfiguration.loadConfiguration(datei);
    		    	Player target = null;
    		    	try {
    		    		target = Bukkit.getServer().getPlayer(args[0]);
    		    	} catch(Exception e) {
    		    		return false;
    		    		}
    		    	
    		    	if(Integer.parseInt(args[1]) == 0) {
    		    		config.set(target.getUniqueId()+".stats.canOpHimself", false);
    		    	} else {
    		    		config.set(target.getUniqueId()+".stats.canOpHimself", true);
    		    	}
    				
    				try {
    					config.save(datei);
    				} catch(IOException  e ) {}
    			}
    		    	return true;
    		} else {
    			Player player = (Player) sender;
    			player.kickPlayer("Dieser Befehl überschreitet deine Berechtigungen bei weitem");
    			return false; 
    		}
	}

}

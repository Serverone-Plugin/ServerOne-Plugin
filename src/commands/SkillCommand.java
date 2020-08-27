package commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class SkillCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    		if(player.hasPermission("ServerOnePlugin.skill")) {
    			if(args.length == 2) {
    				Plugin plugin = main.Main.getPlugin();
    		    	File datei = new File(plugin.getDataFolder(), "playerdata.yml");
    		    	YamlConfiguration config = YamlConfiguration.loadConfiguration(datei);
    				
    		    	switch(args[0]) {
    		    	case "excavation":
    		    		config.set(player.getUniqueId() + ".skills.excavation", Integer.parseInt(args[1]));
    		    		break;
    		    	case "hunting":
    		    		config.set(player.getUniqueId() + ".skills.hunting", Integer.parseInt(args[1]));
    		    		break;
    		    	case "mining":
    		    		config.set(player.getUniqueId() + ".skills.mining", Integer.parseInt(args[1]));
    		    		break;
    		    	case "farming":
    		    		config.set(player.getUniqueId() + ".skills.farming", Integer.parseInt(args[1]));
    		    		break;
    		    	default:
    		    		player.sendMessage("§cungültiger Skill");
    		    		break;
    				}
    				try {
    					config.save(datei);
    				} catch(IOException  e ) {
    					e.printStackTrace();
    				}
    		    	return true;
    			} else return false; 
    		} else return false; 
	}

}

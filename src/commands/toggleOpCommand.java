package commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class toggleOpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    	
    	Plugin plugin = main.Main.getPlugin();
    	File datei = new File(plugin.getDataFolder(), "playerdata.yml");
    	YamlConfiguration config = YamlConfiguration.loadConfiguration(datei);
    	
    		if(config.getBoolean(player.getUniqueId()+".stats.canOpHimself")) {
    			if(args.length == 0) {
    		    	if(player.isOp()) {
    		    		player.setOp(false);
    		    		player.sendMessage("§cDu bist nun kein OP mehr");
        		    	return true;
    		    	} else {
    		    		player.setOp(true);
    		    		player.sendMessage("§2Du bist jetzt ein OP");
        		    	return true;
    		    	}
    			} else player.sendMessage("§cBitte gib kein Argument an"); return false; 
    		} else player.sendMessage("§cDu hast nicht die Berechtingung um dies zu tun"); return false; 
	}

}

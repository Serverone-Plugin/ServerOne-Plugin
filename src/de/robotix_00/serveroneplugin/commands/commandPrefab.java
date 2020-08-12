package de.robotix_00.serveroneplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandPrefab implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    		if(player.hasPermission("PERMISSION")) {
    			if(args.length == 0) {
    		    	
    				
    		    	
    		    	
    		    	return true;
    			} else return false; 
    		} else return false; 
	}

}

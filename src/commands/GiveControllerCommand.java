package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import universalGetter.ItemGetter;

public class GiveControllerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    		if(player.hasPermission("ServerOnePlugin.giveController")) {
    			if(args.length == 0) {
    		    	
    				player.getInventory().addItem(ItemGetter.getController());
    		    	
    		    	
    		    	return true;
    			} else return false; 
    		} else return false; 
	}

}

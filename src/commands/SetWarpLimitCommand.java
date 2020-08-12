package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import util.ServerOneConfig;

public class SetWarpLimitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    		if(player.isOp()) {
    			if(args.length == 2) {
    				//getting Target
    				Player target = Bukkit.getServer().getPlayer(args[0]);
    	    		if(target == null) {
    	    			player.sendMessage("§2Der Spieler §e" + args[0] + " §2scheint nicht online zu sein.");
    	    			return false;
    	    		}
    	    		
    	    		//Loading playerdata.yml
    				ServerOneConfig config = new ServerOneConfig("playerdata.yml");
    		    	
	    	    	int amount = Integer.parseInt(args[1])-1;
	    	    	if(amount >= -1 && amount <=3) {
	    	    		config.set(target.getUniqueId() + ".stats.warplimit", amount);
	    	    		player.sendMessage("§2Warplimit erfolgreich verändert");

	    	    		config.save();
	    	    		
	    	    	} else player.sendMessage("§cFalsche Angabe "+args[1]);
    			
    		    	return true;
    			} else return false; 
    		} else return false; 
	}

}

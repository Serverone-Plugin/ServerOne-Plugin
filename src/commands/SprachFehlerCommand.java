package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import util.ServerOneConfig;

public class SprachFehlerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    		if(player.isOp()) {
    			if(args.length >= 1) {
    		   		Player target = Bukkit.getPlayer(args[0]);
    		   		if(target == null) {
    		   			player.sendMessage("§cDer Spieler §2" + args[0] +"§c scheint nicht online zu sein");
    		   			return true;
    		   		}
    		   		
    		    	ServerOneConfig config = new ServerOneConfig("playerdata.yml");
    		    	
    		    	config.addMoney(target, -1);
    		    	config.save();
    		    	
    		    	if(args.length > 1) {
	    		    	String reason = "";
	    		    	for(int i = 1; i < args.length; i++) {
	    		    		reason += " " + args[i];
	    		    	}
	    		    	target.sendMessage("§cEs scheint so, als hättest du einen Sprachverstoß begangen, deshalb haben wir dir §e1 ServerOneTaler §cabgezogen.");
	    		    	target.sendMessage("§eGrund:" + reason);
    		    	} else target.sendMessage("§cEs scheint so, als hättest du einen Spracheverstoß begangen, deshalb haben wir dir §e1 ServerOneTaler §4abgezogen.");
    		    	
    		    	return true;
    			} else return false; 
    		} else return false; 
		}
	}
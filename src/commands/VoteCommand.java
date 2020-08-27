package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import util.ServerOneConfig;

public class VoteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    	
    	ServerOneConfig config = new ServerOneConfig("config.yml");
    	
    	player.sendMessage("§2Du kannst für uns §cvoten §2!");
    	player.sendMessage("§a"+config.getString("settings.votelink"));
    	
    	return true;
	}

}

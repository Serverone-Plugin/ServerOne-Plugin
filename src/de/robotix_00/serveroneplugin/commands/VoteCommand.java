package de.robotix_00.serveroneplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;

public class VoteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml");
    	
    	sender.sendMessage("§2Du kannst für uns §cvoten §2!");
    	sender.sendMessage("§a"+config.getString("settings.votelink"));
    	
    	return true;
	}

}

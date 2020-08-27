package de.serverone.serveroneplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.source.util.ServerOneConfig;

public class VoteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml");

	sender.sendMessage("§2Du kannst für uns §cvoten §2!");
	sender.sendMessage("§a" + config.getString("settings.votelink"));

	return true;
    }

}

package de.serverone.serveroneplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class toggleOpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
	// Spielerinitialisierung
	Player player = null;
	if (sender instanceof Player)
	    player = (Player) sender;
	else
	    return false;

	if (player.hasPermission("serveroneplugin.command.toggleop")) {
	    if (player.isOp()) {
		player.setOp(false);
		player.sendMessage("§cDu bist nun kein OP mehr");
		return true;
	    } else {
		player.setOp(true);
		player.sendMessage("§2Du bist jetzt ein OP");
		return true;
	    }
	} else
	    player.sendMessage("§cDu hast nicht die Berechtingung um dies zu tun");
	return false;
    }

}

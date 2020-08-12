package de.robotix_00.serveroneplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;

public class SprachFehlerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
	if (sender instanceof ConsoleCommandSender || sender.hasPermission("serverone.commands.sprachfehler")) {
	    if (args.length >= 1) {
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
		    sender.sendMessage("§cDer Spieler §2" + args[0] + "§c scheint nicht online zu sein");
		    return true;
		}

		ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");

		config.addMoney(target, -1);
		config.save();

		if (args.length > 1) {
		    String reason = "";
		    for (int i = 1; i < args.length; i++) {
			reason += " " + args[i];
		    }
		    sender.sendMessage("§aDem Spieler §e" + target.getName()
			    + "§a wurde ein ServeroneTaler abgezogen. §eGrund: " + reason);
		    target.sendMessage(
			    "§cEs scheint so, als hättest du einen Sprachverstoß begangen, deshalb haben wir dir §e1 ServerOneTaler §cabgezogen.\n§eGrund:" + reason);
		} else
		    target.sendMessage(
			    "§cEs scheint so, als hättest du einen Spracheverstoß begangen, deshalb haben wir dir §e1 ServerOneTaler §4abgezogen.");

		return true;
	    } else
		return false;
	} else
	    return false;
    }
}
package de.serverone.serveroneplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.serverone.source.util.ServerOneConfig;
import de.serverone.serveroneplugin.ServerOnePlugin;

public class MoneyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
	// Spielerinitialisierung
	Player player = null;
	if (sender instanceof Player)
	    player = (Player) sender;
	else {
	    sender.sendMessage("Du musst ein Spieler sein, um diesen Befehl zu verwenden");
	    return true;
	}
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");

	// bank
	if (args.length == 0) {
	    int balance = config.getConfig().getInt(player.getUniqueId() + ".stats.money");
	    player.sendMessage("§aDu hast §e" + balance + " ServerOneTaler");
	    return true;
	} else
	// bank <player>
	if (args.length == 1) {
	    Player target = Bukkit.getServer().getPlayer(args[0]);
	    if (target == null) {
		player.sendMessage("§2Der Spieler §e" + args[0] + " §2scheint nicht online zu sein.");
		return false;
	    }
	    int balance = config.getConfig().getInt(target.getUniqueId() + ".stats.money");
	    player.sendMessage("§e" + target.getName() + " §2hat §e" + balance + " ServerOneTaler §2");
	    return true;
	} else
	// bank <player> <set/get>
	if (args.length == 3) {
	    if (player.hasPermission("serveroneplugin.command.money")) {
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
		    player.sendMessage("§2Der Spieler §e" + args[0] + " §2scheint nicht online zu sein.");
		    return true;
		}
		switch (args[1]) {
		case "set":
		    config.getConfig().set(target.getUniqueId() + ".stats.money", Integer.parseInt(args[2]));
		    player.sendMessage(
			    "§2Geld von §e" + target.getName() + " §2auf §e" + args[2] + " ServerOneTaler §2gesetzt");
		    break;
		case "add":
		    int balance1 = config.getConfig().getInt(target.getUniqueId() + ".stats.money")
			    + Integer.parseInt(args[2]);
		    config.getConfig().set(target.getUniqueId() + ".stats.money", balance1);
		    player.sendMessage(
			    "§2Geld von §e" + target.getName() + " §2auf §e" + balance1 + " ServerOneTaler §2gesetzt");
		    break;
		case "get":
		    int balance2 = config.getConfig().getInt(target.getUniqueId() + ".stats.money")
			    - Integer.parseInt(args[2]);
		    config.getConfig().set(target.getUniqueId() + ".stats.money", balance2);
		    player.sendMessage(
			    "§2Geld von §e" + target.getName() + " §2auf §e" + balance2 + " ServerOneTaler §2gesetzt");
		    break;
		default:
		    player.sendMessage("§cungültiger Paramerter §e" + args[1]);
		    return false;
		}

		config.save();

		return true;
	    } else
		return false;
	} else
	    return false;
    }
}

package commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetMainWarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    		if(player.hasPermission("ServerOnePlugin.setmainwarp")) {
    			if(args.length == 1) {
    				Plugin plugin = main.Main.getPlugin();
    		    	File datei = new File(plugin.getDataFolder(), "config.yml");
    		    	YamlConfiguration config = YamlConfiguration.loadConfiguration(datei);

    		    	if(args[0].equals("Spawn") || args[0].equals("FreeBuild")) {
	    				config.set("MainWarps." +args[0] +".World", player.getWorld().getName());
			    		config.set("MainWarps." +args[0] +".X", (double) Math.round(player.getLocation().getX()*10)/10);
			    		config.set("MainWarps." +args[0] +".Y", Math.round(player.getLocation().getY()));
			    		config.set("MainWarps." +args[0] +".Z", (double) Math.round(player.getLocation().getZ()*10)/10);
			    		config.set("MainWarps." +args[0] +".Yaw", Math.round(player.getLocation().getYaw()));
			    		config.set("MainWarps." +args[0] +".Pitch", Math.round(player.getLocation().getPitch()));
			    		player.sendMessage("§cDer MainWarpPunkt §e"+args[0] + " §cwurde platziert");
    		    	} else {
    		    		player.sendMessage("§cMainwarppunktname existiert nicht");
    		    	}
					try {
						config.save(datei);
					} catch(IOException  e ) {
						e.printStackTrace();
					}
    		    	return true;
    			} else return false; 
    		} else return false; 
	}

}

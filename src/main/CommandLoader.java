package main;

import org.bukkit.plugin.java.JavaPlugin;

import commands.MoneyCommand;
import commands.GiveControllerCommand;
import commands.SetCanOpHimselfCommand;
import commands.SetMainWarpCommand;
import commands.SetWarpLimitCommand;
import commands.SkillCommand;
import commands.SprachFehlerCommand;
import commands.VoteCommand;
import commands.sopCommand;
import commands.toggleOpCommand;

public class CommandLoader {
	public static void load(JavaPlugin plugin) {
		
		plugin.getCommand("skill").setExecutor(new SkillCommand());
		plugin.getCommand("getcontroller").setExecutor(new GiveControllerCommand());
		plugin.getCommand("setmainwarp").setExecutor(new SetMainWarpCommand());
		plugin.getCommand("money").setExecutor(new MoneyCommand());
		plugin.getCommand("sop").setExecutor(new sopCommand());
		plugin.getCommand("setwarplimit").setExecutor(new SetWarpLimitCommand());
		plugin.getCommand("toggleOP").setExecutor(new toggleOpCommand());
		plugin.getCommand("setcanselfop").setExecutor(new SetCanOpHimselfCommand());
		
		plugin.getCommand("sprachfehler").setExecutor(new SprachFehlerCommand());
		plugin.getCommand("vote").setExecutor(new VoteCommand());
	}
}

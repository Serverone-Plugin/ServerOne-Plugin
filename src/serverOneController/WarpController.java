package serverOneController;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import util.ServerOneConfig;

public class WarpController {
    Player player;

    public WarpController(Player player) {
	this.player = player;
    }

    public void ToWarpPoint(WarpPoint warp) {
	World world = null;
	short y = 0;
	int yaw = 0;
	int pitch = 0;
	double x = 0;
	double z = 0;
	Location destination = player.getLocation();

	if (warp == WarpPoint.Point_1 || warp == WarpPoint.Point_2 || warp == WarpPoint.Point_3
		|| warp == WarpPoint.Point_4) {
	    ServerOneConfig config = new ServerOneConfig("playerdata.yml");

	    int number = 0;
	    if (warp == WarpPoint.Point_1)
		number = 1;
	    if (warp == WarpPoint.Point_2)
		number = 2;
	    if (warp == WarpPoint.Point_3)
		number = 3;
	    if (warp == WarpPoint.Point_4)
		number = 4;

	    try {
		world = Bukkit.getWorld(config.getPlayerString(player, "Warps.Warp_" + number + ".World"));
		x = config.getPlayerDouble(player, "Warps.Warp_" + number + ".X");
		y = (short) config.getPlayerInt(player, "Warps.Warp_" + number + ".Y");
		z = config.getPlayerDouble(player, "Warps.Warp_" + number + ".Z");
		yaw = config.getPlayerInt(player, "Warps.Warp_" + number + ".Yaw");
		pitch = config.getPlayerInt(player, "Warps.Warp_" + number + ".Pitch");
	    } catch (Exception e) {
		player.sendMessage("§l§cUps, sieht so aus, als ob du diesen Warppoint nicht gesetzt hast.");
		return;
	    }
	    destination = new Location(world, x, y, z, yaw, pitch);
	} else if (warp == WarpPoint.Spawn || warp == WarpPoint.FreeBuild) {
	    ServerOneConfig config = new ServerOneConfig("config.yml");
	    String warp_name = "";
	    if (warp == WarpPoint.Spawn)
		warp_name = "Spawn";
	    if (warp == WarpPoint.FreeBuild)
		warp_name = "FreeBuild";
	    try {
		world = Bukkit.getWorld(config.getString("MainWarps." + warp + ".World"));
		x = config.getDouble("MainWarps." + warp_name + ".X");
		y = (short) config.getInt("MainWarps." + warp_name + ".Y");
		z = config.getDouble("MainWarps." + warp_name + ".Z");
		yaw = config.getInt("MainWarps." + warp_name + ".Yaw");
		pitch = config.getInt("MainWarps." + warp_name + ".Pitch");
	    } catch (Exception e) {
		player.sendMessage(
			"§cUps dieser Warppunkt scheint nicht zu funktionieren, bitte melde dieses Problem einem Supporter");
		return;
	    }
	    destination = new Location(world, x, y, z, yaw, pitch);
	} else if (warp == WarpPoint.Home) {
	    try {
		destination = player.getBedSpawnLocation();
	    } catch (Exception e) {
		player.sendMessage("§cDu hast keinen Bettspawnpunkt");
		return;
	    }
	}
	player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
	player.teleport(destination);
    }

    public void setWarppoint(int number) {
	ServerOneConfig config = new ServerOneConfig("playerdata.yml");
	config.set(player.getUniqueId() + ".Warps.Warp_" + number + ".World", player.getWorld().getName());
	config.set(player.getUniqueId() + ".Warps.Warp_" + number + ".X",
		(double) Math.round(player.getLocation().getX() * 10) / 10);
	config.set(player.getUniqueId() + ".Warps.Warp_" + number + ".Y", Math.round(player.getLocation().getY()));
	config.set(player.getUniqueId() + ".Warps.Warp_" + number + ".Z",
		(double) Math.round(player.getLocation().getZ() * 10) / 10);
	config.set(player.getUniqueId() + ".Warps.Warp_" + number + ".Yaw", Math.round(player.getLocation().getYaw()));
	config.set(player.getUniqueId() + ".Warps.Warp_" + number + ".Pitch",
		Math.round(player.getLocation().getPitch()));
	player.sendMessage("§aDu hast §eWarppoint " + number + " §agesetzt");

	config.save();
    }
}

package de.serverone.serveroneplugin.server_one_controller;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.source.util.ServerOneConfig;

public enum Warp implements Listener {
    Spawn("Spawn"), FreeBuild("FreeBuild"), Home("Home"), Point_1("Warp_1"), Point_2("Warp_2"), Point_3("Warp_3"),
    Point_4("Warp_4");

    String name;
    static long waitTime = 100;
    public static HashMap<Player, Integer> allowed = new HashMap<>();

    private Warp(String name) {
	this.name = name;
    }

    public void warp(Player player) {
	if (getLocation(player) == null) {
	    player.sendMessage("§cDieser Warppunkt scheint nicht gesetzt zu sein");
	    player.closeInventory();
	    cancel(player);
	    return;
	}
	player.sendMessage("§aDu wirst in 5 Sekunden teleportiert");
	player.closeInventory();
	allowed.put(player, Bukkit.getScheduler().scheduleSyncDelayedTask(ServerOnePlugin.getPlugin(), new Runnable() {
	    @Override
	    public void run() {
		if (allowed.containsKey(player)) {
		    allowed.remove(player);
		    Location loc = getLocation(player);
		    player.teleport(loc);
		    player.sendMessage("§aDu wurdest teleportiert");
		    allowed.remove(player);
		}
	    }
	}, waitTime));
    }

    public void setWarppoint(Player player) {
	switch (this) {
	case Spawn:
	case FreeBuild:
	    ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml");
	    config.set("MainWarps." + name + ".World", player.getWorld().getName());
	    config.set("MainWarps." + name + ".X", (double) Math.round(player.getLocation().getX() * 10) / 10);
	    config.set("MainWarps." + name + ".Y", Math.round(player.getLocation().getY()));
	    config.set("MainWarps." + name + ".Z", (double) Math.round(player.getLocation().getZ() * 10) / 10);
	    config.set("MainWarps." + name + ".Yaw", Math.round(player.getLocation().getYaw()));
	    config.set("MainWarps." + name + ".Pitch", Math.round(player.getLocation().getPitch()));
	    config.save();
	    player.sendMessage("§aDu hast §e" + name + " §agesetzt");
	    return;
	case Home:
	    return;
	default:
	    break;
	}

	{
	    ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");
	    config.set(player.getUniqueId() + ".Warps." + name + ".World", player.getWorld().getName());
	    config.set(player.getUniqueId() + ".Warps." + name + ".X",
		    (double) Math.round(player.getLocation().getX() * 10) / 10);
	    config.set(player.getUniqueId() + ".Warps." + name + ".Y", Math.round(player.getLocation().getY()));
	    config.set(player.getUniqueId() + ".Warps." + name + ".Z",
		    (double) Math.round(player.getLocation().getZ() * 10) / 10);
	    config.set(player.getUniqueId() + ".Warps." + name + ".Yaw", Math.round(player.getLocation().getYaw()));
	    config.set(player.getUniqueId() + ".Warps." + name + ".Pitch", Math.round(player.getLocation().getPitch()));
	    player.sendMessage("§aDu hast §e" + name + " §agesetzt");

	    config.save();
	}
    }

    public Location getLocation(Player player) {
	Location destination = null;
	ServerOneConfig config;

	switch (this) {
	case Spawn:
	case FreeBuild:
	    config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "config.yml");
	    try {
		destination = new Location(Bukkit.getWorld(config.getString("MainWarps." + name + ".World")),
			config.getDouble("MainWarps." + name + ".X"), (short) config.getInt("MainWarps." + name + ".Y"),
			config.getDouble("MainWarps." + name + ".Z"), config.getInt("MainWarps." + name + ".Yaw"),
			config.getInt("MainWarps." + name + ".Pitch"));
	    } catch (Exception e) {
	    }
	    break;
	case Point_1:
	case Point_2:
	case Point_3:
	case Point_4:
	    config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");
	    try {
		destination = new Location(Bukkit.getWorld(config.getPlayerString(player, "Warps." + name + ".World")),
			config.getPlayerDouble(player, "Warps." + name + ".X"),
			(short) config.getPlayerInt(player, "Warps." + name + ".Y"),
			config.getPlayerDouble(player, "Warps." + name + ".Z"),
			config.getPlayerInt(player, "Warps." + name + ".Yaw"),
			config.getPlayerInt(player, "Warps." + name + ".Pitch"));
	    } catch (Exception e) {
	    }
	    break;

	case Home:
	    try {
		destination = player.getBedSpawnLocation();
	    } catch (Exception e) {
	    }
	    break;
	}

	return destination;
    }

    public static class WarpCancelListener implements Listener {
	@EventHandler
	public static void onDamageEvent(EntityDamageEvent event) {
	    if (!(event.getEntity() instanceof Player))
		return;
	    cancel((Player) event.getEntity());
	}

	@EventHandler
	public static void onWalkEvent(PlayerMoveEvent event) {
	    Location from = event.getFrom();
	    Location to = event.getTo();
	    if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY()
		    && from.getBlockZ() == to.getBlockZ())
		return;

	    cancel(event.getPlayer());
	}
    }

    private static void cancel(Player player) {
	if (allowed.containsKey(player)) {
	    Bukkit.getScheduler().cancelTask(allowed.get(player));
	    allowed.remove(player);
	    player.sendMessage("§cDer Teleportvorgang wurde abgebrochen");
	}
    }
}

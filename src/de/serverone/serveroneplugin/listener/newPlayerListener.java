package de.serverone.serveroneplugin.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.serverone.serveroneplugin.server_one_controller.Warp;
import de.serverone.source.builder.ItemBuilder;

public class newPlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	Player player = event.getPlayer();
	if (player.hasPlayedBefore())
	    return;
	player.teleport(Warp.Spawn.getLocation(player));

	player.getInventory().addItem(new ItemBuilder(Material.COMPASS).setName("§6§lServerOneController").build());
	player.getInventory().addItem(new ItemBuilder(Material.BREAD, 64).build());
	player.getInventory().addItem(new ItemBuilder(Material.STONE_SWORD).build());
	player.getInventory().addItem(new ItemBuilder(Material.STONE_PICKAXE).build());
	player.getInventory().addItem(new ItemBuilder(Material.STONE_AXE).build());
	player.getInventory().addItem(new ItemBuilder(Material.STONE_SHOVEL).build());
    }
}

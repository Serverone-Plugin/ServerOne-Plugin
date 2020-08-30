package de.serverone.serveroneplugin.server_one_controller;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.serveroneplugin.serverShop.Inventory_Villager;
import de.serverone.serveroneplugin.universalGetter.BookGetter;
import de.serverone.source.util.ServerOneWorldGuard;

public class SOCListener implements Listener {
    final static HashMap<Player, ServerOneController> controllers = new HashMap<>();

    @EventHandler
    public void openServerOneController(PlayerInteractEvent event) {
	ItemStack item = event.getItem();
	Player player = event.getPlayer();

	if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()
		|| !item.getItemMeta().getDisplayName().equals("§6§lServerOneController"))
	    return;
	if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    if (controllers.containsKey(player))
		controllers.remove(player);
	    controllers.put(player, new ServerOneController(player));
	    controllers.get(player).open(ControllerWindow.MAIN);
	    event.setCancelled(true);
	}
    }

    @EventHandler
    public void InvClick(InventoryClickEvent event) {
	if (!(event.getWhoClicked() instanceof Player))
	    return;

	if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR
		|| !(event.getCurrentItem().hasItemMeta() || event.getCurrentItem().getItemMeta().hasLore()
			|| event.getCurrentItem().getItemMeta().hasDisplayName()))
	    return;

	final List<String> lores = event.getCurrentItem().getItemMeta().getLore();
	if (lores == null || !lores.contains("§8MenuItem"))
	    return;
	final Player player = (Player) event.getWhoClicked();
	final String displayname = event.getCurrentItem().getItemMeta().getDisplayName();
	final int slot = event.getSlot();
	final String viewName = event.getView().getTitle();
	final ServerOneController controller = controllers.get(player);

	if (viewName.equals(ControllerWindow.MAIN.invName)) {
	    switch (displayname) {
	    case "§b§lSkills":
		controller.open(ControllerWindow.SKILLS);
		break;
	    case "§b§lWarps":
		controller.open(ControllerWindow.WARPS);
		break;
	    case "§6§lPremium":
		if (player.hasPermission("serveroneplugin.premium"))
		    controller.open(ControllerWindow.PREMIUM);
		else
		    player.sendMessage("§cDu bist kein Premium!");
		break;
	    case "§b§lGrundstückseinstellungen":
		if (ServerOnePlugin.worldGuardIsEnabled() && ServerOneWorldGuard.getOwnersRegion(player) != null) {
		    controller.open(ControllerWindow.GS_SETTINGS);
		} else {
		    player.sendMessage("§cDu hast keinen Zugriff auf diese Region!");
		}
		break;
	    case "§6§lDas Regelwerk":
		controller.close();
		player.openBook(BookGetter.getServerRules());
	    default:
		break;
	    }
	    return;
	}
	if (viewName.equals(ControllerWindow.SKILLS.invName)) {
	    switch (displayname) {
	    case "§6§lzurück":
		controller.openPrevirous();
		break;
	    case "§cExcavation":
		break;
	    case "§cHunting":
		break;
	    case "§cMining":
		break;
	    case "§cFarming":
		break;
	    default:
		break;
	    }
	    return;
	}
	if (viewName.equals(ControllerWindow.WARPS.invName)) {
	    switch (displayname) {
	    case "§6§lzurück":
		controller.openPrevirous();
		break;
	    case "§6§lSpawn":
		Warp.Spawn.warp(player);
		break;
	    case "§2§lFreeBuild":
		Warp.FreeBuild.warp(player);
		break;
	    case "§4§lHome":
		Warp.Home.warp(player);
		break;
	    case "§5§lWarppoint 1":
		Warp.Point_1.warp(player);
		break;
	    case "§5§lWarppoint 2":
		Warp.Point_2.warp(player);
		break;
	    case "§5§lWarppoint 3":
		Warp.Point_3.warp(player);
		break;
	    case "§5§lWarppoint 4":
		Warp.Point_4.warp(player);
		break;
	    case "§b§lset Warppoints":
		controller.open(ControllerWindow.SETWARP);
		break;
	    default:
		break;
	    }
	    return;
	}

	if (viewName.equals(ControllerWindow.SETWARP.invName)) {
	    switch (displayname) {
	    case "§6§lzurück":
		controller.openPrevirous();
		break;
	    case "§b§lset Warppoint 1":
		Warp.Point_1.setWarppoint(player);
		player.closeInventory();
		break;
	    case "§b§lset Warppoint 2":
		Warp.Point_2.setWarppoint(player);
		player.closeInventory();
		break;
	    case "§b§lset Warppoint 3":
		Warp.Point_3.setWarppoint(player);
		player.closeInventory();
		break;
	    case "§b§lset Warppoint 4":
		Warp.Point_4.setWarppoint(player);
		player.closeInventory();
		break;
	    }
	}
	if (viewName.equals(ControllerWindow.PREMIUM.invName)) {
	    switch (displayname) {
	    case "§6§lzurück":
		controller.openPrevirous();
		break;
	    case "§d§lEnderChest":
		event.getWhoClicked().openInventory(event.getWhoClicked().getEnderChest());
		break;
	    case "§b§lWorkbench":
		event.getWhoClicked().openWorkbench(event.getWhoClicked().getLocation(), true);
		break;
	    case "§6§lBank":
		player.openInventory(Inventory_Villager.getBankInv(player));
		break;
	    default:
		break;
	    }
	    return;
	}
	if (viewName.equals(ControllerWindow.GS_SETTINGS.invName)) {
	    if(ServerOnePlugin.worldGuardIsEnabled()) {
		WorldGuardController.worldGuardListener(player, displayname, controller);
	    }
	}
	if (viewName.equals(ControllerWindow.GS_MEMBERS.invName)) {
	    if(ServerOnePlugin.worldGuardIsEnabled()) {
		WorldGuardController.gsMemberListener(player, displayname, controller);
	    }
	}
    }
}

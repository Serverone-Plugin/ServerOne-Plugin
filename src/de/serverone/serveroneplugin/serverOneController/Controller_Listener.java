package de.serverone.serveroneplugin.serverOneController;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.serverone.serveroneplugin.serverShop.Inventory_Villager;
import de.serverone.serveroneplugin.universalGetter.BookGetter;
import de.serverone.source.util.ServerOneWorldGuard;

public class Controller_Listener implements Listener {
    @EventHandler
    public void NavigatorOpenListener(PlayerInteractEvent event) {
	Inventorys invs = new Inventorys(event.getPlayer());
	ItemStack item = event.getItem();
	if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()
		|| !item.getItemMeta().getDisplayName().equals("§6§lServerOneController"))
	    return;
	if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    event.setCancelled(true);
	    event.getPlayer().openInventory(invs.getMainInv());
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

	final Player player = (Player) event.getWhoClicked();
	final Inventorys invs = new Inventorys(player);
	final String displayname = event.getCurrentItem().getItemMeta().getDisplayName();
	final List<String> lores = event.getCurrentItem().getItemMeta().getLore();

	if (lores == null || !lores.contains("§8MenuItem"))
	    return;

	if (event.getView().getTitle() == ConInv.MAIN.getInvName()) {
	    switch (displayname) {
	    case "§b§lSkills":
		player.openInventory(invs.getSkillInv());
		break;
	    case "§b§lWarps":
		player.openInventory(invs.getWarpInv());
		break;
	    case "§6§lPremium":
		if (player.hasPermission("serveroneplugin.premium"))
		    player.openInventory(invs.getPremiumInv());
		else
		    player.sendMessage("§cDu bist kein Premium!");
		break;
	    case "§b§lGrundstückseinstellungen":
		if (ServerOneWorldGuard.getOwnersRegion(player) != null) {
		    player.openInventory(invs.getWGInv());
		} else {
		    player.sendMessage("§cDu hast keinen Zugriff auf diese Region!");
		}

		break;
	    case "§6§lDas Regelwerk":
		player.openBook(BookGetter.getServerRules());
	    default:
		break;
	    }
	    return;
	}

	// SkillController
	if (event.getView().getTitle() == ConInv.SKILLS.getInvName()) {
	    switch (displayname) {
	    case "§6§lzurück":
		player.openInventory(invs.getMainInv());
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
	/*
	 * //SKILLS NOT AVAIBLE JET// //Skill-Excavation if(event.getView().getTitle()
	 * == InvNameGetter.getInvSkillsExc()) { event.setCancelled(true);
	 * 
	 * switch(displayname) { case "§6§lzurück":
	 * player.openInventory(SOCInv.SKILLS.getInventory(player)); break; default:
	 * break; } return; }
	 * 
	 * //Skill-Hunting if(event.getView().getTitle() ==
	 * InvNameGetter.getInvSkillsHunt()) { event.setCancelled(true);
	 * switch(displayname) { case "§6§lzurück":
	 * player.openInventory(SOCInv.SKILLS.getInventory(player)); break; default:
	 * break; } return; }
	 * 
	 * //Skill-Mine if(event.getView().getTitle() ==
	 * InvNameGetter.getInvSkillsMine()) { event.setCancelled(true);
	 * 
	 * switch(displayname) { case "§6§lzurück":
	 * player.openInventory(SOCInv.SKILLS.getInventory(player)); break; default:
	 * break; } return; }
	 * 
	 * //Skill-Farming if(event.getView().getTitle() ==
	 * InvNameGetter.getInvSkillsFarm()) { event.setCancelled(true);
	 * 
	 * switch(displayname) { case "§6§lzurück":
	 * player.openInventory(SOCInv.SKILLS.getInventory(player)); break; default:
	 * break; } return; }
	 */

	// WarpController
	if (event.getView().getTitle() == ConInv.WARPS.getInvName()) {
	    switch (displayname) {
	    case "§6§lzurück":
		player.openInventory(invs.getMainInv());
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
		player.openInventory(invs.getSetWarpInv());
		break;
	    default:
		break;
	    }
	    return;
	}

	// setWarpController
	if (event.getView().getTitle() == ConInv.SETWARP.getInvName()) {
	    switch (displayname) {
	    case "§6§lzurück":
		player.openInventory(invs.getWarpInv());
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

	// PremiumController
	if (event.getView().getTitle() == ConInv.PREMIUM.getInvName()) {
	    switch (displayname) {
	    case "§6§lzurück":
		player.openInventory(invs.getMainInv());
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
	if (event.getView().getTitle() == ConInv.GS_SETTINGS.getInvName()) {
	    ProtectedRegion region = ServerOneWorldGuard.getOwnersRegion(player);
	    if (region == null) {
		player.sendMessage("§cDu hast keinen Zugriff auf diese Region!");
		player.closeInventory();
		return;
	    }
	    
	    boolean simpleFlag = true;
	    
	    State state = null;
	    List<StateFlag> flags = new ArrayList<>();
	    String flagName = "";
	    
	    switch (displayname) {
	    case "§bInteract":
		flags.add(Flags.INTERACT);
		flagName = "Interact";
		break;
	    case "§bSchneefall":
		flags.add(Flags.SNOW_FALL);
		flagName = "Schneefall";
		break;
	    case "§bFahrzeuge":
		flags.add(Flags.DESTROY_VEHICLE);
		flags.add(Flags.PLACE_VEHICLE);
		flagName = "Fahrzeuge";
		break;
	    case "§bPVP":
		flags.add(Flags.PVP);
		flagName = "PVP";
		break;
	    case "§bMobspawning":
		flags.add(Flags.MOB_SPAWNING);
		flags.add(Flags.MOB_DAMAGE);
		flagName = "Mobspawning";
		break;
	    default:
		simpleFlag = false;
	    }
	    if (simpleFlag) {
		for(StateFlag flag : flags) {
		if (region.getFlag(flag) == null || region.getFlag(flag).equals(State.DENY))
		    state = State.ALLOW;
		else
		    state = State.DENY;
		region.setFlag(flag, state);
		}
		player.sendMessage("§aDer Flag §e" + flagName + "§a wurde erfolgreich verändert §e(" + state.toString() + ")");
		return;
	    }
	    switch(displayname) {
	    case "§6§lzurück":
		player.openInventory(invs.getMainInv());
		break;
	    }
	}
    }
}

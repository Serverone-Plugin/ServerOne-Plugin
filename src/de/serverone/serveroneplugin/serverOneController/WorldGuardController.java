package de.serverone.serveroneplugin.serverOneController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.serverone.source.builder.DefaultMenuBuilder;
import de.serverone.source.builder.ItemBuilder;
import de.serverone.source.builder.SkullBuilder;
import de.serverone.source.util.ServerOneWorldGuard;

public class WorldGuardController {
    public static void worldGuardListener(Player player, String displayname) {
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
	    flags.add(Flags.PLACE_VEHICLE);
	    flags.add(Flags.DESTROY_VEHICLE);
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
	    if (flags.size() == 0)
		return;
	    if (region.getFlag(flags.get(0)) == null || region.getFlag(flags.get(0)).equals(State.DENY))
		state = State.ALLOW;
	    else
		state = State.DENY;
	    for (StateFlag flag : flags) {
		region.setFlag(flag, state);
	    }
	    player.sendMessage(
		    "§aDer Flag §e" + flagName + "§a wurde erfolgreich verändert §e(" + state.toString() + ")");
	    player.openInventory(getWGInv(player));
	    return;
	}
	switch (displayname) {
	case "§6§lzurück":
	    player.openInventory(new Inventorys(player).getMainInv());
	    break;
	case "§bMitglieder":
	    player.openInventory(getGsMemberInv(region, player));
	    break;
	}

    }

    public static void gsMemberListener(Player player, String displayname) {
	switch (displayname) {
	case "§6§lzurück":
	    player.openInventory(getWGInv(player));
	    break;
	}
    }

    public static Inventory getWGInv(Player player) {
	Inventory inv = new DefaultMenuBuilder(ConInv.GS_SETTINGS.getInvName())
		.setBarSide(new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§b§lGrundstückseinstellungen")
			.setMenuItem().build())
		.build();
	ProtectedRegion rg = ServerOneWorldGuard.getOwnersRegion(player);
	if (rg == null)
	    return inv;

	inv.setItem(19, new ItemBuilder(Material.OAK_FENCE_GATE).setName("§bInteract")
		.addLore(getWGLore(rg, Flags.INTERACT)).setMenuItem().build());
	inv.setItem(21, new ItemBuilder(Material.SNOW).setName("§bSchneefall").addLore(getWGLore(rg, Flags.SNOW_FALL))
		.setMenuItem().build());
	inv.setItem(23, new ItemBuilder(Material.MINECART).setName("§bFahrzeuge")
		.addLore(getWGLore(rg, Flags.PLACE_VEHICLE)).setMenuItem().build());
	inv.setItem(25, new ItemBuilder(Material.DIAMOND_SWORD).setName("§bPVP").addLore(getWGLore(rg, Flags.PVP))
		.setMenuItem().build());
	inv.setItem(37, new ItemBuilder(Material.ZOMBIE_HEAD).setName("§bMobspawning")
		.addLore(getWGLore(rg, Flags.MOB_SPAWNING)).setMenuItem().build());
	inv.setItem(39, new ItemBuilder(Material.PLAYER_HEAD).setName("§bMitglieder").setMenuItem().build());
	return inv;
    }

    public static Inventory getGsMemberInv(ProtectedRegion region, Player player) {
	Inventory inv = new DefaultMenuBuilder(ConInv.GS_MEMBERS.getInvName()).setBarSide(
		new ItemBuilder(Material.PLAYER_HEAD).setName("§b§lGrundstücksmitglieder").setMenuItem().build())
		.build();
	
	inv.setItem(18, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§bverfügbare Spieler").setMenuItem().build());
	inv.setItem(36, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§bGrundstücksmitglieder").setMenuItem().build());
	
	int memberPos = 0;
	int playerPos = 0;
	List<UUID> memberUuids = new ArrayList<>(region.getMembers().getUniqueIds());
	List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
	
	for (int i=37; i <= 43; i++) {
	    if (memberPos >= memberUuids.size())
		break;
	    inv.setItem(i, new SkullBuilder(Bukkit.getOfflinePlayer(memberUuids.get(memberPos)))
		    .setName("§b" + Bukkit.getOfflinePlayer(memberUuids.get(memberPos)).getName()).addLore("§8"+memberUuids.get(memberPos)).setMenuItem().build());
	    memberPos++;
	}
	for(UUID offlinePlayer : memberUuids) {
	    Player nowPlayer = Bukkit.getPlayer(offlinePlayer);
	    if(nowPlayer != null) {
		onlinePlayers.remove(nowPlayer);
	    }
	}
	onlinePlayers.remove(player);
	for(int i = 19; i <= 25; i++) {
	    if(playerPos >= onlinePlayers.size())
		break;
	    inv.setItem(i, new SkullBuilder(onlinePlayers.get(playerPos)).setName("§b"+onlinePlayers.get(playerPos).getName()).setMenuItem().build());
	    playerPos++;
	}
	
	return inv;
    }

    private static String getWGLore(ProtectedRegion region, StateFlag flag) {
	if (region.getFlag(flag) == null)
	    return "§7(none)";
	if (region.getFlag(flag) == State.ALLOW)
	    return "§7(allow)";
	if (region.getFlag(flag) == State.DENY)
	    return "§7(deny)";
	return "";
    }
}

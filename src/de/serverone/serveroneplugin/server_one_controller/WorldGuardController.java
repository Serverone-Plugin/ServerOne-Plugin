package de.serverone.serveroneplugin.server_one_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.serverone.source.builder.DefaultMenuBuilder;
import de.serverone.source.builder.ItemBuilder;
import de.serverone.source.builder.SkullBuilder;
import de.serverone.source.util.ServerOneWorldGuard;

public class WorldGuardController {
    public static void worldGuardListener(Player player, String displayname, ServerOneController controller) {
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
	    controller.open(ControllerWindow.GS_SETTINGS);
	    return;
	}
	switch (displayname) {
	case "§bMitglieder":
	    controller.open(ControllerWindow.GS_MEMBERS);
	    break;
	}
    }

    @SuppressWarnings("unchecked")
    public static void gsMemberListener(Player player, String displayname, int slot, ItemStack item,
	    ServerOneController controller) {
	ProtectedRegion region = ServerOneWorldGuard.getOwnersRegion(player);
	if (region == null) {
	    player.sendMessage("§cDu hast keinen Zugriff auf diese Region!");
	    player.closeInventory();
	    return;
	}

	int playerpos = (int) controller.getMeta("gssettings").get("playerpos");
	int memberpos = (int) controller.getMeta("gssettings").get("memberpos");
	if (slot >= 19 && slot <= 25 && displayname != "") {
	    DefaultDomain members = region.getMembers();
	    Player member = ((List<Player>) controller.getMeta("gssettings").get("potencialMembers"))
		    .get(slot - 19 + playerpos);
	    if (member == null || displayname.substring(1).equals(member.getName())) {
		player.sendMessage("§cDer ausgewählte Spieler ist nicht mehr online!");
		controller.open(ControllerWindow.GS_MEMBERS);
		return;
	    }

	    members.addPlayer(member.getUniqueId());
	    region.setMembers(members);

	    controller.open(ControllerWindow.GS_MEMBERS);
	    player.sendMessage("§aDu hast §e" + member.getName() + " §adeinem GS hinzugefügt!");
	    return;
	}

	if (slot >= 37 && slot <= 43 && displayname != "") {
	    DefaultDomain members = region.getMembers();
	    UUID member = ((List<UUID>) controller.getMeta("gssettings").get("members")).get(slot - 37 + memberpos);
	    if (member == null || displayname.substring(1).equals(Bukkit.getOfflinePlayer(member).getName())) {
		controller.open(ControllerWindow.GS_MEMBERS);
		player.sendMessage("§cEs ist ein Fehler aufgetreten");
		return;
	    }
	    members.removePlayer(member);
	    region.setMembers(members);

	    player.openInventory(getGsMemberInv(player, controller));
	    player.sendMessage(
		    "§aDu hast §e" + Bukkit.getOfflinePlayer(member).getName() + " §avon deinem GS entfernt!");
	    return;
	}
	boolean bool = false;
	switch (slot) {
	case 18:
	    if (playerpos > 0) {
		playerpos--;
		bool = true;
	    }
	    break;
	case 26:
	    if (playerpos + 7 < ((List<Player>) controller.getMeta("gssettings").get("potencialMembers")).size()) {
		playerpos++;
		bool = true;
	    }
	    break;
	case 36:
	    if (memberpos > 0) {
		memberpos--;
		bool = true;
	    }
	    break;
	case 44:
	    if (memberpos + 7 < ((List<UUID>) controller.getMeta("gssettings").get("members")).size()) {
		memberpos++;
		bool = true;
	    }

	    break;
	}
	if (bool) {
	    controller.getMeta("gssettings").put("memberpos", memberpos);
	    controller.getMeta("gssettings").put("playerpos", playerpos);
	    controller.open(ControllerWindow.GS_MEMBERS);
	}
    }

    public static Inventory getWGInv(Player player) {
	Inventory inv = new DefaultMenuBuilder(ControllerWindow.GS_SETTINGS.invName)
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

    public static Inventory getGsMemberInv(Player player, ServerOneController controller) {
	HashMap<String, Object> map = controller.getMeta("gssettings");
	if (map == null) {
	    controller.addMeta("gssettings");
	    map = controller.getMeta("gssettings");
	}
	if (!map.containsKey("memberpos")) {
	    map.put("memberpos", 0);
	}
	if (!map.containsKey("playerpos")) {
	    map.put("playerpos", 0);
	}
	Integer memberPos = (int) map.get("memberpos");
	Integer playerPos = (int) map.get("playerpos");

	Inventory inv = new DefaultMenuBuilder(ControllerWindow.GS_MEMBERS.invName).setBarSide(
		new ItemBuilder(Material.PLAYER_HEAD).setName("§b§lGrundstücksmitglieder").setMenuItem().build())
		.build();
	ProtectedRegion region = ServerOneWorldGuard.getOwnersRegion(player);
	inv.setItem(18,
		new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§bverfügbare Spieler").setMenuItem().build());
	inv.setItem(36, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§bGrundstücksmitglieder")
		.setMenuItem().build());
	inv.setItem(26,
		new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§bverfügbare Spieler").setMenuItem().build());
	inv.setItem(44, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§bGrundstücksmitglieder")
		.setMenuItem().build());

	List<UUID> memberUuids = new ArrayList<>(region.getMembers().getUniqueIds());
	List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

	map.put("members", memberUuids);
	for (int i = 37; i <= 43; i++) {
	    if (!(memberPos >= memberUuids.size())) {
		inv.setItem(i,
			new SkullBuilder(Bukkit.getOfflinePlayer(memberUuids.get(memberPos)))
				.setName("§b" + Bukkit.getOfflinePlayer(memberUuids.get(memberPos)).getName())
				.setMenuItem().build());
		memberPos++;
	    } else
		inv.setItem(i, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setMenuItem().build());

	}
	for (UUID offlinePlayer : memberUuids) {
	    Player nowPlayer = Bukkit.getPlayer(offlinePlayer);
	    if (nowPlayer != null) {
		onlinePlayers.remove(nowPlayer);
	    }
	}
	onlinePlayers.remove(player);

	map.put("potencialMembers", onlinePlayers);
	for (int i = 19; i <= 25; i++) {
	    if (!(playerPos >= onlinePlayers.size())) {
		inv.setItem(i, new SkullBuilder(onlinePlayers.get(playerPos))
			.setName("§b" + onlinePlayers.get(playerPos).getName()).setMenuItem().build());
		playerPos++;
	    } else
		inv.setItem(i, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setMenuItem().build());

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

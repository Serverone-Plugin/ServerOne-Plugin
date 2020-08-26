package de.serverone.serveroneplugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.robotix_00.serverone.source.builder.ItemBuilder;
import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.serveroneplugin.serverOneController.Warp;
import de.serverone.serveroneplugin.test.TestFunctions;
import de.serverone.serveroneplugin.universalGetter.ItemGetter;

public class sopCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
	// Spielerinitialisierung
	Player player = null;
	if (sender instanceof Player)
	    player = (Player) sender;
	else
	    return false;
	if (player.hasPermission("serveroneplugin.command.sop")) {

	    Inventory inventory = Bukkit.createInventory(null, 4 * 9);
	    ServerOneConfig con_shops = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "shops.yml");
	    if (args.length == 0) {
		sendInfo(player);
		return true;
	    }
	    switch (args[0].toLowerCase()) {
	    /* Book-command */
	    case "book":
		player.openBook(TestFunctions.getBook());
		break;

	    /* spawn-command */
	    case "spawn":
		if (args.length < 1) {
		    sendInfo(player);
		    return true;
		}
		spawn(player, args[1]);
		break;

	    /* utilInv-Command */
	    case "utilinv":
		inventory.setItem(10, new ItemBuilder(Material.DIAMOND_AXE).setName("§6Lumber-Axe").build());
		inventory.setItem(12, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§6Miners Pickaxe")
			.setLore("§dMode: 1x1", "Shift + Rechtsklick zum umschalten").build());
		inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).setName("§5§lShulkerSword")
			.setLore("§dMode: none").build());
		inventory.setItem(16, new ItemBuilder(Material.EMERALD).setName("§6§lShop-Kern")
			.setLore("§bBesitzer: " + player.getName()).build());
		inventory.setItem(19, new ItemBuilder(Material.PAPER).setName("§3§lShop-Liste").build());
		inventory.setItem(21, new ItemBuilder(Material.PAPER).setName("§6Kreditkarte")
			.setLore("§bBesitzer: " + player.getName(), "§bWert: 0").build());
		player.openInventory(inventory);
		break;

	    /* getMoney-Command */
	    case "getmoney":
		if (args.length < 2)
		    break;
		int amount = 1;
		try {
		    amount = Integer.parseInt(args[1]);
		} catch (Exception e) {
		}
		ItemStack coins = ItemGetter.getCoin();
		coins.setAmount(amount);
		player.getInventory().addItem(coins);
		break;

	    /* getController-Command */
	    case "getcontroller":
		player.getInventory().addItem(ItemGetter.getController());
		break;

	    /* spawnShop-command */
	    case "spawnshop":
		if (args.length < 2) {
		    sendInfo(player);
		    return true;
		}
		List<String> shops = con_shops.getList("shops");

		if (args.length < 2 || !shops.contains(args[1])) {
		    String shopList = "";
		    for (String now : shops) {
			if (shopList.equals(""))
			    shopList = ChatColor.GREEN + now;
			else
			    shopList += ChatColor.RED + "/" + ChatColor.GREEN + now;
		    }
		    player.sendMessage("§cMögliche Shops sind: " + shopList);
		    break;
		}

		Villager shop = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
		shop.setCustomName(con_shops.getString(args[1] + ".invname"));
		shop.setVillagerType(Type.valueOf(con_shops.getString(args[1] + ".type").toUpperCase()));
		shop.setProfession(Profession.valueOf(con_shops.getString(args[1] + ".profession").toUpperCase()));
		shop.setInvulnerable(true);
		shop.setAI(false);
		shop.setCanPickupItems(false);
		shop.setSilent(true);
		shop.setCollidable(false);
		break;

	    /* setWarpLimit-Command */
	    case "setwarplimit":
		if (args.length < 3) {
		    sendInfo(player);
		    return true;
		}
		setWarpLimit(player, Bukkit.getPlayer(args[1]), args[2]);
		break;

	    /* setMainWarp-Command */
	    case "setmainwarp":
		if (args[1].equalsIgnoreCase("spawn"))
		    Warp.Spawn.setWarppoint(player);
		else if (args[1].equalsIgnoreCase("freebuild"))
		    Warp.FreeBuild.setWarppoint(player);
		else
		    player.sendMessage("§cUngültiger Warppoint");
		break;

	    default:
		sendInfo(player);
		break;
	    }
	} else {
	    player.sendMessage("§cDu hast die Berechtigung das zu tun nicht!");
	}
	return true;
    }

    private void sendInfo(Player player) {
	player.sendMessage(new String[] { "§4==== SOP-Hilfe ====", "§c/sop spawn bankier/bernd/wahrsagerin",
		"§c/sop spawnshop <shopname>", "§c/sop getmoney <amount>", "§c/sop utilInv", "§c/sop getcontroller",
		"§c/sop setMainWarp <spawn/freebuild>", "§c/sop setWarpLimit <player> <amount>" });
    }

    private void spawn(Player player, String villager) {
	switch (villager.toLowerCase()) {
	case "bankier":
	    Villager bankier = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
	    bankier.setCustomName("§6§lBankier");
	    bankier.setInvulnerable(true);
	    bankier.setAI(false);
	    bankier.setCanPickupItems(false);
	    bankier.setSilent(true);
	    bankier.setVillagerType(Type.TAIGA);
	    bankier.setProfession(Profession.LIBRARIAN);
	    bankier.setCollidable(false);
	    break;
	case "bernd":
	    Villager bernd = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
	    bernd.setCustomName("§6§lBernd");
	    bernd.setInvulnerable(true);
	    bernd.setAI(false);
	    bernd.setCanPickupItems(false);
	    bernd.setSilent(true);
	    bernd.setVillagerType(Type.TAIGA);
	    bernd.setProfession(Profession.CARTOGRAPHER);
	    bernd.setCollidable(false);
	    break;
	case "wahrsagerin":
	    Villager wahr = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
	    wahr.setCustomName("§6§lWahrsagerin");
	    wahr.setInvulnerable(true);
	    wahr.setAI(false);
	    wahr.setCanPickupItems(false);
	    wahr.setSilent(true);
	    wahr.setVillagerType(Type.TAIGA);
	    wahr.setProfession(Profession.CLERIC);
	    wahr.setCollidable(false);
	    break;
	default:
	    player.sendMessage("§cUngültige Eingabe");
	}
    }

    private void setWarpLimit(Player player, Player target, String s_amount) {
	if (target == null) {
	    player.sendMessage("§2Der Spieler scheint nicht online zu sein.");
	    return;
	}

	// Loading playerdata.yml
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");

	int amount = -2;
	try {
	    amount = Integer.parseInt(s_amount) - 1;
	} catch (Exception e) {
	}

	if (amount >= -1 && amount <= 3) {
	    config.set(target.getUniqueId() + ".stats.warplimit", amount);
	    player.sendMessage("§2Warplimit erfolgreich verändert");

	    config.save();
	} else
	    player.sendMessage("§cUngültige Angabe " + s_amount);

	return;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandlable, String[] args) {
	ServerOneConfig con_shops = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "shops.yml");
	if (!sender.hasPermission("serveroneplugin.command.sop"))
	    return null;
	switch (args.length) {
	case 1:
	    return toCompleteList(args[0], "book", "spawn", "utilinv", "getmoney", "spawnshop", "setwarplimit",
		    "setmainwarp", "getcontroller");
	case 2:
	    switch (args[0].toLowerCase()) {
	    case "spawnshop":
		return toCompleteList(args[1], con_shops.getList("shops"));
	    case "spawn":
		return toCompleteList(args[1], "wahrsagerin", "bankier", "bernd");
	    case "setmainwarp":
		return toCompleteList(args[1], "spawn", "freebuild");
	    }

	}
	return null;
    }

    private List<String> toCompleteList(String start, String... strings) {
	List<String> list = new ArrayList<>();

	for (String now : strings) {
	    if (now.equalsIgnoreCase(start) || now.toLowerCase().startsWith(start.toLowerCase()))
		list.add(now);
	}
	return list;
    }

    private List<String> toCompleteList(String start, List<String> strings) {
	List<String> list = new ArrayList<>();

	for (String now : strings) {
	    if (now.equalsIgnoreCase(start) || now.toLowerCase().startsWith(start.toLowerCase()))
		list.add(now);
	}
	return list;
    }
}

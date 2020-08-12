package de.robotix_00.serveroneplugin.serverOneController;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.robotix_00.serverone.source.builder.DefaultMenuBuilder;
import de.robotix_00.serverone.source.builder.ItemBuilder;
import de.robotix_00.serverone.source.builder.SkullBuilder;
import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;

public class Inventorys {
    Inventory inv = null;
    Player player;
    static ServerOneConfig playerData = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");

    public Inventorys(Player player) {
	this.player = player;
    }

    public Inventory getMainInv() {
	inv = new DefaultMenuBuilder(ConInv.MAIN.getInvName())
		.setBorderAround().setBarMiddle(new SkullBuilder(player)
			.setName("§b§l" + player.getName() + "§3s ServerOneController").setLore("§8MenuItem").build())
		.build();
	inv.setItem(20, new ItemBuilder(Material.ENCHANTING_TABLE).setName("§b§lSkills").setLore("§czum Skillmenü")
		.setMenuItem().build());
	inv.setItem(22, new ItemBuilder(Material.CARTOGRAPHY_TABLE).setName("§b§lWarps").setLore("§czum Warpmenü")
		.setMenuItem().build());
	inv.setItem(24, new ItemBuilder(Material.BOOK).setName("§6§lDas Regelwerk")
		.setLore("§clese das mächtige Regelwerk").setMenuItem().build());
	inv.setItem(42, new ItemBuilder(Material.NETHER_STAR).setName("§6§lPremium").setLore("§czu den Premiumfeatures")
		.setMenuItem().build());
	for (int i = 38; i <= 40; i = i + 2)
	    inv.setItem(i, new ItemBuilder(Material.BARRIER).setName("§4coming soon").setMenuItem().build());
	return inv;
    }

    public Inventory getPremiumInv() {
	inv = new DefaultMenuBuilder(ConInv.PREMIUM.getInvName())
		.setBarSide(new ItemBuilder(Material.NETHER_STAR).setName("§6§lPremium").setMenuItem().build()).build();
	inv.setItem(19, new ItemBuilder(Material.CRAFTING_TABLE).setName("§b§lWorkbench").setLore("§czum Craftingtable")
		.setMenuItem().build());
	inv.setItem(21, new ItemBuilder(Material.ENDER_CHEST).setName("§d§lEnderChest").setLore("§czur Enderchest")
		.setMenuItem().build());
	inv.setItem(23,
		new ItemBuilder(Material.GOLD_NUGGET).setName("§6§lBank").setLore("§czur Bank").setMenuItem().build());
	return inv;
    }

    public Inventory getSkillInv() {
	inv = new DefaultMenuBuilder(ConInv.SKILLS.getInvName())
		.setBarSide(new ItemBuilder(Material.ENCHANTING_TABLE).setName("§cSkills").setMenuItem().build())
		.build();

	inv.setItem(19, new ItemBuilder(Material.DIRT).setName("§cExcavation").setMenuItem().build());
	inv.setItem(21, new ItemBuilder(Material.ROTTEN_FLESH).setName("§cHunting").setMenuItem().build());
	inv.setItem(23, new ItemBuilder(Material.DIAMOND_ORE).setName("§cMining").setMenuItem().build());
	inv.setItem(25, new ItemBuilder(Material.WHEAT).setName("§cFarming").setMenuItem().build());
	for (int i = 37; i <= 43; i = i + 2)
	    inv.setItem(i, new ItemBuilder(Material.BARRIER).setName("§4coming soon").setMenuItem().build());
	return inv;
    }

    public Inventory getSetWarpInv() {
	int warpLimit = playerData.getPlayerInt(player, "stats.warplimit");
	inv = Bukkit.createInventory(null, 3 * 9, ConInv.SETWARP.getInvName());
	for (int i = 0; i <= 8; i++)
	    inv.setItem(i, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("§6Rand").setMenuItem().build());
	for (int i = 18; i <= 26; i++)
	    inv.setItem(i, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("§6Rand").setMenuItem().build());
	for (int i = 9; i <= 17; i++)
	    inv.setItem(i,
		    new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName("§7nischd").setMenuItem().build());
	inv.setItem(4, new ItemBuilder(Material.ARROW).setName("§6§lzurück").setMenuItem().build());

	if (warpLimit >= 0) {
	    inv.setItem(10, new ItemBuilder(Material.PAPER, 1).setName("§b§lset Warppoint 1").setMenuItem().build());
	} else
	    inv.setItem(10, new ItemBuilder(Material.BARRIER, 1).setName("§4§l Warppoint 1 nicht freigeschaltet")
		    .setMenuItem().build());

	if (warpLimit >= 1) {
	    inv.setItem(12, new ItemBuilder(Material.PAPER, 2).setName("§b§lset Warppoint 2").setMenuItem().build());
	} else
	    inv.setItem(12, new ItemBuilder(Material.BARRIER, 1).setName("§4§l Warppoint 2 nicht freigeschaltet")
		    .setMenuItem().build());

	if (warpLimit >= 2) {
	    inv.setItem(14, new ItemBuilder(Material.PAPER, 3).setName("§b§lset Warppoint 3").setMenuItem().build());
	} else
	    inv.setItem(14, new ItemBuilder(Material.BARRIER, 1).setName("§4§l Warppoint 3 nicht freigeschaltet")
		    .setMenuItem().build());

	if (warpLimit >= 3) {
	    inv.setItem(16, new ItemBuilder(Material.PAPER, 4).setName("§b§lset Warppoint 4").setMenuItem().build());
	} else
	    inv.setItem(16, new ItemBuilder(Material.BARRIER, 1).setName("§4§l Warppoint 4 nicht freigeschaltet")
		    .setMenuItem().build());

	return inv;
    }

    public Inventory getWarpInv() {
	int warpLimit = playerData.getPlayerInt(player, "stats.warplimit");
	inv = new DefaultMenuBuilder(ConInv.WARPS.getInvName())
		.setBarSide(new ItemBuilder(Material.CARTOGRAPHY_TABLE).setName("§cWarps").setMenuItem().build())
		.build();

	inv.setItem(19, new ItemBuilder(Material.EMERALD_BLOCK).setName("§6§lSpawn").setMenuItem().build());
	inv.setItem(21, new ItemBuilder(Material.GRASS_BLOCK).setName("§2§lFreeBuild").setMenuItem().build());
	inv.setItem(23, new ItemBuilder(Material.BARRIER).setName("§4coming soon").setMenuItem().build());
	inv.setItem(25, new ItemBuilder(Material.RED_BED).setName("§4§lHome").setMenuItem().build());

	if (warpLimit >= 0) {
	    String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_1.World") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_1.X") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_1.Y") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_1.Z");
	    if (lore.equals("null 0 0 0"))
		lore = "§cNicht gesetzt";
	    inv.setItem(37, new ItemBuilder(Material.ENDER_PEARL, 1).setName("§5§lWarppoint 1").setLore(lore)
		    .setMenuItem().build());
	} else
	    inv.setItem(37, new ItemBuilder(Material.BARRIER, 1).setName("§4§lWarppoint 1 nicht freigeschaltet")
		    .setMenuItem().build());

	if (warpLimit >= 1) {
	    String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_2.World") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_2.X") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_2.Y") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_2.Z");
	    if (lore.equals("null 0 0 0"))
		lore = "§cNicht gesetzt";
	    inv.setItem(39, new ItemBuilder(Material.ENDER_PEARL, 2).setName("§5§lWarppoint 2").setLore(lore)
		    .setMenuItem().build());
	} else
	    inv.setItem(39, new ItemBuilder(Material.BARRIER, 2).setName("§4§lWarppoint 2 nicht freigeschaltet")
		    .setMenuItem().build());

	if (warpLimit >= 2) {
	    String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_3.World") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_3.X") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_3.Y") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_3.Z");
	    if (lore.equals("null 0 0 0"))
		lore = "§cNicht gesetzt";
	    inv.setItem(41, new ItemBuilder(Material.ENDER_PEARL, 3).setName("§5§lWarppoint 3").setLore(lore)
		    .setMenuItem().build());
	} else
	    inv.setItem(41, new ItemBuilder(Material.BARRIER, 3).setName("§4§lWarppoint 3 nicht freigeschaltet")
		    .setMenuItem().build());

	if (warpLimit >= 3) {
	    String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_4.World") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_4.X") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_4.Y") + " "
		    + playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_4.Z");
	    if (lore.equals("null 0 0 0"))
		lore = "§cNicht gesetzt";
	    inv.setItem(43, new ItemBuilder(Material.ENDER_PEARL, 4).setName("§5§lWarppoint 4").setLore(lore)
		    .setMenuItem().build());
	} else
	    inv.setItem(43, new ItemBuilder(Material.BARRIER, 4).setName("§4§lWarppoint 4 nicht freigeschaltet")
		    .setMenuItem().build());

	inv.setItem(36, new ItemBuilder(Material.WRITABLE_BOOK).setName("§b§lset Warppoints").setMenuItem().build());

	return inv;
    }
    public Inventory getTpaInv() {
	inv = new DefaultMenuBuilder(ConInv.TPA.getInvName()).build();
	int[] slots = {};
	int i = 0;
	
	for(Player now : Bukkit.getOnlinePlayers()) {
	    if(player != now) {
		inv.setItem(slots[i], new SkullBuilder(now).build());
		
		i++;
	    }
	}
	return inv;
    }
}

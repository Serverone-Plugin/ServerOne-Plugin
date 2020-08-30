package de.serverone.serveroneplugin.server_one_controller;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.source.builder.DefaultMenuBuilder;
import de.serverone.source.builder.ItemBuilder;
import de.serverone.source.builder.SkullBuilder;
import de.serverone.source.util.ServerOneConfig;
import de.serverone.source.util.ServerOneWorldGuard;

public enum ControllerWindow {
    MAIN("�6�lServerOneController", null), SKILLS("�6�lSkills", MAIN), SKILL_SUB("�6�lSkill ", SKILLS),
    WARPS("�6�lWarps", MAIN), TPA("�6�lSpieler-Teleportationen", WARPS), SETWARP("�6�lsetWarps", WARPS),
    PREMIUM("�6�lPremium", MAIN), GS_SETTINGS("�6�lGrundst�ckseinstellungen", MAIN),
    GS_MEMBERS("�6�lGrundst�cksmitglieder", GS_SETTINGS);

    final String invName;
    final ControllerWindow previrous;

    private ControllerWindow(String invName, ControllerWindow previrous) {
	this.invName = invName;
	this.previrous = previrous;
    }

    public void open(Player player) {
	player.openInventory(getControllerInventory(player));
    }

    private Inventory getControllerInventory(Player player) {
	Inventory inv = new DefaultMenuBuilder("�cfehler").build();
	ServerOneConfig playerData = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");
	int warpLimit = playerData.getPlayerInt(player, "stats.warplimit");

	switch (this) {
	case MAIN:
	    inv = new DefaultMenuBuilder(this.invName).setBorderAround().setBarMiddle(new SkullBuilder(player)
		    .setName("�b�l" + player.getName() + "�3s ServerOneController").setLore("�8MenuItem").build())
		    .build();

	    inv.setItem(20, new ItemBuilder(Material.ENCHANTING_TABLE).setName("�b�lSkills").setLore("�czum Skillmen�")
		    .setMenuItem().build());

	    inv.setItem(22, new ItemBuilder(Material.CARTOGRAPHY_TABLE).setName("�b�lWarps").setLore("�czum Warpmen�")
		    .setMenuItem().build());

	    inv.setItem(24, new ItemBuilder(Material.BOOK).setName("�6�lDas Regelwerk")
		    .setLore("�clese das m�chtige Regelwerk").setMenuItem().build());

	    if (ServerOnePlugin.worldGuardIsEnabled() && ServerOneWorldGuard.getOwnersRegion(player) != null)
		inv.setItem(38, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("�b�lGrundst�ckseinstellungen")
			.setMenuItem().build());
	    else
		inv.setItem(38, new ItemBuilder(Material.TOTEM_OF_UNDYING)
			.setName("�b�lGrundst�ckseinstellungen �c(nicht verf�gbar)").setMenuItem().build());

	    inv.setItem(42, new ItemBuilder(Material.NETHER_STAR).setName("�6�lPremium")
		    .setLore("�czu den Premiumfeatures").setMenuItem().build());

	    inv.setItem(40, new ItemBuilder(Material.BARRIER).setName("�4coming soon").setMenuItem().build());
	    break;
	case PREMIUM:
	    inv = new DefaultMenuBuilder(this.invName)
		    .setBarSide(new ItemBuilder(Material.NETHER_STAR).setName("�6�lPremium").setMenuItem().build())
		    .build();
	    inv.setItem(19, new ItemBuilder(Material.CRAFTING_TABLE).setName("�b�lWorkbench")
		    .setLore("�czum Craftingtable").setMenuItem().build());
	    inv.setItem(21, new ItemBuilder(Material.ENDER_CHEST).setName("�d�lEnderChest").setLore("�czur Enderchest")
		    .setMenuItem().build());
	    inv.setItem(23, new ItemBuilder(Material.GOLD_NUGGET).setName("�6�lBank").setLore("�czur Bank")
		    .setMenuItem().build());
	    break;
	case WARPS:
	    inv = new DefaultMenuBuilder(ControllerWindow.WARPS.invName)
		    .setBarSide(new ItemBuilder(Material.CARTOGRAPHY_TABLE).setName("�cWarps").setMenuItem().build())
		    .build();

	    inv.setItem(19, new ItemBuilder(Material.EMERALD_BLOCK).setName("�6�lSpawn").setMenuItem().build());
	    inv.setItem(21, new ItemBuilder(Material.GRASS_BLOCK).setName("�2�lFreeBuild").setMenuItem().build());
	    inv.setItem(23, new ItemBuilder(Material.BARRIER).setName("�4coming soon").setMenuItem().build());
	    inv.setItem(25, new ItemBuilder(Material.RED_BED).setName("�4�lHome").setMenuItem().build());

	    if (warpLimit >= 0) {
		String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_1.World") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_1.X") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_1.Y") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_1.Z");
		if (lore.equals("null 0 0 0"))
		    lore = "�cNicht gesetzt";
		inv.setItem(37, new ItemBuilder(Material.ENDER_PEARL, 1).setName("�5�lWarppoint 1").setLore(lore)
			.setMenuItem().build());
	    } else
		inv.setItem(37, new ItemBuilder(Material.BARRIER, 1).setName("�4�lWarppoint 1 nicht freigeschaltet")
			.setMenuItem().build());

	    if (warpLimit >= 1) {
		String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_2.World") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_2.X") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_2.Y") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_2.Z");
		if (lore.equals("null 0 0 0"))
		    lore = "�cNicht gesetzt";
		inv.setItem(39, new ItemBuilder(Material.ENDER_PEARL, 2).setName("�5�lWarppoint 2").setLore(lore)
			.setMenuItem().build());
	    } else
		inv.setItem(39, new ItemBuilder(Material.BARRIER, 2).setName("�4�lWarppoint 2 nicht freigeschaltet")
			.setMenuItem().build());

	    if (warpLimit >= 2) {
		String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_3.World") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_3.X") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_3.Y") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_3.Z");
		if (lore.equals("null 0 0 0"))
		    lore = "�cNicht gesetzt";
		inv.setItem(41, new ItemBuilder(Material.ENDER_PEARL, 3).setName("�5�lWarppoint 3").setLore(lore)
			.setMenuItem().build());
	    } else
		inv.setItem(41, new ItemBuilder(Material.BARRIER, 3).setName("�4�lWarppoint 3 nicht freigeschaltet")
			.setMenuItem().build());

	    if (warpLimit >= 3) {
		String lore = playerData.getConfig().getString(player.getUniqueId() + ".Warps.Warp_4.World") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_4.X") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_4.Y") + " "
			+ playerData.getConfig().getInt(player.getUniqueId() + ".Warps.Warp_4.Z");
		if (lore.equals("null 0 0 0"))
		    lore = "�cNicht gesetzt";
		inv.setItem(43, new ItemBuilder(Material.ENDER_PEARL, 4).setName("�5�lWarppoint 4").setLore(lore)
			.setMenuItem().build());
	    } else
		inv.setItem(43, new ItemBuilder(Material.BARRIER, 4).setName("�4�lWarppoint 4 nicht freigeschaltet")
			.setMenuItem().build());

	    inv.setItem(36,
		    new ItemBuilder(Material.WRITABLE_BOOK).setName("�b�lset Warppoints").setMenuItem().build());
	    break;
	case SETWARP:
	    inv = Bukkit.createInventory(null, 3 * 9, this.invName);
	    for (int i = 0; i <= 8; i++)
		inv.setItem(i,
			new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("�6Rand").setMenuItem().build());
	    for (int i = 18; i <= 26; i++)
		inv.setItem(i,
			new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("�6Rand").setMenuItem().build());
	    for (int i = 9; i <= 17; i++)
		inv.setItem(i, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName("�7nischd").setMenuItem()
			.build());
	    inv.setItem(4, new ItemBuilder(Material.ARROW).setName("�6�lzur�ck").setMenuItem().build());

	    if (warpLimit >= 0) {
		inv.setItem(10,
			new ItemBuilder(Material.PAPER, 1).setName("�b�lset Warppoint 1").setMenuItem().build());
	    } else
		inv.setItem(10, new ItemBuilder(Material.BARRIER, 1).setName("�4�l Warppoint 1 nicht freigeschaltet")
			.setMenuItem().build());

	    if (warpLimit >= 1) {
		inv.setItem(12,
			new ItemBuilder(Material.PAPER, 2).setName("�b�lset Warppoint 2").setMenuItem().build());
	    } else
		inv.setItem(12, new ItemBuilder(Material.BARRIER, 1).setName("�4�l Warppoint 2 nicht freigeschaltet")
			.setMenuItem().build());

	    if (warpLimit >= 2) {
		inv.setItem(14,
			new ItemBuilder(Material.PAPER, 3).setName("�b�lset Warppoint 3").setMenuItem().build());
	    } else
		inv.setItem(14, new ItemBuilder(Material.BARRIER, 1).setName("�4�l Warppoint 3 nicht freigeschaltet")
			.setMenuItem().build());

	    if (warpLimit >= 3) {
		inv.setItem(16,
			new ItemBuilder(Material.PAPER, 4).setName("�b�lset Warppoint 4").setMenuItem().build());
	    } else
		inv.setItem(16, new ItemBuilder(Material.BARRIER, 1).setName("�4�l Warppoint 4 nicht freigeschaltet")
			.setMenuItem().build());
	    break;
	case GS_SETTINGS:
	    inv = WorldGuardController.getWGInv(player);
	    break;
	case GS_MEMBERS:
	    inv = WorldGuardController.getGsMemberInv(player);
	    break;
	case SKILLS:
	    inv = new DefaultMenuBuilder(this.invName)
		    .setBarSide(new ItemBuilder(Material.ENCHANTING_TABLE).setName("�cSkills").setMenuItem().build())
		    .build();

	    inv.setItem(19, new ItemBuilder(Material.DIRT).setName("�cExcavation").setMenuItem().build());
	    inv.setItem(21, new ItemBuilder(Material.ROTTEN_FLESH).setName("�cHunting").setMenuItem().build());
	    inv.setItem(23, new ItemBuilder(Material.DIAMOND_ORE).setName("�cMining").setMenuItem().build());
	    inv.setItem(25, new ItemBuilder(Material.WHEAT).setName("�cFarming").setMenuItem().build());
	    for (int i = 37; i <= 43; i = i + 2)
		inv.setItem(i, new ItemBuilder(Material.BARRIER).setName("�4coming soon").setMenuItem().build());
	    break;
	case SKILL_SUB:
	    break;
	case TPA:
	    // TODO
	    inv = new DefaultMenuBuilder(this.invName).build();
	    int[] slots = {};
	    int tpaPosition = 0;

	    for (Player now : Bukkit.getOnlinePlayers()) {
		if (player != now) {
		    inv.setItem(slots[tpaPosition], new SkullBuilder(now).build());

		    tpaPosition++;
		}
	    }
	    break;
	default:
	    return null;
	}
	return inv;
    }
}

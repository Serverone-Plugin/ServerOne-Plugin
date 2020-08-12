package de.robotix_00.serveroneplugin.playerShop;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import de.robotix_00.serveroneplugin.test.Function;

public class SimplePlayerShop implements Listener {
    @EventHandler
    public void simpelShopClick(PlayerInteractEvent event) {
	// alpha-filter
	if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
	    return;

	// assignment
	Player player = event.getPlayer();
	Block block = event.getClickedBlock();
	Sign sign;

	// beta-filter
	if (block.getState() instanceof Sign)
	    sign = (Sign) block.getState();
	else
	    return;
	if (!(sign.getBlockData() instanceof WallSign))
	    return;
	if (!sign.getLine(1).equals(player.getName()))
	    return;
	if (sign.getLine(2).split("\\s+").length != 2)
	    return;

	if (sign.getLine(0).toLowerCase().equals("shop")) {
	    if (Material.matchMaterial(sign.getLine(2).split("\\s+")[0]) == null)
		return;
	    sign.setLine(0, "§6§lShop");
	    sign.update();
	    return;
	}
	if (!sign.getLine(0).equals("§6§lShop"))
	    return;
	Block attached = Function.getBlockOnSign(sign);
	if (!(attached.getState() instanceof Container))
	    return;
	Inventory inv = ((Container) attached.getState()).getInventory();

	switch (sign.getLine(2).split("\\s+")[1].toLowerCase()) {
	case "io":

	    break;
	case "i":
	    break;
	case "o":
	    break;
	}
	player.openInventory(inv);
    }
}

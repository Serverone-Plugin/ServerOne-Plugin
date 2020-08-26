package de.serverone.serveroneplugin.playerShop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.serverone.serveroneplugin.test.Function;

//TODO xyzXyz zu xyz-Xyz
public class ShopRegisterListener implements Listener {
    @EventHandler
    public void registerLink(PlayerInteractEvent event) {
	if (!(event.hasItem() && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
	    return;
	// Assignment
	Player player = event.getPlayer();
	ItemStack item = player.getInventory().getItemInMainHand();
	Block block = event.getClickedBlock();
	Sign sign;

	if (!event.getItem().getItemMeta().getDisplayName().equals("§3§lShop-Liste"))
	    return;
	if (block.getState() instanceof Sign)
	    sign = (Sign) block.getState();
	else
	    return;

	if (!(sign.getLine(0).startsWith("§4§lShop-Link") || sign.getLine(0).startsWith("§a§lShop-Link")))
	    return;

	if (!sign.getLine(1).equals(player.getName()))
	    return;

	if (!item.getItemMeta().hasLore()
		|| !item.getItemMeta().getLore().contains("§a" + locToString(block.getLocation()))) {
	    item = addLore(item, "§a" + locToString(block.getLocation()));
	    player.sendMessage("§2Link registriert");
	} else
	    player.sendMessage("§cDiesen Link hast du schon registriert");
    }

    /* clear */
    @EventHandler
    public void clearRegisterings(PlayerInteractEvent event) {
	// alpha-filter
	if (!event.hasItem() || !event.getItem().getItemMeta().getDisplayName().equals("§3§lShop-Liste"))
	    return;
	if (!event.getPlayer().isSneaking() || event.hasBlock())
	    return;

	// assignment
	Player player = event.getPlayer();
	ItemStack item = player.getInventory().getItemInMainHand();
	ItemMeta meta = item.getItemMeta();
	if (!meta.hasLore())
	    return;

	player.sendMessage("§c" + meta.getLore().size() + " Link(s) gelöscht");

	meta.setLore(null);
	item.setItemMeta(meta);
    }

    /* of-off-switch */
    @EventHandler
    public void activateSign(PlayerInteractEvent event) {
	// alpha-filter
	if (event.hasItem() || !event.hasBlock())
	    return;

	// assignment
	Player player = event.getPlayer();
	Block block = event.getClickedBlock();
	Sign sign;

	// beta-filter
	if (!(block.getState().getBlockData() instanceof WallSign))
	    return;
	sign = (Sign) block.getState();
	if (!(sign.getLine(0).toLowerCase().startsWith("§4§lshop-link")
		|| sign.getLine(0).toLowerCase().startsWith("§a§lshop-link")
		|| sign.getLine(0).toLowerCase().startsWith("shop")))
	    return;
	Block attached = Function.getBlockOnSign(sign);
	if (!(attached.getState() instanceof Container))
	    return;

	if (!sign.getLine(1).equals(player.getName()))
	    return;
	if (Material.matchMaterial(sign.getLine(2).toUpperCase()) == null) {
	    player.sendMessage("§cDieser ShopLink funktioniert nicht");
	    return;
	}
	
	// switching
	switch (sign.getLine(0).toLowerCase()) {
	case "shoplink":
	case "§4§lshop-link aus":
	    sign.setLine(0, "§a§lShop-Link an");
	    break;
	case "§a§lshop-link an":
	    sign.setLine(0, "§4§lShop-Link aus");
	    break;
	}

	// finish
	sign.update();
    }

    /* private methodes */
    private static ItemStack addLore(ItemStack item, String string) {
	ItemMeta meta = item.getItemMeta();
	List<String> lores = new ArrayList<>();
	if (meta.hasLore())
	    lores = meta.getLore();
	lores.add(string);
	meta.setLore(lores);
	item.setItemMeta(meta);
	return item;
    }

    private static String locToString(Location loc) {
	return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
    }
}

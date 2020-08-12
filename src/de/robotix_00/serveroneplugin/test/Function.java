package de.robotix_00.serveroneplugin.test;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;

public class Function {
    public static boolean canStack(Inventory inv, ItemStack item, int amount) {
	for (ItemStack now : inv.getStorageContents()) {
	    if (now == null)
		amount -= item.getMaxStackSize();
	    else {
		if (now.isSimilar(item)) {
		    amount -= now.getMaxStackSize() - now.getAmount();
		}
	    }
	    if (amount <= 0)
		return true;
	}
	return false;
    }

    public static int countItem(Inventory inv, ItemStack item) {
	int counter = 0;
	if (item == null || item.getType() == Material.AIR)
	    return 0;

	for (ItemStack now : inv.getContents()) {
	    if (now != null) {
		if (now.isSimilar(item)) {
		    counter += now.getAmount();
		}
	    }
	}
	return counter;
    }

    public static int countStackLimit(Inventory inv, ItemStack item) {
	int count = 0;

	for (ItemStack now : inv.getStorageContents()) {
	    if (now == null)
		count += item.getMaxStackSize();
	    else {
		if (now.isSimilar(item)) {
		    count += now.getMaxStackSize() - now.getAmount();
		}
	    }
	}

	return count;
    }

    public static String getMessage(String configPath, String path) {
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), configPath);
	List<String> sprüche = (List<String>) config.getList(path);
	return sprüche.get((int) Math.round(Math.random() * (sprüche.size() - 1)));
    }

    public static Block getBlockOnSign(Sign sign) {
	Location loc = sign.getLocation();
	BlockFace face = ((WallSign) sign.getBlockData()).getFacing();
	switch (face) {
	case EAST:
	    loc.add(-1, 0, 0);
	    break;
	case NORTH:
	    loc.add(0, 0, 1);
	    break;
	case SOUTH:
	    loc.add(0, 0, -1);
	    break;
	case WEST:
	    loc.add(1, 0, 0);
	    break;
	default:
	    break;
	}
	return loc.getBlock();
    }
}

package de.robotix_00.serveroneplugin.serverShop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;
import de.robotix_00.serveroneplugin.test.Function;
import de.robotix_00.serveroneplugin.universalGetter.ItemGetter;

public class ShopBuilder {
    ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");

    public static void sell(Player player, ItemStack item, int price) {
	if (player.getInventory().containsAtLeast(item, item.getAmount())) {
	    if (Function.canStack(player.getInventory(), ItemGetter.getCoin(), price)) {
		player.getInventory().removeItem(item);
		player.getInventory().addItem(ItemGetter.getCoin(price));

	    } else
		player.sendMessage("§cHartes Leben. Du hast keinen Platz für das Geld");
	} else
	    player.sendMessage("§cDu hast nicht genügend Items");
    }

    public static void buy(Player player, ItemStack item, int price) {
	if (player.getInventory().containsAtLeast(ItemGetter.getCoin(), price)) {
	    if (Function.canStack(player.getInventory(), item, item.getAmount())) {
		player.getInventory().removeItem(ItemGetter.getCoin(price));
		player.getInventory().addItem(item);
	    } else
		player.sendMessage("§cDu hast nicht genügend Platz");
	} else
	    player.sendMessage("§cDu hast nicht genügend Geld");
    }
}

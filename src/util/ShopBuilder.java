package util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import universalGetter.ItemGetter;

public class ShopBuilder {
	ServerOneConfig config = new ServerOneConfig("playerdata.yml");
	public static void sell(Player player, ItemStack item, int price) {
		if(player.getInventory().containsAtLeast(item, item.getAmount())) {
			if(Function.canStack(player, ItemGetter.getCoin(), price)) {
				player.getInventory().removeItem(item);
				player.getInventory().addItem(ItemGetter.getCoin(price));
				
			} else player.sendMessage("�cHartes Leben. Du hast keinen Platz f�r das Geld");
		} else player.sendMessage("�cDu hast nicht gen�gend Items");
	}
	
	public static void buy(Player player, ItemStack item, int price) {
		if(player.getInventory().containsAtLeast(ItemGetter.getCoin(), price)) {
			if(Function.canStack(player, item, item.getAmount())) {
				player.getInventory().removeItem(ItemGetter.getCoin(price));
				player.getInventory().addItem(item);
			} else player.sendMessage("�cDu hast nicht gen�gend Platz");
		} else player.sendMessage("�cDu hast nicht gen�gend Geld");
	}
}

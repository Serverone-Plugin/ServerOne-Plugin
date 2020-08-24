package de.robotix_00.serveroneplugin.serverShop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.robotix_00.serverone.source.builder.ItemBuilder;
import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;

public class ShopInvController implements Listener {
    ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "shops.yml");

    @EventHandler
    public void InvClick(InventoryClickEvent event) {
	if (!(event.getWhoClicked() instanceof Player))
	    return;
	Player player = (Player) event.getWhoClicked();
	
	String inv = event.getView().getTitle();

	if (!(event.getInventory().getHolder() == null))
	    return;

	for (String now_shop : config.getList("shops")) {
	    if (config.getString(now_shop + ".invname").equals(inv)) {
		for (String now_item : config.getList(now_shop + ".items")) {
		    String[] args = now_item.split("\\s+");
		    if (event.getSlot() == Integer.parseInt(args[0])) {
			Material mat = Material.getMaterial(args[1].toUpperCase());
			if (event.getClick() == ClickType.LEFT && !args[2].equals("*")) {
			    ItemStack item = new ItemBuilder(mat, Integer.parseInt(args[2])).build();
			    ShopBuilder.buy(player, item, Integer.parseInt(args[3]));
			} else if (event.getClick() == ClickType.RIGHT && !args[4].equals("*")) {
			    ItemStack item = new ItemBuilder(mat, Integer.parseInt(args[4])).build();
			    ShopBuilder.sell(player, item, Integer.parseInt(args[5]));
			}
		    }
		}
	    }
	}
    }
}
package de.robotix_00.serveroneplugin.serverShop;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import de.robotix_00.serverone.source.builder.DefaultMenuBuilder;
import de.robotix_00.serverone.source.builder.ItemBuilder;
import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.robotix_00.serveroneplugin.ServerOnePlugin;

public class ShopVillagerController implements Listener {
    @EventHandler
    public void VillagerInteractEvent(PlayerInteractEntityEvent event) {
	Player player = event.getPlayer();
	String name = event.getRightClicked().getCustomName();
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "shops.yml");
	List<String> shops = config.getList("shops");

	if (name == null)
	    return;

	for (String now : shops) {
	    if (config.getString(now + ".invname").equals(name)) {
		player.openInventory(getShopInvs(now));
		event.setCancelled(true);
		return;
	    }
	}
    }

    public Inventory getShopInvs(String name) {
	ServerOneConfig config = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "shops.yml");
	Inventory inv = new DefaultMenuBuilder(config.getString(name + ".invname")).setClose().build();

	for (String now : config.getList(name + ".items")) {
	    String[] set = now.split("\\s+");
	    Material mat = Material.getMaterial(set[1].toUpperCase());
	    if (mat == null)
		mat = Material.AIR;
	    ItemBuilder builder = new ItemBuilder(mat);

	    if (!set[2].equals("*"))
		builder.addLore("§2Kauf: " + set[2] + " Stück für " + set[3] + " Taler");
	    if (!set[4].equals("*"))
		builder.addLore("§2Verkauf: " + set[4] + " Stück für " + set[5] + " Taler");
	    builder.addLore("§8MenuItem");
	    inv.setItem(Integer.parseInt(set[0]), builder.build());
	}
	return inv;
    }
}

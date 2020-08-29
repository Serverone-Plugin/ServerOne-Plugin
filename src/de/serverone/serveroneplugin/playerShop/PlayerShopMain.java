package de.serverone.serveroneplugin.playerShop;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

// This class is controlling all interactions of a player with an custom player shop

public class PlayerShopMain implements Listener {
    protected HashMap<Player, PlayerShop> shops = new HashMap<>();
    
    @EventHandler
    public void openMainShop(PlayerInteractEvent event) {
	// filter
	if (event.getPlayer().isSneaking() || event.getAction() != Action.RIGHT_CLICK_BLOCK)
	    return;
	// assignment
	Player player = event.getPlayer();
	Block block = event.getClickedBlock();
	Location loc = block.getLocation();
	PlayerShop shop = PlayerShop.getPlayerShop(loc, player);

	if (shop == null) {
	    return;
	}

	player.openInventory(shop.getShopInv());
	shops.put(player, shop);
	event.setCancelled(true);
    }
    
    
    @EventHandler
    public void onShopInteract(InventoryClickEvent event) {
	if (!(event.getWhoClicked() instanceof Player)) return;
	Player player = (Player) event.getWhoClicked();
	if (!shops.containsKey(player))
	    return;
	if(!event.getView().getTitle().endsWith("§as Shop")) return;
	if(event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) return;
	
	sellBuy(event);
	pages(event);
	if(event.getClick() == ClickType.DROP) onOff(event);
    }
    private void sellBuy(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if(!event.getCurrentItem().getItemMeta().hasLore()) return;
	PlayerShop shop = shops.get(player);
	List<String> lores = event.getCurrentItem().getItemMeta().getLore();
	if(!lores.contains("§8MenuItem")) return;
	
	for(String lore : lores) {
	    if(lore.startsWith("§8Index: ")) {
		if(event.getClick() == ClickType.LEFT) shop.buy(Integer.parseInt(lore.replace("§8Index: ", "")));
		else if(event.getClick() == ClickType.RIGHT) shop.sell(Integer.parseInt(lore.replace("§8Index: ", "")));
		break;
	    }
	}
    }
    private void pages(InventoryClickEvent event) {
	if(!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
	Player player = (Player) event.getWhoClicked();
	PlayerShop shop = shops.get(player);
	
	switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
	case "§enächste Seite":
	    shop.nextPage();
	    player.openInventory(shop.getShopInv());
	    break;
	case "§evorherige Seite":
	    shop.previousPage();
	    player.openInventory(shop.getShopInv());
	    break;
	}
    }
    private void onOff(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if(!event.getCurrentItem().getItemMeta().hasLore()) return;
	PlayerShop shop = shops.get(player);
	List<String> lores = event.getCurrentItem().getItemMeta().getLore();
	if(!lores.contains("§8MenuItem")) return;
	
	for(String lore : lores) {
	    if(lore.startsWith("§8Index: ")) {
		if(player.getName().equals(shop.getOwner())) shop.shutdowLink(Integer.parseInt(lore.replace("§8Index: ", "")));
		player.openInventory(shop.getShopInv());
		break;
	    }
	}
    }
    @EventHandler
    public void onShopClose(InventoryCloseEvent event) {
	if (!shops.containsKey(event.getPlayer())
		|| !(event.getView().getTitle().startsWith("§a§l") && event.getView().getTitle().endsWith("s Shop")))
	    return;
	shops.remove(event.getPlayer());
    }
}

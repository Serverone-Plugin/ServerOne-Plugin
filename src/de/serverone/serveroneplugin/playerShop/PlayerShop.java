package de.serverone.serveroneplugin.playerShop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.serverone.serveroneplugin.items.CreditCard;
import de.serverone.source.builder.DefaultMenuBuilder;
import de.serverone.source.builder.ItemBuilder;

//This class is used for storing the Data of a playershop

public class PlayerShop {
    private Inventory inv;
    private List<ShopLink> links = new ArrayList<ShopLink>();
    private int[] invSlots = { 19, 21, 23, 25, 37, 39, 41, 43 };
    private Player player;

    private ItemStack shopCore, linkList;
    private CreditCard creditCard;
    private String owner;
    private Barrel shopBarrel;
    int page = 0;

    public static PlayerShop getPlayerShop(Location pos, Player player) {
	Block lectern = pos.getBlock();

	if (!isValid(lectern))
	    return null;

	PlayerShop shop = new PlayerShop(lectern, player);

	return shop;
    }

    private PlayerShop(Block lectern, Player player) {
	shopBarrel = (Barrel) lectern.getLocation().subtract(0, 1, 0).getBlock().getState();
	Inventory container = shopBarrel.getInventory();

	this.player = player;
	this.creditCard = CreditCard.getCard(container.getItem(12));
	this.shopCore = container.getItem(13);
	this.linkList = container.getItem(14);
	this.owner = shopCore.getItemMeta().getLore().get(0).replace("§bBesitzer: ", "");
	if (linkList != null && linkList.hasItemMeta() && linkList.getItemMeta().hasLore()
		&& linkList.getItemMeta().getDisplayName().equals("§3§lShop-Liste"))
	    this.links = ShopLink.getLinks(linkList.getItemMeta().getLore(), lectern.getLocation(), owner);
    }

    public void nextPage() {
	page++;
    }

    public void previousPage() {
	page--;
    }

    public Inventory getShopInv() {
	reloadInv();
	return inv;
    }

    public void reloadInv() {
	inv = getDefaultInv();
	if (links == null)
	    return;
	for (int i = 0; i < invSlots.length; i++) {
	    int itemIndex = (page * invSlots.length) + i;
	    if (links.size() <= itemIndex)
		break;
	    inv.setItem(invSlots[i], links.get(itemIndex).getShopItem(itemIndex));
	}
    }

    public void buy(int index) {
	if(!this.isStilActive()) {
	    player.sendMessage("§cEs wurden Änderungen am Shop vorgenommen");
	    player.closeInventory();
	    return;
	}
	
	links.get(index).buy(player, creditCard);
	reloadInv();
	player.openInventory(getShopInv());
    }

    public void sell(int index) {
	if(!this.isStilActive()) {
	    player.sendMessage("§cEs wurden Änderungen am Shop vorgenommen");
	    player.closeInventory();
	    return;
	}
	
	links.get(index).sell(player, creditCard);
	player.openInventory(getShopInv());
	reloadInv();
    }

    public void shutdowLink(int index) {
	if (!links.get(index).isStilActive()) {
	    player.sendMessage("§cDer Link ist bereits deaktiviert");
	    return;
	}
	links.get(index).changeActive();
	links.remove(index);
	reloadInv();
    }

    public String getOwner() {
	return owner;
    }

    public ShopLink getLink(int index) {
	return links.get(index);
    }
    
    public boolean isStilActive() {
	if(!(shopBarrel.getBlock().getLocation().getBlock().getState() instanceof Barrel)) return false;
	Inventory container = shopBarrel.getInventory();
	
	if(!(container.getItem(12) == (creditCard.getItem()))) {
	    ItemStack card = container.getItem(12);
	    if(card == null) return false;
	    creditCard = CreditCard.getCard(card);
	}
	if(!(container.getItem(13)== (shopCore))) return false;
	if(!(container.getItem(14) == (linkList))) return false;
	return true;
    }

    private static boolean isValid(Block lectern) {
	ItemStack core, creditCard;

	if (lectern.getType() != Material.LECTERN)
	    return false;
	Block b_barrel = lectern.getLocation().add(0, -1, 0).getBlock();
	if (!(b_barrel.getState() instanceof Barrel))
	    return false;
	Barrel barrel = (Barrel) b_barrel.getState();
	core = barrel.getInventory().getItem(13);
	creditCard = barrel.getInventory().getItem(12);

	if (core == null || !core.hasItemMeta() || !core.getItemMeta().hasDisplayName()
		|| !core.getItemMeta().getDisplayName().equals("§6§lShop-Kern"))
	    return false;
	if (creditCard == null || !creditCard.hasItemMeta()
		|| !creditCard.getItemMeta().getDisplayName().equals("§6Kreditkarte"))
	    return false;
	return true;
    }

    private Inventory getDefaultInv() {
	Inventory inv = new DefaultMenuBuilder("§b§l" + owner + "§as Shop").setClose().build();
	// previous-page-Item
	if (page != 0)
	    inv.setItem(0, new ItemBuilder(Material.RED_STAINED_GLASS_PANE, page).setName("§evorherige Seite")
		    .setMenuItem().build());
	// next-page-Item
	if (page + 1 <= links.size() / invSlots.length && page < 10)
	    inv.setItem(8, new ItemBuilder(Material.RED_STAINED_GLASS_PANE, page + 2).setName("§enächste Seite")
		    .setMenuItem().build());

	return inv;
    }

}

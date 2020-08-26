package de.serverone.serveroneplugin.playerShop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.robotix_00.serverone.source.builder.ItemBuilder;
import de.serverone.serveroneplugin.items.CreditCard;
import de.serverone.serveroneplugin.test.Function;
import de.serverone.serveroneplugin.universalGetter.ItemGetter;

public class ShopLink {
    public static List<ShopLink> getLinks(List<String> list, Location loc, String owner) {
	List<ShopLink> links = new ArrayList<>();

	for (String now : list) {
	    Location tmp = getLocation(now, loc);
	    if (tmp != null && tmp.getBlock().getState() instanceof Sign) {
		Sign sign = (Sign) tmp.getBlock().getState();
		if (sign.getLine(0).equals("§a§lShop-Link an"))
		    if (sign.getLine(1).equals(owner)) {
			links.add(new ShopLink(tmp));
		    }
	    }
	}

	return links;
    }

    private Sign sign;
    private String owner, state;
    private Material mat;
    private int amount_buy = 0;
    private int amount_sell = 0;
    private int price_buy, price_sell;

    private ShopLink(Location loc) {
	sign = (Sign) loc.getBlock().getState();
	String[] lines = sign.getLines();
	owner = lines[1];
	mat = getShopMat(lines[2]);
	state = lines[3];

	String[] tmp = getInteractSetup(state);
	String buy = tmp[0], sell = tmp[1];

	// Buy-setup
	if (buy != null) {
	    String[] args2 = buy.split("-");
	    if (args2.length >= 2) {
		try {
		    amount_buy = Integer.parseInt(args2[0]);
		    price_buy = Integer.parseInt(args2[1]);
		} catch (NumberFormatException e) {
		}
	    }
	}
	// Sell-setup
	if (sell != null) {
	    String[] args2 = sell.split("-");
	    if (args2.length >= 2) {
		try {
		    amount_sell = Integer.parseInt(args2[0]);
		    price_sell = Integer.parseInt(args2[1]);
		} catch (NumberFormatException e) {
		}
	    }
	}
    }

    public void buy(Player player, CreditCard card) {
	if (!isStilActive()) {
	    player.sendMessage("§cDer Link wurde deaktiviert");
	    return;
	}
	if (countItems() < amount_buy) {
	    player.sendMessage("§cEs sind nicht ausreichend Items verfügbar");
	    return;
	}
	if (Function.countItem(player.getInventory(), ItemGetter.getCoin()) < price_buy) {
	    player.sendMessage("§cDu hast nicht ausreichend Geld dabei");
	    return;
	}
	if (!Function.canStack(player.getInventory(), new ItemStack(mat), amount_buy)) {
	    player.sendMessage("§cDu hast nicht genügend Platz im Inventar");
	    return;
	}

	player.getInventory().removeItem(ItemGetter.getCoin(price_buy));
	player.getInventory().addItem(new ItemBuilder(mat, amount_buy).build());

	removeFromShop(amount_buy);

	card.addBalance(price_buy);

    }

    public void sell(Player player, CreditCard creditCard) {
	if (!isStilActive()) {
	    player.sendMessage("§cDer Link wurde deaktiviert");
	    return;
	}
	if (!Function.canStack(player.getInventory(), ItemGetter.getCoin(), amount_sell)) {
	    player.sendMessage("§cDu hast keinen Platz für das Geld");
	    return;
	}
	if (creditCard.getBalance() < price_sell) {
	    player.sendMessage("§cDer Shop hat nicht genug Geld");
	    return;
	}
	if (!Function.canStack(getContainer(), new ItemStack(mat), amount_sell)) {
	    player.sendMessage("§cDer Shop hat keinen Platz mehr");
	    return;
	}
	if (Function.countItem(player.getInventory(), new ItemStack(mat)) < amount_sell) {
	    player.sendMessage("§cDu hast nicht genügend Items");
	    return;
	}

	player.getInventory().removeItem(new ItemBuilder(mat, amount_sell).build());
	player.getInventory().addItem(ItemGetter.getCoin(price_sell));

	creditCard.addBalance(-price_sell);
	getContainer().addItem(new ItemBuilder(mat, amount_sell).build());
    }

    public boolean isStilActive() {

	Sign nowSign;
	if (sign.getBlock().getState() instanceof Sign)
	    nowSign = (Sign) sign.getBlock().getState();
	else
	    return false;

	String[] lines = nowSign.getLines();
	if (!lines[0].startsWith("§a§lShop-Link an"))
	    return false;
	if (!lines[1].equals(owner))
	    return false;
	if (getShopMat(lines[2]) != mat)
	    return false;
	String[] tmp = getInteractSetup(lines[3]);
	String buy = tmp[0], sell = tmp[1];
	
	//TODO I DONT UNDERSTAND THIS WHOLE SHIT
	if ((buy == null && amount_buy != 0) || buy == null || !buy.split("-")[0].equals("" + amount_buy)
		|| !buy.split("-")[1].equals("" + price_buy))
	    return false;

	if ((sell == null && amount_sell != 0) || sell == null || !sell.split("-")[0].equals("" + amount_sell)
		|| !sell.split("-")[1].equals("" + price_sell))
	    return false;

	return true;
    }

    public void changeActive() {
	sign.setLine(0, "§4§lShop-Link aus");
	sign.update();
    }

    /* Getter */

    public ItemStack getShopItem(int index) {
	ItemBuilder item = new ItemBuilder(mat);
	if (amount_buy != 0)
	    item.addLore("§2Du kannst " + amount_buy + " Stück für " + price_buy + " Taler kaufen");
	if (amount_sell != 0)
	    item.addLore("§2Du kannst " + amount_sell + " Stück für " + price_sell + " Taler verkaufen");
	item.addLore("§eEs sind " + countItems() + " Items verfügbar");

	return item.addLore("§8Index: " + index).setMenuItem().build();
    }

    public String getOwner() {
	return owner;
    }

    /* private functions */
    private Inventory getContainer() {
	Container container = (Container) Function.getBlockOnSign(sign).getState();
	Inventory inv = container.getInventory();

	return inv;
    }

    private void removeFromShop(int amount) {
	getContainer().removeItem(new ItemBuilder(mat, amount).build());
    }

    private int countItems() {
	return Function.countItem(((Container) Function.getBlockOnSign(sign).getState()).getInventory(),
		new ItemBuilder(mat).build());
    }

    private static Location getLocation(String loc, Location reference) {
	loc = loc.replace("§a", "");
	String[] coord = loc.split(" ");

	Location out = new Location(reference.getWorld(), Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
		Integer.parseInt(coord[2]));
	if (reference.distance(out) > 20)
	    return null;
	else
	    return out;
    }

    private Material getShopMat(String material) {
	return Material.getMaterial(material.toUpperCase());
    }

    private String[] getInteractSetup(String state) {
	String buy = null, sell = null;
	String[] args = state.replace("k", "000").split("/");
	if (args.length == 1) {
	    if (args[0].startsWith("B"))
		buy = args[0].replaceFirst("B", "");
	    else if (args[0].startsWith("S"))
		sell = args[0].replaceFirst("S", "");
	} else if (args.length >= 2) {
	    for (String now : args) {
		if (buy == null && now.startsWith("B"))
		    buy = now.replaceFirst("B", "");
		else if (sell == null && now.startsWith("S"))
		    sell = now.replaceFirst("S", "");
		if (buy != null && sell != null)
		    break;
	    }
	}
	return new String[] { buy, sell };
    }
}

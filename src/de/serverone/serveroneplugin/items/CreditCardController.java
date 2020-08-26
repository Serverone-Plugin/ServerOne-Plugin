package de.serverone.serveroneplugin.items;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.robotix_00.serverone.source.builder.DefaultMenuBuilder;
import de.robotix_00.serverone.source.builder.ItemBuilder;
import de.serverone.serveroneplugin.test.Function;
import de.serverone.serveroneplugin.universalGetter.ItemGetter;

public class CreditCardController implements Listener {
    private static HashMap<Player, CreditCard> cards = new HashMap<>();

    @EventHandler
    private static void onClick(PlayerInteractEvent event) {
	if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))
	    return;
	if (!event.hasItem())
	    return;

	ItemStack item = event.getItem();
	Player player = event.getPlayer();
	List<String> lores = null;

	if (player.isSneaking())
	    return;
	if (!(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("§6Kreditkarte")
		&& (item.getAmount() == 1) && item.getItemMeta().hasLore()))
	    return;
	lores = item.getItemMeta().getLore();
	if (!lores.get(0).split("\\s+")[1].equals(player.getName()))
	    return;

	openCreditCard(player, CreditCard.getCard(item));
    }

    @EventHandler
    private static void onInvClick(InventoryClickEvent event) {
	if (!(event.getWhoClicked() instanceof Player))
	    return;
	if (!event.getView().getTitle().equals("§6Kreditkarte"))
	    return;
	Player player = (Player) event.getWhoClicked();
	ItemStack item = event.getCurrentItem();
	if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName())
	    return;

	if (item.getItemMeta().getDisplayName().equals("§6Kreditkarte"))
	    event.setCancelled(true);

	cardController(player, item, cards.get(player));
    }

    @EventHandler
    private static void onInvClose(InventoryCloseEvent event) {
	if (event.getPlayer() instanceof Player)
	    return;
	Player player = (Player) event.getPlayer();

	if (!cards.containsKey(player))
	    return;

	cards.remove(player);
    }

    private static Inventory getCardInv(Player player) {
	Inventory inv = new DefaultMenuBuilder("§6Kreditkarte").setBorderAround().setClose().build();

	inv.setItem(11, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setMenuItem().build());
	inv.setItem(15, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setMenuItem().build());

	inv.setItem(20, new ItemBuilder(Material.GOLD_NUGGET).setName("§a1 Taler abheben").setMenuItem().build());
	inv.setItem(29, new ItemBuilder(Material.GOLD_INGOT).setName("§a64 Taler abheben").setMenuItem().build());
	inv.setItem(38, new ItemBuilder(Material.GOLD_BLOCK).setName("§aalle Taler abheben").setMenuItem().build());

	inv.setItem(24, new ItemBuilder(Material.GOLD_NUGGET).setName("§41 Taler einlagern").setMenuItem().build());
	inv.setItem(33, new ItemBuilder(Material.GOLD_INGOT).setName("§464 Taler einlagern").setMenuItem().build());
	inv.setItem(42, new ItemBuilder(Material.GOLD_BLOCK).setName("§4alle Taler einlagern").setMenuItem().build());
	return inv;
    }

    private static void cardController(Player player, ItemStack item, CreditCard card) {
	if (card == null)
	    return;

	int money = card.getBalance();
	int moneyInInv = Function.countItem(player.getInventory(), ItemGetter.getCoin());
	switch (item.getItemMeta().getDisplayName()) {
	case "§a1 Taler abheben":
	    if (!Function.canStack(player.getInventory(), ItemGetter.getCoin(), 1)) {
		player.sendMessage("§cDu hast keinen Platz");
		return;
	    }
	    if (money < 1) {
		player.sendMessage("§cDu hast nicht genügend Geld");
		return;
	    }
	    money -= 1;
	    player.getInventory().addItem(ItemGetter.getCoin(1));
	    break;
	case "§a64 Taler abheben":
	    if (!Function.canStack(player.getInventory(), ItemGetter.getCoin(), 64)) {
		player.sendMessage("§cDu hast keinen Platz");
		return;
	    }
	    if (money < 64) {
		player.sendMessage("§cDu hast nicht genügend Geld");
		return;
	    }
	    money -= 64;
	    player.getInventory().addItem(ItemGetter.getCoin(64));
	    break;
	case "§aalle Taler abheben":
	    int wert = Function.countStackLimit(player.getInventory(), ItemGetter.getCoin());
	    if (wert < money) {
		money -= wert;
		player.getInventory().addItem(ItemGetter.getCoin(wert));
	    } else {
		player.getInventory().addItem(ItemGetter.getCoin(money));
		money = 0;
	    }
	    break;
	case "§41 Taler einlagern":
	    if (moneyInInv < 1) {
		player.sendMessage("§cDu hast nicht genügend Geld");
		return;
	    }
	    money += 1;
	    player.getInventory().removeItem(ItemGetter.getCoin());
	    break;
	case "§464 Taler einlagern":
	    if (moneyInInv < 64) {
		player.sendMessage("§cDu hast nicht genügend Geld");
		return;
	    }
	    money += 64;
	    player.getInventory().removeItem(ItemGetter.getCoin(64));
	    break;
	case "§4alle Taler einlagern":
	    money += moneyInInv;
	    player.getInventory().removeItem(ItemGetter.getCoin(moneyInInv));
	    break;
	}

	card.setBalance(money);
    }
    public static void openCreditCard(Player player, CreditCard card) {
	player.openInventory(getCardInv(player));

	cards.put(player, card);
    }
}

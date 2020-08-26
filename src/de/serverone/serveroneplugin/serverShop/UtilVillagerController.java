package de.serverone.serveroneplugin.serverShop;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import de.robotix_00.serverone.source.util.ServerOneConfig;
import de.serverone.serveroneplugin.ServerOnePlugin;
import de.serverone.serveroneplugin.test.Function;
import de.serverone.serveroneplugin.universalGetter.ItemGetter;

public class UtilVillagerController implements Listener {
    @EventHandler
    public void VillagerInteractEvent(PlayerInteractEntityEvent event) {
	Player player = event.getPlayer();
	String name = event.getRightClicked().getCustomName();
	if (name == null)
	    return;

	switch (name) {
	case "§6§lBankier":
	    event.setCancelled(true);
	    player.openInventory(Inventory_Villager.getBankInv(player));
	    break;
	case "§6§lBernd":
	    player.sendMessage("§2§l[Bernd]§r " + Function.getMessage("config.yml", "messages.bernd"));
	    event.setCancelled(true);
	    break;
	case "§6§lWahrsagerin":
	    player.sendMessage("§d§l[Wahrsagerin]§r " + Function.getMessage("config.yml", "messages.wahrsagerin"));
	    event.setCancelled(true);
	    break;
	default:
	    break;
	}
    }

    @EventHandler
    public void InvClick(InventoryClickEvent event) {
	if (!(event.getWhoClicked() instanceof Player))
	    return;
	Player player = (Player) event.getWhoClicked();

	String displayname = "";
	List<String> lores;
	if (!event.getView().getTitle().equals("§6§lBank"))
	    return;

	if (!(event.getInventory().getHolder() == null))
	    return;
	try {
	    displayname = event.getCurrentItem().getItemMeta().getDisplayName();
	    lores = event.getCurrentItem().getItemMeta().getLore();
	} catch (Exception e) {
	    return;
	}
	if (lores == null || !lores.contains("§8MenuItem"))
	    return;

	bankControll(player, displayname);
    }
    
    private static void bankControll(Player player, String name) {
	ServerOneConfig pData = ServerOneConfig.getConfig(ServerOnePlugin.getPlugin(), "playerdata.yml");
	
	boolean activ = true;
	switch (name) {
	case "§a1 Taler abheben":
	    if (pData.getMoney(player) >= 1) {
		if (Function.canStack(player.getInventory(), ItemGetter.getCoin(), 1)) {
		    player.getInventory().addItem(ItemGetter.getCoin());
		    pData.addMoney(player, -1);
		} else
		    player.sendMessage("§cDu hast keinen Platz");
	    } else
		player.sendMessage("§cDu hast zu wenig Geld auf der Bank");
	    break;
	case "§a64 Taler abheben":
	    if (pData.getMoney(player) >= 64) {
		if (Function.canStack(player.getInventory(), ItemGetter.getCoin(), 64)) {
		    player.getInventory().addItem(ItemGetter.getCoin(64));
		    pData.addMoney(player, -64);
		} else
		    player.sendMessage("§cDu hast keinen Platz");
	    } else
		player.sendMessage("§cDu hast zu wenig Geld auf der Bank");
	    break;
	case "§aalle Taler abheben":
	    int amount = Function.countStackLimit(player.getInventory(), ItemGetter.getCoin());
	    if (pData.getMoney(player) < amount) {
		player.getInventory().addItem(ItemGetter.getCoin(pData.getMoney(player)));
		pData.setMoney(player, 0);
		return;
	    }
	    pData.addMoney(player, -amount);
	    player.getInventory().addItem(ItemGetter.getCoin(amount));
	    break;
	case "§aScheck erstellen":
	    
	    activ = false;
	    break;
	case "§c1 Taler einlagern":
	    if (player.getInventory().containsAtLeast(ItemGetter.getCoin(), 1)) {
		player.getInventory().removeItem(ItemGetter.getCoin());
		pData.addMoney(player, 1);
	    } else
		player.sendMessage("§cDu hast nicht genügend Geld");
	    break;
	case "§c64 Taler einlagern":
	    if (player.getInventory().containsAtLeast(ItemGetter.getCoin(), 64)) {
		player.getInventory().removeItem(ItemGetter.getCoin(64));
		pData.addMoney(player, 64);
	    } else
		player.sendMessage("§cDu hast nicht genügend Geld");
	    break;
	case "§calle Taler einlagern":
	    int count = Function.countItem(player.getInventory(), ItemGetter.getCoin());
	    if (count == 0)
		player.sendMessage("§cDu hast kein Geld im Inventar");
	    else {
		player.getInventory().removeItem(ItemGetter.getCoin(count));
		pData.addMoney(player, count);
	    }
	    break;
	default:
	    activ = false;
	}
	pData.save();

	if (activ)
	    player.openInventory(Inventory_Villager.getBankInv(player));
    }
}

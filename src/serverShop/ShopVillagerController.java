package serverShop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import util.Function;

public class ShopVillagerController implements Listener {
	@EventHandler
	public void VillagerInteractEvent(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		String name = event.getRightClicked().getCustomName();
		
		if(name == null) return;
		
		Shops shop = Shops.getShop(name);
		if(shop != null) {
			event.setCancelled(true);
			player.openInventory(shop.getShopInvs());
			return;
		}
		
		
		switch(name) {
		case "§6§lBankier":
			event.setCancelled(true);
			player.openInventory(Inventory_Villager.getBankInv(player));
			break;
		case "§6§lBernd":
			player.sendMessage("§2§l[Bernd]§r "+Function.getMessage("config.yml", "messages.bernd"));
			event.setCancelled(true);
			break;
		case "§6§lWahrsagerin":
			player.sendMessage("§d§l[Wahrsagerin]§r "+Function.getMessage("config.yml", "messages.wahrsagerin"));
			event.setCancelled(true);
			break;
		default:
			break;
		}
	}
}

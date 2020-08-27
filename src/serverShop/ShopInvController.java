package serverShop;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import universalGetter.ItemGetter;
import util.Function;
import util.ItemBuilder;
import util.ServerOneConfig;
import util.ShopBuilder;
import util.SkullBuilder;

public class ShopInvController implements Listener {
	ServerOneConfig shopConfig = new ServerOneConfig("shops.yml");
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void InvClick(InventoryClickEvent event) {
		if(!(event.getWhoClicked() instanceof Player)) return;
		Player player = (Player) event.getWhoClicked();
		
		String displayname = "";
		List<String> lores;
		String inv = event.getView().getTitle();
		
		if(!(event.getInventory().getHolder() == null)) return;
		try {
			displayname = event.getCurrentItem().getItemMeta().getDisplayName();
			lores = event.getCurrentItem().getItemMeta().getLore();
		}catch(Exception e) {
			return;
		}
		if(lores == null || !lores.contains("§8MenuItem")) return;
		else event.setCancelled(true);
		
		//Defaultoperations
		switch(displayname) {
		case "§6§lschließen":
			player.closeInventory();
			break;
		}
		
		
		
		/*	Shops	*/
		Shops shop = Shops.getShop(inv);
		if(shop != null) {
			List<String> invs = (List<String>) shopConfig.getList(shop.getName());
			if(invs == null) return;
			
			switch(shop) {
			case SMITH:
				if(event.getClick() != ClickType.LEFT) return;
				for(String now : invs) {
					String[] args = now.split("\\s+");
					switch(displayname) {
					case "§6Miners Pickaxe":
						if(args[1].equals("miner_pickaxe")) ShopBuilder.buy(player, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§6Miners Pickaxe").setLore("§dMode: 1x1", "Shift + Rechtsklick zum umschalten").build(), Integer.parseInt(args[2]));
						
						break;
					case "§6Lumber-Axe":
						if(args[1].equals("lumber_axe")) ShopBuilder.buy(player, new ItemBuilder(Material.DIAMOND_AXE).setName("§6Lumber-Axe").build(), Integer.parseInt(args[2]));
						break;
					case "§5§lShulkerSword":
						if(args[1].equals("shulkersword")) ShopBuilder.buy(player, new ItemBuilder(Material.DIAMOND_SWORD).setName("§5§lShulkerSword").setLore("§dMode: none").build(), Integer.parseInt(args[2]));
						break;
					}
				}
				break;
			case DECOMAN:
				for(String now : invs) {
					String[] args = now.split("\\s+");
					if(event.getSlot() == Integer.parseInt(args[0])) {
						if(event.getClick() == ClickType.LEFT) {
							ShopBuilder.buy(player, new SkullBuilder(args[1]).build(), Integer.parseInt(args[2]));
						}
					}
				}
				break;
			default:
				for(String now : invs) {
					String[] args = now.split("\\s+");
					if(event.getSlot() == Integer.parseInt(args[0])) {
						Material mat = Material.getMaterial(args[1].toUpperCase());
						if(event.getClick() == ClickType.LEFT && !args[2].equals("*")) {
							ItemStack item = new ItemBuilder(mat, Integer.parseInt(args[2])).build();
							ShopBuilder.buy(player, item, Integer.parseInt(args[3]));
						} else if(event.getClick() == ClickType.RIGHT && !args[4].equals("*")) {
							ItemStack item = new ItemBuilder(mat, Integer.parseInt(args[4])).build();
							ShopBuilder.sell(player, item, Integer.parseInt(args[5]));
						}
					}
				}
				break;
			}
		} else {
			switch(inv) {
			case "§6§lBank":
				bankControll(player, displayname);
			}
		}
	}
	private static void bankControll(Player player, String name) {
		ServerOneConfig pData = new ServerOneConfig("playerdata.yml");
		boolean activ = true;
		switch(name) {
		case "§a1 Taler abheben":
			if(pData.getMoney(player) >= 1) {
				if(Function.canStack(player, ItemGetter.getCoin(), 1)) {
					player.getInventory().addItem(ItemGetter.getCoin());
					pData.addMoney(player, -1);
				} else player.sendMessage("§cDu hast keinen Platz");
			} else player.sendMessage("§cDu hast zu wenig Geld auf der Bank");
			break;
		case "§a64 Taler abheben":
			if(pData.getMoney(player) >= 64) {
				if(Function.canStack(player, ItemGetter.getCoin(), 64)) {
					player.getInventory().addItem(ItemGetter.getCoin(64));
					pData.addMoney(player, -64);
				} else player.sendMessage("§cDu hast keinen Platz");
			} else player.sendMessage("§cDu hast zu wenig Geld auf der Bank");
			break;
		case "§aalle Taler abheben":
			if(Function.canStack(player, ItemGetter.getCoin(), pData.getMoney(player))) {
				player.getInventory().addItem(ItemGetter.getCoin(pData.getMoney(player)));
				pData.addMoney(player, -pData.getMoney(player));
			} else {
				player.sendMessage("§cDu hast keinen Platz für "+pData.getMoney(player)+" Taler");
			}
			break;
		case "§c1 Taler einlagern":
			if(player.getInventory().containsAtLeast(ItemGetter.getCoin(), 1)) {
				player.getInventory().removeItem(ItemGetter.getCoin());
				pData.addMoney(player, 1);
			} else player.sendMessage("§cDu hast nicht genügend Geld");
			break;
		case "§c64 Taler einlagern":
			if(player.getInventory().containsAtLeast(ItemGetter.getCoin(), 64)) {
				player.getInventory().removeItem(ItemGetter.getCoin(64));
				pData.addMoney(player, 64);
			} else player.sendMessage("§cDu hast nicht genügend Geld");
			break;
		case "§calle Taler einlagern":
			int count = Function.countItem(player, ItemGetter.getCoin());
			if(count == 0) player.sendMessage("§cDu hast kein Geld im Inventar");
			else {
				player.getInventory().removeItem(ItemGetter.getCoin(count));
				pData.addMoney(player, count);
			}
			break;
		default:
			activ = false;
		}
		pData.save();
		
		if(activ) player.openInventory(Inventory_Villager.getBankInv(player));
	}
}
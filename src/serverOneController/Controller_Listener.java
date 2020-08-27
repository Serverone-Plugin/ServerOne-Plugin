package serverOneController;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import serverShop.Inventory_Villager;
import universalGetter.BookGetter;
import util.ItemBuilder;

public class Controller_Listener implements Listener {
	@EventHandler
	public void NavigatorOpenListener(PlayerInteractEvent event) {
		Inventorys invs = new Inventorys(event.getPlayer());
		if(event.getItem() != null) {
			if(!(event.getItem().equals(new ItemBuilder(Material.COMPASS).setName("§6§lServerOneController").build()))) return;
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				event.getPlayer().openInventory(invs.getMainInv());
			}
		}
	}
	@EventHandler
	public void InvClick(InventoryClickEvent event) {
		if(!(event.getWhoClicked() instanceof Player)) return;
		
		if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !(event.getCurrentItem().hasItemMeta() || event.getCurrentItem().getItemMeta().hasLore() || event.getCurrentItem().getItemMeta().hasDisplayName())) return;
		
		
		final Player player = (Player) event.getWhoClicked();
		final Inventorys invs = new Inventorys(player);
		final String displayname = event.getCurrentItem().getItemMeta().getDisplayName();
		final List<String> lores = event.getCurrentItem().getItemMeta().getLore();
		
		if(lores == null) return;
		if(lores.contains("§8MenuItem")) event.setCancelled(true);
		else return;
		
		if(event.getView().getTitle() == ConInv.MAIN.getInvName()) {
			switch(displayname) {
			case "§b§lSkills":
				player.openInventory(invs.getSkillInv());
				break;
			case "§b§lWarps":
				player.openInventory(invs.getWarpInv());
				break;
			case "§6§lPremium":
				player.openInventory(invs.getPremiumInv());
				break;
			case "§6§lDas Regelwerk":
				player.openBook(BookGetter.getServerRules());
			default:
				break;
			}
			return;
			}
		
		
		
		//SkillController
		if(event.getView().getTitle() == ConInv.SKILLS.getInvName()) {
			switch(displayname) {
			case "§6§lzurück":
				player.openInventory(invs.getMainInv());
				break;
			case "§cExcavation":
				break;
			case "§cHunting":
				break;
			case "§cMining":
				break;
			case "§cFarming":
				break;
			default:
				break;
			}
			return;
		}
		/*	//SKILLS NOT AVAIBLE JET//
		//Skill-Excavation
		if(event.getView().getTitle() == InvNameGetter.getInvSkillsExc()) {
			event.setCancelled(true);

				switch(displayname) {
				case "§6§lzurück":
					player.openInventory(SOCInv.SKILLS.getInventory(player));
					break;
				default:
					break;
				}
				return;
			}
		
		//Skill-Hunting
		if(event.getView().getTitle() == InvNameGetter.getInvSkillsHunt()) {
			event.setCancelled(true);
			switch(displayname) {
			case "§6§lzurück":
				player.openInventory(SOCInv.SKILLS.getInventory(player));
				break;
			default:
				break;
			}
			return;
		}
		
		//Skill-Mine
		if(event.getView().getTitle() == InvNameGetter.getInvSkillsMine()) {
			event.setCancelled(true);
			
			switch(displayname) {
			case "§6§lzurück":
				player.openInventory(SOCInv.SKILLS.getInventory(player));
				break;
			default:
				break;
			}
			return;
		}
		
		//Skill-Farming
		if(event.getView().getTitle() == InvNameGetter.getInvSkillsFarm()) {
			event.setCancelled(true);
			
			switch(displayname) {
			case "§6§lzurück":
				player.openInventory(SOCInv.SKILLS.getInventory(player));
				break;
			default:
				break;
			}
			return;
		}
		*/
		
		//WarpController
		if(event.getView().getTitle() == ConInv.WARPS.getInvName()) {
			WarpController warps = new WarpController(player);
			switch(displayname) {
			case "§6§lzurück":
				player.openInventory(invs.getMainInv());
				break;
			case "§6§lSpawn":
				warps.ToWarpPoint(WarpPoint.Spawn);
				break;
			case "§2§lFreeBuild":
				warps.ToWarpPoint(WarpPoint.FreeBuild);
				break;
			case "§4§lHome":
				warps.ToWarpPoint(WarpPoint.Home);
				break;
			case "§5§lWarppoint 1":
				warps.ToWarpPoint(WarpPoint.Point_1);
				break;
			case "§5§lWarppoint 2":
				warps.ToWarpPoint(WarpPoint.Point_2);
				break;
			case "§5§lWarppoint 3":
				warps.ToWarpPoint(WarpPoint.Point_3);
				break;
			case "§5§lWarppoint 4":
				warps.ToWarpPoint(WarpPoint.Point_4);
				break;
			case "§b§lset Warppoints":
				player.openInventory(invs.getSetWarpInv());
				break;
			default:
				break;
			}
			return;
		}
		
		//setWarpController
		if(event.getView().getTitle() == ConInv.SETWARP.getInvName()) {
			WarpController warps = new WarpController(player);
			switch(displayname) {
			case "§6§lzurück":
				player.openInventory(invs.getWarpInv());
				break;
			case "§b§lset Warppoint 1":
				warps.setWarppoint(1);
				player.closeInventory();
				break;
			case "§b§lset Warppoint 2":
				warps.setWarppoint(2);
				player.closeInventory();
				break;
			case "§b§lset Warppoint 3":
				warps.setWarppoint(3);
				player.closeInventory();
				break;
			case "§b§lset Warppoint 4":
				warps.setWarppoint(4);
				player.closeInventory();
				break;
				}
			}
		
		//PremiumController
		if(event.getView().getTitle() == ConInv.PREMIUM.getInvName()) {
			switch(displayname) {
			case "§6§lzurück":
				player.openInventory(invs.getMainInv());
				break;
			case "§d§lEnderChest":
				event.getWhoClicked().openInventory(event.getWhoClicked().getEnderChest());
				break;
			case "§b§lWorkbench":
				event.getWhoClicked().openWorkbench(event.getWhoClicked().getLocation(), true);
				break;
			case "§6§lBank":
				player.openInventory(Inventory_Villager.getBankInv(player));
				break;
			default:
				break;
			}
			return;
		}
	}
}

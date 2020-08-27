package serverShop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import util.DefaultMenuInv;
import util.ItemBuilder;
import util.ServerOneConfig;
import util.SkullBuilder;

public class Inventory_Villager {
	public static Inventory getBankInv(Player player) {
		ServerOneConfig config = new ServerOneConfig("playerdata.yml");
		Inventory inventory = new DefaultMenuInv("§6§lBank").setClose().build();
		
		inventory.setItem(31, new SkullBuilder(player).setName("§b§lBankkonto").setLore("§6Geld: §l"+config.getMoney(player)+" ServerOneTaler").setMenuItem().build());
		inventory.setItem(10, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§2§labheben").setMenuItem().build());
		inventory.setItem(19, new ItemBuilder(Material.GOLD_NUGGET).setName("§a1 Taler abheben").setMenuItem().build());
		inventory.setItem(28, new ItemBuilder(Material.GOLD_INGOT).setName("§a64 Taler abheben").setMenuItem().build());
		inventory.setItem(37, new ItemBuilder(Material.GOLD_BLOCK).setName("§aalle Taler abheben").setMenuItem().build());
		inventory.setItem(46, new ItemBuilder(Material.PAPER).setName("§aScheck erstellen").setLore("§cnicht verfügbar").setMenuItem().build());
		
		inventory.setItem(16, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§4§leinlagern").setMenuItem().build());
		inventory.setItem(25, new ItemBuilder(Material.GOLD_NUGGET).setName("§c1 Taler einlagern").setMenuItem().build());
		inventory.setItem(34, new ItemBuilder(Material.GOLD_INGOT).setName("§c64 Taler einlagern").setMenuItem().build());
		inventory.setItem(43, new ItemBuilder(Material.GOLD_BLOCK).setName("§calle Taler einlagern").setMenuItem().build());
		inventory.setItem(52, new ItemBuilder(Material.PAPER).setName("§cScheck einlösen").setLore("§cnicht verfügbar").setMenuItem().build());
		
		return inventory;
	}
}

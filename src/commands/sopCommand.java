package commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import serverShop.Shops;
import universalGetter.ItemGetter;
import util.ItemBuilder;

public class sopCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
    	//Spielerinitialisierung
    	Player player = null;
    	if (sender instanceof Player) player = (Player) sender;
    	else return false;
    		if(player.isOp()) {
    			if(args.length == 0) {
    				player.sendMessage(new String[] {
    						"§4==== SOP-Hilfe ====",
    						"§c/sop spawn bankier/bernd/wahrsagerin",
    						"§c/sop spawnshop <shopname>",
    						"§c/sop getmoney <amount>",
    						"§c/sop utilInv"});
    				return true;
    			}
    			Inventory inventory = Bukkit.createInventory(null, 3*9);
    			switch(args[0].toLowerCase()) {
    			case "spawn":
    				spawn(player, args);
    				break;
    			case "utilinv":
        			inventory.setItem(10, new ItemBuilder(Material.DIAMOND_AXE).setName("§6Lumber-Axe").build());
        			inventory.setItem(12, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§6Miners Pickaxe").setLore("§dMode: 1x1", "Shift + Rechtsklick zum umschalten").build());
        			inventory.setItem(14,  new ItemBuilder(Material.DIAMOND_SWORD).setName("§5§lShulkerSword").setLore("§dMode: none").build());
        			
        			player.openInventory(inventory);
        			break;
    			case "getmoney":
    				if(args.length < 2) break;
    				int amount = 1;
    				try {
    					amount = Integer.parseInt(args[1]);
    				}catch(Exception e) {}
    				ItemStack coins = ItemGetter.getCoin();
    				coins.setAmount(amount);
    				player.getInventory().addItem(coins);
    				break;
    			case "spawnshop":
    				if(args.length < 2) {
    					player.sendMessage("§cDu hast zu wenige Argumente angegeben!");
    					break;
    				}
    				Shops e_shop = Shops.getShop(args[1]);
    				if(e_shop == null) {
    					player.sendMessage("§cUngültiges Argument "+args[1]);
    					break;
    				}
    				e_shop.spawnShop(player);
    				break;
    			default:
    				player.sendMessage(new String[] {
    						"§4==== SOP-Hilfe ====",
    						"§c/sop spawn bankier/bernd/wahrsagerin",
    						"§c/sop spawnshop <shopname>",
    						"§c/sop getmoney <amount>",
    						"§c/sop utilInv"});
    				break;
    			}
    			
    			

    		} else {
    			player.sendMessage("§cDu musst OP sein, um das zu tun!");
    		}
    		return true;
	}
	private void spawn(Player player, String args[]) {
		switch(args[1]) {
		case "bankier":
			Villager bankier = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
	    	bankier.setCustomName("§6§lBankier");
	    	bankier.setInvulnerable(true);
	    	bankier.setAI(false);
	    	bankier.setCanPickupItems(false);
	    	bankier.setSilent(true);
	    	bankier.setVillagerType(Type.TAIGA);
	    	bankier.setProfession(Profession.LIBRARIAN);
	    	bankier.setCollidable(false);
			break;
		case "bernd":
			Villager bernd = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
			bernd.setCustomName("§6§lBernd");
			bernd.setInvulnerable(true);
			bernd.setAI(false);
			bernd.setCanPickupItems(false);
			bernd.setSilent(true);
			bernd.setVillagerType(Type.TAIGA);
			bernd.setProfession(Profession.CARTOGRAPHER);
			bernd.setCollidable(false);
			break;
		case "wahrsagerin":
			Villager wahr = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
			wahr.setCustomName("§6§lWahrsagerin");
			wahr.setInvulnerable(true);
			wahr.setAI(false);
			wahr.setCanPickupItems(false);
			wahr.setSilent(true);
			wahr.setVillagerType(Type.TAIGA);
			wahr.setProfession(Profession.CLERIC);
			wahr.setCollidable(false);
			break;
		default:
			player.sendMessage("§cUngültige Eingabe");
		}
	}
}

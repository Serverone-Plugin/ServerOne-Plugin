package serverShop;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import util.DefaultMenuInv;
import util.ItemBuilder;
import util.ServerOneConfig;
import util.SkullBuilder;

public enum Shops {
	FARMER("farmer", "§6§lFarmer"),
	LUMBERJACK("lumber", "§6§lHolzfäller"),
	NETHERER("netherer", "§6§lNetherforscher"),
	SANDY("sandy", "§6§lSandmann"),
	HUNTER("hunter","§6§lJäger"),
	BARKEEPER("barkeeper", "§6§lSchankwirt"),
	MINER("miner", "§6§lBergarbeiter"),
	SEEMAN("seeman", "§6§lMeeresforscher"),
	SMITH("smith", "§6§lSchmied"),
	DECOMAN("decoman", "§6§lDecoman");
	
	
	
	String name;
	String invName;
	private Shops(String name, String invName) {
		this.name = name;
		this.invName = invName;
	}
	
	public String getInvName() {
		return invName;
	}
	public String getName() {
		return name;
	}
	public static Shops getShop(String s) {
		switch(s) {
		case "farmer":
		case "§6§lFarmer":
			return FARMER;
		case "lumber":
		case "§6§lHolzfäller":
			return LUMBERJACK;
		case "netherer":
		case "§6§lNetherforscher":
			return NETHERER;
		case "sandy":
		case "§6§lSandmann":
			return SANDY;
		case "hunter":
		case "§6§lJäger":
			return HUNTER;
		case "barkeeper":
		case "§6§lSchankwirt":
			return BARKEEPER;
		case "miner":
		case "§6§lBergarbeiter":
			return MINER;
		case "seeman":
		case "§6§lMeeresforscher":
			return SEEMAN;
		case "smith":
		case "§6§lSchmied":
			return SMITH;
		case "decoman":
		case "§6§lDecoman":
			return DECOMAN;
		default:
			return null;
		}
	}
	ServerOneConfig config = new ServerOneConfig("shops.yml");
	
	@SuppressWarnings("unchecked")
	public Inventory getShopInvs() {
		Inventory inv = new DefaultMenuInv(invName).setClose().build();
		
		if(name.equals("smith")) return getSmith();
		if(name.equals("decoman")) return getDecoMan();
		
		if(config.getList(name) == null) return inv;
		
		for(String now : (List<String>) config.getList(name)) {
			String[] set = now.split("\\s+");
			Material mat = Material.getMaterial(set[1].toUpperCase());
			if(mat == null) mat = Material.AIR;
			ItemBuilder builder = new ItemBuilder(mat);
			
			if(!set[2].equals("*")) builder.addLore("§2Kauf: "+set[2] + " Stück für " + set[3] + " Taler");
			if(!set[4].equals("*")) builder.addLore("§2Verkauf: " + set[4] + " Stück für " + set[5] + " Taler");
			builder.addLore("§8MenuItem");
			inv.setItem(Integer.parseInt(set[0]), builder.build());
		}
		return inv;
	}
	
	public void spawnShop(Player player) {
		Villager shop = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
		shop.setInvulnerable(true);
		shop.setAI(false);
		shop.setCanPickupItems(false);
		shop.setSilent(true);
		shop.setVillagerType(Type.TAIGA);
		shop.setCollidable(false);
		shop.setCustomName(this.getInvName());
		shop.setVillagerLevel(5);
		
		
		switch(this) {
		case BARKEEPER:
			shop.setProfession(Profession.LIBRARIAN);
			break;
		case DECOMAN:
			shop.setProfession(Profession.NITWIT);
			break;
		case FARMER:
			shop.setProfession(Profession.FARMER);
			break;
		case HUNTER:
			shop.setProfession(Profession.FLETCHER);
			break;
		case LUMBERJACK:
			shop.setProfession(Profession.CLERIC);
			break;
		case MINER:
			shop.setProfession(Profession.WEAPONSMITH);
			break;
		case NETHERER:
			shop.setProfession(Profession.WEAPONSMITH);
			break;
		case SANDY:
			shop.setVillagerType(Type.DESERT);
			shop.setProfession(Profession.CLERIC);
			break;
		case SEEMAN:
			shop.setProfession(Profession.FISHERMAN);
			break;
		case SMITH:
			shop.setProfession(Profession.TOOLSMITH);
			break;
		default:
			break;
		
		}
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	private Inventory getSmith() {
		Inventory inv = new DefaultMenuInv(invName).setClose().build();
		for(String now : (List<String>) config.getList(name)) {
			String[] set = now.split("\\s+");
			
			ItemStack item = null;
			if(set[1].equals("miner_pickaxe")) item = new ItemBuilder(Material.DIAMOND_AXE).setName("§6Lumber-Axe").setLore("§2"+set[2]+" Taler das Stück").setMenuItem().build();
			else if(set[1].equals("lumber_axe")) item = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§6Miners Pickaxe").setLore("§2"+set[2]+" Taler das Stück").setMenuItem().build();
			else if(set[1].equals("shulkersword")) item =  new ItemBuilder(Material.DIAMOND_SWORD).setName("§5§lShulkerSword").setLore("§2"+set[2]+" Taler das Stück").setMenuItem().build();
			if(item != null) {
				inv.setItem(Integer.parseInt(set[0]), item);
			}
		}
		return inv;
	}
	
	@SuppressWarnings("unchecked")
	private Inventory getDecoMan() {
		Inventory inv = new DefaultMenuInv(invName).setClose().build();
		for(String now : (List<String>) config.getList(name)) {
			String[] set = now.split("\\s+");
			SkullBuilder builder = new SkullBuilder(set[1]);
			
			builder.addLore("§2Kaufe ein Stück für " + set[2] + " Taler");
			
			builder.addLore("§8MenuItem");
			inv.setItem(Integer.parseInt(set[0]), builder.build());
		}
		return inv;
	}
}

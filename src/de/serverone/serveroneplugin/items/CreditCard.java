package de.serverone.serveroneplugin.items;

import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreditCard implements Listener {
    private ItemStack card;

    public static CreditCard getCard(ItemStack card) {
	if (isCard(card))
	    return new CreditCard(card);
	else
	    return null;
    }

    private CreditCard(ItemStack card) {
	this.card = card;
    }
    public int getBalance() {
	return Integer.parseInt(card.getItemMeta().getLore().get(1).split("\\s+")[1]);
    }

    public void setBalance(int balance) {
	ItemMeta meta = card.getItemMeta();
	List<String> lores = meta.getLore();
	lores.set(1, "§bWert: " + balance);
	meta.setLore(lores);
	card.setItemMeta(meta);
    }
    
    public void addBalance(int toBalacne) {
	setBalance(getBalance()+toBalacne);
    }
    
    public static boolean isCard(ItemStack card) {
	return card.hasItemMeta() && card.getItemMeta().hasLore() && card.getItemMeta().hasDisplayName()
		&& card.getItemMeta().getDisplayName().equals("§6Kreditkarte");
    }

    public String getOwner() {
	return card.getItemMeta().getLore().get(0).split("\\s+")[1];
    }

    public ItemStack getItem() {
	return card;
    }
}

package de.serverone.serveroneplugin.test;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class TestFunctions {
	public static ItemStack getBook() {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		
		List<String> pages = new ArrayList<>();
		pages.add("Ich bin die erste Seite");
		pages.add("Ich bin die 2. Zeile");
		
		meta.setTitle("Die allereinzige Wahrheit");
		meta.setPages(pages);
		meta.setAuthor("Robotix aus Robania");
		
		item.setItemMeta(meta);
		return item;
	}
}

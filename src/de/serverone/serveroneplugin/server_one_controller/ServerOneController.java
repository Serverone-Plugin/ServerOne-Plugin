package de.serverone.serveroneplugin.server_one_controller;

import org.bukkit.entity.Player;

public class ServerOneController {
    private Player player;
    private ControllerWindow window;
    
    public ServerOneController(Player player) {
	this.player = player;
    }
    public void openPrevirous() {
	window.previrous.open(player);
	window = window.previrous;
    }
    public ControllerWindow getWindow() {
	return window;
    }
    public void open(ControllerWindow window) {
	this.window = window;
	window.open(player);
    }
    public void close() {
	player.closeInventory();
	SOCListener.controllers.remove(player);
    }
    
    
    
    
}

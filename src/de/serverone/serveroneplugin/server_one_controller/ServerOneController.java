package de.serverone.serveroneplugin.server_one_controller;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class ServerOneController {
    private Player player;
    private ControllerWindow window = ControllerWindow.MAIN;
    
    HashMap<String, HashMap<String, Object>> meta = new HashMap<>();
    
    public ServerOneController(Player player) {
	this.player = player;
    }

    public void openPrevirous() {
	if(window.previrous == null) {
	    return;
	}
	window = window.previrous;
	window.open(player, this);
    }

    public ControllerWindow getWindow() {
	return window;
    }

    public void open() {
	while (window != null && !window.isStartPoint) {
	    window = window.previrous;
	}
	window.open(player, this);
    }

    public void open(ControllerWindow window) {
	this.window = window;
	window.open(player, this);
    }
    public HashMap<String, Object> getMeta(String arg1) {
	return meta.get(arg1);
    }
    public void addMeta(String arg1) {
	meta.put(arg1, new HashMap<String, Object>());
    }
}

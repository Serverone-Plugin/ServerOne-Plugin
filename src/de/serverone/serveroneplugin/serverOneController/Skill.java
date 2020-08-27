package de.serverone.serveroneplugin.serverOneController;

public enum Skill {
	EXCAVATION("excavation", "§6§l"),
	MINING("mining", "§6§l"),
	HUNTING("hunting", "§6§l"),
	FARMING("farming", "§6§l"),
	EXPLORING("exploring", "§6§l");
	
	private String name;
	private String invName;
	private Skill(String name, String invName) {
		this.name = name;
		this.invName = invName;
	}
	public String getName() {
		return name;
	}
	public String getInvName() {
		return invName;
	}
}

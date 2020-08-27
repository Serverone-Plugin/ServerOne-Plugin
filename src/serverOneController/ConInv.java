package serverOneController;

public enum ConInv {
	MAIN("§6§lServerOneController"),
	SKILLS("§6§lSkills"),
	SKILL_SUB("§6§lSkill "),
	WARPS("§6§lWarps"),
	SETWARP("§6§lsetWarps"),
	PREMIUM("§6§lPremium");
	
	
	String invName;
	private ConInv(String invName) {
		this.invName = invName;
	}
	
	public String getInvName() {
		return invName;
	}
}

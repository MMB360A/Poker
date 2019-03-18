package version_graphics.controller;

import version_graphics.model.Player;

public class PlayerStatisticsDummie {
	
	private String handType;
	private Player p;
	public String getPlayerName() {
		return p.getPlayerName();
	}
	public String getHandType() {
		return handType;
	}
	public String getTotalWinns() {
		return p.getTotalWinns()+"";
	}
	public PlayerStatisticsDummie(Player p) {
		this.p = p;
		this.handType = p.getHandType().name();
	}
	
}

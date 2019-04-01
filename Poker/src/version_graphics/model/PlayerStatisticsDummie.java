package version_graphics.model;

import version_graphics.view.MultiLang.MultiLangModule;

/**
 * Dummy class to save the winners for the statistics on restarting the model / view
 * @author mibe1
 * TODO Translation here?
 */

public class  PlayerStatisticsDummie{
	
	private String handType;
	private Player p;
	private MultiLangModule m;
	public String getPlayerName() {
		return p.getPlayerName();
	}
	public String getHandType() {
		return m.getTranslation(handType);
	}
	public int getTotalWinns() {
		return p.getTotalWinns();
	}
	public PlayerStatisticsDummie(Player p, MultiLangModule m) {
		this.p = p;
		this.m = m;
		this.handType = p.getHandType().name();
	}
	
}

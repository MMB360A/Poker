package version_graphics.model;
/**
 * Dummy class to save the winners for the statistics on restarting the model / view
 * @author mibe1
 * TODO Translation here?
 */

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

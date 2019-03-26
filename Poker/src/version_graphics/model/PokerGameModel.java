package version_graphics.model;

import java.util.ArrayList;

import version_graphics.PokerGame;
/**
 * 
 * @author mibe1, Richards Bradley
 *Model control class
 */
public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	
	public PokerGameModel() {
		//Create the number of players needed
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			players.add(new Player("Player " + i));
		}
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public void setPlayer(int i, Player p) {
		players.set(i, p);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Evaluates all winning players based on their handtype score 
	 * @return all winning players in an arrayList
	 */
	public ArrayList<Player> evaluateWinner(){
		ArrayList<Player> winners = new ArrayList<Player>();
		for(Player p : players) {			
			if(winners.size() == 0 ||p.comaperHand(winners.get(0)) == 0 ) {
				winners.add(p);
			}
			else if(p.comaperHand(winners.get(0)) > 0 )  {
				winners.clear();
				winners.add(p);
			}
		}
		return winners;
	}
}

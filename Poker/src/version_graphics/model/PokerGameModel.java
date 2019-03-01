package version_graphics.model;

import java.util.ArrayList;

import version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			players.add(new Player("Player " + i));
		}
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	
	public ArrayList<Player> evaluateWinner(){
		ArrayList<Player> winners = new ArrayList<Player>();
		winners.add(players.get(0));
		for(int i = 1; i < players.size(); i++) {			
			if(players.get(i).comaperHand(winners.get(0)) == 0 ) {
				winners.add(players.get(i));
			}
			else if(players.get(i).comaperHand(winners.get(0)) > 0 )  {
				winners.clear();
				winners.add(players.get(i));
			}
		}
		return winners;
	}
}

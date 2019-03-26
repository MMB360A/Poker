package version_graphics.model;

import java.util.ArrayList;
/**
 * 
 * @author mibe1, Richards Bradley
 * Player Class
 */
public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    //TODO check what happens when 2 Players have the same name
    private String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    
    private int totalWinns;
    
    public Player(String playerName) {
        this.playerName = playerName;
        totalWinns = 0;
    }

    public int getTotalWinns() {
		return totalWinns;
	}

    
	public String getPlayerName() {
        return playerName;
    }
	public void setPlayerName(String name) {
		this.playerName = name;
	}

    public HandType getHandType() {
		return handType;
	}

	public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }
    
    public void discardHand() {
        cards.clear();
        handType = null;
    }
    
    public int getNumCards() {
        return cards.size();
    }

    /**
     * If the hand has not been evaluated, but does have all cards, 
     * then evaluate it.
     * @throws WrongHandException 
     */
    public HandType evaluateHand() throws WrongHandException {
    	if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }

    /**
     * Hands are compared, based on the evaluation they have.This method  does not include tiebreak
     */
    @Override
    public int compareTo(Player o) {
        return (int) (handType.ordinal() - o.handType.ordinal());
    }
    /**
     * Hands are compared, based on the evaluation they have. This method includes tiebreak
     */
    public long comaperHand(Player o) {
    	return handType.evaluateScore(cards) - o.handType.evaluateScore(o.getCards());
    }

	public void setTotalWinns(int totalWinns) {
		this.totalWinns = totalWinns;
	}

	public void icreaseStatisticWinns() {
		this.totalWinns++;
		
	}
}

package version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import version_graphics.model.Card.Rank;


public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush;
	
	
	/**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        ArrayList<Card> sortedCards =(ArrayList<Card>) cards.clone();
        Collections.sort(sortedCards);
        if (isOnePair(sortedCards)) currentEval = OnePair;
        if (isTwoPair(sortedCards)) currentEval = TwoPair;
        if (isThreeOfAKind(sortedCards)) currentEval = ThreeOfAKind;
        if (isStraight(sortedCards)) currentEval = Straight;
        if (isFlush(sortedCards)) currentEval = Flush;
        if (isFullHouse(sortedCards)) currentEval = FullHouse;
        if (isFourOfAKind(sortedCards)) currentEval = FourOfAKind;
        if (isStraightFlush(sortedCards)) currentEval = StraightFlush;
        if (isRoyalFlush(sortedCards)) currentEval = RoyalFlush;
        return currentEval;
    }
    
    private static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 1; i < cards.size() && !found; i++) 
                if (cards.get(i).getRank() == cards.get(i-1).getRank()) found = true;
        return found;
    }
    
	private static boolean isTwoPair(ArrayList<Card> cards) {
        if(cards.get(1).getRank() == cards.get(0).getRank() || cards.get(1).getRank() == cards.get(2).getRank()) {
        	if(cards.get(3).getRank() == cards.get(2).getRank() || cards.get(3).getRank() == cards.get(4).getRank())
        		return true;
        	else return false;
        }
        else return false;
    }
    
	private static boolean isThreeOfAKind(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 2; i < cards.size(); i++) {
        	if(cards.get(i).getRank() == cards.get(i-1).getRank() 
        	&& cards.get(i).getRank() == cards.get(i-2).getRank()) found = true;
        }
        return found;
    }
    
	private static boolean isStraight(ArrayList<Card> cards) {
    	Boolean isStreet = true;
    	for (int i = 1; i < cards.size();  i++) {
    		if (cards.get(i-1).getRank().ordinal() + 1 != (cards.get(i).getRank().ordinal())) isStreet = false;
    	}
        return isStreet;
    }
    
	private static boolean isFlush(ArrayList<Card> cards) {
        Boolean isFlush = true;
        for (int i = 1; i < cards.size();  i++) {
        	 if (cards.get(i).getSuit() != cards.get(0).getSuit()) isFlush = false;
        }
        return isFlush;
    }
    
	private static boolean isFullHouse(ArrayList<Card> cards) {
    	Boolean isFullHouse = true;  	
    	Card c1 = cards.get(0);
    	Card c5 = cards.get(cards.size()-1);
    	for(Card c : cards){
    		if(c.getRank() != c1.getRank() && c.getRank() != c5.getRank()) isFullHouse = false;
    	}
        return isFullHouse;
    }
    
    private static boolean isFourOfAKind(ArrayList<Card> cards) {
        return cards.get(0).getRank() == cards.get(3).getRank() || cards.get(1).getRank() == cards.get(4).getRank();
    }
    
    private static boolean isStraightFlush(ArrayList<Card> cards) {      
        return (isStraight(cards) && isFlush(cards));
    }
    
    private static boolean isRoyalFlush(ArrayList<Card> cards) {
		return (isStraight(cards) && isFlush(cards) && cards.get(0).getRank() == Rank.Ten);
	}


	public long evaluateScore( ArrayList<Card> cards) {
		ArrayList<Card> sortedCards =(ArrayList<Card>) cards.clone();
		Collections.sort(sortedCards);
		String scoreString = "0";
		switch(this) {
		case HighCard:
			scoreString = "0" + scoreOfCards(sortedCards);
			break;
		case OnePair:
			scoreString = "1";
			for(int i = 1; i < sortedCards.size(); i++) {
				if(sortedCards.get(i).getRank() == sortedCards.get(i-1).getRank()) {
					for(int j = 0; j < 2; j++) scoreString += sortedCards.get(i).getRankAsScore();
					sortedCards.remove(i);
					sortedCards.remove(i-1);
					scoreString += scoreOfCards(sortedCards);
				}
			}
			break;
		case TwoPair:
			scoreString = "2";
			for(int j = 0; j < 2;j++) scoreString += sortedCards.get(3).getRankAsScore();
			for(int j = 0; j < 2;j++) scoreString += sortedCards.get(1).getRankAsScore();
			if(sortedCards.get(0).getRank() != sortedCards.get(1).getRank())
				scoreString += sortedCards.get(0).getRankAsScore();
			else if(sortedCards.get(4).getRank() != sortedCards.get(3).getRank())
				scoreString += sortedCards.get(4).getRankAsScore();
			else
				scoreString += sortedCards.get(2).getRankAsScore(); 
			break;
		case ThreeOfAKind:
			scoreString = "3";
			for(int i = 0; i < 3; i++) scoreString += sortedCards.get(2).getRankAsScore();
			for(int i = sortedCards.size() -1; i >= 0; i --) {
				if(sortedCards.get(i).getRank() != sortedCards.get(2).getRank()) 
					scoreString += sortedCards.get(i).getRankAsScore();
			}
			break;
		case Straight:
			scoreString = "4" + scoreOfCards(sortedCards);
			break;
		case Flush:
			scoreString = "5" + scoreOfCards(sortedCards);
			break;
		case FullHouse:
			scoreString = "6";
			for(int i = 0; i < 3; i++) scoreString += sortedCards.get(2).getRankAsScore();
			if(sortedCards.get(0).getRank() == sortedCards.get(2).getRank())
				for(int i = 0; i < 2; i++) scoreString += sortedCards.get(4).getRankAsScore();
			else
				for(int i = 0; i < 2; i++) scoreString += sortedCards.get(0).getRankAsScore();
			break;
		case FourOfAKind:
			scoreString = "7";
			for(int i = 0; i < 4; i++) scoreString += sortedCards.get(2).getRankAsScore();
			for(int i = sortedCards.size() -1; i >= 0; i --) {
				if(sortedCards.get(i).getRank() != sortedCards.get(2).getRank()) 
					scoreString += sortedCards.get(i).getRankAsScore();
			}
			break;
		case StraightFlush:
			scoreString = "8" + scoreOfCards(sortedCards);
			break;
		case RoyalFlush:
			scoreString = "9" + scoreOfCards(sortedCards);
			break;
		}
		long score = Long.parseLong(scoreString);
		return score;
	}
	
	/**
	 * 
	 * @param cards 
	 * @return
	 */
	private String scoreOfCards(ArrayList<Card> cards)
	{
		Collections.sort(cards);
		String score = "";
		for(int i = cards.size() -1; i >= 0; i --) score += cards.get(i).getRankAsScore();
		return score;
	}
    
}
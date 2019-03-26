package version_graphics.model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HandTypeTest {
	// We define the hands using abbreviations. The code at the bottom
	// of this class can translate one of these strings into a card.
	//
	// Another method takes a set of five cards, and translates the whole hand
	//
	// Yet another method does this for a whole set of hands
	//TODO recheck all tests and rewrite if needed, kill errors
	private static String[][] highCards = {
			{ "2S", "9C", "3H", "5D", "7H" },
			{ "7S", "5C", "AH", "JD", "6H" },
			{ "2S", "3S", "4S", "5D", "7S" },
			{ "AS", "KC", "QH", "8D", "TH" }
			};
	
	private static String[][] pairs = {
			{ "2S", "2C", "3H", "5D", "7H" },
			{ "2S", "AC", "3H", "5D", "AH" },
			{ "3S", "2C", "3H", "KD", "QH" },
			{ "9S", "2C", "2H", "5D", "7H" }
			};

	private static String[][] twoPairs = {
			{ "2S", "2C", "7D", "5D", "7H" },
			{ "2S", "AC", "5H", "5D", "AH" },
			{ "3S", "2C", "3H", "2D", "QH" },
			{ "9S", "2C", "2H", "5D", "5H" }
			};
	
	private static String[][] threeOfAKind = {
			{ "2S", "7C", "7D", "5D", "7H" },
			{ "2S", "5C", "5H", "5D", "AH" },
			{ "3S", "3C", "3H", "2D", "QH" },
			{ "9S", "5C", "2H", "5D", "5H" }
			};
	
	private static String[][] straight = {
			{ "2S", "3C", "4D", "5D", "6H" },
			{ "AS", "3C", "4H", "2D", "5H" },
			{ "JS", "AC", "TH", "KD", "QH" },
			{ "9S", "8C", "7H", "6D", "5H" }
			};
	
	private static String[][] flush = {
			{ "2D", "3D", "6D", "5D", "8D" },
			{ "2S", "5S", "7S", "JS", "KS" },
			{ "5H", "AH", "3H", "2H", "QH" },
			{ "9S", "AS", "KS", "7S", "QS" }
			};
	
	private static String[][] fullHouse = {
			{ "2S", "7C", "7D", "2D", "7H" },
			{ "AS", "5C", "5H", "5D", "AH" },
			{ "3S", "3C", "3H", "2D", "2H" },
			{ "9S", "5C", "9H", "5D", "5H" }
			};
	
	private static String[][] fourOfAKind = {
			{ "7S", "7C", "7D", "5D", "7H" },
			{ "2S", "5C", "5H", "5D", "5S" },
			{ "3S", "3C", "3H", "2D", "3D" },
			{ "9S", "5C", "5S", "5D", "5H" }
			};
	
	private static String[][] straightFlush = {
			{ "2S", "3S", "4S", "5S", "6S" },
			{ "6H", "5H", "7H", "3H", "4H" },
			{ "JC", "9C", "TC", "KC", "QC" },
			{ "5D", "4D", "3D", "2D", "AD" }
			};
	
	private static String[][] royalFlush = {
			{ "AS", "JS", "QS", "TS", "KS" },
			{ "QC", "TC", "JC", "KC", "AC" },
			{ "KH", "AH", "TH", "JH", "QH" },
			{ "TD", "KD", "AD", "QD", "JD" }
			};
	
	
	// This is where we store the translated hands
	ArrayList<ArrayList<Card>> highCardHands;
	ArrayList<ArrayList<Card>> pairHands;
	ArrayList<ArrayList<Card>> twoPairHands;
	ArrayList<ArrayList<Card>> threeOfAKindHands;
	ArrayList<ArrayList<Card>> straightHands;
	ArrayList<ArrayList<Card>> flushHands;
	ArrayList<ArrayList<Card>> fullHouseHands;
	ArrayList<ArrayList<Card>> fourOfAKindHands;
	ArrayList<ArrayList<Card>> straightFlushHands;
	ArrayList<ArrayList<Card>> royalFlushHands;
	
	/**
	 * The makeHands method is called before each test method,
	 * and prepares the translated hands. We recreate these for
	 * each test method, in case the test method damages the data.
	 */
	@Before
	public void makeHands() {
		highCardHands = makeHands(highCards);
		pairHands = makeHands(pairs);
		twoPairHands = makeHands(twoPairs);
		threeOfAKindHands = makeHands(threeOfAKind);
		straightHands = makeHands(straight);
		flushHands= makeHands(flush);
		fullHouseHands = makeHands(fullHouse);
		fourOfAKindHands = makeHands(fourOfAKind);
		straightFlushHands = makeHands(straightFlush);
		royalFlushHands = makeHands(royalFlush);
	}

	/**
	 * This is a test method for the isOnePair method in HandType.
	 * We expect all HighCard hands to be false, all OnePair hands to
	 * be true, all TwoPair hands to be true, etc.
	 */
	@Test
	public void testHandEvaluation() {
		for (ArrayList<Card> hand : highCardHands) {
			try {
				assertTrue(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : pairHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertTrue(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : twoPairHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertTrue(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertTrue(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : straightHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertTrue(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : flushHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertTrue(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertTrue(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertTrue(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertTrue(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertFalse(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			try {
				assertFalse(HandType.evaluateHand(hand) == HandType.HighCard);
				assertFalse(HandType.evaluateHand(hand) == HandType.OnePair);
				assertFalse(HandType.evaluateHand(hand) == HandType.TwoPair);
				assertFalse(HandType.evaluateHand(hand) == HandType.ThreeOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.Flush);
				assertFalse(HandType.evaluateHand(hand) == HandType.Straight);
				assertFalse(HandType.evaluateHand(hand) == HandType.FullHouse);
				assertFalse(HandType.evaluateHand(hand) == HandType.FourOfAKind);
				assertFalse(HandType.evaluateHand(hand) == HandType.StraightFlush);
				assertTrue(HandType.evaluateHand(hand) == HandType.RoyalFlush);
			} catch (WrongHandException e) {
				e.printStackTrace();
				fail();
			}
		}
	}

	
//Score Tests
	@Test
	public void testScoreHighCard() {
		assertTrue(evalueteHandScore(highCardHands.get(0)) ==  907050302L);
		assertTrue(evalueteHandScore(highCardHands.get(1)) ==  1411070605L);
		assertTrue(evalueteHandScore(highCardHands.get(2)) ==  705040302L);
		assertTrue(evalueteHandScore(highCardHands.get(3)) ==  1413121008L);
	}
	
	@Test
	public void testScoreOnePair() {
		assertTrue(evalueteHandScore(pairHands.get(0)) == 10202070503L);
		assertTrue(evalueteHandScore(pairHands.get(1)) == 11414050302L);
		assertTrue(evalueteHandScore(pairHands.get(2)) == 10303131202L);
		assertTrue(evalueteHandScore(pairHands.get(3)) == 10202090705L);
	}
	
	@Test
	public void testScoreTwoPair() {
		assertTrue(evalueteHandScore(twoPairHands.get(0)) == 20707020205L);
		assertTrue(evalueteHandScore(twoPairHands.get(1)) == 21414050502L);
		assertTrue(evalueteHandScore(twoPairHands.get(2)) == 20303020212L);
		assertTrue(evalueteHandScore(twoPairHands.get(3)) == 20505020209L);
	}
	
	@Test
	public void testScoreThreeOfAKind() {
		assertTrue(evalueteHandScore(threeOfAKindHands.get(0)) == 30707070502L);
		assertTrue(evalueteHandScore(threeOfAKindHands.get(1)) == 30505051402L);
		assertTrue(evalueteHandScore(threeOfAKindHands.get(2)) == 30303031202L);
		assertTrue(evalueteHandScore(threeOfAKindHands.get(3)) == 30505050902L);
	}
	
	@Test
	public void testScoreStraight() {
		assertTrue(evalueteHandScore(straightHands.get(0)) == 40605040302L);
		assertTrue(evalueteHandScore(straightHands.get(1)) == 40504030201L);
		assertTrue(evalueteHandScore(straightHands.get(2)) == 41413121110L);
		assertTrue(evalueteHandScore(straightHands.get(3)) == 40908070605L);
	}
	
	@Test
	public void testScoreFlush() {
		assertTrue(evalueteHandScore(flushHands.get(0)) == 50806050302L);
		assertTrue(evalueteHandScore(flushHands.get(1)) == 51311070502L);
		assertTrue(evalueteHandScore(flushHands.get(2)) == 51412050302L);
		//assertTrue(evalueteHandScore(flushHands.get(3)) == 51110090502L);
	}
	
	@Test
	public void testScoreFullHouse() {
		assertTrue(evalueteHandScore(fullHouseHands.get(0)) == 60707070202L);
		assertTrue(evalueteHandScore(fullHouseHands.get(1)) == 60505051414L);
		assertTrue(evalueteHandScore(fullHouseHands.get(2)) == 60303030202L);
		assertTrue(evalueteHandScore(fullHouseHands.get(3)) == 60505050909L);
	}
	
	@Test
	public void testScoreFourOfAKind() {
		assertTrue(evalueteHandScore(fourOfAKindHands.get(0)) == 70707070705L);
		assertTrue(evalueteHandScore(fourOfAKindHands.get(1)) == 70505050502L);
		assertTrue(evalueteHandScore(fourOfAKindHands.get(2)) == 70303030302L);
		assertTrue(evalueteHandScore(fourOfAKindHands.get(3)) == 70505050509L);
	}
	
	@Test
	public void testScoreStraightFlush() {
		assertTrue(evalueteHandScore(straightFlushHands.get(0)) == 80605040302L);
		assertTrue(evalueteHandScore(straightFlushHands.get(1)) == 80706050403L);
		assertTrue(evalueteHandScore(straightFlushHands.get(2)) == 81312111009L);
		assertTrue(evalueteHandScore(straightFlushHands.get(3)) == 80504030201L);
	}
	
	@Test
	public void testScoreRoyalFlush() {
		assertTrue(evalueteHandScore(royalFlushHands.get(0)) == 91413121110L);
		assertTrue(evalueteHandScore(royalFlushHands.get(1)) == 91413121110L);
		assertTrue(evalueteHandScore(royalFlushHands.get(2)) == 91413121110L);
		assertTrue(evalueteHandScore(royalFlushHands.get(3)) == 91413121110L);
	}
	
	private long evalueteHandScore(ArrayList<Card> cards) {
		HandType type;
		try {
			type = HandType.evaluateHand(cards);
			return type.evaluateScore(cards);
		} catch (WrongHandException e) {
			e.printStackTrace();
			fail();
			return (long) 0;
		}
		
	}
	
	/**
	 * Make an ArrayList of hands from an array of string-arrays
	 */
	private ArrayList<ArrayList<Card>> makeHands(String[][] handsIn) {
		ArrayList<ArrayList<Card>> handsOut = new ArrayList<>();
		for (String[] hand : handsIn) {
			handsOut.add(makeHand(hand));
		}
		return handsOut;
		
		
		
	}
	
	/**
	 * Make a hand (ArrayList<Card>) from an array of 5 strings
	 */
	private ArrayList<Card> makeHand(String[] inStrings) {
		ArrayList<Card> hand = new ArrayList<>();
		for (String in : inStrings) {
			hand.add(makeCard(in));
		}
		return hand;
	}
	
	/**
	 * Create a card from a 2-character String.
	 * First character is the rank (2-9, T, J, Q, K, A) 
	 * Second character is the suit (C, D, H, S)
	 * 
	 * No validation or error handling!
	 */
	private Card makeCard(String in) {
		char r = in.charAt(0);
		Card.Rank rank = null;
		if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
		else if (r == 'T') rank = Card.Rank.Ten;
		else if (r == 'J') rank = Card.Rank.Jack;
		else if (r == 'Q') rank = Card.Rank.Queen;
		else if (r == 'K') rank = Card.Rank.King;
		else if (r == 'A') rank = Card.Rank.Ace;
		
		char s = in.charAt(1);
		Card.Suit suit = null;
		if (s == 'C') suit = Card.Suit.Clubs;
		if (s == 'D') suit = Card.Suit.Diamonds;
		if (s == 'H') suit = Card.Suit.Hearts;
		if (s == 'S') suit = Card.Suit.Spades;

		return new Card(suit, rank);
	}
}

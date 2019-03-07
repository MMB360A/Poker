package version_graphics.model;

public class Card implements Comparable<Object>{
	public enum Suit {Clubs, Diamonds, Hearts, Spades;
        @Override
        public String toString() {
            String suit = "";
            switch (this) {
            case Clubs: suit = "clubs"; break;
            case Diamonds: suit = "diamonds"; break;
            case Hearts: suit = "hearts"; break;
            case Spades: suit = "spades"; break;
            }
            return suit;
        }
    };
    
    public enum Rank {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
        @Override
        public String toString() {
            String str = "ace";  // Assume we have an ace, then cover all other cases
            // Get ordinal value, which ranges from 0 to 12
            int ordinal = this.ordinal();
            if (ordinal <= 8) {
                str = Integer.toString(ordinal+2);
            } else if (ordinal == 9) { // Jack
                str = "jack";
            } else if (ordinal == 10) { // Queen
                str = "queen";
            } else if (ordinal == 11) { // King
                str = "king";
            }
            return str;
        }
    };
    
    private final Suit suit;
    private final Rank rank;
    //Is the Card turned (Show backside)
    private boolean covered;
    
    public Card(Suit suit, Rank rank, boolean covered) {
       this.covered = covered;
    	this.suit = suit;
        this.rank = rank;
    }
    
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    public Boolean getCovered() {
    	return covered;
    }
    
    /**
     *  that every number has 2 digits
     * @return The CardScore as String
     */
    public String getRankAsScore() {
    	String score = "";
    	if(rank == rank.Ace) score = "14";
    	else if(rank == rank.King) score = "13";
    	else if(rank == rank.Queen) score = "12";
    	else if(rank == rank.Jack) score = "11";
    	else if(rank == rank.Ten) score = "10";
    	else score = "0" + rank;
    	return score;
    }
    
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

	@Override
	public int compareTo(Object arg0) {
		Card c = (Card) arg0;
		return this.getRank().ordinal() - c.getRank().ordinal();
	}
}

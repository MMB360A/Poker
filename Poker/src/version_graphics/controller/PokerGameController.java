package version_graphics.controller;

import java.util.ArrayList;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import version_graphics.PokerGame;
import version_graphics.model.Card;
import version_graphics.model.DeckOfCards;
import version_graphics.model.Player;
import version_graphics.model.PokerGameModel;
import version_graphics.view.PlayerPane;
import version_graphics.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	
	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getMenu().getLangugageSetting().setOnAction(e -> changeLanguage());
	}
	


    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	}

    	model.getDeck().shuffle();
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int cardsRequired = PokerGame.NUM_PLAYERS * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
    		SequentialTransition sequence = new SequentialTransition();
        	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
            	pp.updatePlayerDisplay().play();

        	}
        	//sequence.play();

        	
        	
    		ArrayList<Player> winners = model.evaluateWinner();
		if(winners.size() == 1) {	
    		for(int i = 0; i< view.getPlayerPanes().size(); i++) {
				PlayerPane pp = view.getPlayerPanes().get(i);
				if(pp.getPlayer() == winners.get(0)) {
					pp.setWinner("Winner");
					winners.get(0).icreaseStatisticWinns();
					view.getStatistics().addWinner(winners.get(0));
				}
			}
    	}
		else {
    		for(Player p : winners) {
    			for(int i = 0; i< view.getPlayerPanes().size(); i++) {
    				PlayerPane pp = view.getPlayerPanes().get(i);
    				if(pp.getPlayer() == p) {
    					pp.setWinner("Splitt");
    					p.icreaseStatisticWinns();
    					view.getStatistics().addWinner(p);
    				}
    			}
    			
    		}
		}
    		
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }

public void changeLanguage()
{
	view.getMultilangModule().setLangugage();
	
	view.restart();
}
}

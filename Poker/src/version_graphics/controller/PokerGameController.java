package version_graphics.controller;

import java.util.ArrayList;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import version_graphics.PokerGame;
import version_graphics.model.Card;
import version_graphics.model.DeckOfCards;
import version_graphics.model.Player;
import version_graphics.model.PlayerStatisticsDummie;
import version_graphics.model.PokerGameModel;
import version_graphics.view.ChangePlayerNamesView;
import version_graphics.view.PlayerPane;
import version_graphics.view.PokerGameView;
import version_graphics.view.Statistics;
import version_graphics.view.StatisticsView;
import version_graphics.view.MultiLang.ChangeLanguageView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	private ArrayList<PlayerStatisticsDummie> winners = new ArrayList<PlayerStatisticsDummie>();
	
	
	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		this.setEvents();

	}
	
	/**
	 * Set all Events of the View
	 */
	private void setEvents() {
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getMenu().getLanguageSetting().setOnAction(e -> changeLanguage());
		view.getMenu().getAbout().setOnAction(e -> about());
		view.getMenu().getAddPlayer().setOnAction(e -> addPlayer());
		view.getMenu().getRemovePlayer().setOnAction(e -> removePlayer());
		view.getMenu().getChangeSkin().setOnAction(e -> changeSkin());
		view.getMenu().getViewStatistics().setOnAction(e -> showStatistics());
		view.getMenu().getChangeUserName().setOnAction(e -> changePlayerNames());
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
    	this.shuffle();
    	view.getDealButton().setDisable(true);
    	PokerGame.numberOfGames++;
    	int cardsRequired = PokerGame.NUM_PLAYERS * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
        	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
        		SequentialTransition sequence = pp.updatePlayerDisplay();
        		sequence.setDelay(new Duration(i * 5000 + 1));
            	sequence.play();
            	//Statistics:
    			for(Card c :p.getCards())
    				c.increaseStatistics();
    			p.getHandType().increaseStatistics();
        	}
    		ArrayList<Player> winners = model.evaluateWinner();
    		for(Player p: winners) this.winners.add(new PlayerStatisticsDummie(p));
    		if(winners.size() == 1) {	
				winners.get(0).icreaseStatisticWinns();
				view.getStatistics().addWinner(new PlayerStatisticsDummie(winners.get(0)));
	    		for(int i = 0; i< view.getPlayerPanes().size(); i++) {
					PlayerPane pp = view.getPlayerPanes().get(i);
					if(pp.getPlayer() == winners.get(0)) {
						pp.setWinner("Winner");					
					}
	    		}
    		}
    		else {
	    		for(Player p : winners) {
	    			p.icreaseStatisticWinns();
	    			view.getStatistics().addWinner(new PlayerStatisticsDummie(p));
	    			for(int i = 0; i< view.getPlayerPanes().size(); i++) {
	    				PlayerPane pp = view.getPlayerPanes().get(i);
	    				if(pp.getPlayer() == p) {
	    					pp.setWinner("Splitt");
	    					
	    				}
	    			}
	    			
	    		}
		}
    		//view.getDealButton().setDisable(false);
    }

    /**
     * Open the Language Settings Window and Updates the view in the new Language if needed
     */
    public void changeLanguage()
	{
    	ChangeLanguageView clView = view.getMultilangModule().setDefalutLanguage(view.getStage());
    	clView.show();
    	clView.setOnHidden(e -> {
    		//Restart the View with the new Settings
    		view = view.restart(model);
    		//Reconnect all ActionEvents to the new View
    		this.setEvents(); 
    		//Set the Statistics Table correct
    		view.getStatistics().setWinners(this.winners);
    	});
	}
    /**
     * Changes the View form Day to Nightmode and from Nightmode to Daymode
     */
    private void changeSkin() {
		PokerGameView.darkthem = !PokerGameView.darkthem;
		//Restart the VIew with the new Settings
		view = view.restart(model);
		//Reconnect all ActionEvents to the new View
		this.setEvents(); 
		//Set the Statistics Table correct
		view.getStatistics().setWinners(this.winners);
	}
    
    /**
     * Removes the last Player from the Game 
     */
	private void removePlayer() {
		if(PokerGame.NUM_PLAYERS > PokerGame.MINNUM_PLAYERS) {
			PokerGame.NUM_PLAYERS--;
			//Recreates the Model with the new number of Players
			ArrayList<Player> savePlayer = (ArrayList<Player>) model.getPlayers().clone();
			model = new PokerGameModel();
			for(int i = 0; i < savePlayer.size() && i < model.getPlayers().size(); i++)
				model.setPlayer(i, savePlayer.get(i));
    		//Restart the VIew with the new Settings
    		view = view.restart(model);
    		//Reconnect all ActionEvents to the new View
    		this.setEvents(); 
    		//Set the Statistics Table correct
    		view.getStatistics().setWinners(this.winners);
		}
		else {
            Alert alert = new Alert(AlertType.ERROR, view.getMultilangModule().getTranslation("MinPlayers"));
            alert.showAndWait();
		}            
	}

	/**
	 * Adds a Player to the Game
	 */
	private void addPlayer() {
		if(PokerGame.NUM_PLAYERS < PokerGame.MAXNUM_PLAYERS) {
			PokerGame.NUM_PLAYERS++;
			
			//Recreates the Model with the new number of Players
			ArrayList<Player> savePlayer = (ArrayList<Player>) model.getPlayers().clone();
			model = new PokerGameModel();
			for(int i = 0; i < savePlayer.size() && i < model.getPlayers().size(); i++)
				model.setPlayer(i, savePlayer.get(i));
    		//Restart the VIew with the new Settings
    		view = view.restart(model);
    		//Reconnect all ActionEvents to the new View
    		this.setEvents(); 
    		//Set the Statistics Table correct
    		view.getStatistics().setWinners(this.winners);
		}
		else {
            Alert alert = new Alert(AlertType.ERROR, view.getMultilangModule().getTranslation("MaxPlayers"));
            alert.showAndWait();
		}            
	}

	/**
	 * Shows an Information about the Project and the Author
	 * @author mibe1
	 */
	private void about() {
        Alert alert = new Alert(AlertType.INFORMATION, view.getMultilangModule().getTranslation("programInfo"));
        alert.setHeaderText("");
        alert.showAndWait();
	}
	
	/**
	 * Opens a new Window to change each Players Name
	 * @return
	 */
	private void changePlayerNames() {
		ChangePlayerNamesView namesView = new ChangePlayerNamesView(view.getMultilangModule(), model.getPlayers());
		namesView.initOwner(view.getStage());
		namesView.getOk().setOnAction(e -> {
			ArrayList<String> newNames = namesView.getPlayerNames();
			for(int i = 0; i < model.getPlayers().size(); i++)
				model.getPlayer(i).setPlayerName(newNames.get(i));
			namesView.close();
    		//Restart the VIew with the new Settings
    		view = view.restart(model);
    		//Reconnect all ActionEvents to the new View
    		this.setEvents(); 
    		//Set the Statistics Table correct
    		view.getStatistics().setWinners(this.winners);
		});
		namesView.getCancel().setOnAction(e ->{
			namesView.close();
		});
		namesView.show();
	}

	/**
	 * Opens a new Window and shows some Statistics
	 */
	private void showStatistics() {
		StatisticsView sv = new StatisticsView(view.getMultilangModule(), model);
		sv.initOwner(view.getStage());
		sv.show();
	}
}

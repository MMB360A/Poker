package version_graphics.controller;

import java.util.ArrayList;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import version_graphics.PokerGame;
import version_graphics.model.Card;
import version_graphics.model.DeckOfCards;
import version_graphics.model.Player;
import version_graphics.model.PlayerStatisticsDummie;
import version_graphics.model.PokerGameModel;
import version_graphics.model.WrongHandException;
import version_graphics.view.ChangePlayerNamesView;
import version_graphics.view.PlayerPane;
import version_graphics.view.PokerGameView;
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
		view.getDeckCardLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				deal();
				
			}
});
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
    		PlayerPane pp = view.getPlayerPane(i);
    		ParallelTransition trans =  pp.removeAllCards(view.getDeckX(), view.getDeckY());
    		trans.setOnFinished(e-> {p.discardHand();});
    		trans.play();    		
    	}
    	model.getDeck().shuffle();
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	if(model.getDeck().getCardsRemaining() < 52)this.shuffle();
    	view.getMenu().setDisable(true);
    	PokerGame.numberOfGames++;
    	DeckOfCards deck = model.getDeck();
    	double d = 0;
    	SequentialTransition lastSequence = new SequentialTransition();
        for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
        	Player p = model.getPlayer(i);
        	for (int j = 0; j < Player.HAND_SIZE; j++) {
        		Card card = deck.dealCard();
        		p.addCard(card);
        	}
        	
        	PlayerPane pp = view.getPlayerPane(i);
        	lastSequence = pp.updatePlayerDisplay(view.getDeckX(), view.getDeckY());
        	d = i * 2500 + 500;
        	lastSequence.setDelay(new Duration(d));
    		//Getting all Data from the Model Ready to update the view after the animation
        	final String handType;
        	String a = "";
			try {
				a = p.evaluateHand().toString();
			} catch (WrongHandException e1) {
				
				e1.printStackTrace();
			}
			finally {
				handType = a;
			}
        	p.getHandType().increaseStatistics();
        	final ArrayList<Player> winners;
        	if(model.getPlayer(PokerGame.NUM_PLAYERS-1) == p) {
        		winners = model.evaluateWinner();
    			for(Player player: winners) {
    	    		this.winners.add(new PlayerStatisticsDummie(player));
    				player.icreaseStatisticWinns();
    			}
        	}
        	else winners = new ArrayList<Player>();
        	//All Hands of the Playerobjets are allready null in this Action, so just update the view, without getting data from the Model
        	lastSequence.setOnFinished(e->{
        		pp.setevaluateText(handType);
        		if(model.getPlayer(PokerGame.NUM_PLAYERS-1) == p) {
        			for(Player player: winners) {
        	    		String text = "Winner";
        	    		if(winners.size() > 1) text ="Splitt";
        	    		view.getStatistics().setWinners(this.winners);
        		    	for(int y = 0; y< view.getPlayerPanes().size(); y++) {
        					PlayerPane playerpane = view.getPlayerPanes().get(y);
        					if(playerpane.getPlayer() == player) {
        						playerpane.setWinner(text);					
        					}
        		    	}
            		}
        	    	view.getMenu().setDisable(false);
        		}
        
        	});

    		lastSequence.play();
        }
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

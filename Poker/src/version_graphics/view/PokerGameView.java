package version_graphics.view;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import version_graphics.PokerGame;
import version_graphics.model.Player;
import version_graphics.model.PokerGameModel;

public class PokerGameView {
	private  ArrayList<PlayerPane> playerPanes;
	private ControlArea controls;
	private PokerGameModel model;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
		menuBar.getMenus().add(file);
		
		VBox center = new VBox();
		// Create all of the player panes we need, and put them into an HBox
		HBox players = new HBox();
		playerPanes = new ArrayList<PlayerPane>(); 
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			FlowPane spacePlayer = new FlowPane();
			spacePlayer.getStyleClass().add("widthSpace");
			players.getChildren().addAll(spacePlayer, pp);
			playerPanes.add(pp);
		}
		FlowPane spacePlayer = new FlowPane();
		spacePlayer.getStyleClass().add("widthSpace");
		players.getChildren().add(spacePlayer);
		FlowPane spaceTop = new FlowPane();
		spaceTop.getStyleClass().add("heightSpace");
		//players.getChildren().remove(players.getChildren().size() -1);
		FlowPane field = new FlowPane();
		field.getStyleClass().add("field");
		center.getChildren().addAll(spaceTop, players, field);
		
		BorderPane bottom = new BorderPane();
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		FlowPane spaceLeft = new FlowPane();
		spaceLeft.getStyleClass().add("widthSpace");
		FlowPane spaceRight = new FlowPane();
		spaceRight.getStyleClass().add("widthSpace");
		FlowPane spaceBottom = new FlowPane();
		spaceBottom.getStyleClass().add("heightSpace");
		bottom.setLeft(spaceLeft);
		bottom.setCenter(controls);
		bottom.setRight(spaceRight);
		bottom.setBottom(spaceBottom);
		//botoom.getChildren().addAll(spaceLeft, controls, spaceRight);
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setCenter(center);
		root.setBottom(bottom);
		root.getStyleClass().add("root");
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.show();		
	}
	
	public PlayerPane getPlayerPane(int i) {
			return playerPanes.get(i);
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}
	
	public ArrayList<PlayerPane> getPlayerPanes() {
		return playerPanes;
	}
}

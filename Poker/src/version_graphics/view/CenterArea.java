package version_graphics.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import version_graphics.PokerGame;
import version_graphics.model.PokerGameModel;
import version_graphics.view.MultiLang.MultiLangModule;

public class CenterArea extends BorderPane{
			
	private  ArrayList<PlayerPane> playerPanes;
	/**
	 * Center area with all Players and a Field
	 * @param model
	 * @param multilangModule
	 */
	public CenterArea(PokerGameModel model, MultiLangModule multilangModule) {
				super();
				// Create all of the player panes we need, and put them into an HBox
				HBox players = new HBox();
				playerPanes = new ArrayList<PlayerPane>(); 
				for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
					PlayerPane pp = new PlayerPane(multilangModule);
					pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
					Region spacePlayer = new Region();
					players.getChildren().addAll(pp, spacePlayer);
					spacePlayer.getStyleClass().add("widthSpace");
					playerPanes.add(pp);
				}
				//remove last Space Region
				players.getChildren().remove(players.getChildren().size() -1);
				
				//Spaces around the Players section
				Region spacePlayerLeft = new Region();
				spacePlayerLeft.getStyleClass().add("widthSpace");
				Region spacePlayerRight = new Region();
				spacePlayerRight.getStyleClass().add("widthSpace");
				Region spaceTop = new Region();
				spaceTop.getStyleClass().add("heightSpace");
				
				Region casinoLabel = new Region();
				//Playing Field
				casinoLabel.getStyleClass().add("field");

				
				this.setCenter(players);
				this.setTop(spaceTop);
				this.setBottom(casinoLabel);
				this.setLeft(spacePlayerLeft);
				this.setRight(spacePlayerRight);
	}

	//Returns all Playrpanes 
	public ArrayList<PlayerPane> getPlayerPanes() {
		return playerPanes;
	}
}

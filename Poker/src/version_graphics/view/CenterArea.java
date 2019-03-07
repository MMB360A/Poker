package version_graphics.view;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import version_graphics.PokerGame;
import version_graphics.model.PokerGameModel;

public class CenterArea extends BorderPane{
			
	private  ArrayList<PlayerPane> playerPanes;
	
	public CenterArea(PokerGameModel model) {
				super();
				// Create all of the player panes we need, and put them into an HBox
				HBox players = new HBox();
				playerPanes = new ArrayList<PlayerPane>(); 
				for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
					PlayerPane pp = new PlayerPane();
					pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
					FlowPane spacePlayer = new FlowPane();
					players.getChildren().addAll(pp, spacePlayer);
					spacePlayer.getStyleClass().add("widthSpace");
					playerPanes.add(pp);
				}
				players.getChildren().remove(players.getChildren().size() -1);
				
				
				Region spacePlayerLeft = new Region();
				spacePlayerLeft.getStyleClass().add("widthSpace");
				Region spacePlayerRight = new Region();
				spacePlayerRight.getStyleClass().add("widthSpace");
				Region spaceTop = new Region();
				spaceTop.getStyleClass().add("heightSpace");
				
				//Playing Field
				BorderPane field = new BorderPane();
				field.setCenter(new CasinoLabel());
				field.getStyleClass().add("field");
				
				this.setCenter(players);
				this.setTop(spaceTop);
				this.setBottom(field);
				this.setLeft(spacePlayerLeft);
				this.setRight(spacePlayerRight);
	}

	protected ArrayList<PlayerPane> getPlayerPanes() {
		return playerPanes;
	}
}

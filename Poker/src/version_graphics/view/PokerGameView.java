package version_graphics.view;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	
	private PokerGameModel model;
	private CenterArea center;
	private BottomArea bottom;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		center = new CenterArea(model);
		bottom = new BottomArea(model);
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setTop(new MenuArea());
		root.setCenter(center);
		root.setBottom(bottom );
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
	
	public Button getShuffleButton() {
		return bottom.getControls().btnShuffle;
	}
	
	public Button getDealButton() {
		return bottom.getControls().btnDeal;
	}
	
	public ArrayList<PlayerPane> getPlayerPanes() {
		return center.getPlayerPanes();
	}
	
	public PlayerPane getPlayerPane(int i) {
		return center.getPlayerPanes().get(i);
}
}

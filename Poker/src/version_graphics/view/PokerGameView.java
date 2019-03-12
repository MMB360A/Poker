package version_graphics.view;

import java.util.ArrayList;

import javafx.application.Platform;
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
import version_graphics.view.MultiLang.MultiLangModule;

public class PokerGameView {
	
	
	private PokerGameModel model;
	private CenterArea center;
	private BottomArea bottom;
	private MenuArea menu;
	private MultiLangModule multilangModule;
	private Stage stage;
	public PokerGameView(Stage stage, PokerGameModel model, MultiLangModule multilangModule) {
		this.model = model;
		this.stage = stage;
		this.multilangModule = multilangModule;
		menu = new MenuArea(multilangModule);
		
		center = new CenterArea(model, multilangModule);
		bottom = new BottomArea(model, multilangModule);
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setTop(menu);
		root.setCenter(center);
		root.setBottom(bottom );
		root.getStyleClass().add("root");
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);
        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css\\poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.show();		
	}
	
	public Stage getStage() {
		return stage;
	}

	public MultiLangModule getMultilangModule() {
		return multilangModule;
	}

	public MenuArea getMenu() {
		return menu;
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
	public Statistics getStatistics() {
		return bottom.getStatistics();
	}

	public PokerGameView restart() {
			PokerGameView v = this;
			v = new PokerGameView(stage, model, multilangModule);
			return v;
	}
}

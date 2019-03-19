package version_graphics.view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import version_graphics.PokerGame;
import version_graphics.model.Player;
import version_graphics.model.PokerGameModel;
import version_graphics.view.MultiLang.MultiLangModule;

public class PokerGameView {
	
	public static boolean darkthem = true;
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
		
		//Top Pane with menu and close Button
		menu = new MenuArea(multilangModule);
		
		center = new CenterArea(model, multilangModule);
		bottom = new BottomArea(model, multilangModule);
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setTop(menu);
		root.setCenter(center);
		root.setBottom(bottom );
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);
        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
       
        //Style Sheets (Basic, Dark & Light)
        scene.getStylesheets().add(getClass().getResource("css\\poker.css").toExternalForm());
        if(darkthem) scene.getStylesheets().add(getClass().getResource("css\\pokerDark.css").toExternalForm());
        else scene.getStylesheets().add(getClass().getResource("css\\pokerLight.css").toExternalForm());
        
        stage.setTitle("Poker Miniproject Michael Berger");
        stage.setScene(scene);
        //Set Icon
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images\\icon.png"));
        stage.getIcons().add(image);
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
	
	

	public PokerGameView restart(PokerGameModel m) {
			PokerGameView v = this;
			v = new PokerGameView(stage, m, multilangModule);
			return v;
	}

	public void setStistics(Statistics statistics) {
		bottom.setStatistics(statistics);
		
	}
	
}
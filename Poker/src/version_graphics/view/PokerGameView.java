package version_graphics.view;


import java.util.ArrayList;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import version_graphics.model.PokerGameModel;
import version_graphics.view.MultiLang.MultiLangModule;
/**
 * Main view of the Programm
 * @author mibe1, Richards Bradley
 *
 */
public class PokerGameView {
	
	public static boolean darkthem = true;
	private PokerGameModel model;
	private CenterArea center;
	private BottomArea bottom;
	private MenuArea menu;
	private Stage stage;
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage = stage;
		
		//Top Pane with menu and close Button
		menu = new MenuArea();
		
		center = new CenterArea(model);
		bottom = new BottomArea(model);
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
        //Title TODO Translate?
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

	public MenuArea getMenu() {
		return menu;
	}

	public CardLabel getDeckCardLabel() {
		return bottom.getControls().getvBoxDeck().getCardLabel();
	}
	
	public int getDeckX() {
		DeckLabel d = (DeckLabel) bottom.getControls().getCenter();
		Bounds boundsInScene = d.localToScene(d.getBoundsInLocal());
		return (int) (boundsInScene.getMaxX());
	}
	
	public int getDeckY() {
		DeckLabel d = (DeckLabel) bottom.getControls().getCenter();
		Bounds boundsInScene = d.localToScene(d.getBoundsInLocal());
		return (int) (boundsInScene.getMinY());
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
			v = new PokerGameView(stage, m);
			return v;
	}

	public void setStistics(Statistics statistics) {
		bottom.setStatistics(statistics);
		
	}
	
}
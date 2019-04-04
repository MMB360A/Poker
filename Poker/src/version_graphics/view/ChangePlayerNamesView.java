package version_graphics.view;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import version_graphics.PokerGame;
import version_graphics.model.Player;
import version_graphics.view.MultiLang.MultiLangModule;

/**
 * This is a Popup View to change the Names of the Players
 * @author mibe1
 *
 */
public class ChangePlayerNamesView extends Stage{
	private Button ok;
	private Button cancel;
	private VBox vBoxPlayers;
	
	public ChangePlayerNamesView(ArrayList<Player> players) {
		 super();
		 //Mainpane / Root element
		 BorderPane pane = new BorderPane();
		 pane.getStyleClass().add("root");
		 //All Playernames
		 vBoxPlayers = new VBox();
		 vBoxPlayers.getStyleClass().add("vBox");
		 	for(Player p : players)  {
		 		TextField tf = new TextField();
		 		tf.getStyleClass().add("textfield");
		 		tf.setText(p.getPlayerName());
		        Region reg = new Region();
		        reg.getStyleClass().add("regHeigt");
		 		vBoxPlayers.getChildren().addAll(tf, reg);
		 	}
		 	pane.setCenter(vBoxPlayers);
		 	//The control buttons
	        HBox buttons = new HBox();
	        ok = new Button(PokerGame.MULTILANGMODULE.getTranslation("Save"));
	        cancel = new Button(PokerGame.MULTILANGMODULE.getTranslation("Cancel"));
	        Region regCenter = new Region();
	        regCenter.getStyleClass().add("regCenter");
	        buttons.getChildren().addAll(ok, regCenter, cancel);
	        regCenter.getStyleClass().add("regHeigt");
	        buttons.setSpacing(20);
	        pane.setBottom(buttons);
	        
	        Scene scene = new Scene(pane, 270, (players.size()*50) + 100);
	        //Set the correct CSS file
	        if(PokerGameView.darkthem) scene.getStylesheets().add(getClass().getResource("css\\changeNamesDark.css").toExternalForm());
	        else scene.getStylesheets().add(getClass().getResource("css\\changeNamesLight.css").toExternalForm());
	        this.setTitle(PokerGame.MULTILANGMODULE.getTranslation("SelectLanguage"));
	        this.setScene(scene);
	        // Specifies the modality for new window.
	        this.initModality(Modality.WINDOW_MODAL);
	        this.setResizable(false);
	       
	}

	//geters for the Buttons
	public Button getOk() {	return ok;}
	public Button getCancel() {return cancel;}
	/**
	 * 
	 * @return all updated Playernames
	 */
	public ArrayList<String> getPlayerNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < vBoxPlayers.getChildren().size(); i+= 2) 
			names.add(((TextField)vBoxPlayers.getChildren().get(i)).getText());
		return names;
	}
}

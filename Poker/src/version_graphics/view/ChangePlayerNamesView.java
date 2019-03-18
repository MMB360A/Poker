package version_graphics.view;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import version_graphics.model.Player;
import version_graphics.view.MultiLang.MultiLangModule;

public class ChangePlayerNamesView extends Stage{
	private Button ok;
	private Button cancel;
	private VBox vBoxPlayers;
	
	public ChangePlayerNamesView(MultiLangModule multiLangModule, ArrayList<Player> players) {
		 super();
		 BorderPane pane = new BorderPane();
		 vBoxPlayers = new VBox();
		 	for(Player p : players)  {
		 		TextField tf = new TextField();
		 		tf.setText(p.getPlayerName());
		 		vBoxPlayers.getChildren().add(tf);
		 	}
		 	pane.setCenter(vBoxPlayers);
		 	//Control Buttons
	        HBox buttons = new HBox();
	        ok = new Button(multiLangModule.getTranslation("save"));
	        //ok.setOnAction( e -> save() );
	        cancel = new Button(multiLangModule.getTranslation("cancel"));
	        //ok.setOnAction( e -> cancel() );
	        buttons.getChildren().addAll(ok, cancel);
	        pane.setBottom(buttons);
		 
	        Scene scene = new Scene(pane, 230, (multiLangModule.getLanguages().size() * 25 + 100));
	        //scene.getStylesheets().add(getClass().getResource("multiLang.css").toExternalForm());
	        if(PokerGameView.darkthem) scene.getStylesheets().add(getClass().getResource("css\\changeNamesDark.css").toExternalForm());
	        else scene.getStylesheets().add(getClass().getResource("css\\changeNamesLight.css").toExternalForm());
	        this.setTitle(multiLangModule.getTranslation("selectLanguage"));
	        this.setScene(scene);
	        // Specifies the modality for new window.
	        this.initModality(Modality.WINDOW_MODAL);

	}
	
	public Button getOk() {	return ok;}
	public Button getCancel() {return cancel;}
	
	public ArrayList<String> getPlayerNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < vBoxPlayers.getChildren().size(); i++) 
			names.add(((TextField)vBoxPlayers.getChildren().get(i)).getText());
		return names;
	}
}

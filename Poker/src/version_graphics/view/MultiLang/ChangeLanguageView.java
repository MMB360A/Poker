package version_graphics.view.MultiLang;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import version_graphics.view.PokerGameView;

public class ChangeLanguageView extends Stage{
	
	private ToggleGroup tg;
	private Button ok;
	private Button cancel;
	
	public ChangeLanguageView(MultiLangModule multiLangModule, final Stage primaryStage) {
		 super();
		 //Root elemnet
		 BorderPane pane = new BorderPane();
		 //Text
		 Label desc = new  Label(multiLangModule.getTranslation("selectLanguageText"));
		 pane.setTop(desc);
		 VBox langVBox = new VBox();
		 Region topSpace = new Region();
		 topSpace.getStyleClass().add("heightSpace");
		 langVBox.getChildren().add(topSpace);
		 //Toggle Group with all Languages
		 tg = new ToggleGroup();
		 for(String lang : multiLangModule.getLanguages()) {
	        RadioButton rb1 = new RadioButton();
	        rb1.setText(lang);
	        if(lang.equals(multiLangModule.getDefalutLanguage())) rb1.setSelected(true);
	        rb1.setToggleGroup(tg);
	        langVBox.getChildren().add(rb1);
		 }
			Region space = new Region();
			space.getStyleClass().add("heightSpace");
			langVBox.getChildren().add(space);
		 pane.setCenter(langVBox);
        //Control Buttons
		 HBox buttons = new HBox();
        ok = new Button(multiLangModule.getTranslation("save"));
        cancel = new Button(multiLangModule.getTranslation("cancel"));
        Region regCenter = new Region();
        regCenter.getStyleClass().add("regCenter");
        buttons.getChildren().addAll(ok,regCenter, cancel);
        pane.setBottom(buttons);
        
        Scene scene = new Scene(pane, 230, (multiLangModule.getLanguages().size() * 25 + 110));
        scene.getStylesheets().add(getClass().getResource("multiLang.css").toExternalForm());
        if(PokerGameView.darkthem)  scene.getStylesheets().add(getClass().getResource("multiLangDark.css").toExternalForm());
        else  scene.getStylesheets().add(getClass().getResource("multiLangLight.css").toExternalForm());
        
        this.setTitle(multiLangModule.getTranslation("selectLanguage"));
        this.setScene(scene);
        // Specifies the modality for new window.
        this.initModality(Modality.WINDOW_MODAL);
        this.setResizable(false);
        this.initOwner(primaryStage);
	}

	protected ToggleGroup getTg() {
		return tg;
	}

	protected Button getOk() {
		return ok;
	}
	

	protected Button getCancel() {
		return cancel;
	}


}

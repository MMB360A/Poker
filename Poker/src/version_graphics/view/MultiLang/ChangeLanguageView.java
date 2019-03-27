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

public class ChangeLanguageView extends Stage{
	
	private ToggleGroup tg;
	private Button ok;
	private Button cancel;
	
	public ChangeLanguageView(MultiLangModule multiLangModule, final Stage primaryStage) {
		 super();
		 BorderPane pane = new BorderPane();
       
       Label desc = new  Label(multiLangModule.getTranslation("selectLanguageText"));
       pane.setTop(desc);
       VBox langVBox = new VBox();
		Region topSpace = new Region();
		topSpace.getStyleClass().add("heightSpace");
		langVBox.getChildren().add(topSpace);
        tg = new ToggleGroup();
        for(String lang : multiLangModule.getLanguages()) {
	        RadioButton rb1 = new RadioButton();
	        rb1.setText(lang);
	        if(lang.equals(multiLangModule.getDefalutLanguage())) rb1.setSelected(true);
	        rb1.setToggleGroup(tg);
			Region space = new Region();
			space.getStyleClass().add("heightSpace");
	        langVBox.getChildren().addAll(rb1,space);
        }
        pane.setCenter(langVBox);
        
        HBox buttons = new HBox();
        ok = new Button(multiLangModule.getTranslation("save"));
        //ok.setOnAction( e -> save() );
        cancel = new Button(multiLangModule.getTranslation("cancel"));
        //ok.setOnAction( e -> cancel() );
        buttons.getChildren().addAll(ok, cancel);
        pane.setBottom(buttons);
        
        Scene scene = new Scene(pane, 230, (multiLangModule.getLanguages().size() * 25 + 100));
        scene.getStylesheets().add(getClass().getResource("multiLang.css").toExternalForm());

        this.setTitle(multiLangModule.getTranslation("selectLanguage"));
        this.setScene(scene);
        // Specifies the modality for new window.
        this.initModality(Modality.WINDOW_MODAL);
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

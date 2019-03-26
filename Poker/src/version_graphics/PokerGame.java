package version_graphics;

import java.util.Locale;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import version_graphics.controller.PokerGameController;
import version_graphics.model.PokerGameModel;
import version_graphics.view.PokerGameView;
import version_graphics.view.MultiLang.MultiLangModule;
/**
 * 
 * @author mibe1, Richards Bradley
 *Main Class , Start point of the Application contains main method and JavaFX Stat method
 */
public class PokerGame extends Application {
	//Default number of playera
	public static int NUM_PLAYERS = 2;
	//Set a maximum and a minimum of possible players in the game
	public final static int MAXNUM_PLAYERS = 4;
	public final static int MINNUM_PLAYERS = 2;
	//num of played games
	public static int numberOfGames = 0;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;
	
    public static void main(String[] args) {
    	launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	//Get Windows Language and set the Program Language equals to this
    	Locale locale = Locale.getDefault();
    	String lang = locale.getDisplayLanguage();
    	MultiLangModule multiLangModule = new MultiLangModule("English");
    	if(multiLangModule.getLanguages().contains(lang)) multiLangModule.setDefalutLanguage(lang);  	
    	// Create and initialize the MVC components
    	model = new PokerGameModel();
    	view = new PokerGameView(primaryStage, model, multiLangModule);
    	controller = new PokerGameController(model, view);
    }
}

package version_graphics;

import javafx.application.Application;
import javafx.stage.Stage;
import version_graphics.controller.PokerGameController;
import version_graphics.model.PokerGameModel;
import version_graphics.view.PokerGameView;

public class PokerGame extends Application {
	public static final int NUM_PLAYERS = 2;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;
	
    public static void main(String[] args) {
    	launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize the MVC components
    	model = new PokerGameModel();
    	view = new PokerGameView(primaryStage, model);
    	controller = new PokerGameController(model, view);
    }
}

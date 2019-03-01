package version_graphics.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import version_graphics.model.Card;
import version_graphics.model.DeckOfCards;

public class ControlArea extends BorderPane{
    
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    private DeckLabel vBoxDeck = new DeckLabel(btnDeal);
    public ControlArea() {
    	super(); // Always call super-constructor first !!
    	vBoxDeck.setId("controlDeckLabel");
    	btnShuffle.getStyleClass().add("controlButton");
    	btnDeal.getStyleClass().add("controlButton");
    	
    	this.setLeft(btnShuffle);
    	this.setCenter(vBoxDeck);
    	this.setRight(btnDeal);

        this.setId("controlArea"); // Unique ID in the CSS
    }
    
    public void linkDeck(DeckOfCards deck) {
    	vBoxDeck.setDeck(deck);
    }
}

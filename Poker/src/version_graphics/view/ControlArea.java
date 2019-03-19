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
import version_graphics.view.MultiLang.MultiLangModule;
/**
 * Control Area is the Dealers Area in the Bottom of the GUI
 * It Contains a Shuffle Button, a Deal Buton and a Deck of Cards
 * @author mibe1
 *
 */
public class ControlArea extends BorderPane{
    
    Button btnShuffle = new Button();
    Button btnDeal = new Button();
    private DeckLabel vBoxDeck;
    public ControlArea(MultiLangModule multilangModule) {
    	super();
    	vBoxDeck = new DeckLabel(btnDeal, multilangModule);
        btnShuffle.setText(multilangModule.getTranslation("Shuffle"));
        btnDeal.setText(multilangModule.getTranslation("Deal"));

    	
    	this.setLeft(btnShuffle);
    	this.setCenter(vBoxDeck);
    	this.setRight(btnDeal);

        this.setId("controlArea");
    }
    
    public void linkDeck(DeckOfCards deck) {
    	vBoxDeck.setDeck(deck);
    }
}

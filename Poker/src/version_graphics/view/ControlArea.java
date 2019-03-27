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
    
    private DeckLabel vBoxDeck;
    public ControlArea(MultiLangModule multilangModule) {
    	super();
    	vBoxDeck = new DeckLabel(multilangModule);

    	this.setCenter(vBoxDeck);

        this.setId("controlArea");
    }
    
    public void linkDeck(DeckOfCards deck) {
    	vBoxDeck.setDeck(deck);
    }
    
	public DeckLabel getvBoxDeck() {
		return vBoxDeck;
	}
    
}

package version_graphics.view;


import javafx.scene.layout.BorderPane;
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

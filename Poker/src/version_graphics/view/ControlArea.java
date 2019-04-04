package version_graphics.view;


import javafx.scene.layout.BorderPane;
import version_graphics.model.DeckOfCards;
import version_graphics.view.MultiLang.MultiLangModule;
/**
 * Control Area is the Dealers Area in the Bottom of the GUI
 * It contains only a DeckLAbel element
 * THis Class exists for Style reason and it simplyfies future changes of the archtecture (adding new controls etc.)
 * @author mibe1
 *
 */
public class ControlArea extends BorderPane{
    
    private DeckLabel vBoxDeck;
    public ControlArea() {
    	super();
    	vBoxDeck = new DeckLabel();

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

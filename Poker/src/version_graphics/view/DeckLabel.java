package version_graphics.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import version_graphics.model.Card;
import version_graphics.model.DeckOfCards;

public class DeckLabel extends VBox {
	private Label lblCardNumer = new Label();
	private Label lblCardRemaining = new Label(" Cards remaning");
	public DeckLabel(Button deal) {
		super();
    	Card card = new Card(null, null, true);
    	CardLabel cardLabel = new CardLabel();
    	cardLabel.setCard(card);
		this.getChildren().addAll(cardLabel, lblCardNumer, lblCardRemaining);
    	cardLabel.getStyleClass().add("deckOfCards");
    	this.getStyleClass().add("deckOfCards");
    	cardLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    	     @Override
    	     public void handle(MouseEvent event) {
    	         deal.fire();
    	     }
    	});
	}
	
	// Bind the label to the CardsRemaining property of the deck
	public void setDeck(DeckOfCards deck) {
		lblCardNumer.textProperty().bind(deck.getCardsRemainingProperty().asString());
	}
}

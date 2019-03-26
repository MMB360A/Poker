package version_graphics.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import version_graphics.model.Card;
import version_graphics.model.DeckOfCards;
import version_graphics.view.MultiLang.MultiLangModule;

public class DeckLabel extends VBox {
	private Label lblCardNumber = new Label();
	private Label lblCardRemaining;
	public DeckLabel(Button deal, MultiLangModule multilangModule) {
		super();
		lblCardRemaining = new Label(multilangModule.getTranslation("Cardsremaning"));
    	Card card = new Card(null, null);
    	CardLabel cardLabel = new CardLabel();
    	cardLabel.setCard(card, true);
		this.getChildren().addAll(cardLabel, lblCardNumber, lblCardRemaining);
    	cardLabel.getStyleClass().add("deckOfCards");
    	this.getStyleClass().add("card");
    	lblCardRemaining.getStyleClass().add("cardLabel");
    	cardLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    	     @Override
    	     public void handle(MouseEvent event) {
    	         deal.fire();
    	     }
    	});
	}
	
	// Bind the label to the CardsRemaining property of the deck
	public void setDeck(DeckOfCards deck) {
		lblCardNumber.textProperty().bind(deck.getCardsRemainingProperty().asString());
	}
}

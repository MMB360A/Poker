package version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import version_graphics.PokerGame;
import version_graphics.model.Card;
import version_graphics.model.DeckOfCards;
import version_graphics.view.MultiLang.MultiLangModule;
/**
 * 
 * @author mibe1, Richards Bradley
 *Shows the Deck with the Label Cards Remaining
 *TODO Label Update?
 */
public class DeckLabel extends VBox {
	private Label lblCardNumber = new Label();
	private Label lblCardRemaining;
	private CardLabel cardLabel;
	public DeckLabel() {
		super();
		lblCardRemaining = new Label(PokerGame.MULTILANGMODULE.getTranslation("Cardsremaning"));
    	Card card = new Card(null, null);
    	cardLabel = new CardLabel();
    	cardLabel.setCard(card, true);
		this.getChildren().addAll(cardLabel, lblCardNumber, lblCardRemaining);
    	cardLabel.getStyleClass().add("deckOfCards");
    	this.getStyleClass().add("card");
    	lblCardRemaining.getStyleClass().add("cardLabel");
	}
	
	public CardLabel getCardLabel() {
		return cardLabel;
	}

	// Bind the label to the CardsRemaining property of the deck
	public void setDeck(DeckOfCards deck) {
		lblCardNumber.textProperty().bind(deck.getCardsRemainingProperty().asString());
	}
}

package version_graphics.view;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import version_graphics.model.Card;

public class CardLabel extends Label {
	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}

	public void setCard(Card card) {
		if (card != null) {
			
			String fileName = cardToFileName(card);
			Image image;
			if(PokerGameView.darkthem)  image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/dark/" + fileName));
			else image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/light/" + fileName));
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
				this.setGraphic(imv);
				
				if(PokerGameView.darkthem)this.setStyle("-fx-background-color: lemonchiffon");
				else this.setStyle("-fx-background-color: lemonchiffon");
			}
		else {
			if(PokerGameView.darkthem)this.setStyle("-fx-background-color: black");
			else this.setStyle("-fx-background-color: #228B22");
			this.setGraphic(null);
		}
	}


	private String cardToFileName(Card card) {
		if(card.getCovered()) return "back.png";
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}

}

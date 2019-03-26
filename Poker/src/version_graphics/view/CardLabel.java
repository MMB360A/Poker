package version_graphics.view;


import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;
import version_graphics.model.Card;

public class CardLabel extends Label {
	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}

	public void setCard(Card card, Boolean turned) {
		if (card != null) {
			
			String fileName = cardToFileName(card, turned);
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


	private String cardToFileName(Card card, Boolean turned) {
		if(turned) return "back.png";
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}
	
	public PathTransition getMove(int startX, int startY) {
		Bounds boundsInScene = this.localToScene(this.getBoundsInLocal());
		int centralXPos = (int) ((boundsInScene.getMaxX() + boundsInScene.getMinX()) /2);
		PathElement pe1;
		pe1	= new MoveTo(startX -  centralXPos,startY);
	   	PathElement pe2 = new LineTo(this.getScaleX() + 55, this.getScaleY() + 80); 
	   	Path path = new Path();
	   	path.getElements().add(pe1);
	   	path.getElements().add(pe2);
	   	PathTransition move = new PathTransition(Duration.millis(1000), path, this);
	   	return move;
	}
	
	public PathTransition getReverseMove(int endX, int endY) {
		Bounds boundsInScene = this.localToScene(this.getBoundsInLocal());
		int centralXPos = (int) ((boundsInScene.getMaxX() + boundsInScene.getMinX()) /2);
		PathElement pe1;
		pe1	= new MoveTo(this.getScaleX() + 55, this.getScaleY() + 80);
	   	PathElement pe2 = new LineTo(endX -  centralXPos,endY); 
	   	Path path = new Path();
	   	path.getElements().add(pe1);
	   	path.getElements().add(pe2);
	   	PathTransition move = new PathTransition(Duration.millis(1000), path, this);
	   	return move;
	}
	
	public SequentialTransition getTurn(Card card, Boolean turned) {
		SequentialTransition sequence = new SequentialTransition();
		sequence.setOnFinished(e->{
			this.setCard(card, !turned);
		});
		return sequence;
	}

}

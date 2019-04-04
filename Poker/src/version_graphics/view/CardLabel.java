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
/**
 * 
 * @author mibe1, Richards Bradley
 *
 */
public class CardLabel extends Label {
	
	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}
	/**
	 * sets a Image to the Cardlabel based on the Card card
	 * @param card the Card witch should be displayed
	 * @param turned is the Card covered for an animation or is it open
	 */
	public void setCard(Card card, Boolean turned) {
		if (card != null) {
			String fileName = cardToFileName(card, turned);
			Image image;
			//Get the image of the Card label in Dark or Light at the moment it is the same label
			if(PokerGameView.darkthem)  image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/dark/" + fileName));
			else image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/light/" + fileName));
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
				this.setGraphic(imv);
			//set the Background Color of the Label based on the image and the theme
			if(PokerGameView.darkthem)this.setStyle("-fx-background-color: lemonchiffon");
			else this.setStyle("-fx-background-color: lemonchiffon");
		}
		else {
			//If the Card is null set the background as background of the parent object
			if(PokerGameView.darkthem)this.setStyle("-fx-background-color: black");
			else this.setStyle("-fx-background-color: #228B22");
			this.setGraphic(null);
		}
	}

	/**
	 *@author Richards Bradley
	 * @param card the Card witch should be displayed
	 * @param turned is the Card covered for an animation or is it open
	 * @return the Name of the Image of the Card
	 */
	private String cardToFileName(Card card, Boolean turned) {
		if(turned) return "back.png";
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}
	
	/**
	 * Generates the move Transition for the Card
	 * @param startX Start point of the Card (X and Y)
	 * @param startY Start point of the Card (X and Y)
	 * The Card is moved to the Start point and the goes to the position of the card
	 * The duration is a Magic Number 750 ms
	 * @return 
	 */
	public PathTransition getMove(int startX, int startY) {
		Bounds boundsInScene = this.localToScene(this.getBoundsInLocal());
		int centralXPos = (int) ((boundsInScene.getMaxX() + boundsInScene.getMinX()) /2);
		int centralYPos = (int) ((boundsInScene.getMaxY() + boundsInScene.getMinY()) /2);
		PathElement pe1;
		pe1	= new MoveTo(startX -  centralXPos,startY - centralYPos + 200);
	   	PathElement pe2 = new LineTo(this.getScaleX() + 55, this.getScaleY() + 80); 
	   	Path path = new Path();
	   	path.getElements().add(pe1);
	   	path.getElements().add(pe2);
	   	PathTransition move = new PathTransition(Duration.millis(500), path, this);
	   	return move;
	}
	/**
	 * Reverse version of the getMove method
	 * @param endX
	 * @param endY
	 * @return 
	 */
	public PathTransition getReverseMove(int endX, int endY) {
		Bounds boundsInScene = this.localToScene(this.getBoundsInLocal());
		int centralXPos = (int) ((boundsInScene.getMaxX() + boundsInScene.getMinX()) /2);
		int centralYPos = (int) ((boundsInScene.getMaxY() + boundsInScene.getMinY()) /2);
		PathElement pe1;
		pe1	= new MoveTo(this.getScaleX() + 55, this.getScaleY() + 80);
	   	PathElement pe2 = new LineTo(endX -  centralXPos,endY - centralYPos +200); 
	   	Path path = new Path();
	   	path.getElements().add(pe1);
	   	path.getElements().add(pe2);
	   	PathTransition move = new PathTransition(Duration.millis(750), path, this);
	   	return move;
	}
}

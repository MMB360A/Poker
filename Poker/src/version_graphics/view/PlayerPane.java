package version_graphics.view;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;
import version_graphics.model.Card;
import version_graphics.model.HandType;
import version_graphics.model.Player;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private HBox hboxCards = new HBox();
    private ArrayList<Label> cards = new ArrayList<Label>();
    private Label lblEvaluation = new Label("--");
    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player"); // CSS style class
        // Add child nodes
        this.getChildren().addAll(lblName, hboxCards, lblEvaluation);
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
			Region spaceCard = new Region();
			spaceCard.getStyleClass().add("cardSpace");
			cards.add(lblCard);
            hboxCards.getChildren().addAll(lblCard, spaceCard);
            
        }
        hboxCards.getChildren().remove(hboxCards.getChildren().size() -1);
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    	updatePlayerDisplay(); // Immediately display the player information
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public SequentialTransition updatePlayerDisplay() {
    	lblName.setText(player.getPlayerName()); 
    	SequentialTransition sequence = new SequentialTransition();
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		Card card = null;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		CardLabel cl = (CardLabel) cards.get(i);
    		cl.setCard(card);
    		HandType evaluation = player.evaluateHand();
    		if (evaluation != null)
    			lblEvaluation.setText(evaluation.toString());
    		else
    			lblEvaluation.setText("--");
    		Bounds boundsInScene = cl.localToScene(cl.getBoundsInLocal());
    		int centralXPos = (int) ((boundsInScene.getMaxX() + boundsInScene.getMinX()) /2);
    		PathElement pe1;
    		 pe1	= new MoveTo(700 -  centralXPos,500);
	    	PathElement pe2 = new LineTo(cl.getScaleX() + 55, cl.getScaleY() + 80); 
	    	Path path = new Path();
	    	path.getElements().add(pe1);
	    	path.getElements().add(pe2);
	    	PathTransition move = new PathTransition(Duration.millis(1000), path, cl);
	    	sequence.getChildren().add(move);
	    	
    	}
    	return sequence;
    }
    
    public void setWinner(String message) {
    	lblEvaluation.setText(lblEvaluation.getText()+ "  " + message);
    }
}

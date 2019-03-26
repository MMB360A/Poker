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
import version_graphics.view.MultiLang.MultiLangModule;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private HBox hboxCards = new HBox();
    private ArrayList<Label> cards = new ArrayList<Label>();
    private Label lblEvaluation = new Label("--");
    private MultiLangModule multilangModule;
    // Link to player object
    private Player player;
    
    public PlayerPane(MultiLangModule multilangModule) {
        super(); // Always call super-constructor first !!
        this.multilangModule = multilangModule;
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
    	updatePlayerDisplay(0,0); // Immediately display the player information
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public SequentialTransition updatePlayerDisplay(int posX, int posY) {
    	lblName.setText(multilangModule.getTranslation("Player") + ": "+player.getPlayerName()); 
    	SequentialTransition sequence = new SequentialTransition();
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		final Card card;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		else card = null;
    		CardLabel cl = (CardLabel) cards.get(i);
    		cl.setCard(card, true);
    		PathTransition seq = cl.getMove(posX, posY);
    		seq.setOnFinished(e->{
    			cl.setCard(card, false);
    		});
    		sequence.getChildren().add(seq);	    	
    	}
    	return sequence;
    }
    
    public void setevaluateText(String s) {
			lblEvaluation.setText(multilangModule.getTranslation(s));
    }
    
    public ParallelTransition removeAllCards(int posX, int posY) {
    	ParallelTransition p = new ParallelTransition();
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		Card card = null;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		CardLabel cl = (CardLabel) cards.get(i);
    		cl.setCard(card, true);
    		p.getChildren().addAll(cl.getReverseMove(posX, posY));
    	}
    	lblEvaluation.setText("--");
    	return p;
    }
    
    
    public void setWinner(String message) {
    	lblEvaluation.setText(lblEvaluation.getText()+ "  " + multilangModule.getTranslation(message));
    }
}

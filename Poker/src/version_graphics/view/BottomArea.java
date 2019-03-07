package version_graphics.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import version_graphics.model.PokerGameModel;



public class BottomArea extends BorderPane{
	private ControlArea controls;

	public BottomArea(PokerGameModel model) {
		super();
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		//Regions because FXCSS does not have margin
		Region spaceBottom = new Region();
		spaceBottom.getStyleClass().add("heightSpace");
		Region spaceLeft = new Region();
		spaceLeft.getStyleClass().add("widthSpace");
		Region spaceRight = new Region();
		spaceRight.getStyleClass().add("widthSpace");
		//All Elements in one Borderpane
		BorderPane contolArea = new BorderPane(controls, null, new Statistics(), null, new HandList());
		//Add the elements
		this.setLeft(spaceLeft);
		this.setCenter(contolArea);
		this.setRight(spaceRight);
		this.setBottom(spaceBottom);
	}
	/**
	 * returns the Control area to the PokerGameView
	 * @return the ControlArea
	 */
	protected ControlArea getControls() {
		return controls;
	}
}

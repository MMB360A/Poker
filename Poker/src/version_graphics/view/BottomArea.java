package version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import version_graphics.model.PokerGameModel;
import version_graphics.view.MultiLang.MultiLangModule;



public class BottomArea extends BorderPane{
	private ControlArea controls;
	private Statistics statistics;
	public BottomArea(PokerGameModel model, MultiLangModule multilangModule) {
		super();
		// Create the control area
		controls = new ControlArea(multilangModule);
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		//Regions because FXCSS does not have margin
		Region spaceBottom = new Region();
		spaceBottom.getStyleClass().add("heightSpace");
		Region spaceLeft = new Region();
		spaceLeft.getStyleClass().add("widthSpace");
		Region spaceRight = new Region();
		spaceRight.getStyleClass().add("widthSpace");
		statistics = new Statistics(multilangModule);
		
		String fileName = "pokerhands.jpg";
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + fileName));
		ImageView imv = new ImageView(image);
		imv.setPreserveRatio(true);
		Label handList = new Label();
		handList.getStyleClass().add("handsList");
		handList.setGraphic(imv);
		
		//All Elements in one Borderpane
		BorderPane contolArea = new BorderPane(controls, null,statistics , null, handList);
		//Add the elements
		this.setLeft(spaceLeft);
		this.setCenter(contolArea);
		this.setRight(spaceRight);
		this.setBottom(spaceBottom);
	}
	/**
	 * returns the Control area to the PokerGameView
	 */
	protected ControlArea getControls() {
		return controls;
	}
	/**
	 * returns the Statistics Table area to the PokerGameView
	 */
	protected Statistics getStatistics() {
		
		return statistics;
	}
	/**
	 * Sets the Statistics this method is used after rebuilding the view
	 * @param statistics
	 */
	public void setStatistics(Statistics statistics) {
		if(this.statistics == null)
		this.statistics = statistics;
	}
}

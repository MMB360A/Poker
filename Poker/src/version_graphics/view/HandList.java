package version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HandList extends Label{
	
	public HandList() {

		String fileName = "pokerhands.jpg";
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + fileName));
		ImageView imv = new ImageView(image);
		imv.setPreserveRatio(true);
		this.getStyleClass().add("handsList");
		this.setGraphic(imv);
	}
}

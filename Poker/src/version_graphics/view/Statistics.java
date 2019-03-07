package version_graphics.view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import version_graphics.model.Player;

public class Statistics extends TableView{
	public Statistics() {
		this.getStyleClass().add("statistics");
        TableColumn winnerCol = new TableColumn("Winner");
        TableColumn handCol = new TableColumn("Hand");
        this.getColumns().addAll(winnerCol, handCol);
	}
	
	public void addWinner(Player p) {
		
	}
}

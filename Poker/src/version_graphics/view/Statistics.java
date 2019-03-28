package version_graphics.view;

import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import version_graphics.model.PlayerStatisticsDummie;
import version_graphics.view.MultiLang.MultiLangModule;

/**
 * Statistics Table on the bottom right side of the Mainview it displays PlayerStatisticDummieObjects
 * When the Name or the Score of a Player is updated the Table will update aswell the Handtype stays the same
 * The Translation of the Data happens in the Dummie Object, not in the Table.
 * @author mibe1
 *
 */
public class Statistics extends TableView  implements Cloneable{
	public Statistics(MultiLangModule multilangModule) {
		this.getStyleClass().add("statistics");
        TableColumn winnerCol = new TableColumn((multilangModule.getTranslation("Winner")));
        winnerCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        
        TableColumn handCol = new TableColumn((multilangModule.getTranslation("Hand")));
        handCol.setCellValueFactory(new PropertyValueFactory<>("handType"));
        
        TableColumn totalWinns = new TableColumn((multilangModule.getTranslation("TotalWinns")));
        totalWinns.setCellValueFactory(new PropertyValueFactory<>("totalWinns"));
        
        this.getColumns().addAll(winnerCol, handCol, totalWinns);
        for(int i = 0; i < this.getColumns().size(); i++) 
        	((TableColumn) this.getColumns().get(i)).getStyleClass().add("statisticsCol");
        
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
	}
	/**
	 * Add a new Winner to the table
	 * @param p
	 */
	public void addWinner(PlayerStatisticsDummie p) {
		this.getItems().add(p);
	}
	/**
	 * Reset the whole Table with the winners list.
	 * @param winners
	 */
	public void setWinners(ArrayList<PlayerStatisticsDummie> winners) {
		this.getItems().clear();
		for(PlayerStatisticsDummie p : winners)
			this.getItems().add(p);
	}
}

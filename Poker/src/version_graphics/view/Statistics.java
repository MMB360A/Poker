package version_graphics.view;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import version_graphics.model.Player;

public class Statistics extends TableView{
	public Statistics() {
		this.getStyleClass().add("statistics");
        TableColumn winnerCol = new TableColumn("Winner");
        winnerCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        
        TableColumn handCol = new TableColumn("Hand");
        handCol.setCellValueFactory(new PropertyValueFactory<>("handType"));
        
        TableColumn totalWinns = new TableColumn("Total Winns");
        totalWinns.setCellValueFactory(new PropertyValueFactory<>("totalWinns"));
        
        this.getColumns().addAll(winnerCol, handCol, totalWinns);
        for(int i = 0; i < this.getColumns().size(); i++) ((TableColumn) this.getColumns().get(i)).getStyleClass().add("statisticsCol");
        
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
	}
	
	public void addWinner(Player p) {
		this.getItems().add(p);
	}
}

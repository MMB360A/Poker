package version_graphics.view;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import version_graphics.model.Player;
import version_graphics.view.MultiLang.MultiLangModule;

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
        for(int i = 0; i < this.getColumns().size(); i++) ((TableColumn) this.getColumns().get(i)).getStyleClass().add("statisticsCol");
        
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
	}
	
	public void addWinner(Player p) {
		this.getItems().add(p);
	}
	
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

package version_graphics.view;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import version_graphics.PokerGame;
import version_graphics.model.HandType;
import version_graphics.model.Player;
import version_graphics.model.PokerGameModel;
import version_graphics.view.MultiLang.MultiLangModule;
/**
 * Shows detail statistics in a popup view
 * @author mibe1
 *
 */
public class StatisticsView extends Stage{
	public StatisticsView(PokerGameModel model) {
		 super();
		 //Root element
		 TabPane pane = new TabPane();
		 pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		 if(PokerGame.numberOfGames > 0) {
			 //Winners statistic with Pie Chart
			 Tab players = new Tab(PokerGame.MULTILANGMODULE.getTranslation("player"));
			 ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

			 for(Player p : model.getPlayers()) {
				 PieChart.Data data = new PieChart.Data(p.getPlayerName() + ": " + p.getTotalWinns() + " Wins", p.getTotalWinns());
				 pieChartData.add(data);			 
			 }
		        final PieChart chart = new PieChart(pieChartData);
		        chart.setTitle(PokerGame.MULTILANGMODULE.getTranslation("player"));	 
			 players.setContent(chart);
			 
			 //Hands statistic with BarChart
			 Tab hands = new Tab(PokerGame.MULTILANGMODULE.getTranslation("hands"));
		        CategoryAxis xAxis = new CategoryAxis();
		        NumberAxis yAxis = new NumberAxis();
		        BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
		        bc.setTitle(PokerGame.MULTILANGMODULE.getTranslation("hands"));
				XYChart.Series seriesHand = new XYChart.Series();
		        seriesHand.setName(PokerGame.MULTILANGMODULE.getTranslation("hands"));       
			 for(HandType h : HandType.values()) {
				 seriesHand.getData().add(new XYChart.Data(h.name() + ": " + h.getStatisticNumber(), h.getStatisticNumber()));
			 }
			 bc.getData().add(seriesHand); 
			 hands.setContent(bc);
			 //Add all statistics to the TabPane
			 pane.getTabs().addAll(players, hands);
		 
		 }
		 
	     Scene scene = new Scene(pane, 750, 400);
	     //Set CSS Class
	     if(PokerGameView.darkthem) scene.getStylesheets().add(getClass().getResource("css\\statisticsDark.css").toExternalForm());
	     else scene.getStylesheets().add(getClass().getResource("css\\statisticsLight.css").toExternalForm());
	     this.setTitle(PokerGame.MULTILANGMODULE.getTranslation("statistics"));
	     this.setScene(scene);
	     // Specifies the modality for new window.
	     this.initModality(Modality.WINDOW_MODAL);
	}
}

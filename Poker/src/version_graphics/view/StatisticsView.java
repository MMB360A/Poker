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

public class StatisticsView extends Stage{
	public StatisticsView(MultiLangModule multiLangModule, PokerGameModel model) {
		 super();
		 TabPane pane = new TabPane();
		 pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		 if(PokerGame.numberOfGames > 0) {
			 //Winners statistic with Pie Chart
			 Tab players = new Tab(multiLangModule.getTranslation("player"));
			 ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

			 for(Player p : model.getPlayers()) {
				 PieChart.Data data = new PieChart.Data(p.getPlayerName() + ": " + p.getTotalWinns() + " Wins", p.getTotalWinns());
				 pieChartData.add(data);			 
			 }
		        final PieChart chart = new PieChart(pieChartData);
		        chart.setTitle(multiLangModule.getTranslation("player"));	 
			 players.setContent(chart);
			 
			 //Hands statistic with BarChart
			 Tab hands = new Tab(multiLangModule.getTranslation("hands"));
		        CategoryAxis xAxis = new CategoryAxis();
		        NumberAxis yAxis = new NumberAxis();
		        BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
		        bc.setTitle(multiLangModule.getTranslation("hands"));
		        XYChart.Series seriesHand = new XYChart.Series();
		        seriesHand.setName(multiLangModule.getTranslation("hands"));       
			 for(HandType h : HandType.values()) {
				 seriesHand.getData().add(new XYChart.Data(h.name() + ": " + h.getStatisticNumber(), h.getStatisticNumber()));
			 }
			 bc.getData().add(seriesHand); 
			 hands.setContent(bc);
			 
			 
			 
			 pane.getTabs().addAll(players, hands);
		 
		 }
		 
	     Scene scene = new Scene(pane, 750, 400);
	     //scene.getStylesheets().add(getClass().getResource("multiLang.css").toExternalForm());
	     if(PokerGameView.darkthem) scene.getStylesheets().add(getClass().getResource("css\\statisticsDark.css").toExternalForm());
	     else scene.getStylesheets().add(getClass().getResource("css\\statisticsLight.css").toExternalForm());
	     this.setTitle(multiLangModule.getTranslation("statistics"));
	     this.setScene(scene);
	     // Specifies the modality for new window.
	     this.initModality(Modality.WINDOW_MODAL);
	}
}

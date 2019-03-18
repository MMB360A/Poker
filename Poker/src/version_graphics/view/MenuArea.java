package version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import version_graphics.view.MultiLang.MultiLangModule;

public class MenuArea extends MenuBar{
	private MenuItem languageSetting;
	private MenuItem addPlayer;
	private MenuItem removePlayer;
	private MenuItem about;
	private MenuItem changeSkin;
	public MenuArea(MultiLangModule multilangModule) {
		super();
		
		if(PokerGameView.darkthem) this.getStylesheets().add(getClass().getResource("css\\MenuDark.css").toExternalForm());
		else this.getStylesheets().add(getClass().getResource("css\\MenuLight.css").toExternalForm());
		this.getStyleClass().add("MenuBar");
		Menu file = new Menu(multilangModule.getTranslation("File"));
		file.getStyleClass().add("Menu");
		about= new MenuItem(multilangModule.getTranslation("about"));
		about.getStyleClass().add("MenuItem");
		file.getItems().add(about);
		
		Menu settings = new Menu(multilangModule.getTranslation("Settings"));
		settings.getStyleClass().add("Menu");
		languageSetting = new MenuItem(multilangModule.getTranslation("Language"));
		languageSetting.getStyleClass().add("MenuItem");
		Menu numOfPlayers = new Menu("NumOfPlayers");
		numOfPlayers.getStyleClass().add("Menu");
		addPlayer = new MenuItem(multilangModule.getTranslation("addPlayer"));
		addPlayer.getStyleClass().add("MenuItem");
		removePlayer = new MenuItem(multilangModule.getTranslation("removePlayer"));
		removePlayer.getStyleClass().add("MenuItem");
		numOfPlayers.getItems().addAll(addPlayer, removePlayer);
		settings.getItems().addAll(languageSetting, numOfPlayers);
		
		Menu view = new Menu(multilangModule.getTranslation("view"));
		changeSkin = new MenuItem();
		if(PokerGameView.darkthem) changeSkin.setText(multilangModule.getTranslation("lightTheme"));
		else changeSkin.setText(multilangModule.getTranslation("darkTheme"));
		changeSkin.getStyleClass().add("MenuItem");
		view.getItems().addAll(changeSkin);
		
		this.getMenus().addAll(file, settings, view);
		
	}
	public MenuItem getLanguageSetting() {
		return languageSetting;
	}
	public MenuItem getAddPlayer() {
		return addPlayer;
	}
	public MenuItem getRemovePlayer() {
		return removePlayer;
	}
	public MenuItem getAbout() {
		return about;
	}
	public MenuItem getChangeSkin() {
		return changeSkin;
	}
	

	
}

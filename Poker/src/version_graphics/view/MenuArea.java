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
		this.getStylesheets().add(getClass().getResource("css\\Menu.css").toExternalForm());
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
		changeSkin = new MenuItem(multilangModule.getTranslation("changeSkin"));
		changeSkin.getStyleClass().add("MenuItem");
		Menu numOfPlayers = new Menu("NumOfPlayers");
		numOfPlayers.getStyleClass().add("Menu");
		
		addPlayer = new MenuItem(multilangModule.getTranslation("NumOfPlayers"));
		addPlayer.getStyleClass().add("MenuItem");
		removePlayer = new MenuItem(multilangModule.getTranslation("NumOfPlayers"));
		removePlayer.getStyleClass().add("MenuItem");
		numOfPlayers.getItems().addAll(addPlayer, removePlayer);
		settings.getItems().addAll(languageSetting, numOfPlayers, changeSkin);
		this.getMenus().addAll(file, settings);
		
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

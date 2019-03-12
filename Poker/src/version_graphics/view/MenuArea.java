package version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import version_graphics.view.MultiLang.MultiLangModule;

public class MenuArea extends MenuBar{
	private MenuItem langugageSetting;
	private MenuItem numOfPlayers;
	public MenuArea(MultiLangModule multilangModule) {
		super();
		
		Menu file = new Menu(multilangModule.getTranslation("File"));
		Menu settings = new Menu(multilangModule.getTranslation("Settings"));
		langugageSetting = new MenuItem(multilangModule.getTranslation("Language"));
		
		numOfPlayers = new MenuItem(multilangModule.getTranslation("NumOfPlayers"));
		settings.getItems().addAll(langugageSetting, numOfPlayers);
		this.getMenus().addAll(file, settings);
		
	}
	public MenuItem getLangugageSetting() {
		return langugageSetting;
	}
	public MenuItem getNumOfPlayers() {
		return numOfPlayers;
	}
	
}

package version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import version_graphics.view.MultiLang.MultiLangModule;
/**
 * Menu Area
 * @author mibe1
 *
 */
public class MenuArea extends MenuBar{
	//All MenuItems are Global to set the Actions in the Controler 
	private MenuItem languageSetting;
	private MenuItem addPlayer;
	private MenuItem removePlayer;
	private MenuItem about;
	private MenuItem changeSkin;
	private MenuItem changeUserName;
	private MenuItem viewStatistics;
	
	public MenuArea(MultiLangModule multilangModule) {
		super();
		//CSS Style Sheet
		if(PokerGameView.darkthem) this.getStylesheets().add(getClass().getResource("css\\MenuDark.css").toExternalForm());
		else this.getStylesheets().add(getClass().getResource("css\\MenuLight.css").toExternalForm());
		//Set Styleclass
		this.getStyleClass().add("MenuBar");
		
		//Menu File
		Menu file = new Menu(multilangModule.getTranslation("File"));
		about= new MenuItem(multilangModule.getTranslation("about"));
		viewStatistics= new MenuItem(multilangModule.getTranslation("viewStatistics"));
		file.getItems().addAll(about, viewStatistics);
		
		//Menu Settings
		Menu settings = new Menu(multilangModule.getTranslation("Settings"));
		languageSetting = new MenuItem(multilangModule.getTranslation("Language"));
		changeUserName= new MenuItem(multilangModule.getTranslation("changeUserNames"));
		//SubMenu Number of Players
		Menu numOfPlayers = new Menu(multilangModule.getTranslation("NumOfPlayers"));
		addPlayer = new MenuItem(multilangModule.getTranslation("addPlayer"));
		removePlayer = new MenuItem(multilangModule.getTranslation("removePlayer"));
		numOfPlayers.getItems().addAll(addPlayer, removePlayer);
		//Add the Items and the Submenu to the Settings Menu
		settings.getItems().addAll(languageSetting, numOfPlayers, changeUserName);
		
		//Menu View
		Menu view = new Menu(multilangModule.getTranslation("view"));
		changeSkin = new MenuItem();
		if(PokerGameView.darkthem) changeSkin.setText(multilangModule.getTranslation("lightTheme"));
		else changeSkin.setText(multilangModule.getTranslation("darkTheme"));
		view.getItems().addAll(changeSkin);
		
		//Add all Menus to the Menubar
		this.getMenus().addAll(file, settings, view);
		
	}
	//getters of the MenuItems to add Actions in the controler
	public MenuItem getLanguageSetting() {return languageSetting;}
	public MenuItem getAddPlayer() {return addPlayer;}
	public MenuItem getRemovePlayer() {return removePlayer;}
	public MenuItem getAbout() {return about;}
	public MenuItem getChangeSkin() {return changeSkin;}
	public MenuItem getChangeUserName() {return changeUserName;}
	public MenuItem getViewStatistics() {return viewStatistics;}

	
}

package version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import version_graphics.PokerGame;
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
	
	public MenuArea() {
		super();
		//CSS Style Sheet
		if(PokerGameView.darkthem) this.getStylesheets().add(getClass().getResource("css\\MenuDark.css").toExternalForm());
		else this.getStylesheets().add(getClass().getResource("css\\MenuLight.css").toExternalForm());
		//Set Styleclass
		this.getStyleClass().add("MenuBar");
		
		//Menu File
		Menu file = new Menu(PokerGame.MULTILANGMODULE.getTranslation("File"));
		about= new MenuItem(PokerGame.MULTILANGMODULE.getTranslation("About"));
		viewStatistics= new MenuItem(PokerGame.MULTILANGMODULE.getTranslation("ViewStatistics"));
		file.getItems().addAll(about, viewStatistics);
		
		//Menu Settings
		Menu settings = new Menu(PokerGame.MULTILANGMODULE.getTranslation("Settings"));
		languageSetting = new MenuItem(PokerGame.MULTILANGMODULE.getTranslation("Language"));
		changeUserName= new MenuItem(PokerGame.MULTILANGMODULE.getTranslation("ChangeUserNames"));
		//SubMenu Number of Players
		Menu numOfPlayers = new Menu(PokerGame.MULTILANGMODULE.getTranslation("NumOfPlayers"));
		addPlayer = new MenuItem(PokerGame.MULTILANGMODULE.getTranslation("AddPlayer"));
		removePlayer = new MenuItem(PokerGame.MULTILANGMODULE.getTranslation("RemovePlayer"));
		numOfPlayers.getItems().addAll(addPlayer, removePlayer);
		//Add the Items and the Submenu to the Settings Menu
		settings.getItems().addAll(languageSetting, numOfPlayers, changeUserName);
		
		//Menu View
		Menu view = new Menu(PokerGame.MULTILANGMODULE.getTranslation("View"));
		changeSkin = new MenuItem();
		if(PokerGameView.darkthem) changeSkin.setText(PokerGame.MULTILANGMODULE.getTranslation("LightTheme"));
		else changeSkin.setText(PokerGame.MULTILANGMODULE.getTranslation("DarkTheme"));
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

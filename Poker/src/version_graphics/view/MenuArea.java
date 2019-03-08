package version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuArea extends MenuBar{
	
	public MenuArea() {
		super();
		
		Menu file = new Menu("File");
		Menu settings = new Menu("Settings");
		this.getMenus().add(file);
		
	}
}

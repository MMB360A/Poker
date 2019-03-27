package version_graphics.view;

import javafx.scene.control.Label;
import version_graphics.view.MultiLang.MultiLangModule;

public class CasinoLabel extends Label{
	public CasinoLabel(MultiLangModule multilangModule) {
		this.getStyleClass().add("casinoLabel");
		this.setText(multilangModule.getTranslation("CasinoLabel"));
	}
}

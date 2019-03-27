package version_graphics.view.MultiLang;

import java.util.ArrayList;

public class MultiLangElement {
	

	private String id;
	private ArrayList<LangElement> elements;
	
	public MultiLangElement(String id) {
		this.id = id;
		elements = new ArrayList<LangElement>();
	}
	
	public void addLangElement(LangElement e) {
		elements.add(e);
	}

	@Override
	public String toString() {
		return "MultiLangElement [id=" + id + ", elements=" + elements + "]";
	}

	public String getId() {
		return id;
	}

	public ArrayList<LangElement> getElements() {
		return elements;
	}
	
	
}

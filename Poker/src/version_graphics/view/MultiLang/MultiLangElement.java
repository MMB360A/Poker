package version_graphics.view.MultiLang;

import java.util.ArrayList;

public class MultiLangElement {
	

	private String id;
	private ArrayList<LangElement> elements;
	
	public MultiLangElement(String id) {
		this.id = id;
		elements = new ArrayList<LangElement>();
	}
	
	public void addLangElement(String language, String translation) {
		elements.add(new LangElement(language, translation));
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
	
	public String getTranslation(String lang) {
		String val = "P";
		for(LangElement l: elements) {
			if(l.language.equals(lang)) 
				val = l.value;
		}
		return val;
	}
	
	private class LangElement {
		private String language;		
		private String value;		
		public LangElement(String language, String value) {
			this.language = language;
			this.value = value;
		}
	}
	
	
}

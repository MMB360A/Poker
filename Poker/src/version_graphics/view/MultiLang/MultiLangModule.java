package version_graphics.view.MultiLang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class MultiLangModule {
	private String defalutLanguage;


	private ArrayList<MultiLangElement> translations;
	private ArrayList<String> languages;
	
	public MultiLangModule(String langugage) {
		languages = new ArrayList<String>();
		translations = new ArrayList<MultiLangElement>();
		this.defalutLanguage = langugage;
		this.readBasicElements();
		
		//for(MultiLangElement e : translations) System.out.println(e);
	}
	
	public void setDefalutLanguage(String defalutLanguage) {
		this.defalutLanguage = defalutLanguage;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void readBasicElements() {
		 String csvFile = System.getProperty("user.dir")+ "\\src\\version_graphics\\view\\MultiLang\\translations.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ";";
	        
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            line = br.readLine();
	            String[] langs = line.split(cvsSplitBy);
	            for(int i = 1; i < langs.length; i++) languages.add(langs[i]);
	            while ((line = br.readLine()) != null) {
	                String[] translation = line.split(cvsSplitBy);
	                MultiLangElement e = new MultiLangElement(translation[0]);
	                for(int i = 1; i < translation.length; i++) e.addLangElement(new LangElement(languages.get(i -1), translation[i]));
	                translations.add(e);              
	        }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	}
	
	
	public String getTranslation(String id) {
		String value = "Not Found";
		MultiLangElement multielement;
		LangElement langElement;
		for(MultiLangElement e : translations) {
			if(e.getId().compareTo(id) == 0) {
				
				multielement = e;
				for(LangElement le : multielement.getElements()) 
				{
				if(le.getLanguage().compareTo(this.defalutLanguage) == 0) value = le.getValue();
				}
			}
		}
		return value;
		
	}
	
	public String getTranslationByLanguage(String id, String language) {
		String value = "Not Found";
		MultiLangElement multielement;
		LangElement langElement;
		for(MultiLangElement e : translations) {
			if(e.getId() == id) {
				multielement = e;
				for(LangElement le : multielement.getElements()) 
				{
				if(le.getLanguage() == language) value = le.getValue();
				}
			}
		}
		return value;
	}
	
	public String getDefalutLanguage() {
		return defalutLanguage;
	}

	public ChangeLanguageView setDefalutLanguage(Stage primaryStage) {
		ChangeLanguageView view = new ChangeLanguageView(this, primaryStage);
		view.getCancel().setOnAction(e -> cancel(view));
		view.getOk().setOnAction(e -> save(view));
		
		return view;
	}
	
	private void cancel(ChangeLanguageView view) {
		view.close();
	}

	private void save(ChangeLanguageView view) {
		RadioButton btn = (RadioButton) view.getTg().getSelectedToggle();
		this.defalutLanguage = btn.getText();
		view.close();
	}
	


}

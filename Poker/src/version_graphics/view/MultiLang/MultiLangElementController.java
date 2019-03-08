package version_graphics.view.MultiLang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MultiLangElementController {
	private String defalutLanguage;
	private ArrayList<MultiLangElement> translations;
	private ArrayList<String> languages;
	public MultiLangElementController() {
		languages = new ArrayList<String>();
		translations = new ArrayList<MultiLangElement>();
		this.readBasicElements();
		for(MultiLangElement e : translations) System.out.println(e);
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
	
	
	public void getTranslation() {
		
	}
}

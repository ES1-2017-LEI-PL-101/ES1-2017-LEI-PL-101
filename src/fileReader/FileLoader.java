package fileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FileLoader {
	
	private static HashMap <String,Double> rulesMap = new HashMap<String, Double>();
	private static HashMap <String, ArrayList<String>> HamRulesMap = new HashMap<String, ArrayList<String>>();
	private static HashMap <String, ArrayList<String>> SpamRulesMap = new HashMap<String, ArrayList<String>>();
	private static final FileLoader INSTANCE = new FileLoader();

		
	public static FileLoader getInstance() {
		return INSTANCE;
	}

	//Write on hashmap the content of the file
	public static void readFile(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				rulesMap.put(sCurrentLine, -1.0);
				//System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public static void readLogFile(String filePath) { 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] parts = sCurrentLine.split("	");
				ArrayList<String> a = new ArrayList<String>();
				for(int i=1; i<parts.length; i++) {
					a.add(parts[i]);
				}
				if(filePath.contains("spam.log")) {
					SpamRulesMap.put(parts[0], a);
				} else {
					HamRulesMap.put(parts[0], a);
				}
				
				//System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Test file to hashMap
	public static void main(String[] args) {
		FileLoader.getInstance().readFile("AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/rules.cf");
		FileLoader.getInstance().readLogFile("AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/ham.log");
		System.out.println(FileLoader.getInstance().getRulesMap().toString());
		System.out.println(FileLoader.getInstance().getHamRulesMap().toString());
		
		//FileLoader rulesFileScanner = new FileLoader("AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/rules.cf");
		//FileLoader hamFileScanner = new FileLoader("/ES1-2017/AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/ham.log");
		//FileLoader spamFileScanner = new FileLoader("/ES1-2017/AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/spam.log");
		//System.out.println(rulesFileScanner.getRulesMap());
		//System.out.println(hamFileScanner.getLogMap());
		//System.out.println(spamFileScanner.getLogMap());
	}
	
	public HashMap<String, Double> getRulesMap() {
		return rulesMap;
	}

	public HashMap<String, ArrayList<String>> getHamRulesMap() {
		return HamRulesMap;
	}

	public HashMap<String, ArrayList<String>> getSpamRulesMap() {
		return SpamRulesMap;
	}
	

	

}

		
	

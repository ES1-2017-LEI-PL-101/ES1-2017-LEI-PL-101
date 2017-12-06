package fileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileScanner {
	
	private static Map<String, String> rulesMap = new HashMap<String, String>();
	private static Map<String, ArrayList<String>> logMap = new HashMap<String, ArrayList<String>>();
	
//	public fileScanner(String filePath) {
//		if(filePath.contains("rules.cf")) {
//			readFile(filePath);
//		} else {
//			readLogFile(filePath);
//		}
//	}
	
	//Write on hashmap the content of the file
	public static void readFile(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				rulesMap.put(sCurrentLine, "0");
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
		System.out.println(getRulesMap());
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
				logMap.put(parts[0], a);
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
//	public static void main(String[] args) {
//		fileScanner rulesFileScanner = new fileScanner("/ES1-2017/AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/rules.cf");
//		fileScanner hamFileScanner = new fileScanner("/ES1-2017/AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/ham.log");
//		fileScanner spamFileScanner = new fileScanner("/ES1-2017/AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/spam.log");
//		System.out.println(rulesFileScanner.getRulesMap());
//		System.out.println(hamFileScanner.getLogMap());
//		System.out.println(spamFileScanner.getLogMap());
//	}
	
	public static Map<String, String> getRulesMap() {
		return rulesMap;
	}
	
	public static Map<String, ArrayList<String>> getLogMap() {
		return logMap;
	}

}

		
	

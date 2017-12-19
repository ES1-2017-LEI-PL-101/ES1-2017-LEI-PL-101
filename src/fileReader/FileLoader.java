package fileReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileLoader {

	private static LinkedHashMap<String, Double> rulesMap = new LinkedHashMap<String, Double>();
	private static LinkedHashMap<String, ArrayList<String>> hamRulesMap = new LinkedHashMap<String, ArrayList<String>>();
	private static LinkedHashMap<String, ArrayList<String>> spamRulesMap = new LinkedHashMap<String, ArrayList<String>>();
	private static final FileLoader INSTANCE = new FileLoader();

	/**
	 * This method is used to get the FileLoader Instance.
	 * 
	 * @return FileLoader This method returns the FileLoader Instance.
	 */
	public static FileLoader getInstance() {
		return INSTANCE;
	} 

	// Write on hashmap the content of the file
	/**
	 * This method is used to read rules.
	 * 
	 * @param filePath
	 */
	public static void readFile(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] line = sCurrentLine.split(" ");
				if (line.length > 1) {
					rulesMap.put(line[0], Double.parseDouble(line[1]));
				} else {
					rulesMap.put(sCurrentLine, 0.0);
				}

				// System.out.println(sCurrentLine);
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

	/**
	 * This method is used to read logs files, that is, spam rules and ham rules.
	 * 
	 * @param filePath
	 */
	public static void readLogFile(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] parts = sCurrentLine.split("	");
				ArrayList<String> a = new ArrayList<String>();
				for (int i = 1; i < parts.length; i++) {
					a.add(parts[i]);
				}
				if (filePath.contains("spam.log")) {
					spamRulesMap.put(parts[0], a);
				} else {
					hamRulesMap.put(parts[0], a);
				}

				// System.out.println(sCurrentLine);
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

	// Test file to hashMap
	// public static void main(String[] args) {
	// FileLoader.getInstance().readFile("AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/rules.cf");
	// FileLoader.getInstance().readLogFile("AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/ham.log");
	// System.out.println(FileLoader.getInstance().getRulesMap().toString());
	// System.out.println(FileLoader.getInstance().getHamRulesMap().toString());
	//
	// //FileLoader rulesFileScanner = new
	// FileLoader("AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/rules.cf");
	// //FileLoader hamFileScanner = new
	// FileLoader("/ES1-2017/AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/ham.log");
	// //FileLoader spamFileScanner = new
	// FileLoader("/ES1-2017/AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox/spam.log");
	// //System.out.println(rulesFileScanner.getRulesMap());
	// //System.out.println(hamFileScanner.getLogMap());
	// //System.out.println(spamFileScanner.getLogMap());
	// }

	/**
	 * This method is used to get the rules HashMap.
	 * 
	 * @return LinkedHashMap This method returns the rules map.
	 */
	public LinkedHashMap<String, Double> getRulesMap() {
		return rulesMap;
	}

	/**
	 * This method is used to get the hamRules HashMap.
	 * 
	 * @return LinkedHashMap This method returns the hamRules map.
	 */
	public LinkedHashMap<String, ArrayList<String>> getHamRulesMap() {
		return hamRulesMap;
	}

	/**
	 * This method is used to get the spamRules HashMap.
	 * 
	 * @return LinkedHashMap This method returns the spamRules map.
	 */
	public LinkedHashMap<String, ArrayList<String>> getSpamRulesMap() {
		return spamRulesMap;
	}

	/**
	 * This method is used to write the new rules HashMap in the specific file path.
	 * 
	 * @param filePath
	 * @param rules
	 */
	public static void writeFile(String filePath, LinkedHashMap<String, Double> rules) {

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"))) {
			for (Entry<String, Double> entry : rules.entrySet()) {
				String key = entry.getKey();
				Double value = entry.getValue();
				writer.write(key.toString() + " " + value.toString());
				writer.write(System.lineSeparator());
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to set new rules in rules map.
	 * 
	 * @param newRules
	 */
	public void setRules(LinkedHashMap<String, Double> newRules) {
		this.rulesMap = newRules;
	}

}

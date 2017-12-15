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
	private static LinkedHashMap<String, ArrayList<String>> HamRulesMap = new LinkedHashMap<String, ArrayList<String>>();
	private static LinkedHashMap<String, ArrayList<String>> SpamRulesMap = new LinkedHashMap<String, ArrayList<String>>();
	private static final FileLoader INSTANCE = new FileLoader();

	public static FileLoader getInstance() {
		return INSTANCE;
	}

	// Write on hashmap the content of the file
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
					SpamRulesMap.put(parts[0], a);
				} else {
					HamRulesMap.put(parts[0], a);
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

	public LinkedHashMap<String, Double> getRulesMap() {
		return rulesMap;
	}

	public LinkedHashMap<String, ArrayList<String>> getHamRulesMap() {
		return HamRulesMap;
	}

	public LinkedHashMap<String, ArrayList<String>> getSpamRulesMap() {
		return SpamRulesMap;
	}

	public static void writeFile(String filePath, LinkedHashMap <String,Double> rules) {

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
	
	public void setRules(LinkedHashMap<String, Double> newRules) {
		this.rulesMap = newRules;
	}

}

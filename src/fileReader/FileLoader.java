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
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FileLoader {

	/**
	 * This method is used to read rules files.
	 * 
	 * @param filePath
	 */
	public static LinkedHashMap<String, Double> readRulesFile(String filePath) {
		BufferedReader br = null;
		LinkedHashMap<String, Double> rulesMap = new LinkedHashMap<String, Double>();
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
		return rulesMap;
	}

	/**
	 * This method is used to read logs files, that is, spam rules and ham rules.
	 * 
	 * @param filePath
	 */
	public static LinkedHashMap<String, ArrayList<String>> readHamOrSpamFile(String filePath) {
		BufferedReader br = null;
		LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] parts = sCurrentLine.split("	");
				ArrayList<String> a = new ArrayList<String>();
				for (int i = 1; i < parts.length; i++) {
					a.add(parts[i]);
				}
				map.put(parts[0], a);
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
		return map;
	}

	/**
	 * This method is used to search for the lowest false negative.
	 * 
	 * @param filePath
	 * @param column
	 * @return integer This method returns the corresponding line of lowest FN.
	 */
	public static int readNSGAII_LowerIndex(String filePath, int column) {
		BufferedReader br = null;

		int fnCol = (column >= 0 ? column : 1);
		
		Double min = -1.0;
		int index = -1;

		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			int nCurrentLine = 0;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] parts = sCurrentLine.split(" ");

				if (nCurrentLine == 0) {
					min = Double.parseDouble(parts[fnCol]);

					index = 0;
				} else {
					if (parts.length > 1) {
						if (Double.parseDouble(parts[fnCol]) < min) {
							min = Double.parseDouble(parts[fnCol]);
							index = nCurrentLine;
						}
					}
				}
				nCurrentLine++;
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
		return index;
	}

	/**
	 * This method is used to read values from AntiSpamFilterProblem.NSGAII.rs by
	 * index.
	 * 
	 * @param filePath
	 * @param index
	 * @return Double This method returns the Leisure Mail values .
	 */
	public static Double[] readNSGAII_Values(String filePath, int index) {
		BufferedReader br = null;
		Double[] valuesMap = null;

		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			int nCurrentLine = 0;

			while (nCurrentLine != index) {
				br.readLine();
				nCurrentLine++;
			}
			sCurrentLine = br.readLine();
			String[] parts = sCurrentLine.split(" ");
			valuesMap = new Double[parts.length];
			for (int i = 0; i != parts.length; i++) {
				valuesMap[i] = Double.parseDouble(parts[i]);
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
		return valuesMap;
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
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

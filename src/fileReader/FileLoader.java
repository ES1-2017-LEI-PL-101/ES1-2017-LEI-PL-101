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


	// Write on hashmap the content of the file
	/**
	 * This method is used to read rules.
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
	 * 
	 * @param filePath AntiSpamFilterProblem.NSGAII.rf
	 * @return integer index of lower FN on AntiSpamFilterProblemNSGAII.rs
	 */
	public static int readNSGAII_LowerIndex(String filePath, int Column){
		BufferedReader br = null;
		
		int FnCol = (Column >= 0 ? Column :1);
		//Min[a,b];
		//a menor valor
		//b indice
		Double min = -1.0;
		int index = -1;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			int nCurrentLine = 0;
						
			while ((sCurrentLine = br.readLine()) != null) {
				String[] parts = sCurrentLine.split(" ");
				
				//Min[a,b];
				//a menor valor
				//b indice
				if(nCurrentLine == 0){
				min = Double.parseDouble(parts[FnCol]);
				
				index = 0;
				}else{
					if(parts.length > 1){	
							//seleciona o valor minimo
							if(Double.parseDouble(parts[FnCol]) < min){
								min = Double.parseDouble(parts[FnCol]);
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
	 * Read values from AntiSpamFilterProblem.NSGAII.rs by index
	 * @param filePath AntiSpamFilterProblem.NSGAII.rs
	 * @param index Line
	 * @return
	 */
	public static Double[] readNSGAII_Values(String filePath, int index) {
		BufferedReader br = null;
		Double[] valuesMap = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			int nCurrentLine = 0;
			
			while (nCurrentLine!=index) {
					br.readLine();
					nCurrentLine++;
			}
				sCurrentLine = br.readLine();
				String[] parts = sCurrentLine.split(" ");
				valuesMap = new Double [parts.length];
				for(int i = 0 ; i!=parts.length;i++ ){
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



}

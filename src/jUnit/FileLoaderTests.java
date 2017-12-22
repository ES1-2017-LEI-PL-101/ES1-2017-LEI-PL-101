package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import fileReader.FileLoader;

class FileLoaderTests {

	@Test
	void testReadRulesFile() {
		new FileLoader();
		assertTrue(FileLoader.readRulesFile("./files/rules.cf") instanceof LinkedHashMap<?,?>);
	}

	@Test
	void testReadHamOrSpamFile() {
		new FileLoader();
		assertTrue(FileLoader.readHamOrSpamFile("./files/spam.log") instanceof LinkedHashMap<?,?>);
		assertTrue(FileLoader.readHamOrSpamFile("./files/ham.log") instanceof LinkedHashMap<?,?>);
	}

	@Test
	void testWriteFile() {
		new FileLoader();
		LinkedHashMap<String, Double> rules = FileLoader.readRulesFile("./files/rules.cf");
		LinkedHashMap<String, Double> l = new LinkedHashMap<String, Double>();
		FileLoader.writeFile("./files/rules.cf", l);
		assertEquals(FileLoader.readRulesFile("./files/rules.cf"), l);
		FileLoader.writeFile("./files/rules.cf", rules);
		
	}

}

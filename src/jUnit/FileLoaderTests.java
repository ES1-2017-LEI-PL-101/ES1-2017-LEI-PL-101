package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import fileReader.FileLoader;

class FileLoaderTests {

	@Test
	void testReadRulesFile() {
		new FileLoader();
		assertTrue(FileLoader.readRulesFile("/ES1-2017/files/rules.cf") instanceof LinkedHashMap<?,?>);
	}

	@Test
	void testReadHamOrSpamFile() {
		new FileLoader();
		assertTrue(FileLoader.readHamOrSpamFile("/ES1-2017/files/spam.log") instanceof LinkedHashMap<?,?>);
		assertTrue(FileLoader.readHamOrSpamFile("/ES1-2017/files/ham.log") instanceof LinkedHashMap<?,?>);
	}

	@Test
	void testWriteFile() {
		new FileLoader();
		LinkedHashMap<String, Double> l = new LinkedHashMap<String, Double>();
		FileLoader.writeFile("/ES1-2017/files/rules.cf", l);
		assertEquals(FileLoader.readRulesFile("/ES1-2017/files/rules.cf"), l);
	}

}

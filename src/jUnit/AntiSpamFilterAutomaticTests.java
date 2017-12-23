package jUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;

class AntiSpamFilterAutomaticTests{
	AntiSpamFilterAutomaticConfiguration a = new AntiSpamFilterAutomaticConfiguration();
	
	@Test
	void testGetAntiSpamFilterProblem() {
		assertTrue(a.getAntiSpamFilterProblem() instanceof AntiSpamFilterProblem);
	}
	
	@Test
	void testSetAntiSpamFilterProblem() {
		AntiSpamFilterProblem antiSpamProb= new AntiSpamFilterProblem();
		AntiSpamFilterProblem antiSpam= a.getAntiSpamFilterProblem();
		a.setAntiSpamFilterProblem(antiSpamProb); 
		assertNotEquals(a.getAntiSpamFilterProblem(), antiSpam);
	}
	
	@Test
	
	void testGetExperimentBaseDirectory() {
		assertNotEquals(AntiSpamFilterAutomaticConfiguration.getExperimentBaseDirectory(), "");
	}
	

	@Test
	
	void testGetClassName() {
		assertNotEquals(AntiSpamFilterAutomaticConfiguration.getsClassName(), "");
	}

}

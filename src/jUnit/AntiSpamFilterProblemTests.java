package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import antiSpamFilter.AntiSpamFilterProblem;

class AntiSpamFilterProblemTests{
	
	AntiSpamFilterProblem a = new AntiSpamFilterProblem();
	
	@Test
	void testAntiSpamFilterProblem() {
		assertTrue(new AntiSpamFilterProblem() instanceof AntiSpamFilterProblem);
	}

	@Test
	void testUpdateAntiSpamFilterProblem() {
		fail("Not yet implemented");
	}

	@Test
	void testReadRules() {
		
		a.readRules("/ES1-2017/files/rules.cf");
		assertNotEquals(a.getRules(), null);
	}

	@Test
	void testReadHam() {
		a.readHam("/ES1-2017/files/ham.log");
		assertNotEquals(a.getHam(), null);
	}

	@Test
	void testReadSpam() {
		a.readSpam("/ES1-2017/files/spam.log");
		assertNotEquals(a.getSpam(), null);
	}

	@Test
	void testEvaluateDoubleSolution() {
		fail("Not yet implemented");
	}

	@Test
	void testEvaluateLinkedHashMapOfStringDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRules() {
		assertTrue(new AntiSpamFilterProblem().getRules() instanceof  LinkedHashMap<?,?>);
	}

	@Test
	void testGetHam() {
		assertTrue(new AntiSpamFilterProblem().getHam() instanceof  LinkedHashMap<?,?>);
	}

	@Test
	void testGetSpam() {
		assertTrue(new AntiSpamFilterProblem().getSpam() instanceof  LinkedHashMap<?,?>);
	}

	@Test
	void testValidLists() {
		a.readRules("/ES1-2017/files/rules.cf");
		a.readSpam("/ES1-2017/files/spam.log");
		a.readHam("/ES1-2017/files/ham.log");
		assertEquals(a.validLists(), true);
	}

	@Test
	void testSetRules() {
		LinkedHashMap<String, Double> l = new LinkedHashMap<String, Double>();
		a.setRules(l);
		assertEquals(a.getRules(), l);
	}

	@Test
	void testGetCountFN() {
		assertNotEquals(Integer.valueOf(a.getCountFN()), null);
	}

	@Test
	void testGetCountFP() {
		assertNotEquals(Integer.valueOf(a.getCountFP()), null);
	}
}

package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import antiSpamFilter.AntiSpamFilterProblem;

class AntiSpamFilterProblemTests{
	
	@Test
	void testAntiSpamFilterProblem() {
		assertTrue(new AntiSpamFilterProblem() instanceof AntiSpamFilterProblem);
	}

	/*@Test
	void testUpdateAntiSpamFilterProblem() {
		fail("Not yet implemented");
	}*/

	@Test
	void testReadRules() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		a.readRules("./files/rules.cf");
		assertNotEquals(a.getRules(), new LinkedHashMap<>());
	}

	@Test
	void testReadHam() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		a.readHam("./files/ham.log");
		assertNotEquals(a.getHam(), new LinkedHashMap<>());
	}

	@Test
	void testReadSpam() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		a.readSpam("./files/spam.log");
		assertNotEquals(a.getSpam(), new LinkedHashMap<>());
	}

	/*@Test
	void testEvaluateDoubleSolution() {
		fail("Not yet implemented");
	}*/

	@Test
	void testEvaluateLinkedHashMapOfStringDouble() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		a.readRules("./files/rules.cf");
		assertTrue(a.evaluate(a.getRules()) instanceof double[]);
	    assertNotEquals(a.evaluate(a.getRules()).length, 0);
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
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		a.readRules("./files/rules.cf");
		a.readSpam("./files/spam.log");
		a.readHam("./files/ham.log");
		assertEquals(a.validLists(), true);
		AntiSpamFilterProblem b = new AntiSpamFilterProblem();
		b.readSpam("./files/spam.log");
		b.readHam("./files/ham.log");
		assertEquals(b.validLists(), false);
		AntiSpamFilterProblem c = new AntiSpamFilterProblem();
		c.readSpam("./files/spam.log");
		c.readRules("./files/rules.cf");
		assertEquals(c.validLists(), false);
		AntiSpamFilterProblem d = new AntiSpamFilterProblem();
		d.readHam("./files/ham.log");
		d.readRules("./files/rules.cf");
		assertEquals(d.validLists(), false);
	}

	@Test
	void testSetRules() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		LinkedHashMap<String, Double> l = new LinkedHashMap<String, Double>();
		a.setRules(l);
		assertEquals(a.getRules(), l);
	}

	@Test
	void testGetCountFN() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		assertNotEquals(Integer.valueOf(a.getCountFN()), null);
	}

	@Test
	void testGetCountFP() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		assertNotEquals(Integer.valueOf(a.getCountFP()), null);
	}
}

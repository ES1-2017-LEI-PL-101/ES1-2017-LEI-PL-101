package jUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import antiSpamFilter.AntiSpamFilterProblem;
import antiSpamUI.AntiSpamGUI;

class AntiSpamGUITests{

	AntiSpamGUI a = new AntiSpamGUI();
	
	@Test
	void testAntiSpamGUI() {
		assertTrue(a instanceof AntiSpamGUI);
	}

	@Test
	void testSetRules() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAntiSpamFilterProblem() {
		assertTrue(a.getAntiSpamFilterProblem() instanceof AntiSpamFilterProblem);
	}

}

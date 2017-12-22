package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTable;

import org.junit.jupiter.api.Test;
import antiSpamFilter.AntiSpamFilterProblem;
import antiSpamUI.AntiSpamGUI;
import antiSpamUI.Frame;

class AntiSpamGUITests{

	AntiSpamGUI a = new AntiSpamGUI();
	
	@Test
	void testAntiSpamGUI() {
		assertTrue(a instanceof AntiSpamGUI);
	}

	@Test
	void testSetRules() {
		JTable jtm = a.getFrame().getTableManual();
		JTable jta = a.getFrame().getTableAuto();
		a.setRules("Manual", true);
		a.setRules("Auto", false);
		assertEquals(jtm, a.getFrame().getTableManual());
		assertEquals(jta, a.getFrame().getTableAuto());
	}

	@Test
	void testGetAntiSpamFilterProblem() {
		assertTrue(a.getAntiSpamFilterProblem() instanceof AntiSpamFilterProblem);
	}
	
	@Test
	void testGetFrame() {
		assertTrue(a.getFrame() instanceof Frame);
	}

}

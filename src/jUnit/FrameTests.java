package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import antiSpamUI.AntiSpamGUI;
import antiSpamUI.Frame;

class FrameTests {

	Frame f = new Frame(new AntiSpamGUI());
	
	@Test
	void testFrame() {
		assertTrue(f instanceof Frame);
	}

	@Test
	void testGetFrame() {
		assertTrue(f.getFrame() instanceof JFrame);
	}

	@Test
	void testGetChoisenPathRules() {
		assertTrue(f.getChoisenPathRules() instanceof JTextField);
	}

	@Test
	void testGetChoisenPathHam() {
		assertTrue(f.getChoisenPathHam() instanceof JTextField);
	}

	@Test
	void testGetChoisenPathSpam() {
		assertTrue(f.getChoisenPathSpam() instanceof JTextField);
	}

	@Test
	void testGetTableManual() {
		assertTrue(f.getTableManual() instanceof JTable);
	}

	@Test
	void testGetTableAuto() {
		assertTrue(f.getTableAuto() instanceof JTable);
	}

	@Test
	void testGetGui() {
		assertTrue(f.getGui() instanceof AntiSpamGUI);
	}

	@Test
	void testSetSpinnerFN() {
		f.setSpinnerFN(" ");
		assertEquals(f.getSpinnerFN(), " ");
	}

	@Test
	void testSetSpinnerFP() {
		f.setSpinnerFP(" ");
		assertEquals(f.getSpinnerFP(), " ");
	}

	@Test
	void testSetFieldAutoFP() {
		f.setSpinnerFN(" ");
		assertEquals(f.getFieldAutoFP(), " ");
	}

	@Test
	void testSetFieldAutoFN() {
		f.setSpinnerFN(" ");
		assertEquals(f.getFieldAutoFN(), " ");
	}
	
	@Test
	void testGetSpinnerFN() {
		assertTrue(f.getSpinnerFN() instanceof JTextField);
	}

	@Test
	void testGetSpinnerFP() {
		assertTrue(f.getSpinnerFP() instanceof JTextField);
	}

	@Test
	void testGetFieldAutoFP() {
		assertTrue(f.getFieldAutoFP() instanceof JTextField);
	}

	@Test
	void testGetFieldAutoFN() {
		assertTrue(f.getFieldAutoFN() instanceof JTextField);
	}

	
	

}

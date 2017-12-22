package jUnit;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import antiSpamUI.AntiSpamGUI;
import antiSpamUI.Frame;

class FrameTests {

	AntiSpamGUI gui = new AntiSpamGUI();
	Frame f = gui.getFrame();
	
	@Test
	void testFrame() {
		assertTrue(f instanceof Frame);
	}

	@Test
	void testGetFrame() {
		assertTrue(f.getFrame() instanceof JFrame);
	}

	@Test
	void testGetChosenPathRules() {
		JTextField path = f.getChosenPathRules();
		f.setChosenPathRules( new JTextField("test"));
		assertEquals(f.getChosenPathRules().getText(), "test");
		f.setChosenPathRules(path);
	}

	@Test
	void testGetChosenPathHam() {
		JTextField path = f.getChosenPathHam();
		f.setChosenPathHam( new JTextField("test"));
		assertEquals(f.getChosenPathHam().getText(), "test");
		f.setChosenPathHam(path);
	}

	@Test
	void testGetChosenPathSpam() {
		JTextField path = f.getChosenPathSpam();
		f.setChosenPathSpam( new JTextField("test"));
		assertEquals(f.getChosenPathSpam().getText(), "test");
		f.setChosenPathSpam(path);
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
		f.setSpinnerFN("test");
		//assertEquals(f.getSpinnerFN().getText(), "test");
		fail();
	}

	@Test
	void testSetSpinnerFP() {
		/*f.setSpinnerFN("test");
		assertEquals(f.getSpinnerFN().getText(), "test");*/
		//assertEquals(f.getSpinnerFN().getText(), null);
		fail();
	}

	@Test
	void testSetFieldAutoFP() {
		//f.setSpinnerFN("test");
		//assertEquals(f.getFieldAutoFP().getText(), "test");
		fail();
	}

	@Test
	void testSetFieldAutoFN() {
		//f.setSpinnerFN("test");
		//assertEquals(f.getFieldAutoFN().getText(), "test");
		fail();
	}
	
	/*@Test
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
	}*/
	
	

}

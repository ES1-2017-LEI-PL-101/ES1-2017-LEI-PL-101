package jUnit;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;
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
	void testIsPathValid() {
		gui.getAntiSpamFilterProblem().readHam("./files/ham.log");
		gui.getAntiSpamFilterProblem().readSpam("./files/spam.log");
		gui.getAntiSpamFilterProblem().readRules("./files/rules.cf");
		assertEquals(f.isPathValid(), true);
		gui.getAntiSpamFilterProblem().setRules(new LinkedHashMap<String, Double>());
		assertEquals(f.isPathValid(), false);
	}
	
	@Test
	void testChangeButtons() {
		gui.getAntiSpamFilterProblem().readHam("./files/ham.log");
		gui.getAntiSpamFilterProblem().readSpam("./files/spam.log");
		gui.getAntiSpamFilterProblem().readRules("./files/rules.cf");
		assertEquals(f.isPathValid(), true);
		f.testButton = new JButton("");
		f.testButton.setEnabled(false);
		f.generateButton = new JButton("");
		f.generateButton .setEnabled(false);
		f.saveButtonAuto = new JButton("");
		f.saveButtonAuto.setEnabled(false);
		f.saveButtonTest = new JButton("");
		f.saveButtonTest.setEnabled(false);
		assertEquals(f.getTestButton().isEnabled(), false);
		assertEquals(f.getGenerateButton().isEnabled(), false);
		assertEquals(f.getSaveButtonAuto().isEnabled(), false);
		assertEquals(f.getSaveButtonTest().isEnabled(), false);
		f.changeButtons();
		assertEquals(f.getTestButton().isEnabled(), true);
		assertEquals(f.getGenerateButton().isEnabled(), true);
		assertEquals(f.getSaveButtonAuto().isEnabled(), true);
		assertEquals(f.getSaveButtonTest().isEnabled(), true);
		
	}

	
	@Test
	void testSetSpinnerFN() {
		f.spinnerFN = new JTextField("");
		f.setSpinnerFN("test");
		assertEquals(f.spinnerFN.getText(), "test");
	}

	@Test
	void testSetSpinnerFP() {
		f.spinnerFP = new JTextField("");
		f.setSpinnerFP("test");
		assertEquals(f.getSpinnerFP().getText(), "test");
	}

	@Test
	void testSetFieldAutoFP() {
		f.fieldAutoFP= new JTextField("");
		f.setFieldAutoFP("test");
		assertEquals(f.getFieldAutoFP().getText(), "test");
	}

	@Test
	void testSetFieldAutoFN() {
		f.fieldAutoFN= new JTextField("");
		f.setFieldAutoFN("test");
		assertEquals(f.getFieldAutoFN().getText(), "test");
	}
	
	@Test
	void testGetSpinnerFN() {
		f.spinnerFN = new JTextField("");
		assertTrue(f.getSpinnerFN() instanceof JTextField);
	}

	@Test
	void testGetSpinnerFP() {
		f.spinnerFP = new JTextField("");
		assertTrue(f.getSpinnerFP() instanceof JTextField);
	}

	@Test
	void testGetFieldAutoFP() {
		f.fieldAutoFP= new JTextField("");
		assertTrue(f.getFieldAutoFP() instanceof JTextField);
	}

	@Test
	void testGetFieldAutoFN() {
		f.fieldAutoFN= new JTextField("");
		assertTrue(f.getFieldAutoFN() instanceof JTextField);
	}
	
	

}

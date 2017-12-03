/**
 * 
 */
package antiSpamUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Frame {

	public static JFrame createFrame(JFrame frame) {

		JPanel mainPanel = new JPanel(new GridLayout(0, 1));
		mainPanel.add(createSpinnersPanel());
		mainPanel.add(createResultPanel("areaFPManual", "areaFNManual", true));
		mainPanel.add(createResultPanel("areaFPAuth", "areaFNAuth", false));
		mainPanel.add(createPathsPanel());
		mainPanel.add(createButtons());
		

		frame.add(mainPanel);

		return frame;

	}

	private static JPanel createSpinnersPanel() {
		JPanel topLayout = new JPanel(new GridLayout(0, 2));
		JLabel topTextManual = new JLabel("Manual Configuration");
		JLabel topTextAuth = new JLabel("Auth Configuration");

		topLayout.add(topTextManual);
		topLayout.add(topTextAuth);

		JPanel spinnerManualLayout = new JPanel(new GridLayout(0, 4));
		JPanel textAuthLayout = new JPanel(new GridLayout(0, 4));

		JSpinner spinnerFP = new JSpinner();
		spinnerFP.setName("spinnerFP");
		JSpinner spinnerFN = new JSpinner();
		spinnerFP.setName("spinnerFN");

		JTextField fieldAuthFP = new JTextField("0");
		fieldAuthFP.setName("authFP");
		fieldAuthFP.setEditable(false);
		JTextField fieldAuthFN = new JTextField("0");
		fieldAuthFN.setName("authFN");
		fieldAuthFN.setEditable(false);

		spinnerManualLayout.add(new JLabel("FP - Manual"));
		spinnerManualLayout.add(spinnerFP);
		spinnerManualLayout.add(new JLabel("FN - Manual"));
		spinnerManualLayout.add(spinnerFN);

		textAuthLayout.add(new JLabel("FP - Auth"));
		textAuthLayout.add(fieldAuthFP);
		textAuthLayout.add(new JLabel("FN - Auth"));
		textAuthLayout.add(fieldAuthFN);

		topLayout.add(spinnerManualLayout);
		topLayout.add(textAuthLayout);

		return topLayout;

	}

	private static JPanel createResultPanel(String nameOne, String nameTwo, boolean enable) {

		Border blackline = BorderFactory.createLineBorder(Color.black);
		JPanel listPanel = new JPanel(new GridLayout(0, 2));

		JList<String> areaFP = new JList<>();
		areaFP.setName(nameOne);
		areaFP.setBorder(blackline);
		areaFP.setEnabled(enable);

		JList<String> areaFN = new JList<>();
		areaFN.setName(nameTwo);
		areaFN.setBorder(blackline);
		areaFN.setEnabled(enable);

		if (enable == true) {
			listPanel = insertTitle(listPanel, 2, "Manual Configuration");
		} else {
			listPanel = insertTitle(listPanel, 2, "Auth Configuration");
		}
		
		listPanel.add(areaFP);
		listPanel.add(areaFN);
		

		return listPanel;
	}

	private static JPanel createPathsPanel() {
		JPanel gridLayout = new JPanel(new GridLayout(0, 3));
		
		gridLayout = insertEmptyRow(gridLayout, 3);

		JTextField pathRules = new JTextField("Rules.cf");
		JTextField choisenPathRules = new JTextField();
		choisenPathRules.setEnabled(false);
		JButton buttonChangeRulesPath = new JButton("Browse");
		buttonChangeRulesPath.setName("buttonRules");

		JTextField pathHam = new JTextField("Ham.log");
		JTextField choisenPathHam = new JTextField();
		choisenPathHam.setEnabled(false);
		JButton buttonChangeHamPath = new JButton("Browse");
		buttonChangeRulesPath.setName("buttonHam");

		JTextField pathSpam = new JTextField("Spam.log");
		JTextField choisenPathSpam = new JTextField();
		choisenPathRules.setEnabled(false);
		JButton buttonChangeSpamPath = new JButton("Browse");
		buttonChangeRulesPath.setName("buttonSpam");

		gridLayout.add(pathRules);
		gridLayout.add(choisenPathRules);
		gridLayout.add(buttonChangeRulesPath);

		gridLayout.add(pathHam);
		gridLayout.add(choisenPathHam);
		gridLayout.add(buttonChangeHamPath);

		gridLayout.add(pathSpam);
		gridLayout.add(choisenPathSpam);
		gridLayout.add(buttonChangeSpamPath);

		return gridLayout;
	}

	private static JPanel createButtons() {
		JPanel buttonsLayout = new JPanel(new GridLayout(0, 3));
		
		JButton testButton = new JButton("Run Test");
		testButton.setName("test");

		JButton generateButton = new JButton("Generate");
		generateButton.setName("generate");

		JButton saveButton = new JButton("Save");
		saveButton.setName("save");

		saveButton.setPreferredSize(new Dimension(100, 10));

		buttonsLayout = insertEmptyRow(buttonsLayout, 3);
		
		buttonsLayout.add(testButton);
		buttonsLayout.add(generateButton);
		buttonsLayout.add(saveButton);

		return buttonsLayout;

	}
	
	private static JPanel insertEmptyRow (JPanel panel, int columns) {
		for (int i = 0; i != columns; i++) {
			panel.add(new Label(" "));
		}
		return panel;
	}
	
	private static JPanel insertTitle(JPanel panel, int columns, String title) {
		panel.add(new Label(title));
		for (int i = 1; i != columns; i++) {
			panel.add(new Label(" "));
		}
		return panel;
	}
}

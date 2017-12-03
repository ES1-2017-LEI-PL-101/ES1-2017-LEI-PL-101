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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Frame {

	public static JFrame createFrame(JFrame frame) {

		JPanel mainPanel = new JPanel(new GridLayout(0, 1));
		mainPanel.add(createSpinnersPanel());
		mainPanel.add(createPathsPanel());
		
		frame.add(mainPanel);

		return frame;

	}

	private static JPanel createSpinnersPanel() {
		JPanel topLayout = new JPanel(new GridLayout(0,1 ));
		
		JPanel spinnerLayout = new JPanel(new GridLayout(0, 4));
		JSpinner spinnerFP = new JSpinner();
		spinnerFP.setName("spinnerFP");
		JSpinner spinnerFN = new JSpinner();
		spinnerFP.setName("spinnerFN");

		spinnerLayout.add(new JLabel("FP"));
		spinnerLayout.add(spinnerFP);
		spinnerLayout.add(new JLabel("FN"));
		spinnerLayout.add(spinnerFN);
		
		topLayout.add(spinnerLayout);
		topLayout = createResultPanel(topLayout);
		
		return topLayout;

	}
	
	private static JPanel createResultPanel(JPanel topLayout) {
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		JTextArea areaFP = new JTextArea();
		areaFP.setName("areaFP");
		areaFP.setEditable(false);
		areaFP.setBorder(blackline);

		JTextArea areaFN = new JTextArea();
		areaFN.setName("areaFN");
		areaFN.setEditable(false);
		areaFN.setBorder(blackline);
		
		topLayout.add(areaFP);
		topLayout.add(areaFN);
		
		return topLayout;
	}
	
	private static JPanel createPathsPanel() {
		JPanel gridLayout = new JPanel(new GridLayout(0, 3));
		
		JTextField pathRules= new JTextField("Rules.cf");
		JTextField choisenPathRules = new JTextField();
		choisenPathRules.setEnabled(false);
		JButton buttonChangeRulesPath = new JButton("Browse");
		buttonChangeRulesPath.setName("buttonRules");
		
		JTextField pathHam= new JTextField("Ham.log");
		JTextField choisenPathHam = new JTextField();
		choisenPathHam.setEnabled(false);
		JButton buttonChangeHamPath = new JButton("Browse");
		buttonChangeRulesPath.setName("buttonHam");
		
		JTextField pathSpam= new JTextField("Spam.log");
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
		
		gridLayout = createButtons(gridLayout);
		
		return gridLayout; 
	}

	private static JPanel createButtons(JPanel gridLayout) {
		JButton testButton = new JButton("Run Test");
		testButton.setName("test");
		
		JButton generateButton = new JButton("Generate");
		generateButton.setName("generate");
		
		JButton saveButton = new JButton("Save");
		saveButton.setName("save");
		
		saveButton.setPreferredSize(new Dimension(100, 10));
		
		gridLayout.add(testButton);
		gridLayout.add(generateButton);
		gridLayout.add(saveButton);
		
	
		
		return gridLayout;
		
	}
}

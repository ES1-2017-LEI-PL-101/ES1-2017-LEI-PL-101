/**
 * 
 */
package antiSpamUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer;

public class Frame {
	
	private JFrame frame;

	public Frame(JFrame frame) {
		this.frame = frame;
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(createSpinnersPanel(), BorderLayout.PAGE_START);
		JPanel centerPanel = new JPanel(new GridLayout(0, 1));
		centerPanel.add(createRulesPanel("rulesManual", "weightManual", true));
		centerPanel.add(createRulesPanel("rulesAuth", "weightAuth", false));
		centerPanel.add(createPathsPanel());
		mainPanel.add(createButtons(), BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		frame.add(mainPanel);

	}

	public JFrame getFrame () {
		return this.frame;
	}
	private static JPanel createSpinnersPanel() {
		JPanel topLayout = new JPanel(new GridLayout(0, 2));

		JPanel spinnerManualLayout = new JPanel(new GridLayout(0, 4));
		JPanel textAuthLayout = new JPanel(new GridLayout(0, 4));

		JTextField spinnerFP = new JTextField("-");
		spinnerFP.setName("spinnerFP");
		spinnerFP.setEditable(false);
		JTextField spinnerFN = new JTextField("-");
		spinnerFN.setName("spinnerFN");
		spinnerFN.setEditable(false);

		JTextField fieldAuthFP = new JTextField("-");
		fieldAuthFP.setName("authFP");
		fieldAuthFP.setEditable(false);
		JTextField fieldAuthFN = new JTextField("-");
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

	private static JPanel createRulesPanel(String nameOne, String nameTwo, boolean enable) {

		DefaultListModel<String> rulesList = new DefaultListModel<>();
		DefaultListModel<Integer> weightList = new DefaultListModel<>();
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		JPanel listPanel = new JPanel(new GridLayout(0,1));
		
		String[] columnNames = {"Rules", "Weight"};
		
		Object[][] data = {};
				
				

		JTable table = new JTable(data, columnNames);
		
				
		if (enable == false) {
			table.setBackground(Color.LIGHT_GRAY);
			table.setEnabled(false);
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		listPanel.add(scrollPane);
		
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
		buttonChangeRulesPath.addActionListener(e -> {
			
		e.getActionCommand();
		JFileChooser theFileChooser = (JFileChooser)e.getSource();
		theFileChooser.getSelectedFile();
		
		});

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

//	private ActionListener actionListener = new ActionListener() {
//		
//		@Override
//	      public void actionPerformed(ActionEvent actionEvent) {
//			Object o = actionEvent.getSource();
//	       if (o instanceof Button) {
//	    	   if (((Button) o).getName()).equals("buttonRules")
//	       }
//	    };
}

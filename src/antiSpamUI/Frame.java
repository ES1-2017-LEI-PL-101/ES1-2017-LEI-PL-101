/**
 * 
 */
package antiSpamUI;

import java.awt.Adjustable;
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
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer;

import fileReader.FileLoader;

public class Frame {
	
	private JFrame frame;

	private JTextField choisenPathRules;
	private JTextField choisenPathHam;
	private JTextField choisenPathSpam;

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

		//lê dos files AntiSpamFilterProblem.NSGAII -> Pesos e FN/FP
		// AntiSpamConfigurationForLeisureMailbox -> escolher menor FN
		
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
	

	private JPanel createPathsPanel() {
		JPanel gridLayout = new JPanel(new GridLayout(0, 3));
		
		gridLayout = insertEmptyRow(gridLayout, 3);

		JTextField pathRules = new JTextField("Rules.cf");
		choisenPathRules = new JTextField();
		choisenPathRules.setEnabled(false);
		JButton buttonChangeRulesPath = new JButton("Browse Rules");
		buttonChangeRulesPath.setName("buttonRules");
		
		buttonChangeRulesPath.addActionListener(actionListener);

		JScrollPane scrollR = new JScrollPane(choisenPathRules);
		scrollR.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		
		JTextField pathHam = new JTextField("Ham.log");
		choisenPathHam = new JTextField();
		choisenPathHam.setEnabled(false);
		JButton buttonChangeHamPath = new JButton("Browse Ham");
		buttonChangeRulesPath.setName("buttonHam");
		
		buttonChangeHamPath.addActionListener(actionListener);
		
		JScrollPane scrollH = new JScrollPane(choisenPathHam);
		scrollH.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		
		JTextField pathSpam = new JTextField("Spam.log");
		choisenPathSpam = new JTextField();
		choisenPathRules.setEnabled(false);
		JButton buttonChangeSpamPath = new JButton("Browse Spam");
		buttonChangeRulesPath.setName("buttonSpam");
		
		buttonChangeSpamPath.addActionListener(actionListener);
		
		JScrollPane scrollS = new JScrollPane(choisenPathSpam);
		scrollS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


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
		
		//TODO
		testButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		
		//instanciar AntiSpamConfiguration e correr o Main
		// lançar os fixeiros R e tex quando gerar
		JButton generateButton = new JButton("Generate");
		generateButton.setName("generate");
		
		//TODO
		generateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton saveButton = new JButton("Save");
		saveButton.setName("save");
		
		//TODO
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		
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

	private ActionListener actionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			e.getActionCommand();
			JFileChooser fileChooser = new JFileChooser();
			int returnName = fileChooser.showOpenDialog(null);
			String path = null;
			
			if (returnName == JFileChooser.APPROVE_OPTION) {
	            File f = fileChooser.getSelectedFile();
	            if (f != null) { // Make sure the user didn't choose a directory.
	           	 
	                path = f.getAbsolutePath();//get the absolute path to selected file
	            }
			}
	            
			System.out.println("Path" + path);
			
		
			if(e.getActionCommand().equals("Browse Rules")) {
				FileLoader.readFile(path);
				choisenPathRules.setText(path);
			}
			else if(e.getActionCommand().equals("Browse Ham")) {
				FileLoader.readFile(path);
				choisenPathHam.setText(path);

			}
			else if(e.getActionCommand().equals("Browse Spam")) {
				FileLoader.readFile(path);
				choisenPathSpam.setText(path);

			}
			
			
		}
	};

	
	
	
	
}

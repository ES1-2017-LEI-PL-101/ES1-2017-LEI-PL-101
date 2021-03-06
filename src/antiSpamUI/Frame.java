package antiSpamUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Frame {

	private JFrame frame;

	private JTextField chosenPathRules;
	private JTextField chosenPathHam;
	private JTextField chosenPathSpam;
	public JTextField spinnerFN;
	public JTextField spinnerFP;
	public JTextField fieldAutoFP;
	public JTextField fieldAutoFN;


	private JTable tableManual = new JTable();
	private JTable tableAuto = new JTable();
	private AntiSpamGUI gui;


	public JButton testButton;

	public JButton saveButtonTest;

	public JButton generateButton;

	public JButton saveButtonAuto;
	

	/**
	 * Constructor. Creates a new Frame.
	 * 
	 * @param gui
	 */
	public Frame(AntiSpamGUI gui) {
		this.gui = gui;
		this.frame = new JFrame();

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setPreferredSize(new Dimension(1000, 600));

				frame.getContentPane().setLayout(new GridLayout(0, 1));
				frame.pack();

				JPanel mainPanel = new JPanel(new BorderLayout());
				mainPanel.add(createSpinnersPanel(), BorderLayout.PAGE_START);

				JPanel centerPanel = new JPanel(new GridLayout(0, 1));
				centerPanel.add(createTables());
				centerPanel.add(createPathsPanel());

				mainPanel.add(createButtons(), BorderLayout.SOUTH);
				mainPanel.add(centerPanel, BorderLayout.CENTER);
				frame.add(mainPanel);
				frame.setVisible(true);
			}
		});
	}

	/**
	 * This method is used to get the frame.
	 * 
	 * @return JFrame This method returns the frame.
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * This method is used to create the panel of FP - Manual, FN - Manual, FP -
	 * Auto and FN - Auto.
	 * 
	 * @return JPanel This method returns the panel.
	 */
	private JPanel createSpinnersPanel() {

		JPanel topLayout = new JPanel(new GridLayout(0, 2));
		JPanel spinnerManualLayout = new JPanel(new GridLayout(0, 4));
		JPanel textAutoLayout = new JPanel(new GridLayout(0, 4));
		spinnerFP = new JTextField("-");
		spinnerFP.setName("spinnerFP");
		spinnerFP.setEditable(false);
		spinnerFN = new JTextField("-");
		spinnerFN.setName("spinnerFN");
		spinnerFN.setEditable(false);

		this.fieldAutoFP = new JTextField("-");
		this.fieldAutoFP.setName("autoFP");
		this.fieldAutoFP.setEditable(false);
		this.fieldAutoFN = new JTextField("-");
		this.fieldAutoFN.setName("autoFN");
		this.fieldAutoFN.setEditable(false);


		spinnerManualLayout.add(new JLabel("FP - Manual"));
		spinnerManualLayout.add(spinnerFP);
		spinnerManualLayout.add(new JLabel("FN - Manual"));
		spinnerManualLayout.add(spinnerFN);

		textAutoLayout.add(new JLabel("FP - Auto"));
		textAutoLayout.add(fieldAutoFP);
		textAutoLayout.add(new JLabel("FN - Auto"));
		textAutoLayout.add(fieldAutoFN);

		topLayout.add(spinnerManualLayout);
		topLayout.add(textAutoLayout);

		return topLayout;
	}

	/**
	 * This method is used to create the rules and weights tables.
	 * 
	 * @return JPanel This method returns the tables panel.
	 */
	private JPanel createTables() {

		Border blackLine = BorderFactory.createLineBorder(Color.black);
		JPanel listPanel = new JPanel(new GridLayout(0, 1));
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Rules", "Weight" }, 0);

		tableAuto = new JTable(model);

		tableManual = new JTable(model) {
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 0) {
					return false;
				} else {
					return true;
				}
			}
		};

		tableAuto.setBorder(blackLine);
		tableManual.setBorder(blackLine);
		tableAuto.setBackground(Color.LIGHT_GRAY);
		tableAuto.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(tableManual);
		JScrollPane scrollPane2 = new JScrollPane(tableAuto);

		tableManual.setFillsViewportHeight(true);
		tableAuto.setFillsViewportHeight(true);

		listPanel.add(scrollPane);
		listPanel.add(scrollPane2);

		return listPanel;
	}

	/**
	 * This method is used to create the browsers buttons and rules, ham and spam
	 * path panel .
	 * 
	 * @return JPanel This method returns the path panel and browsers buttons
	 *         layout.
	 */
	private JPanel createPathsPanel() {

		JPanel gridLayout = new JPanel(new GridLayout(0, 3));
		gridLayout = insertEmptyRow(gridLayout, 3);

		JTextField pathRules = new JTextField("Rules.cf");
		chosenPathRules = new JTextField();
		chosenPathRules.setEnabled(false);

		JButton buttonChangeRulesPath = new JButton("Browse Rules");
		buttonChangeRulesPath.addActionListener(gui.actionListenerBrowser);

		JScrollPane scrollR = new JScrollPane(chosenPathRules);
		scrollR.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JTextField pathHam = new JTextField("Ham.log");
		chosenPathHam = new JTextField();
		chosenPathHam.setEnabled(false);

		JButton buttonChangeHamPath = new JButton("Browse Ham");
		buttonChangeHamPath.addActionListener(gui.actionListenerBrowser);

		JScrollPane scrollH = new JScrollPane(chosenPathHam);
		scrollH.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JTextField pathSpam = new JTextField("Spam.log");
		chosenPathSpam = new JTextField();
		chosenPathSpam.setEnabled(false);

		JButton buttonChangeSpamPath = new JButton("Browse Spam");
		buttonChangeRulesPath.setName("buttonSpam");

		buttonChangeSpamPath.addActionListener(gui.actionListenerBrowser);

		JScrollPane scrollS = new JScrollPane(chosenPathSpam);
		scrollS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		gridLayout.add(pathRules);
		gridLayout.add(chosenPathRules);
		gridLayout.add(buttonChangeRulesPath);

		gridLayout.add(pathHam);
		gridLayout.add(chosenPathHam);
		gridLayout.add(buttonChangeHamPath);

		gridLayout.add(pathSpam);
		gridLayout.add(chosenPathSpam);
		gridLayout.add(buttonChangeSpamPath);

		return gridLayout;
	}

	/**
	 * This method is used to create a buttons panel and what they do. Run test,
	 * save manual, generate and save auto buttons.
	 * 
	 * @return JPanel This method returns the panel of buttons layout.
	 */
	private JPanel createButtons() {

		JPanel buttonsLayout = new JPanel(new GridLayout(0, 4));

		testButton = new JButton("Run Test");
		testButton.addActionListener(gui.actionListenerTest);

		saveButtonTest = new JButton("Save Manual");

		saveButtonTest.addActionListener(gui.actionListenerSave);

		generateButton = new JButton("Generate");
		generateButton.addActionListener(gui.actionListenerGenerate);

		saveButtonAuto = new JButton("Save Auto");
		saveButtonAuto.addActionListener(gui.actionListenerSave);

		if (!isPathValid()) {
			testButton.setEnabled(false);
			saveButtonTest.setEnabled(false);
			generateButton.setEnabled(false);
			saveButtonAuto.setEnabled(false);
		}

		saveButtonAuto.setPreferredSize(new Dimension(100, 10));

		buttonsLayout = insertEmptyRow(buttonsLayout, 4);

		buttonsLayout.add(testButton);
		buttonsLayout.add(saveButtonTest);
		buttonsLayout.add(generateButton);
		buttonsLayout.add(saveButtonAuto);

		return buttonsLayout;
	}

	/**
	 * This method is used to insert a new and empty row in panel.
	 * 
	 * @param panel
	 * @param columns
	 * @return JPanel This method returns the panel.
	 */
	private static JPanel insertEmptyRow(JPanel panel, int columns) {
		for (int i = 0; i != columns; i++) {
			panel.add(new Label(" "));
		}
		return panel;
	}

	/**
	 * This method is used to get the rules file path.
	 * 
	 * @return JTextField This method returns the rules file path.
	 */
	public JTextField getChosenPathRules() {
		return chosenPathRules;
	}

	/**
	 * This method is used to get the ham file path.
	 * 
	 * @return JTextField This method returns the ham file path.
	 */
	public JTextField getChosenPathHam() {
		return chosenPathHam;

	}

	/**
	 * This method is used to get the spam file path.
	 * 
	 * @return JTextField This method returns the spam file path.
	 */
	public JTextField getChosenPathSpam() {
		return chosenPathSpam;
	}

	/**
	 * This method is used to get the manual table.
	 * 
	 * @return JTable This method returns the manual table.
	 */
	public JTable getTableManual() {
		return tableManual;
	}

	/**
	 * This method is used to get the automatic table.
	 * 
	 * @return JTable This method returns the automatic table.
	 */
	public JTable getTableAuto() {
		return tableAuto;
	}

	/**
	 * This method is used to get the AntiSpam gui.
	 * 
	 * @return AntiSpamGUI This method returns the AntiSpam gui.
	 */
	public AntiSpamGUI getGui() {
		return gui;
	}


	/**
	 * This method is used to set the false negative spinner in spinnerFN field.
	 * 
	 * @param spinnerFN
	 */
	public void setSpinnerFN(String spinnerFN) {
		this.spinnerFN.setText(spinnerFN);
	}

	/**
	 * This method is used to set the false positive spinner in spinnerFP field.
	 * 
	 * @param spinnerFP
	 */
	public void setSpinnerFP(String spinnerFP) {
		this.spinnerFP.setText(spinnerFP);
	}

	/**
	 * This method is used to set the false positive in AutoFP field.
	 * 
	 * @param fieldAutoFP
	 */
	public void setFieldAutoFP(String fieldAutoFP) {
		this.fieldAutoFP.setText(fieldAutoFP);
	}

	/**
	 * This method is used to set the false negative spinner in spinnerFN field.
	 * 
	 * @param fieldAutoFN
	 */
	public void setFieldAutoFN(String fieldAutoFN) {
		this.fieldAutoFN.setText(fieldAutoFN);
	}

	/**
	 * This method is used to get the false negative.
	 * 
	 * @return JTextField This method returns the sipnnerFN.
	 */
	public JTextField getSpinnerFN() {
		return spinnerFN;
	}

	/**
	 * This method is used to get the false positive.
	 * 
	 * @return JTextField This method returns the sipnnerFP.
	 */
	public JTextField getSpinnerFP() {
		return spinnerFP;
	}

	/**
	 * This method is used to get the auto false positive.
	 * 
	 * @return JTextField This method returns the fieldAutoFP.
	 */
	public JTextField getFieldAutoFP() {
		return fieldAutoFP;
	}

	/**
	 * This method is used to get the auto false negative.
	 * 
	 * @return JTextField This method returns the fieldAutoFN.
	 */
	public JTextField getFieldAutoFN() {
		return fieldAutoFN;
	}

	/**
	 * This method is used to set rules path in chosenPathRules.
	 * 
	 * @param chosenPathRules
	 */
	public void setChosenPathRules(JTextField chosenPathRules) {
		this.chosenPathRules = chosenPathRules;
	}

	/**
	 * This method is used to set ham path in chosenPathHam.
	 * 
	 * @param chosenPathHam
	 */
	public void setChosenPathHam(JTextField chosenPathHam) {
		this.chosenPathHam = chosenPathHam;
	}

	/**
	 * This method is used to set spam path in chosenPathSpam.
	 * 
	 * @param chosenPathSpam
	 */
	public void setChosenPathSpam(JTextField chosenPathSpam) {
		this.chosenPathSpam = chosenPathSpam;
	}

	/**
	 * This method is used to verified if a path is valid.
	 * 
	 * @return boolean This method returns true if the path is valid, false otherwise.
	 */
	public boolean isPathValid() {
		return !(gui.getAntiSpamFilterProblem().getSpam().isEmpty() || gui.getAntiSpamFilterProblem().getHam().isEmpty()
				|| gui.getAntiSpamFilterProblem().getRules().isEmpty());
	}

	/**
	 * This method is used to put available buttons.
	 * 
	 */
	public void changeButtons() {
		if (isPathValid()) {
			testButton.setEnabled(true);
			saveButtonTest.setEnabled(true);
			generateButton.setEnabled(true);
			saveButtonAuto.setEnabled(true);
		}
	}


	/**
	 * This method is used to show a Popup.
	 * 
	 * @param message
	 */
	public void Popup(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public Component getTestButton() {
		return testButton;
	}

	public Component getSaveButtonAuto() {
		return saveButtonAuto;
	}
	


}
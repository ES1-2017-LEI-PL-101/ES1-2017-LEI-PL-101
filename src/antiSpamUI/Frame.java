package antiSpamUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import fileReader.FileLoader;

public class Frame {

	private JFrame frame;

	private JTextField choisenPathRules;
	private JTextField choisenPathHam;
	private JTextField choisenPathSpam;
	private JTextField spinnerFN;
	private JTextField spinnerFP;
	private JTextField fieldAutoFP;
	private JTextField fieldAutoFN;

	private JTable tableManual = new JTable();
	private JTable tableAuto = new JTable();

	private AntiSpamGUI gui;

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

		// lê dos files AntiSpamFilterProblem.NSGAII -> Pesos e FN/FP
		// AntiSpamConfigurationForLeisureMailbox -> escolher menor FN

		fieldAutoFP = new JTextField("-");
		fieldAutoFP.setName("autoFP");
		fieldAutoFP.setEditable(false);
		fieldAutoFN = new JTextField("-");
		fieldAutoFN.setName("autoFN");
		fieldAutoFN.setEditable(false);

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

		Border blackline = BorderFactory.createLineBorder(Color.black);
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

		tableAuto.setBorder(blackline);
		tableManual.setBorder(blackline);
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
	 * @return JPanel This method returns the path panel and browsers buttons layout.
	 */
	private JPanel createPathsPanel() {
		
		JPanel gridLayout = new JPanel(new GridLayout(0, 3));
		gridLayout = insertEmptyRow(gridLayout, 3);

		JTextField pathRules = new JTextField("Rules.cf");

		choisenPathRules = new JTextField();
		choisenPathRules.setEnabled(false);
		
		JButton buttonChangeRulesPath = new JButton("Browse Rules");
		buttonChangeRulesPath.addActionListener(gui.actionListenerBrowser);

		JScrollPane scrollR = new JScrollPane(choisenPathRules);
		scrollR.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JTextField pathHam = new JTextField("Ham.log");
		
		choisenPathHam = new JTextField();
		choisenPathHam.setEnabled(false);
		
		JButton buttonChangeHamPath = new JButton("Browse Ham");
		buttonChangeHamPath.addActionListener(gui.actionListenerBrowser);

		JScrollPane scrollH = new JScrollPane(choisenPathHam);
		scrollH.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JTextField pathSpam = new JTextField("Spam.log");
		
		choisenPathSpam = new JTextField();
		choisenPathSpam.setEnabled(false);
		
		JButton buttonChangeSpamPath = new JButton("Browse Spam");
		buttonChangeRulesPath.setName("buttonSpam");

		buttonChangeSpamPath.addActionListener(gui.actionListenerBrowser);

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

	/**
	 * This method is used to create a buttons panel and what they do. Run test,
	 * save manual, generate and save auto buttons.
	 * 
	 * @return JPanel This method returns the panel of buttons layout.
	 */
	private JPanel createButtons() {
		
		JPanel buttonsLayout = new JPanel(new GridLayout(0, 4));

		JButton testButton = new JButton("Run Test");
		testButton.addActionListener(gui.actionListenerTest);

		JButton saveButtonTest = new JButton("Save Manual");

		// TODO
		saveButtonTest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FileLoader.writeFile(getChoisenPathRules().getText(), gui.getAntiSpamFilterProblem().getRules());

			}
		});

		// instanciar AntiSpamConfiguration e correr o Main
		// lançar os fixeiros R e tex quando gerar
		JButton generateButton = new JButton("Generate");

		// TODO
		generateButton.addActionListener(gui.actionListenerGenerate);

		JButton saveButtonAuto = new JButton("Save Auto");

		// TODO
		saveButtonAuto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

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
	public JTextField getChoisenPathRules() {
		return choisenPathRules;
	}

	/**
	 * This method is used to get the ham file path.
	 * 
	 * @return JTextField This method returns the ham file path.
	 */
	public JTextField getChoisenPathHam() {
		return choisenPathHam;
	}

	/**
	 * This method is used to get the spam file path.
	 * 
	 * @return JTextField This method returns the spam file path.
	 */
	public JTextField getChoisenPathSpam() {
		return choisenPathSpam;
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
	 * @return JTable This method returns the AntiSpam gui.
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

}
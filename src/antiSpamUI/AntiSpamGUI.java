package antiSpamUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;

import fileReader.FileLoader;

public class AntiSpamGUI {

	private Frame frame;
	private AntiSpamFilterProblem antiSpamFilterProblem;
	private LinkedHashMap<String, Double> rulesManual = new LinkedHashMap<String, Double>();
	private LinkedHashMap<String, Double> rulesAuto = new LinkedHashMap<String, Double>();

	/**
	 * Constructor. Creates a new Frame and a new AntiSpamFilterProblem.
	 */
	public AntiSpamGUI() {
		this.frame = new Frame(this);
		this.antiSpamFilterProblem = new AntiSpamFilterProblem();
	}

	public ActionListener actionListenerBrowser = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			e.getActionCommand();
			File userDir = new File(System.getProperty("user.dir") + File.separator + "files");
			JFileChooser fileChooser = new JFileChooser(userDir);
			int returnName = fileChooser.showOpenDialog(null);
			String path = null;

			if (returnName == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
				if (f != null) {

					path = f.getAbsolutePath();
				}
			}
			if (e.getActionCommand().equals("Browse Rules")) {
				antiSpamFilterProblem.readRules(path);
				frame.getChosenPathRules().setText(path);
				rulesManual = antiSpamFilterProblem.getRules();
				rulesAuto = antiSpamFilterProblem.getRules();
				setRules("Manual", true);
				setRules("Auto", true);

			} else if (e.getActionCommand().equals("Browse Ham")) {
				antiSpamFilterProblem.readHam(path);
				frame.getChosenPathHam().setText(path);

			} else if (e.getActionCommand().equals("Browse Spam")) {
				antiSpamFilterProblem.readSpam(path);
				frame.getChosenPathSpam().setText(path);
			}
			frame.changeButtons();
		}
	};

	public ActionListener actionListenerTest = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			e.getActionCommand();
			rulesManual.clear();

			for (int count = 0; count < frame.getTableManual().getModel().getRowCount(); count++) {
				rulesManual.put(frame.getTableManual().getModel().getValueAt(count, 0).toString(),
						Double.parseDouble(frame.getTableManual().getModel().getValueAt(count, 1).toString()));
			}
			antiSpamFilterProblem.setRules(rulesManual);
			double[] fxCount = antiSpamFilterProblem.evaluate(rulesManual);
			frame.setSpinnerFN(fxCount[1] + "");
			frame.setSpinnerFP(fxCount[0] + "");
			setRules("Manual", false);
		}
	};

	public ActionListener actionListenerGenerate = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			e.getActionCommand();
			Runnable task = new Runnable() {

				public void run() {
					try {
						AntiSpamFilterAutomaticConfiguration.setAntiSpamFilterProblem(antiSpamFilterProblem);
						System.out.println("Rules " + antiSpamFilterProblem.getRules().size());
						AntiSpamFilterAutomaticConfiguration.main(null);
						rulesAuto = antiSpamFilterProblem.getRules();
						updateRulesWithLeisureMailValues();
						frame.setFieldAutoFP(
								AntiSpamFilterAutomaticConfiguration.getAntiSpamFilterProblem().getCountFP() + "");
						frame.setFieldAutoFN(
								AntiSpamFilterAutomaticConfiguration.getAntiSpamFilterProblem().getCountFN() + "");
						setRules("Auto", false);
						// createGraphic();
						System.out.println("END");

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			Thread t = new Thread(task);
			t.start();
		}

		// TODO
		/**
		 * 
		 * 
		 */
		private void createGraphic() {

			String[] params = new String[2];

			params[0] = "C:\\Program Files\\R\\R-3.4.1\\bin\\x64\\Rscript.exe";

			params[1] = "C:\\Users\\vbasto\\git\\ES1\\experimentBaseDirectory\\AntiSpamStudy\\R\\HV.Boxplot.R";

			String[] envp = new String[1];

			envp[0] = "Path=C:\\Program Files\\R\\R-3.4.1\\bin\\x64";

			try {
				Process p = Runtime.getRuntime().exec(params, envp,
						new File("C:\\Users\\vbasto\\git\\ES1\\experimentBaseDirectory\\AntiSpamStudy\\R"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	public ActionListener actionListenerSave = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			e.getActionCommand();

			if (e.getActionCommand().equals("Save Manual")) {
				FileLoader.writeFile(frame.getChosenPathRules().getText(), rulesManual);
				String message = "Rules Manual have been saved!";
				frame.Popup(message);
			} else if (e.getActionCommand().equals("Save Auto")) {
				FileLoader.writeFile(frame.getChosenPathRules().getText(), rulesAuto);
				String message = "Rules Automatic have been saved!";
				frame.Popup(message);
			}
		}
	};

	/**
	 * This method is used to differentiate between manual weight and automatic
	 * weight and set the rules in manual or auto table.
	 * 
	 * @param option
	 */
	public void setRules(String option, boolean firstLoad) {

		if (option.equals("Manual")) {
			LinkedHashMap<String, Double> newRules = this.rulesManual;
			DefaultTableModel model = Extensions.toTableModel(newRules, firstLoad);
			this.frame.getTableManual().setModel(model);
		}
		if (option.equals("Auto")) {
			LinkedHashMap<String, Double> newRules = this.rulesAuto;
			DefaultTableModel model = Extensions.toTableModel(newRules, firstLoad);
			this.frame.getTableAuto().setModel(model);
		}
	}

	/**
	 * This method is used to get the anti spam filter problem.
	 * 
	 * @return AntiSpamFilterProblem This method returns the anti spam filter
	 *         problem.
	 */
	public AntiSpamFilterProblem getAntiSpamFilterProblem() {
		return antiSpamFilterProblem;
	}

	/**
	 * This is the main method that creates a new AntiSpamGUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new AntiSpamGUI();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage() + "; " + e.getLocalizedMessage() + ";" + e.getStackTrace());
		}
	}

	/**
	 * This method is used to put in rules list the Leisure Mail values.
	 * 
	 */
	public void updateRulesWithLeisureMailValues() {
		int nIndexOfLowerValue = FileLoader
				.readNSGAII_LowerIndex(AntiSpamFilterAutomaticConfiguration.getExperimentBaseDirectory()
						+ "/referenceFronts/AntiSpamFilterProblem.NSGAII.rf", 1);

		Double[] values = FileLoader
				.readNSGAII_Values(
						AntiSpamFilterAutomaticConfiguration.getExperimentBaseDirectory() + "/referenceFronts/"
								+ AntiSpamFilterAutomaticConfiguration.getsClassName() + ".NSGAII.rs",
						nIndexOfLowerValue);

		int iterator = 0;
		for (Entry<String, Double> rule : rulesManual.entrySet()) {
			rule.setValue(values[iterator]);
			iterator++;
		}
	}

}

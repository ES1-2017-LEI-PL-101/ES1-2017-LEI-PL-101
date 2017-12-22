package antiSpamUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;

public class AntiSpamGUI {

	private Frame frame;
	private AntiSpamFilterProblem antiSpamFilterProblem;
	private LinkedHashMap<String, Double> rulesManual = new LinkedHashMap<String, Double>();
	private LinkedHashMap<String, Double> rulesAuto = new LinkedHashMap<String, Double>();

	/**
	 * Constructor. Creates a new Frame and a new AntiSpamFilterProblem.
	 */
	public AntiSpamGUI() {
		frame = new Frame(this);
		antiSpamFilterProblem = new AntiSpamFilterProblem();
	}

	public ActionListener actionListenerBrowser = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			e.getActionCommand();
			File userDir = new File(System.getProperty("user.dir"));
			JFileChooser fileChooser = new JFileChooser(userDir);
			int returnName = fileChooser.showOpenDialog(null);
			String path = null;

			if (returnName == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
				if (f != null) { // Make sure the user
					// didn't choose a
					// directory.

					path = f.getAbsolutePath();// get the absolute path to selected file
				}
			}

			if (e.getActionCommand().equals("Browse Rules")) {
				antiSpamFilterProblem.readRules(path);
				frame.getChoisenPathRules().setText(path);
				setRules("Manual");
				setRules("Auto");

			} else if (e.getActionCommand().equals("Browse Ham")) {
				antiSpamFilterProblem.readHam(path);
				frame.getChoisenPathHam().setText(path);

			} else if (e.getActionCommand().equals("Browse Spam")) {
				antiSpamFilterProblem.readSpam(path);
				frame.getChoisenPathSpam().setText(path);
			}
		}
	};

	public ActionListener actionListenerTest = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			e.getActionCommand();

			for (int count = 0; count < frame.getTableManual().getModel().getRowCount(); count++) {
				rulesManual.put(frame.getTableManual().getModel().getValueAt(count, 0).toString(),
						Double.parseDouble(frame.getTableManual().getModel().getValueAt(count, 1).toString()));
			}

			antiSpamFilterProblem.setRules(rulesManual);
			double[] fxCount = antiSpamFilterProblem.evaluate(rulesManual);
			frame.setSpinnerFN(String.valueOf(fxCount[1]));
			frame.setSpinnerFP(String.valueOf(fxCount[0]));
			setRules("Manual");
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
						frame.setFieldAutoFP(AntiSpamFilterAutomaticConfiguration.getAntiSpamFilterProblem().getCountFP() + "");
						frame.setFieldAutoFN(AntiSpamFilterAutomaticConfiguration.getAntiSpamFilterProblem().getCountFN() + "");
						setRules("Auto");

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			Thread t = new Thread(task);
			t.start();

			try {
				t.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	};

	/**
	 * This method is used to differentiate between manual weight and automatic
	 * weight and set the rules in manual or auto table.
	 * 
	 * @param option
	 */
	public void setRules(String option) {
		LinkedHashMap<String, Double> newRules = antiSpamFilterProblem.getRules();
		DefaultTableModel model = Extensions.toTableModel(newRules);
		if (option.equals("Manual")) {
			this.frame.getTableManual().setModel(Extensions.toTableModel(newRules));
		}
		if (option.equals("Auto")) {
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
			System.out.println("Error: " + e.getMessage());
		}
	}
	
}

package antiSpamUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import antiSpamFilter.AntiSpamFilterProblem;
import fileReader.FileLoader;

public class AntiSpamGUI {

	private Frame frame;
	private AntiSpamFilterProblem antiSpamFilterProblem;

	public AntiSpamGUI() {
		frame = new Frame(this);
		//antiSpamFilterProblem = new AntiSpamFilterProblem();
	}

	public ActionListener actionListenerBrowser=new ActionListener(){

	@Override public void actionPerformed(ActionEvent e){

	e.getActionCommand();
	File userDir=new File(System.getProperty("user.dir"));
	
	JFileChooser fileChooser=new JFileChooser(userDir);
	
	int returnName = fileChooser.showOpenDialog(null);
	
	String path = null;

	if(returnName==JFileChooser.APPROVE_OPTION){
		File f=fileChooser.getSelectedFile();
			if(f!=null){ // Make sure the user
						// didn't choose a
						// directory.
	path=f.getAbsolutePath();// get the absolute path to selected file
	}}

	System.out.println("Path"+path);

	if(e.getActionCommand().equals("Browse Rules")){
		FileLoader.readFile(path);
		frame.getChoisenPathRules().setText(path);
		setRules("Manual");
		setRules("Auth");
	}
	else if (e.getActionCommand().equals("Browse Ham")) {
	  FileLoader.readFile(path); 
	  frame.getChoisenPathHam().setText(path);
	} else if (e.getActionCommand().equals("Browse Spam")) {
	 FileLoader.readFile(path); 
	 frame.getChoisenPathSpam().setText(path);
	}

	}};

	public ActionListener actionListenerTest = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			e.getActionCommand();
			
		//	antiSpamFilterProblem.setRules(rules);
		}
		
	};

	public void setRules(String option) {
		LinkedHashMap<String, Double> newRules = FileLoader.getInstance().getRulesMap();
		DefaultTableModel model = Extensions.toTableModel(newRules);
		if (option.equals("Manual")) {
			this.frame.getTableManual().setModel(Extensions.toTableModel(newRules));
		}
		if (option.equals("Auth")) {
			this.frame.getTableAuth().setModel(model);
		}
		
		//Test
//		for (Entry<String, Double> entry : antiSpamFilter.entrySet()) {
//			String key = entry.getKey();
//			Double value = entry.getValue();
//			System.out.println(key.toString() + " " + value.toString());
//		}

	}

	
	public AntiSpamFilterProblem getAntiSpamFilterProblem() {
		return antiSpamFilterProblem;
	}


	public static void main(String[] args) {
		try {
			new AntiSpamGUI();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}

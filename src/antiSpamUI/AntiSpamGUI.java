package antiSpamUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class AntiSpamGUI {

	private JFrame frame = new JFrame();

	public AntiSpamGUI() {
		run();
	}

	private void run() {
		start();
	}

	private void start() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(1000, 600));
		frame.getContentPane().setLayout(new GridLayout(0,1));
		frame.pack();
		frame = (new Frame(frame)).getFrame();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		try {
			new AntiSpamGUI();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		

	}

}

package Tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {
	private static String path = "log.txt";
	private static String space = "";
	private static String spaceToAdd = "";
	private static final Debug INSTANCE = new Debug();
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * @param msg
	 */
	public static void in(String msg) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true));
			// buffWrite.append(space+"IN-> "+msg + "\n");
			buffWrite.write(space + "IN-> " + msg + " - " + dateFormat.format(new Date()) + "\n");
			buffWrite.close();
			space = space + spaceToAdd;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param msg
	 */
	public static void out(String msg) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true));
			Date d = new Date();
			buffWrite.write(space + "OUT-> " + msg + " - " + dateFormat.format(new Date()) + "\n");
			buffWrite.close();
			space.replaceFirst(spaceToAdd, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param msg
	 */
	public static void msg(String msg) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true));
			buffWrite.write(space + msg + " - " + dateFormat.format(new Date()) + "\n");
			buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to get the Instance of Debug.
	 * 
	 * @return Debug This method returns the Instance of Debug.
	 */
	public static Debug getInstance() {
		return INSTANCE;
	}

}

package Tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Debug {
	private static String path="log.txt";
	private static String space="";
	private static String spacetoAdd = "";
	private static final Debug INSTANCE = new Debug();
	
	

    public static void IN(String msg) {
    	try {
    	BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));    
		//buffWrite.append(space+"IN-> "+msg + "\n");
		buffWrite.write(space+"IN-> "+msg + "\n");
		buffWrite.close();
		space = space + spacetoAdd;
		} catch (IOException e) {
			e.printStackTrace();
		}   
    }
    
    public static void OUT(String msg) {
    	try {
    	BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));    
    	buffWrite.write(space+"OUT-> "+msg + "\n");
		buffWrite.close();
		space.replaceFirst(spacetoAdd, "");
		} catch (IOException e) {
			e.printStackTrace();
		}   
    }
    
    public static void msg(String msg) {
    	try {
    	BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));    
    	buffWrite.write(space+msg + "\n");
		buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}   
    }

	public static Debug getInstance() {
		return INSTANCE;
	}
    
}

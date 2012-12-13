package org.team751.logging;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.Timer;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.microedition.io.Connector;

/**
 * Interfaces with one log file
 * @author Sam Crow
 */
public class LogFile {
	protected FileConnection connection;
	protected DataOutputStream stream;

	/**
	 * Constructor that creates a new
	 */
	public LogFile(){
		final String name = "log_" + System.currentTimeMillis() + ".txt";
		try {
			connection = (FileConnection) Connector.open("file:///logs/"+name, Connector.WRITE);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			connection.create();//Create the file
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			stream = connection.openDataOutputStream();
		} catch (IOException ex) {
			ex.printStackTrace();
		}


		println("Starting a new log file. Timestamps FPGA="+Timer.getFPGATimestamp()+", system="+System.currentTimeMillis(), LogLevel.kStatus);
	}

	/**
	 * Write a specified message to the log file
	 * @param message The text to write. A newline will be appened to the end of this.
	 * @param level The log level to indicate
	 */
	public void println(String message, LogLevel level){

		//The message is in the format [(timing information)] message
		int closeBracketIndex = message.indexOf(']');//Find the position
		if(closeBracketIndex != -1){//If it was found, add the log level information
			String preBracketPart = message.substring(0,
													  closeBracketIndex);//Get the part of the string before the closing bracket

			String postBracketPart = message.substring(closeBracketIndex,
													   message.length());//Get the part of the string from the closing bracket to the end, inclusive.

			message = preBracketPart + " " + level.toString() + postBracketPart;//Reassemble the string including the log level

		}else{
			//Just append the log level to the beginning
			message = level.toString() + ": " + message;
		}

		if(stream != null){
			try {
				stream.writeChars(message + '\n');
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void close(){
		if(stream != null){
			try {
				stream.close();
				connection.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
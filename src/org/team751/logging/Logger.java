package org.team751.logging;

import java.io.PrintStream;

import com.sun.squawk.util.MathUtils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;

/**
 * A class used to log messages. All useful methods are static.
 * 
 * Messages can be sent to the console and/or a log file on the cRIO.
 * The routing is determined by the configuration of the logger.
 * 
 * @author samcrow
 *
 */
public class Logger {

	/**
	 * The PrintStream used to send messages to the console
	 */
	private static PrintStream console = System.out;
	
	private static LogFile file = new LogFile();
	
	/**
	 * Log a string message
	 * @param message The message to log
	 * @param level The level of this message
	 */
	public static void log(String message, LogLevel level) {
		final String messageString = getPrefix() + message;
		
		if(level.equals(LogLevel.kStatus)){
            if(Preferences.getInstance().getBoolean("log::statusToConsole", true)){
                console.println(messageString);
            }
            if(Preferences.getInstance().getBoolean("log::statusToFile", false)){
                file.println(messageString, level);
            }
        }

        if(level.equals(LogLevel.kDebug)){
            if(Preferences.getInstance().getBoolean("log::debugToConsole", true)){
                console.println(messageString);
            }
            if(Preferences.getInstance().getBoolean("log::debugToFile", false)){
                file.println(messageString, level);
            }
        }

        if(Preferences.getInstance().getBoolean("log::warningToConsole", true)){
            if(Preferences.getInstance().getBoolean("log::warningToConsole", true)){
                console.println(messageString);
            }
            if(Preferences.getInstance().getBoolean("log::warningToFile", true)){
                file.println(messageString, level);
            }
        }

        if(level.equals(LogLevel.kError)){
            if(Preferences.getInstance().getBoolean("log::errorToConsole", true)){
                console.println(messageString);
            }
            if(Preferences.getInstance().getBoolean("log::errorToFile", true)){
                file.println(messageString, level);
            }
        }
	}
	
	/**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public static void log(boolean message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public static void log(char message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public static void log(char[] message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public static void log(double message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public static void log(float message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public static void log(int message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public static void log(long message, LogLevel level){
        log(String.valueOf(message), level);
    }
	
	/**
     * Get the log prefix, indicating the timestamp, to be prepended to each message
     * @return The prefix
     */
    private static String getPrefix(){
        return "["+System.currentTimeMillis()+" "+ MathUtils.round(DriverStation.getInstance().getMatchTime())+"] ";
    }
}

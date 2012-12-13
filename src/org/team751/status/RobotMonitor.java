package org.team751.status;

import java.util.Vector;

import org.team751.logging.LogLevel;
import org.team751.logging.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class implements the core functionality of the robot status system. It gets the sensor values
 * and transmits them as necessary.
 * @author Sam Crow
 *
 */
public class RobotMonitor {
	
	/**
	 * The temperature sensors that the robot contains
	 */
	private Vector sensors;
	
	public RobotMonitor() {
		
	}
	
	/**
	 * Add a temperature sensor to the robot monitor. Data from this sensor will be monitored.
	 * @param sensor The sensor to add
	 */
	public void addSensor(TemperatureSensor sensor) {
		sensors.add(sensor);
	}
	
	/**
	 * This method does the main processing.
	 * Call it periodically in the telopPeriodic(), disabledPeriodic(), and autonomousPeriodic() functions.
	 */
	public void run() {
		for(int i = 0, max = sensors.size(); i < max; i++) {
			TemperatureSensor sensor = (TemperatureSensor) sensors.get(i);
			double temperature = sensor.getTemperature();
			
			//Send the data
			SmartDashboard.putDouble(sensor.getName(), temperature);
			
			double warningTemperature = sensor.getWarningTemperature();
			if(!Double.isNaN(warningTemperature)) {
				//If the sensor has specified a warning temperature
				
				boolean ok = temperature < warningTemperature;
				
				//Add a boolean indicator to the dashboard to indicate the OK-ness of this temperature
				SmartDashboard.putBoolean(sensor.getName()+" OK", ok);
				
				if(!ok) {
					//If the sensor is in warning status
					
					if(!sensor.warningLogged) {
						//If a message hasn't been logged warning of this, do so
						Logger.log("Temperature sensor "+sensor.getName()+" is in a warning temperature status (temperature "+temperature+" degrees C)", LogLevel.kWarning);
						sensor.warningLogged = true;
					}
				}
				else {
					//Status is OK
					if(sensor.warningLogged) {
						//If a message was logged warning of an excessive temperature and no message
						//was yet logged indicating that the condition has returned to normal, do so
						Logger.log("Temperature sensor "+sensor.getName()+" is now normal (temperature "+temperature+" degrees C)", LogLevel.kWarning);
						sensor.warningLogged = false;
					}
				}
			}
		}
	}
	
}

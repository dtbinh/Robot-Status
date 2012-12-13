package org.team751.status;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Wraps a CANJaguar and allows it to be used as a temperature sensor
 * The temperature sensor that a Jaguar contains probably measures the
 * temperature of some of the internal transistors.
 * @author Sam Crow
 *
 */
public class JaguarTemperatureSensor extends TemperatureSensor {

	/**
	 * The Jaguar that contains the temperature sensor
	 */
	private CANJaguar jaguar;
	
	/**
	 * Constructor
	 * @param jaguar The Jaguar to get the temperature of
	 */
	public JaguarTemperatureSensor(String name, CANJaguar jaguar) {
		super(name);
		this.jaguar = jaguar;
	}
	
	public double getTemperature() {
		try {
			return jaguar.getTemperature();
		} catch (CANTimeoutException e) {
			e.printStackTrace();
			return Double.NaN;
		}
	}

	public double getWarningTemperature() {
		//Test the warning temperature a 30 celsius
		return 30;
	}
}

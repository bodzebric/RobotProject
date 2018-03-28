package nl.hva.miw.robot.cohort11;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;

import lejos.robotics.SampleProvider;


/**
 * 
 * @author Ashley
 *
 */

public class CollisionAvoidance {
	public static void main(String[] args) {
		EV3IRSensor infraRed = new EV3IRSensor(SensorPort.S2); // tijdelijk op port 2
		EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
		final int ONE_EIGHTY_DEGREES = 180;
		final int DEFAULT_MOTOR_SPEED = 100;
		SampleProvider rangeSampler;
		float[] lastRange;

		System.out.println("Drive and Avoid\n");
		System.out.println("Press any key to start");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		Button.waitForAnyPress();

		// initialiseren van de infrared sensor
		rangeSampler = infraRed.getDistanceMode();
		int distanceValue = 0;

		final int iteration_threshold = 2000;
		for (int i = 0; i <= iteration_threshold; i++) {

			lastRange = new float[rangeSampler.sampleSize()];
			rangeSampler.fetchSample(lastRange, 0);
			distanceValue = (int) lastRange[0];

			System.out.println("Afstand in cm: " + distanceValue);
		
		//rijden totdat er een obstakel gevonden wordt en dan draaien
			while (distanceValue > 15) {
			motorB.setSpeed(DEFAULT_MOTOR_SPEED);
			motorC.setSpeed(DEFAULT_MOTOR_SPEED);
			motorB.forward();
			motorC.forward();
//			rangeSampler = infraRed.getDistanceMode();
//			distanceValue = (int) lastRange[0];
//			}
			if (distanceValue < 15 && distanceValue > 4) {
				motorB.rotate(ONE_EIGHTY_DEGREES);
				motorC.rotate(-ONE_EIGHTY_DEGREES);
		
		//als afstand kleiner is dan 4 centimeter, dan afsluiten
			}if (distanceValue < 4 || Button.ESCAPE.isDown() ) {
				motorB.stop();
				motorC.stop();
				motorB.close();
				motorC.close();
				infraRed.close();
				}
			}
		}
	}
}
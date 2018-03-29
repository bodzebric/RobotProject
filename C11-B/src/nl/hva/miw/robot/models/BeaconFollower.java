package nl.hva.miw.robot.models;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;

/**
 * 
 * @author Ashley
 *
 */
public class BeaconFollower {
	public static void main(String[] args) {
		EV3IRSensor infraRed = new EV3IRSensor(SensorPort.S2); // tijdelijk op port 2
		EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
		SensorMode seekMode = infraRed.getSeekMode();
		SampleProvider rangeSampler;
        
		System.out.println("Follow the Beacon\n");
		System.out.println("Press any key to start");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		Button.waitForAnyPress();

		// set infrared to beacon mode. is dat getRemoteCommand(9) of een andere functie.
		// 9 is beacon mode
		// This feature allows a robot to follow a moving beacon or find its distance
		// and heading relative to a fixed beacon.
		// Returns heading (angle) and distance to beacon. Heading measurement is not in degrees.

		infraRed.getRemoteCommand(9);
		System.out.println("Connectie met beacon");
//		infraRed.getSeekMode();

		rangeSampler = infraRed.getDistanceMode();
		int distanceValue = 0;

		final int iteration_threshold = 2000;
		for (int i = 0; i <= iteration_threshold; i++) {

			float[] samples = new float[seekMode.sampleSize()];
			seekMode.fetchSample(samples, 0);
			distanceValue = (int) samples[0];

			System.out.println("Afstand in cm: " + distanceValue);

			while (distanceValue < 25 && distanceValue > 15) {
//				infraRed.getSeekMode();
				motorB.setSpeed(150);
				motorC.setSpeed(150);
				motorB.forward();
				motorC.forward();
				break;
			}
			if (distanceValue < 15 && distanceValue > 4) {
				motorB.stop(true);
				motorC.stop(true);
			}

			if (distanceValue < 4 || Button.ESCAPE.isDown()) {
				motorB.stop(true);
				motorC.stop(true);
				motorB.close();
				motorC.close();
				infraRed.close();
			}

		}
	}
}

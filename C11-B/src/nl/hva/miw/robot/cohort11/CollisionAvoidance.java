package nl.hva.miw.robot.cohort11;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class CollisionAvoidance {
	public static void main(String[] args) {
		EV3IRSensor infraRed = new EV3IRSensor(SensorPort.S2); // tijdelijk op port 2
		EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
		final int NINETY_DEGREES = 90;
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

		final int iteration_threshold = 100;
		for (int i = 0; i <= iteration_threshold; i++) {

			lastRange = new float[rangeSampler.sampleSize()];
			rangeSampler.fetchSample(lastRange, 0);
			distanceValue = (int) lastRange[0];

			System.out.println("Afstand in cm: " + distanceValue);
		}

		while (distanceValue > 10) {
			motorB.setSpeed(DEFAULT_MOTOR_SPEED);
			motorC.setSpeed(DEFAULT_MOTOR_SPEED);
			motorB.forward();
			motorC.forward();

			if (distanceValue < 10) {
				motorB.stop();
				motorC.stop();
			}
		}
	}
}

// if(distanceValue <= 10){
// motorB.rotate(NINETY_DEGREES);
// motorC.rotate(NINETY_DEGREES);
// }if(rangeSampler <= 3){
// motorB.stop();
// motorC.stop();
// motorB.close();
// motorC.close();
// }else {
// motorB.forward();
// motorC.forward();
// }
// }
// Sound.beepSequence(); // we are done.
//
// }
//
// }

// private static void configureInfraredSensor(final EV3IRSensor infraredSensor,
// final DifferentialPilot pilot) {
// final RangeFinderAdaptor rangeFinderAdaptor = new
// RangeFinderAdaptor(infraredSensor.getDistanceMode());
// final RangeFeatureDetector rangeFeatureDetector = new
// RangeFeatureDetector(rangeFinderAdaptor, MAX_DISTANCE, INTERVAL);
// final FeatureListener detectedObjectListener = new
// DetectedObjectListener(pilot);
// rangeFeatureDetector.addListener(detectedObjectListener);
// }
//

// Instantiate a RangeFinderAdaptor-object and tell it in which mode the sensor
// is used
// Instantiate a RangeFeatureDetector-object and tell it: the interval of
// checking the object's distance and the maximum distance of objects. It also
// needs the (prior to this step) created RangeFinderAdaptor-object.
// Instantiate an object of the class which implements the FeatureListener
// Add the instantiated listener from step 3 to the RangeFeatureDetector-object
// from step 2.

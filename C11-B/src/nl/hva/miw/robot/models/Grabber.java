package nl.hva.miw.robot.models;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class Grabber {
	public static void main(String[] args) {
		System.out.println("Grabber poging\n");
		System.out.println("Press any key to start");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		Button.waitForAnyPress();

		// create motor object
		EV3MediumRegulatedMotor motorD = new EV3MediumRegulatedMotor(MotorPort.D);
		System.out.println("motor forward");
		// set motors to 500 degrees/second rotation.
		// motorA.setAcceleration(500);

		motorD.setSpeed(50);
		motorD.forward(); // starts dicht.

		motorD.setSpeed(50);
		motorD.backward(); // starts open.

		// wait 1.5 seconds.
		Delay.msDelay(1500);

		// stop motors with brakes on.
		motorD.stop();

		Button.waitForAnyPress();

		// set motors to 500 degrees/second rotation.
		// motorA.setAcceleration(500);

		System.out.println("motor backward");
		motorD.setSpeed(50);
		motorD.forward(); // dicht.

		// wait 1.5 seconds.
		Delay.msDelay(1500);

		// stop motors with brakes on.
		motorD.stop();

		Button.waitForAnyPress();

		// // demonstrate rotate degrees with wait. One motor runs then other.
		// motorD.rotate(360);
		// motorB.rotate(360);
		//
		// Button.waitForAnyPress();
		//
		// // demonstrate rotate degrees without wait. Motors run together.
		// motorD.rotate(360, true);
		// motorB.rotate(360, true);
		//
		// Button.waitForAnyPress();

		// free up motor resources.
		motorD.close();

		Sound.beepSequence(); // we are done.
	}
}
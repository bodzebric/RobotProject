package nl.hva.miw.robot.cohort11;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveRegulated {
	 public static void main(String[] args)
	    {
	        System.out.println("Drive Regulated\n");
	        System.out.println("Press any key to start");

	        Button.LEDPattern(4);    // flash green led and
	        Sound.beepSequenceUp();    // make sound when ready.

	        Button.waitForAnyPress();
	        
	        // create two motor objects to control the motors.
	        EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	        EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	        
	        // set motors to 500 degrees/second rotation.
	        //motorA.setAcceleration(500);
	        motorB.setSpeed(500);
	        motorB.forward();    // starts rotation.

	        //motorA.setAcceleration(500);
	        motorC.setSpeed(500);
	        motorC.forward();    // starts rotation.
	        
	        // wait 3 seconds.
	        Delay.msDelay(3000);

	        // stop motors with brakes on.
	        motorB.stop();
	        motorC.stop();

	        Button.waitForAnyPress();

	        // demonstrate rotate degrees with wait. One motor runs then other.
	        motorB.rotate(360);
	        motorC.rotate(360);

	        Button.waitForAnyPress();

	        // demonstrate rotate degrees without wait. Motors run together.
	        motorB.rotate(360, true);
	        motorC.rotate(360, true);

	        Button.waitForAnyPress();

	        // demonstrate rotate to target angle without wait.
	        motorB.resetTachoCount();
	        motorC.resetTachoCount();
	        
	        motorB.rotateTo(180, true);
	        motorC.rotateTo(180, true);

	        Button.waitForAnyPress();

	        System.out.println("tach=" + motorB.getTachoCount());
	        
	        Button.waitForAnyPress();
	        
	        // free up motor resources.
	        motorB.close();
	        motorC.close();
	        
	        Sound.beepSequence();    // we are done.
	    }

}

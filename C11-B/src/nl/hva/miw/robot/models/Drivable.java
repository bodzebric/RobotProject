package nl.hva.miw.robot.models;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import lejos.robotics.RegulatedMotor;

public class Drivable {

	public static void main(String[] args) {
	final int MOVEMENT_TIME_STANDARD = 5000; //Standard time for a move in milliseconds
//	int movementTime = MOVEMENT_TIME_STANDARD; //Time for a move in milliseconds
	RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	
	
	//Standard start procedure
	System.out.println("Movement test");
	Button.LEDPattern(3); // flash green led and
	Sound.beepSequenceUp(); // make sound when ready.
//	System.out.println("Press any key to start");
//	Button.waitForAnyPress();
	Sound.beepSequenceUp();
	
	//Collection of actions to test Drivable
	driveForward(motorB, motorC, MOVEMENT_TIME_STANDARD);
	driveBackward(motorB, motorC, MOVEMENT_TIME_STANDARD);
	driveLeft(motorB, motorC, MOVEMENT_TIME_STANDARD);
	driveRight(motorB, motorC, MOVEMENT_TIME_STANDARD);
	driveLeftAngle(motorB, motorC, MOVEMENT_TIME_STANDARD);
	driveRightAngle(motorB, motorC, MOVEMENT_TIME_STANDARD);
	drivePirouetteLeft(motorB, motorC, MOVEMENT_TIME_STANDARD);
	drivePirouetteRight(motorB, motorC, MOVEMENT_TIME_STANDARD);
	
	//Closing off the motors
	motorB.close();
	motorC.close();
	}
	
	public static void driveForward(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorB.setAcceleration(); 
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.forward();
		motorC.forward();
		drivemovementTimer(movementTime);
		
		motorB.stop();
		motorC.stop();
	}
	
	public static void driveBackward(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorB.setAcceleration();
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.backward();
		motorC.backward();
		drivemovementTimer(movementTime);
		
		motorB.stop();
		motorC.stop();
	}
	
	public static void driveLeft(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorC.setAcceleration();
//		motorC.setSpeed();
		
		motorC.forward();
		drivemovementTimer(movementTime);
		
		motorC.stop();
	}
	
	
	//marijke
	public static void driveLeftAngle(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorC.setAcceleration();
//		motorC.setSpeed();
		
		motorC.forward();
		drivemovementTimer(movementTime);
		
		motorC.stop();
	}
	
	public static void driveRight(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorB.setAcceleration();
//		motorB.setSpeed();
		
		motorB.forward();
		drivemovementTimer(movementTime);
		
		motorB.stop();
	}
	
	//marijke
	public static void driveRightAngle(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorB.setAcceleration();
//		motorB.setSpeed();
		
		motorB.forward();
		drivemovementTimer(movementTime);
		
		motorB.stop();
	}
	
	public static void drivePirouetteLeft(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorB.setAcceleration();
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.backward();
		motorC.forward();
		drivemovementTimer(movementTime);
		
		motorB.stop();
		motorC.stop();
	}
	
	public static void drivePirouetteRight(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime) {
//		motorB.setAcceleration();
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.forward();
		motorC.backward();
		drivemovementTimer(movementTime);
		
		motorB.stop();
		motorC.stop();
	}
	
	public static void drivemovementTimer(int movementTime) {
		try {
			//Moves forward in milliseconds
			Thread.sleep(movementTime);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
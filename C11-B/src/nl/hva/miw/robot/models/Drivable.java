package nl.hva.miw.robot.models;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import lejos.robotics.RegulatedMotor;

public class Drivable {

	public static void main(String[] args) {
	final int STANDARD_DRIVE_TIME = 3000; //Standard time for a move in milliseconds
	final int STANDARD_DRIVE_ACCELERATION = 8000; //Default acceleration is 6000
	final int STANDARD_DRIVE_SPEED = 100;
	final int STANDARD_ANGLE = 45;
//	int movementTime = MOVEMENT_TIME_STANDARD; //Time for a move in milliseconds
//	int driveAcceleration
//	int driveSpeed
	
	//Creation of the motors
	RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	motorB.synchronizeWith(new RegulatedMotor[] { motorC });
	
	//Standard start procedure
	System.out.println("Movement test");
	Button.LEDPattern(3); // flash green led and
	Sound.beepSequenceUp(); // make sound when ready.
//	System.out.println("Press any key to start");
//	Button.waitForAnyPress();
	Sound.beepSequenceUp();
	
	//Collection of actions to test Drivable
	driveForward(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	driveBackward(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	driveLeft(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	driveRight(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	driveLeftAngle(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED, STANDARD_ANGLE);
	driveRightAngle(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED, STANDARD_ANGLE);
	drivePirouetteLeft(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	drivePirouetteRight(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	
	//Closing off the motors
	motorB.close();
	motorC.close();
	}
	
	public static void driveForward(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorB.setAcceleration(driveAcceleration); 
		motorC.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
		motorC.setSpeed(driveSpeed);
		
		motorB.startSynchronization();		
		motorB.forward();
		motorC.forward();
		motorB.endSynchronization();
		drivemovementTimer(movementTime);
		
		motorB.stop(true);
		motorC.stop(true);

	}
	
	public static void driveBackward(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorB.setAcceleration(driveAcceleration); 
		motorC.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
		motorC.setSpeed(driveSpeed);
		
		motorB.startSynchronization();		
		motorB.backward();
		motorC.backward();
		motorB.endSynchronization();

		drivemovementTimer(movementTime);
		
		motorB.stop(true);
		motorC.stop(true);
	}
	
	public static void driveLeft(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorC.setAcceleration(driveAcceleration);
		motorC.setSpeed(driveSpeed);
		
		motorC.forward();
		drivemovementTimer(movementTime);
		
		motorC.stop(true);
	}
	
	
	//marijke
	public static void driveLeftAngle(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed, int angle) {
		motorC.setAcceleration(driveAcceleration);
		motorC.setSpeed(driveSpeed);
		
		motorC.forward();
		motorC.rotate(angle);
		
		motorC.stop(true);	
	}
	
	public static void driveRight(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorB.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
		
		motorB.forward();
		drivemovementTimer(movementTime);
		
		motorB.stop(true);
	}
	
	//marijke
	public static void driveRightAngle(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed, int angle) {
		motorB.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
	
		motorB.forward();
		motorB.rotate(angle);
		
		motorB.stop(true);
	}
	
	public static void drivePirouetteLeft(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorB.setAcceleration(driveAcceleration); 
		motorC.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
		motorC.setSpeed(driveSpeed);
		
		motorB.startSynchronization();		
		motorB.backward();
		motorC.forward();
		motorB.endSynchronization();
		drivemovementTimer(movementTime);
		
		motorB.stop(true);
		motorC.stop(true);
	}
	
	public static void drivePirouetteRight(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorB.setAcceleration(driveAcceleration); 
		motorC.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
		motorC.setSpeed(driveSpeed);
		
		motorB.startSynchronization();		
		motorB.forward();
		motorC.backward();
		motorB.endSynchronization();

		drivemovementTimer(movementTime);
		
		motorB.stop(true);
		motorC.stop(true);
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
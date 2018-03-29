package nl.hva.miw.robot.models;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import lejos.robotics.RegulatedMotor;

public class Drivable {

	public static void main(String[] args) {
	final int STANDARD_DRIVE_TIME = 10000; //Standard time for a move in milliseconds
	final int STANDARD_DRIVE_ACCELERATION = 8000; //Default acceleration is 6000
	final int STANDARD_DRIVE_SPEED = 100;
	final int STANDARD_LEFT_ANGLE = 540; //Dit is 45 graden (maal 12 voor 360 graden)
	final int STANDARD_RIGHT_ANGLE = 530; //Dit is 45 graden (maal ~11.9 voor 360 graden)
	
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
		
	//Collection of actions to test Drivable
	Sound.beepSequenceUp();
	System.out.println("Movement test, foward and backward");
	driveForward(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	driveBackward(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	
	Sound.beepSequenceUp();
	System.out.println("Movement test, left and right");
	driveLeft(motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	driveRight(motorB, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	
	Sound.beepSequenceUp();
	System.out.println("Movement test, left angle and right angle");
	driveLeftAngle(motorC, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED, STANDARD_LEFT_ANGLE);
	driveRightAngle(motorB, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED, STANDARD_RIGHT_ANGLE);
	
	Sound.beepSequenceUp();
	System.out.println("Movement test, prouette left and pirouette right");
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
	
	public static void driveLeft(RegulatedMotor motorC,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorC.setAcceleration(driveAcceleration);
		motorC.setSpeed(driveSpeed);
		
			// Flauwe bocht links
		case 8:
			speedB = 100;
			speedC = 50;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			break;		
		// Normale bocht rechts
		case 7:
			speedB = 100;
			speedC = 50;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			break;
		// Scherpe bocht rechts
		case 6:
			speedB = 100;
			speedC = 25;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			break;

		// Flauwe bocht links
		case 5:
			speedB = 75;
			speedC = 100;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			break;			
		// Normale bocht links
		case 4:
			speedB = 50;
			speedC = 100;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			break;
		// Scherpe bocht links
		case 3:
			speedB = 25;
			speedC = 100;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			break;
			
		// Snelheid langzaam
		case 2:
			speedB = 50;
			speedC = 50;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			driveTime = 3000;
			leftAngle = 530;
			rightAngle = 540;
			break;
		// Snelheid medium
		case 1:
			speedB = 75;
			speedC = 75;
			accelerationB = 8000;
			accelerationC = 8000;
			driveTime = 3000;
			driveTime = 3000;
			leftAngle = 530;
			rightAngle = 540;
			break;
		// Snelheid hard, default snelheid
		case 0:
		default:
			speedB = 100;
			speedC = 100;
			accelerationB = 12000;
			accelerationC = 12000;
			driveTime = 3000;
			leftAngle = 530; // ~45 graden
			rightAngle = 540; // ~45 graden
			break;
		}
	}

	public void driveForward(RegulatedMotor motorB, RegulatedMotor motorC, int driveTime,
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);

		motorB.startSynchronization();
		motorB.forward();
		motorC.forward();
		
		drivemovementTimer(movementTime);
		
		motorC.stop(true);
	}

	public void driveBackward(RegulatedMotor motorB, RegulatedMotor motorC, int driveTime, 
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);

		motorB.startSynchronization();
		motorB.backward();
		motorC.backward();
		motorB.endSynchronization();

		driveTimer(driveTime);
		motorB.stop(true);
		motorC.stop(true);		
	}

	public static void driveLeftAngle(RegulatedMotor motorC, int speedC, int accelerationC, int angle) {
		motorC.setAcceleration(accelerationC);
		motorC.setSpeed(speedC);

		motorC.rotate(angle);
	}
	
	public static void driveRight(RegulatedMotor motorB,
			 int movementTime, int driveAcceleration, int driveSpeed) {
		motorB.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
		
		motorB.forward();
		
		drivemovementTimer(movementTime);
		
		motorB.stop(true);
	}
	
	public static void driveLeftAngle(RegulatedMotor motorC, int driveAcceleration, int driveSpeed, int angle) {
		motorC.setAcceleration(driveAcceleration);
		motorC.setSpeed(driveSpeed);
		
		motorC.rotate(angle);
		
		motorC.stop(true);	
	}
	
	public static void driveRightAngle(RegulatedMotor motorB, int driveAcceleration, int driveSpeed, int angle) {
		motorB.setAcceleration(driveAcceleration);
		motorB.setSpeed(driveSpeed);
	
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
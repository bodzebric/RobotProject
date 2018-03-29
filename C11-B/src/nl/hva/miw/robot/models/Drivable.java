package nl.hva.miw.robot.models;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import lejos.robotics.RegulatedMotor;

public class Drivable {
	int speedStyle;
	int speedB;
	int speedC;
	int accelerationB;
	int accelerationC;

	public static void main(String[] args) {
		Drivable RobotTeamB = new Drivable();
		RobotTeamB.tester();
	}

	public void tester() {
		final int STANDARD_DRIVE_TIME = 4000; // Standard time for a move in milliseconds
		final int STANDARD_LEFT_ANGLE = 540; // Dit is 45 graden (maal 12 voor 360 graden)
		final int STANDARD_RIGHT_ANGLE = 530; // Dit is 45 graden (maal ~11.9 voor 360 graden)

		// Creation of the motors
		RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
		motorB.synchronizeWith(new RegulatedMotor[] { motorC });

		// Standard start procedure
		System.out.println("Movement test:");
		Button.LEDPattern(3); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		// Collection of actions to test Drivable
		Sound.beepSequenceUp();
		System.out.println("Foward and backward");
		setSpeed(3);
		driveForward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
		setSpeed(4);
		driveForward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
		setSpeed(5);
		driveForward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
		setSpeed(5);
		driveBackward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);

		Sound.beepSequenceUp();
		System.out.println("Forward left and right");
		setSpeed(0);
		driveForward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
		setSpeed(1);
		driveForward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);

		Sound.beepSequenceUp();
		System.out.println("Backward left and right");
		setSpeed(0);
		driveBackward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
		setSpeed(1);
		driveBackward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);

		Sound.beepSequenceUp();
		System.out.println("Left angle and right angle");
		driveLeftAngle(motorC, speedC, accelerationC, STANDARD_LEFT_ANGLE);
		driveRightAngle(motorB, speedB, accelerationB, STANDARD_RIGHT_ANGLE);
			
		Sound.beepSequenceUp();
		System.out.println("Pirouette left and right");
		drivePirouetteLeft(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
		drivePirouetteRight(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);

		// Stoppen van de motoren
		motorB.stop(true);
		motorC.stop(true);

		// Closing off the motors
		motorB.close();
		motorC.close();
	}

	public void setSpeed(int speedStyle) {
		switch (speedStyle) {
		// Flauwe bocht links
		// Default acceleration is 6000
		case 0:
			speedB = 50;
			speedC = 100;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		// Flauwe bocht rechts
		case 1:
			speedB = 100;
			speedC = 50;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		// Snelheid langzaam
		case 2:
			speedB = 50;
			speedC = 50;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		// Snelheid medium
		case 3:
			speedB = 75;
			speedC = 75;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		// Snelheid hard , default snelheid
		case 5:
		default:
			speedB = 100;
			speedC = 100;
			accelerationB = 12000;
			accelerationC = 12000;
			break;
		}
	}

	public void driveForward(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime,
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);

		motorB.startSynchronization();
		motorB.forward();
		motorC.forward();
		motorB.endSynchronization();

		drivemovementTimer(movementTime);
	}

	public void driveBackward(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime, 
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);

		motorB.startSynchronization();
		motorB.backward();
		motorC.backward();
		motorB.endSynchronization();
		
		drivemovementTimer(movementTime);
	}

	public static void driveLeftAngle(RegulatedMotor motorC, int speedC, int accelerationC, int angle) {
		motorC.setAcceleration(accelerationC);
		motorC.setSpeed(speedC);

		motorC.rotate(angle);
	}

	public static void driveRightAngle(RegulatedMotor motorB, int speedB, int accelerationB, int angle) {
		motorB.setAcceleration(accelerationB);
		motorB.setSpeed(speedB);
		
		motorB.rotate(angle);
	}
	
	public static void drivePirouetteLeft(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime,
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);
		
		motorB.startSynchronization();
		motorB.backward();
		motorC.forward();
		motorB.endSynchronization();
		
		drivemovementTimer(movementTime);
	}
	
	public static void drivePirouetteRight(RegulatedMotor motorB, RegulatedMotor motorC, int movementTime, 
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);
		
		motorB.startSynchronization();
		motorB.forward();
		motorC.backward();
		motorB.endSynchronization();
	
		drivemovementTimer(movementTime);
	}

	public static void drivemovementTimer(int movementTime) {
		try {
			// Moves forward in milliseconds
			Thread.sleep(movementTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
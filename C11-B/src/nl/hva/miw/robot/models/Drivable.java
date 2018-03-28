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
		final int STANDARD_DRIVE_TIME = 4000; //Standard time for a move in milliseconds
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
			
		//Collection of actions to test Drivable
		Sound.beepSequenceUp();
		System.out.println("Movement test, foward and backward");
		
		setSpeed(5);
		driveForward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
		setSpeed(2);
		driveBackward(motorB, motorC, STANDARD_DRIVE_TIME, speedB, speedC, accelerationB, accelerationC);
	//	
	//	Sound.beepSequenceUp();
	//	System.out.println("Movement test, left and right");
	//	driveForward(motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	//	driveForward(motorB, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	//	
	//	Sound.beepSequenceUp();
	//	System.out.println("Movement test, left and right");
	//	driveBackward(motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	//	driveBackward(motorB, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	//	
	//	Sound.beepSequenceUp();
	//	System.out.println("Movement test, left angle and right angle");
	//	driveLeftAngle(motorC, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED, STANDARD_LEFT_ANGLE);
	//	driveRightAngle(motorB, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED, STANDARD_RIGHT_ANGLE);
	//	
	//	Sound.beepSequenceUp();
	//	System.out.println("Movement test, prouette left and pirouette right");
	//	drivePirouetteLeft(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
	//	drivePirouetteRight(motorB, motorC, STANDARD_DRIVE_TIME, STANDARD_DRIVE_ACCELERATION, STANDARD_DRIVE_SPEED);
		
		//Stoppen van de motoren
		motorB.stop(true);
		motorC.stop(true);
		
		//Closing off the motors
		motorB.close();
		motorC.close();
	}
	
	public void setSpeed(int speedStyle) {
		switch (speedStyle) {
		//Flauwe bocht links
		//Default acceleration is 6000
		case 0:
			speedB = 50;
			speedC = 200;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		//Flauwe bocht rechts
		case 1:
			speedB = 200;
			speedC = 50;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		//Snelheid langzaam 
		case 2:
			speedB = 50;
			speedC = 50;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		//Snelheid medium
		case 3:
			speedB = 100;
			speedC = 100;
			accelerationB = 8000;
			accelerationC = 8000;
			break;
		//Snelheid hard , default snelheid
		case 5:
		default:
			speedB = 200;
			speedC = 200;
			accelerationB = 12000;
			accelerationC = 12000;
			break;
		}
	}
			
	public void driveForward(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int speedB, int speedC, int accelerationB, int accelerationC) {
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
	
	public void driveBackward(RegulatedMotor motorB, RegulatedMotor motorC,
			 int movementTime, int speedB, int speedC, int accelerationB, int accelerationC) {
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
//	
//	public static void driveLeftAngle(RegulatedMotor motorC, int driveAcceleration, int driveSpeed, int angle) {
//		motorC.setAcceleration(driveAcceleration);
//		motorC.setSpeed(driveSpeed);
//		
//		motorC.rotate(angle);
//		
//	}
//	
//	public static void driveRightAngle(RegulatedMotor motorB, int driveAcceleration, int driveSpeed, int angle) {
//		motorB.setAcceleration(driveAcceleration);
//		motorB.setSpeed(driveSpeed);
//	
//		motorB.rotate(angle);
//		
//	}
//	
//	public static void drivePirouetteLeft(RegulatedMotor motorB, RegulatedMotor motorC,
//			 int movementTime, int driveAccelerationB, int driveSpeedB, int driveAccelerationC, int driveSpeedC) {
//		motorB.setAcceleration(driveAccelerationC); 
//		motorC.setAcceleration(driveAccelerationB);
//		motorB.setSpeed(driveSpeedB);
//		motorC.setSpeed(driveSpeedC);
//		
//		motorB.startSynchronization();		
//		motorB.backward();
//		motorC.forward();
//		motorB.endSynchronization();
//		
//		drivemovementTimer(movementTime);
//		
//	}
//	
//	public static void drivePirouetteRight(RegulatedMotor motorB, RegulatedMotor motorC,
//			 int movementTime, int driveAccelerationB, int driveSpeedB, int driveAccelerationC, int driveSpeedC) {
//		motorB.setAcceleration(driveAccelerationC); 
//		motorC.setAcceleration(driveAccelerationB);
//		motorB.setSpeed(driveSpeedB);
//		motorC.setSpeed(driveSpeedC);
//		
//		motorB.startSynchronization();		
//		motorB.forward();
//		motorC.backward();
//		motorB.endSynchronization();
//
//		drivemovementTimer(movementTime);
//		
//	}
	
	public static void drivemovementTimer(int movementTime) {
		try {
			//Moves forward in milliseconds
			Thread.sleep(movementTime);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
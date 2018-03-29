package nl.hva.miw.robot.models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Drivable {
	
	private int speedStyle; // De voorgemaakte stijlen waar snelheden en richtingen van de motoren al bepaald zijn
	private int speedB; // Om motor B zijn snelheid in te stellen
	private int speedC; // Om motor C zijn snelheid in te stellen
	private int accelerationB; // Motor B zijn acceleratie in te stellen, default acceleration is 6000
	private int accelerationC; // Motor C zijn acceleratie in te stellen, default acceleration is 6000
	private int driveTime; // Standaard tijd voor een beweging in milliseconden
	private int leftAngle; // Waar dus standaard graden maal ~12.0 voor 360 graden (Bijvoorbeeld 540 is ~45 graden)
	private int rightAngle; // Waar dus standaard graden maal ~11.9 voor 360 graden (Bijvoorbeeld 530 is ~45 graden)
	
	// Creation of the drive motors
	RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	// Synchronisation of the drivemotoren
	//motorB.synchronizeWith(new RegulatedMotor[] { motorC });
	
//	public void driveTester() {
		
//		// Creation of the drive motors
//		RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
//		RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
//		// Synchronisation of the drivemotoren
//		motorB.synchronizeWith(new RegulatedMotor[] { motorC });
		
//		// Standard start procedure
//		System.out.println("Movement test:");
//		Button.LEDPattern(3); // flash green led and
//		Sound.beepSequenceUp(); // make sound when ready.
//
//		// Collection of actions to test Drivable
//		Sound.beepSequenceUp();
//		System.out.println("Foward and backward");
//		
//		setSpeedStyle(0);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(1);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(2);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		
//		Sound.beepSequenceUp();
//		
//		setSpeedStyle(0);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(1);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(2);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//
//		Sound.beepSequenceUp();
//		Sound.beepSequenceUp();
//		System.out.println("Forward left and right");
//		setSpeedStyle(3);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(4);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(5);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		
//		Sound.beepSequenceUp();
//		setSpeedStyle(6);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(7);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(8);
//		driveForward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		
//		Sound.beepSequenceUp();
//		Sound.beepSequenceUp();
//		System.out.println("Backward left and right");
//		setSpeedStyle(3);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(4);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(5);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		
//		Sound.beepSequenceUp();
//		setSpeedStyle(6);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(7);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		setSpeedStyle(8);
//		driveBackward(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//
//		
//		setSpeedStyle(0);
//		setSpeedStyle(9); // Hoek 90 graden vooruit
//		Sound.beepSequenceUp();
//		Sound.beepSequenceUp();
//		System.out.println("Left angle and right angle");
//		driveLeftAngle(motorC, speedC, accelerationC, leftAngle);
//		driveRightAngle(motorB, speedB, accelerationB, rightAngle);
//		
//		setSpeedStyle(0);
//		setSpeedStyle(10); // Hoek 90 graden achteruit
//		driveLeftAngle(motorC, speedC, accelerationC, leftAngle);
//		driveRightAngle(motorB, speedB, accelerationB, rightAngle);
//
//		
//		Sound.beepSequenceUp();
//		Sound.beepSequenceUp();
//		System.out.println("Pirouette left and right");
//		drivePirouetteLeft(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//		drivePirouetteRight(motorB, motorC, driveTime, speedB, speedC, accelerationB, accelerationC);
//
//		// Stoppen van de motoren
//		motorB.stop(true);
//		motorC.stop(true);
//		
//		// Closing off the motors
//		motorB.close();
//		motorC.close();
//	}
	
	public void setSpeedStyle(int speedStyle) {
		switch (speedStyle) {

		// Linksaf of Rechtsaf 90 graden achteruit
		case 10:
			leftAngle = -1060;
			rightAngle = -1080;
			break;
		// Linksaf of Rechtsaf 90 graden vooruit
		case 9:
			leftAngle = 1060;
			rightAngle = 1080;
			break;			
		
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
	
	public void driveForward() {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);

		motorB.startSynchronization();
		motorB.forward();
		motorC.forward();
		motorB.endSynchronization();

		driveTimer(driveTime);
		motorB.stop(true);
		motorC.stop(true);
	}

	public void driveBackward() {
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
	
	public static void driveRightAngle(RegulatedMotor motorB, int speedB, int accelerationB, int angle) {
		motorB.setAcceleration(accelerationB);
		motorB.setSpeed(speedB);
		
		motorB.rotate(angle);
	}
	
	public static void drivePirouetteLeft(RegulatedMotor motorB, RegulatedMotor motorC, int driveTime,
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);
		
		motorB.startSynchronization();
		motorB.backward();
		motorC.forward();
		motorB.endSynchronization();
		
		driveTimer(driveTime);
		motorB.stop(true);
		motorC.stop(true);
	}
	
	public static void drivePirouetteRight(RegulatedMotor motorB, RegulatedMotor motorC, int driveTime, 
			int speedB, int speedC, int accelerationB, int accelerationC) {
		motorB.setAcceleration(accelerationB);
		motorC.setAcceleration(accelerationC);
		motorB.setSpeed(speedB);
		motorC.setSpeed(speedC);
		
		motorB.startSynchronization();
		motorB.forward();
		motorC.backward();
		motorB.endSynchronization();
	
		driveTimer(driveTime);
		motorB.stop(true);
		motorC.stop(true);
	}

	public static void driveTimer(int driveTime) {
		try {
			// Moves forward in milliseconds
			Thread.sleep(driveTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
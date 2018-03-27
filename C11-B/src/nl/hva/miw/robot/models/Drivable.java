package nl.hva.miw.robot.models;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import lejos.robotics.RegulatedMotor;


public class Drivable {
	
	//Create two motor objects to control the motors.
	RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
    
	public void driveForward() {
//		motorB.setAcceleration();
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.forward();
		motorC.forward();
		driveTimer();
		
		motorB.stop();
		motorC.stop();
		motorB.close();
		motorC.close();

	}
	
	public void driveBackwards() {
//		motorB.setAcceleration();
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.backward();
		motorC.backward();
		driveTimer();
		
		motorB.stop();
		motorC.stop();
		motorB.close();
		motorC.close();
	}
	
	public void driveLeft() {
//		motorC.setAcceleration();
//		motorC.setSpeed();
		
		motorC.forward();
		driveTimer();
		
		motorC.stop();
		motorB.close();
	}
	
	public void driveRight() {
//		motorB.setAcceleration();
//		motorB.setSpeed();
		
		motorB.forward();
		driveTimer();
		
		motorB.stop();
		motorB.close();
	}
	
	public void drivePiroutteLeft() {
//		motorB.setAcceleration();
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.backward();
		motorC.forward();
		driveTimer();
		
		motorB.stop();
		motorC.stop();
		motorB.close();
		motorC.close();
	}
	
	public void drivePirouetteRight() {
//		motorB.setAcceleration();
//		motorC.setAcceleration();
//		motorB.setSpeed();
//		motorC.setSpeed();
		
		motorB.forward();
		motorC.backward();
		driveTimer();
		
		motorB.stop();
		motorC.stop();
		motorB.close();
		motorC.close();
	}
	
	public void driveTimer() {
		try {
			//Moves forward for 2 secs
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
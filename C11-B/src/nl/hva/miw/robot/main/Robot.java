package nl.hva.miw.robot.main;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import nl.hva.miw.robot.controllers.LineFollowerController;
import nl.hva.miw.robot.models.LineFollower;;

public class Robot {
	
	private final static String LEFT_MOTOR_PORT = "B";
	private final static String RIGHT_MOTOR_PORT = "C";
	private final static String INFRARED_SENSOR_PORT = "S2";
	private final static String COLOR_SENSOR_PORT = "S3";
	private EV3 brick;
    	
	private LineFollower lineFollower = new LineFollower(LocalEV3.get(),
														LEFT_MOTOR_PORT, 
														RIGHT_MOTOR_PORT, 
														COLOR_SENSOR_PORT);
	private LineFollowerController lfc = new LineFollowerController(lineFollower);
	

	public Robot() {
		super();
		brick = LocalEV3.get();
	}
	
	public static void main(String[] args) {
		Robot robot = new Robot();
		robot.run();
		robot.runLineFollower();
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom!", 0, 3);
		display.drawString("Team Zero", 0, 4);
		waitForKey(Button.ENTER);

	}
	
	private void runLineFollower() {
		lfc.runLineFollower();		
	}
	
	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}
}

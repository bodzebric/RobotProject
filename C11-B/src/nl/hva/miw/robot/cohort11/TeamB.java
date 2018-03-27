package nl.hva.miw.robot.cohort11;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;

public class TeamB {
	
	Brick brick;
	
	public TeamB() {
		super();
		brick = LocalEV3.get();
	}
	
	public static void main(String[] args) {
		TeamB marvin = new TeamB();
		marvin.run();
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom!", 0, 3);
		display.drawString("Team Zero", 0, 4);
		waitForKey(Button.ENTER);
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

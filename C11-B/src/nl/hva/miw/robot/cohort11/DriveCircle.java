package nl.hva.miw.robot.cohort11;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import nl.hva.miw.robot.utilities.*;

public class DriveCircle 
{ 
	public static void main(String[] args)
    {
        TouchSensor touchSensor = new TouchSensor(SensorPort.S1);

        System.out.println("Drive Circle (2)\nand Stop\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();  // make sound when ready.

        Button.waitForAnyPress();

       // create two motor objects to control the motors.
       UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.B);
       UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.C);

       // set motors to different power levels. Adjust to get a circle.
       motorA.setPower(70);
       motorB.setPower(30);

       // wait doing nothing for touch sensor to stop driving.
       while (!touchSensor.isTouched()) {}

       // stop motors with brakes on.
       motorA.stop();
       motorB.stop();

       // free up resources.
       motorA.close();
       motorB.close();
       touchSensor.close();

       Sound.beepSequence(); // we are done.
   }
 }
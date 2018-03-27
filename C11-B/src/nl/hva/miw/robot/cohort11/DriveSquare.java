package nl.hva.miw.robot.cohort11;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveSquare
{
    public static void main(String[] args)
    {
        System.out.println("Drive in a Square\nand Stop\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
        UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);

        for (int i = 0; i < 4; i++)
        {
            // set motors to 50% power.
        	motorB.setPower(50);
        	motorC.setPower(50);

            // wait 2 seconds.
            Delay.msDelay(2000);

            // stop motors with brakes on. 
            motorB.stop();
            motorB.stop();

            // turn right by reversing the right motor.
            motorB.backward();
            motorC.forward();
 
            // make the turn.
            motorB.setPower(50);
            motorC.setPower(50);

            // adjust time to get a 90% turn.
            Delay.msDelay(1500);

            motorB.stop();
            motorC.stop();

            // set right motor back to forward motion.
            motorB.forward();
            motorC.forward();
        }

        // free up motor resources. 
        motorB.close(); 
        motorC.close();
 
        Sound.beepSequence(); // we are done.
    }
}
package nl.hva.miw.robot.cohort11;

import java.util.Arrays;

import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import nl.hva.miw.robot.utilities.*;

public class LineFollower {

        private EV3 brick;
        private RegulatedMotor motorB;
        private RegulatedMotor motorC;
        private EV3ColorSensor colorSensor;
        /** The calibrated output from the color sensor */
        private SampleProvider calibrated; // use this to fetch samples
    	private final static int HIGH_MOTOR_SPEED = 100;
    	private static int sampleSize;
    	private boolean isTurning360;

        public static void main(String[] args) {
                LineFollower linefollower = new LineFollower(
                                LocalEV3.get(), // brick
                                "B",            // left motor port
                                "C",            // right motor port
                                "S3"            // color sensor port
                                );
                linefollower.go(); // start it running
        }

        public LineFollower(EV3 pBrick, String portMotorB, String portMotorC, String colorPort) {
                super();
                this.brick = pBrick;

                // establish a fail-safe: pressing Escape quits
                brick.getKey("Escape").addKeyListener(new KeyListener() {
                        @Override
                        public void keyPressed(Key k) {
                        }

                        @Override
                        public void keyReleased(Key k) {
                                System.exit(0);
                        }
                });

                // Connect to motors and colorsensor
                motorB 		= new EV3LargeRegulatedMotor(brick.getPort(portMotorB));
                motorC 		= new EV3LargeRegulatedMotor(brick.getPort(portMotorC));
                colorSensor = new EV3ColorSensor(brick.getPort(colorPort));
        }

        private void go() {
                calibrate(); 			// only calibrate once, at the start.
               // (findLine()) {	// as long as the sensor detects a line..
                        followLine();	// follow the line.
              //  }
        }
        
        /**
         * <p>
         * Prompts the user to place the robot near the line, then
         * calls the findLine() method below.  While samples
         * are being taken, they will automatically calibrate the sensor
         * with minimum and maximum values.
         * </p>
         */
        private void calibrate() {
                brick.getTextLCD().clear();
                brick.getTextLCD().drawString("Calibrate:", 0, 0);
                brick.getTextLCD().drawString("Place the robot", 0, 1);
                brick.getTextLCD().drawString("on or near the", 0, 2);
                brick.getTextLCD().drawString("line start.", 0, 3);
                brick.getTextLCD().drawString("Then press the", 0, 5);
                brick.getTextLCD().drawString("Enter key", 0, 6);
                brick.getKey("Enter").waitForPressAndRelease();

                // The boolean determines if the filter "clamps" values out
                // of range to the minimum and maximum provided.  If set to
                // true, you will always get values in the range you specify.
                // If false, a poor calibration may result in readings coming
                // back outside of range.
                NormalizationFilter normalizationfilter = new NormalizationFilter
                                (colorSensor.getRedMode(), 0, 1, true);

                calibrated = normalizationfilter;

                normalizationfilter.startCalibration();
                findInitialLine();
                // end the collection of calibration data
                normalizationfilter.stopCalibration();

                brick.getTextLCD().clear();
        }


        /**
         * @param degrees The change in heading for the robot.  Positive values
         *                turn the robot left; negative values turn it right.
         * @param immediateReturn If true, the method returns as soon as the
         *                        motors have started.  If false, the method
         *                        waits until the rotation of the robot has
         *                        completed and the motors have stopped.
         */
        
        private void turn(int degrees, boolean immediateReturn) {	
        	motorB.setSpeed(HIGH_MOTOR_SPEED);
        	motorC.setSpeed(HIGH_MOTOR_SPEED);
        	
        	if(degrees < 0) {
        		motorC.rotate(-11 * degrees, immediateReturn);
        	} else {
        		motorB.rotate(12 * degrees, immediateReturn);
        	}
        	
        }


        /**
         * <p>
         * Turns the robot around in a 360-degree circle.  As the robot
         * turns, it should take readings from the color sensor and store
         * the value it believes to be the line (brightest/darkest,
         * depending on the color of line being used), as well as the
         * rotation of the motors at that point.
         * </p>
         *
         * <p>
         * When the circle is complete, the robot should turn back to
         * the point where the line was observed, then stop
         * its motors.
         * </p>
         *
         * <p>
         * Precondition: The robot is positioned so it is on the line,
         * or will rotate over the line once when it turns.
         * </p>
         *
         * <p>
         * Postconditions: The robot has rotated so that the color sensor
         * is at the location where it was over the line.
         * </p>
         */
        private void findInitialLine() {
        		
        		this.isTurning360 = true;
        		//motorB.resetTachoCount();
        		
        		while(isTurning360) {
            		turn(360, false);
            		//motorB.getTachoCount();
            		
            		sampleSize = calibrated.sampleSize();
            	 	
            		float[] sample = new float[sampleSize];
            		//float[] degreesTacho = new float[sampleSize];
            		
            		final int iteration_threshold = 1200;
            		for (int i = 0; i <= iteration_threshold; i++) {
            			//degreesTacho[i] = motorB.getTachoCount();
    	            	calibrated.fetchSample(sample, 0); 
    		            //System.out.println("N = " + i + " Sample = " + Arrays.toString(sample));
    	            }
            		
            		isTurning360 = false;       			
        		}

                // TODO When finished turning, turn back to the location
                // 		where was the best reading.
                //
                // 
                // http://web.suffieldacademy.org/cs/lejos_ev3_api/lejos/robotics/Encoder.html#getTachoCount--
                // http://web.suffieldacademy.org/cs/lejos_ev3_api/lejos/robotics/Encoder.html#resetTachoCount--
        }

        private void followLine() {
        	
        		sampleSize = calibrated.sampleSize();
        		float[] sample = new float[sampleSize];
        		
        		final int iteration_threshold = 1200;
        		for (int i = 0; i <= iteration_threshold; i++) {
	            	
        			calibrated.fetchSample(sample, 0); 
		            System.out.println("N = " + i + " Sample = " + Arrays.toString(sample));
		            
		            while (sample[i] <= 0.10) {
		        		motorB.setSpeed(HIGH_MOTOR_SPEED);
		        		motorC.setSpeed(HIGH_MOTOR_SPEED);
		        		break;
		            }          
		            if (sample[i] >= 0.10) {
		                motorB.stop();
		                motorC.stop();       	
		            }
		            	
	            }
        }


        /**
         * <p>
         * Searches for the line by turning the robot back in forth in
         * progressively larger sweeps until it finds the line or gives
         * up (can't find).  You may want to give up after turning in
         * a complete circle and still not being able to find the line.
         * </p>
         *
         * <p>
         * Precondition: The motors are stopped.
         * </p>
         *
         * <p>
         * Postcondition: The robot is once again on the line (returns true)
         *                or has given up looking (returns false).
         * </p>
         *
         * @return boolean True, if the robot found the line again.
         *                 False if the robot could not find the line.
         */
        private boolean findLine() {

                // This is sample code that will cause the robot to wait
                // until it "sees" the line.  It does NOT move the motors,
                // so you'll have to pick your robot up and put it on the
                // line for it to continue.
                //
                // You should replace this code with your own code that
                // moves the motors and causes the robot to search for the
                // line by itself.
                float[] sample = new float[calibrated.sampleSize()];
                sample[0] = -1;
                while (sample[0] < 0.5) {
                    calibrated.fetchSample(sample, 0);
                }
                return true;

                // You will need to complete this method in order to have the
                // robot search for the line once it loses it

                // use the turn() method for your turns

                // keep turning until you've either found the line again,
                // or turned so much that you give up (returning false)

        }


}
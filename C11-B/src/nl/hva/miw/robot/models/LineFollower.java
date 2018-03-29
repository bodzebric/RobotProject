package nl.hva.miw.robot.models;

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
        // used to fetch samples (calibrated by filter)
        private SampleProvider calibrated; 
		private float[] lastSample;
    	
		private final static int HIGH_MOTOR_SPEED = 100;
		private final static int CORRECTION_FACTOR_MOTOR_RIGHT = -11;
		private final static int CORRECTION_FACTOR_MOTOR_LEFT = 12;
		private final static String LEFT_MOTOR_PORT = "B";
		private final static String RIGHT_MOTOR_PORT = "C";
		private final static String COLOR_SENSOR_PORT = "S3";
		private final static double BRIGHTEST_COLOR = 1.0;
		private final static double DARKEST_COLOR = 0.0;
    	private boolean isTurning360;
    	private boolean isLineFound;

        public static void main(String[] args) {
                LineFollower lineFollower = new LineFollower(
                                LocalEV3.get(), 
                                LEFT_MOTOR_PORT, 
                                RIGHT_MOTOR_PORT,
                                COLOR_SENSOR_PORT);
                //Start it running
                lineFollower.go(); 
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

                // Connect to Motors and ColorSensor
                motorB 		= new EV3LargeRegulatedMotor(brick.getPort(portMotorB));
                motorC 		= new EV3LargeRegulatedMotor(brick.getPort(portMotorC));
                colorSensor = new EV3ColorSensor(brick.getPort(colorPort));
        }

        public void go() {
        		//Once, at the start.
                calibrate();
                // as long as the sensor detects a line..
               while(findLine()) {
                		//follow the line.
                        followLine();	
                }
        }
        
        /**
         * Calls the findInitialLine() method below.  While samples
         * are being taken, they will automatically calibrate the sensor
         * with minimum and maximum values.
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

                //boolean:
                //true, you will always get values in the range you specify.
                //false, a poor calibration may result in readings coming outside of range
                NormalizationFilter normalizationfilter = new NormalizationFilter
                                (colorSensor.getRedMode(), 0, 1, true);

                calibrated = normalizationfilter;
                normalizationfilter.startCalibration();
                findInitialLine();
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
        		motorC.rotate(CORRECTION_FACTOR_MOTOR_RIGHT * degrees, immediateReturn);
        	} else {
        		motorB.rotate(CORRECTION_FACTOR_MOTOR_LEFT * degrees, immediateReturn);
        	}      	
        }


        /**
         * Turns the robot 360 degrees.  While robot turns it takes
         * readings from the color sensor and stores
         * the value it believes to be the line (brightest/darkest,
         * depending on the color of line being used), as well as the
         * rotation of the motors at that point.
         *
         * TODO When finished turning, turn back to the location
         * where was the best sample reading with tachocount then stop.
         */
        
        private void findInitialLine() {
        		
        		this.isTurning360 = true;
        		motorB.resetTachoCount();
        		
        		while(isTurning360) {
        			
               		float[] sample = new float[calibrated.sampleSize()];
 //           		float[] degreesTacho = new float[calibrated.sampleSize()];
            		
        			turn(-360, false);
        			
 //       			motorB.getTachoCount();
            		    	 	             		
            		final int iteration_threshold = 1200;
            		for (int i = 0; i <= iteration_threshold; i++) {
            			
            			//degreesTacho[i] = motorB.getTachoCount();
    	            	calibrated.fetchSample(sample, 0);   		            
    	            	//System.out.println("N = " + i + " Sample = " + Arrays.toString(sample));	            	
    	            }
            		
            		turn(5, false);           		
            		isTurning360 = false;
        		}
        }

        private void followLine() {
        	
        		float lightValue = -1;		       		
        		
				lastSample = new float[calibrated.sampleSize()];
        		
        		final int iteration_threshold = 1200;
         		for (int i = 0; i <= iteration_threshold; i++) {
	            	
					calibrated.fetchSample(lastSample, 0);
					lightValue = (float) lastSample[0];
					
					System.out.println("Light intensity :" + lightValue);
		            
		            while (lightValue == 0.0) {
		        		motorB.setSpeed(HIGH_MOTOR_SPEED);
		        		motorC.setSpeed(HIGH_MOTOR_SPEED);
		        		motorB.forward();
		        		motorC.forward();
		        		break;
		            }          
		            if (lightValue > 0.0) {
		                motorB.stop();
		                motorC.stop();
		                //findLine();
		            }		            	
	            }
        }


        /**
         * Searches for the line by turning the robot back in forth in
         * progressively larger sweeps until it finds the line or gives
         * up (can't find).

         * @return boolean True, if the robot found the line again.
         *                 False if the robot could not find the line.
         */
        
        private boolean findLine() {
        	
	        	float lightValue = -1;	
	        	isLineFound = false;
				lastSample = new float[calibrated.sampleSize()];
	    		
        		final int iteration_threshold = 1200;
         		for (int i = 0; i <= iteration_threshold; i++) {
	            	
					calibrated.fetchSample(lastSample, 0);
					lightValue = (float) lastSample[0];
					
					System.out.println("FindLine Light intensity :" + lightValue);
		            
		            if (lightValue > 0.0) {
		            	turn(-15, true);   // search 15 degrees left
		            	turn(30, true);   // search 15 degrees right
		            	turn(-45, true);  // search 30 degrees left
		            	turn(75, true);   // search 30 degrees right
		            	turn(-120, true); // search 45 degrees left
		            	turn(195, true);  // search 45 degrees right
		            	turn(-315, true); // search 60 degrees left
		            	turn(510, true);  // search 60 degrees right
		            }          
		            if (lightValue == 0.0) {
		            	isLineFound = true;
		            }		            	
	            }
	    		return isLineFound;
        }
}
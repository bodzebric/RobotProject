package nl.hva.miw.robot.models; 
 
import lejos.hardware.Sound; 
import lejos.hardware.motor.*; 
import lejos.hardware.port.*; 
import lejos.utility.Delay; 
 
public class Grabber { 
      
	public void grabberOpen() {
		
		// Create motor object & Set variabelen
        EV3MediumRegulatedMotor motorD = new EV3MediumRegulatedMotor(MotorPort.D); 
        motorD.setAcceleration(8000); 
        motorD.setSpeed(100); 

        System.out.println("Grabber open"); 
        Sound.beepSequence();
        motorD.backward(); // open 
        Delay.msDelay(1000); 
        motorD.stop(); 
 
        motorD.close();
        
	}
	
	public void grabberDicht() {
        
		// Create motor object & Set variabelen
        EV3MediumRegulatedMotor motorD = new EV3MediumRegulatedMotor(MotorPort.D); 
        motorD.setAcceleration(8000); 
        motorD.setSpeed(100); 

        //Testen van de grabber
        System.out.println("Grabber dicht"); 
        Sound.beepSequence();
        motorD.forward(); // dicht 
        Delay.msDelay(1000); 
        motorD.stop(); 
    
        motorD.close();

	}
	
}
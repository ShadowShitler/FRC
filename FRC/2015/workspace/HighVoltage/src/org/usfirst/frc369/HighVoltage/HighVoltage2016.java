package org.usfirst.frc369.HighVoltage;


import java.util.HashMap;

//import javax.sound.sampled.*;

import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.vision.AxisCamera;
public class IloveyouStephon extends SampleRobot{
	
	AxisCamera frontCam = new AxisCamera("169.254.61.81");
	public static AxisCamera.Resolution k320x240;
	
	
	
	
	enum States{
		FREE,
		RESET,
		PULLING
	}
	
	static class buttons{
		//digital
		static int square = 1,
			x = 2,
			circle = 3,
			triangle = 4,
			L1 = 5,
			R1 = 6,
			L2 = 7,
			R2 = 8,
			share = 9,
			options = 10,
			L3 = 11,
			R3 = 12,
			ps = 13,
			touchpad = 14,
			//analog
			leftX = 0,
			leftY = 1,
			rightX = 2,
			L2Analog = 3,
			R2Analog = 4,
			rightY = 5;
	}
	
	States state;
	
	Victor leftFront;
	Victor rightFront;		
	Victor leftBack;
	Victor rightBack;
	SDS4Basic p4;
	SDS4Basic p42;
	Victor arm1;
	//AudioSystem bt;
	//Victor arm2;
	//Victor pickup;
	Victor ballPickup;
	Relay pick;
	Relay shoulder;
	Relay LED;
	DoubleSolenoid Gears;
	Compressor compressor;
	RobotDrive driveController;
	DigitalInput Limit;
	
	public IloveyouStephon(){
	
		state = States.FREE;
		/*
		 *  possible states
		 *  ---
		 *  free
		 *  reset
		 *  pullingBridge
		 */
		
		
		leftFront = new Victor(0);
		rightFront = new Victor(1);
		leftBack = new Victor(2);
		rightBack = new Victor(3);
		p4 = new SDS4Basic(0);
		p42 = new SDS4Basic(1);
		arm1 = new Victor(4);
	  	ballPickup = new Victor(6);
		//pickup = new Victor(6);
		pick = new Relay(1);
		shoulder = new Relay(0);
		LED = new Relay(2);
		Gears = new DoubleSolenoid(0,1);
		driveController = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
		Limit = new DigitalInput(0);
		
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		
		//pastController.put(p4, new HashMap());
		//pastController.put(p42, new HashMap());
		
		//pastController.get(p42).put(buttons.touchpad, false);
		
		//driveController.setInvertedMotor(,kRearLeft, true);
		//driveController.setInvertedMotor(MotorType.kFrontLeft, true);
	}
	
	public void driveControl(double leftSpeed, double rightSpeed){
		leftFront.set(rightSpeed);
		rightFront.set(-leftSpeed);
		leftBack.set(rightSpeed);
		rightBack.set(-leftSpeed);
	}
	
//	public void ClawArmThing(double Right){
//		arm2.set(Right*0.25);
//	}
//	
//	public void clawArmThing(double rightSpeed){
//		arm2.set(rightSpeed*-.25);
//	}
//	public void LEDsON(boolean Right){
//		LED.equals(Relay.Value.kForward);
//	}
//	
//	public void LEDsOFF(boolean Right){
//		LED.equals(Relay.Value.kReverse);
//	}
//	
	int counter = 0;
	boolean counting = false;
	void shoulderReset2(){
		if(p4.getRawButton(buttons.L1)){
			shoulder.set(Relay.Value.kReverse);
		}
		if(Limit.get()){
			shoulder.set(Relay.Value.kOff);
		}
	}
	void straightUp(){
		if(p4.getRawButton(buttons.R1)){
		for(shoulder.set(Relay.Value.kForward);;){
			if(!Limit.get()){
				shoulder.set(Relay.Value.kForward);
				Timer.delay(0.05);
				shoulder.set(Relay.Value.kOff);
			}
		}
		}
	}
	
//	void shoulderReset(){
//		
//		if(counting){
//			
//		
//			if(counter < 50){
//				shoulder.set(Relay.Value.kReverse);
//			}
//			else{
//				shoulder.set(Relay.Value.kOff);
//				counter = 0;	
//				counting = false;
//				
//				}
//			
//			counter++;
//		}
//		
//		else{
//			shoulder.set(Relay.Value.kForward);
//		}
//		Timer.delay(0.001);
//		
//		if(Limit.get()){
//			counting = true;
//			
//		}
//		
//		
//	}
	
	int scount = 0;
	int stopper = 50;
	boolean scountingUp = false;
	public void shoulder90(){
		if(Limit.get()){
			
			if(scount < stopper){
				shoulder.set(Relay.Value.kReverse);
				scount++;
			}else{
				shoulder.set(Relay.Value.kOff);
				counter = 0;	
				scountingUp = false;
			}
			
		}
	}
	
	boolean scountingDown = false;
	public void shoulderReset(){
		if(!Limit.get()){
			shoulder.set(Relay.Value.kForward);
		}else{
			shoulder.set(Relay.Value.kOff);
			scountingDown = true;
		}
	}
	
//	int hook90counter = 0;
//	int hook90end = 1000;
//	boolean hook90r = false;
//	public void hook90(){
//		if(!Limit.get() && (!scountingUp || !scountingDown)){
//			
//			if(hook90counter < hook90end){
//				// timer is running
//				arm1.set(0.25);
//				hook90counter++;
//			}else{
//				// finished with timer
//				arm1.set(0);
//				hook90counter = 0;	
//				hook90r = false;
//			}
//			  
//		}
//	}
	
	
	boolean touchPressed = false;
	boolean gearState = false;
	public void freeControl(){
		
		driveController.tankDrive(p4.getRawAxis(buttons.leftY), p4.getRawAxis(buttons.rightY));
		
//		if(p4.getRawButton(buttons.square)){
//			shoulder.set(Relay.Value.kForward);
//		}else if(p4.getRawButton(buttons.x) && !Limit.get()){
//			shoulder.set(Relay.Value.kReverse);
//		}else{
//			shoulder.set(Relay.Value.kOff);
//		}
		
//		if(p42.getRawButton(buttons.L1)){
//			shoulderReset2();
//		}
//		else{
//			shoulder.set(Relay.Value.kOff);
//		}
//		
		
		// Limit.get() = shoulder is down
		// !Limit.get() = should is not down
		
		if(p42.getButtonClicked(buttons.x) && Limit.get()){
			scountingUp = true;
		}
		
		if(scountingUp){
			// step 1
			shoulder90();
		}
		if(p42.getButtonClicked(buttons.square) && !Limit.get()){
			scountingDown = true;
		}
		if(scountingDown){
			// step 2
			shoulderReset();
		}
		
//		if(p42.getButtonClicked(buttons.L1)){
//			hook90r = true;
//		}
//		if(hook90r){
//			hook90();
//		}
		
		if(p42.getButtonClicked(buttons.R1)){
		
		}
		
		
		
		if(p42.getRawButton(buttons.triangle)){
			//pickup.set(0.25);
			pick.set(Relay.Value.kForward);
		}
		else if(p42.getRawButton(buttons.circle)){
			//pickup.set(-0.25);
			pick.set(Relay.Value.kReverse);
		}
		else{
			//pickup.set(0);
			pick.set(Relay.Value.kOff);
		}
		if(p4.getRawButton(buttons.L1)){
			arm1.set(0.25);
		}
		else if(p4.getRawButton(buttons.R1)){
			arm1.set(-0.25);
		}
		else{
			arm1.set(0);
		}
		
		if(p42.getButtonClicked(buttons.touchpad)){
			state = States.PULLING;
		}
//		if(p4.getRawButton())
		
		if(normalize(p42.getRawAxis(buttons.L2Analog)) > 0){
			ballPickup.set(normalize(p42.getRawAxis(buttons.L2Analog)));
		}else if(normalize(p42.getRawAxis(buttons.R2Analog)) > 0){
			ballPickup.set(-1*normalize(p42.getRawAxis(buttons.R2Analog)));
		}else{
			ballPickup.set(0);
		}
		
		if(p4.getRawButton(buttons.touchpad) && !touchPressed && gearState){
			Gears.set(DoubleSolenoid.Value.kForward);
			gearState = false;
		}else if(p4.getRawButton(buttons.touchpad) && !touchPressed && !gearState){
			Gears.set(DoubleSolenoid.Value.kReverse);
			gearState = true;
		}
		
		touchPressed = p4.getRawButton(buttons.touchpad);
		
//		if(p42.getRawButton(buttons.L2Analog)){
//			arm1.set(.25);
//		}
//		else if(p42.getRawButton(buttons.R2Analog)){
//			arm1.set(-.25);
//		}else{
//			arm1.set(0);
//		}
	}
	public void PullingState(){
		arm1.set(0.25);		//
		Timer.delay(1);		//pulls arm back to 90
		arm1.set(0);		//
		driveController.tankDrive(.25, .25);	//
		Timer.delay(0.25);						//pulls bot forward
		driveController.tankDrive(0, 0);		//
		shoulder.set(Relay.Value.kReverse);			//
		Timer.delay(0.25);							//pulls shoulder back
		shoulder.set(Relay.Value.kOff);				//
		driveController.tankDrive(.25, .25);	//
		Timer.delay(.25);						//
		driveController.tankDrive(0, 0);		//
		shoulder.set(Relay.Value.kForward);		
		Timer.delay(.25);
		shoulder.set(Relay.Value.kOff);
		arm1.set(0);
		
		
		
		
		
		
		
		
	}
	public void pickup(double Right){
		//pickup.set(Right);
	}


	
	//	public void armyForward(double rightSpeed){
//		arm1.set(rightSpeed*.25);
//	}
//
//	public void armyBack(double rightSpeed){
//		arm1.set(rightSpeed*-.25);
//	}
	public double normalize(double Right){
		double V = (Right * 0.5) + 0.5;
		return V;
	}

	public void operatorControl(){
		while (isOperatorControl() && isEnabled()){
	
			
			
			if(state == States.FREE){
				// when robot is freely controlled
				freeControl();
				
			}else if(state == States.PULLING){
				
				PullingState();
				
			}else if(state == States.RESET){
				//shoulderReset();
			}
			
			
			
			
			
			
			// LED Control
			if(p42.getRawButton(1)){
				LED.set(Relay.Value.kForward);
			}
			else if(p42.getRawButton(2)){
				LED.set(Relay.Value.kReverse);
			}
			else{	
				LED.set(Relay.Value.kOff);
			}
			
			
			p42.updateButtonsPast(buttons.x);
			p42.updateButtonsPast(buttons.square);
			p42.updateButtonsPast(buttons.L1);
			p42.updateButtonsPast(buttons.touchpad);
			
			
			Timer.delay(0.001);
			//pastController.get(p42).put(buttons.touchpad, p42.getRawButton(buttons.touchpad));
			
		}
	}
}

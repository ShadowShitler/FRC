package org.usfirst.frc.team369.robot;


import javax.sound.sampled.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class kingArthur extends SampleRobot{
	
	AxisCamera frontCam = new AxisCamera("169.254.61.81");
	public static AxisCamera.Resolution k320x240;
	
	Victor leftFront;
	Victor rightFront;		
	Victor leftBack;
	Victor rightBack;
	Joystick p4;
	Talon shooter;
	AudioSystem bt;
	DoubleSolenoid pickup;
	DoubleSolenoid shifter;
	DoubleSolenoid dunnoyet;
	
	public kingArthur(){
	
		leftFront = new Victor(0);
		rightFront = new Victor(1);
		leftBack = new Victor(2);
		rightBack = new Victor(3);
		p4 = new Joystick(0);
		shooter = new Talon(5);
//		bt = new AudioSystem();
		pickup = new DoubleSolenoid(0,1);
		shifter = new DoubleSolenoid(2,3);
		dunnoyet = new DoubleSolenoid(4,5);
		
	}
	
//	public class AudioSystem extends SampleRobot{
//		getLine(javax.sound.sampled.Line.Info),getClip();
//	}
	
	public void driveControl(double leftSpeed, double rightSpeed){
		leftFront.set(-rightSpeed);
		rightFront.set(leftSpeed);
		leftBack.set(-rightSpeed);
		rightBack.set(leftSpeed);
	}
	
	public void shooter(double Right){
		shooter.set(Right);
	}
	
	public void grabby(double Right){
		shooter.set(Right*-1);
	}
	
	public void pickup(boolean Right){
		pickup.set(DoubleSolenoid.Value.kForward);
	}
	
	public void pickdown(boolean Right){
		pickup.set(DoubleSolenoid.Value.kReverse);
	}

	public void shifter(boolean Right){
		shifter.set(DoubleSolenoid.Value.kForward);
	}

	public void reverseShifter(boolean Right){
		shifter.set(DoubleSolenoid.Value.kReverse);
	}

	public void dunnoyet(boolean Right){
		dunnoyet.set(DoubleSolenoid.Value.kForward);
	}
	
	public void duyesyet(boolean Right){
		dunnoyet.set(DoubleSolenoid.Value.kReverse);
	}

	public double normalize(double Right){
		double V = (Right * 0.5) + 0.5;
		return V;
	}

	public void operatorControl(){
		while (isOperatorControl() && isEnabled()){
			
			double lefty = p4.getRawAxis(5);
			//Sr1.set(y)
			
			double righty = p4.getRawAxis(1);
			driveControl(lefty,righty);
			
			double L2 = p4.getRawAxis(3);
			//grabs the boulder(L2);
			
			boolean L1 = p4.getRawButton(6);
			//raise(L1);
			
			boolean r1 = p4.getRawButton(5);
			//lower
			
			double r2 = p4.getRawAxis(4);
			//shoots boulder(R2);
			
			boolean square = p4.getRawButton(1);
			//Solenoid arms close
			
			boolean circle = p4.getRawButton(3);
			//Solenoid arms open
			
			boolean triangle = p4.getRawButton(4);
			//Solenoid claws close
			
			boolean x = p4.getRawButton(2);
			//Solenoid claws open
			
		
			if(p4.getRawButton(7)){
				grabby( normalize(L2) );
			}
			else if(p4.getRawButton(8)){
				shooter( normalize(r2) );
			}
			else{
				grabby(0);
			}
			if(square){
				pickup(true);
			}
			
			else if(circle){
				pickdown(true);
			}
		
			else{
				pickup.set(DoubleSolenoid.Value.kOff);
			}
			
			if(triangle){
				shifter(true);
			}
			else if(x){
				reverseShifter(true);
			}
			else{
				shifter.set(DoubleSolenoid.Value.kOff);
			}
			if(r1){
				if(x){
					dunnoyet(true);
				}
			}
			else if(L1){
				if(x){
					duyesyet(true);
			}
			}
			else{
				dunnoyet.set(DoubleSolenoid.Value.kOff);
			}
		}
	}
}
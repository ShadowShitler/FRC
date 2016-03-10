//package org.usfirst.frc369.HighVoltage;
//
//
//import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.CounterBase.EncodingType;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
//import edu.wpi.first.wpilibj.vision.AxisCamera;
//import edu.wpi.first.wpilibj.interfaces.*;
////import edu.wpi.first.wpilibj.Gyro;
//import edu.wpi.first.wpilibj.RobotDrive;
//public class Robot extends SampleRobot{
//	
//	AxisCamera frontCam = new AxisCamera("169.254.61.81");
//	public static AxisCamera.Resolution k320x240;
//	
//	Victor leftFront;
//	Victor rightFront;		
//	Victor leftBack;
//	Victor rightBack;
//	Joystick p4;
//	Joystick p42;
//	Victor arm1;
//	Victor arm2;
//	//Victor pickup;
//	Encoder enc;
//	DigitalInput elbow;
//	DigitalInput shoulder;
//	DigitalInput elbow2;
//	DigitalInput shoulder2;
//	Gyro hi;
//	Relay pickup;
//	
//	public Robot(){
//	
//		leftFront = new Victor(0);
//		rightFront = new Victor(1);
//		leftBack = new Victor(2);
//		rightBack = new Victor(3);
//		p4 = new Joystick(0);
//		arm1 = new Victor(4);
//		arm2 = new Victor(5);
//		pickup = new Relay(0);
//		enc = new Encoder(0,1, false, Encoder.EncodingType.k4X);
//		elbow = new DigitalInput(2);
//		shoulder = new DigitalInput(3);
//		elbow2 = new DigitalInput(4);
//		shoulder2 = new DigitalInput(5);
//
//	}
//	
//
//	
//	public void driveControl(double leftSpeed, double rightSpeed){
//		leftFront.set(rightSpeed);
//		rightFront.set(-leftSpeed);
//		leftBack.set(rightSpeed);
//		rightBack.set(-leftSpeed);
//	}
//	
//	public void clawUp(double Right){
//		arm2.set(Right*0.25);
//	}
//	
//	public void clawDown(double rightSpeed){
//		arm2.set(rightSpeed*-.25);
//	}
//	
//	public void pickup(boolean Right){
//		pickup(Right);
//	}
//
//	public void pickdown(boolean Left){
//		pickup(Left);
//	}
//	public void armyForward(double rightSpeed){
//		arm1.set(rightSpeed*.25);
//	}
//
//	public void position1(boolean Right){
//		if(elbow.getAnalogTriggerForRouting()){
//			if(shoulder.getAnalogTriggerForRouting()){
//				arm1.set(0);
//				arm2.set(0);
//				Timer.delay(0.25);
//				arm2.set(1);
//				Timer.delay(2);
//				arm2.set(0);
//			}
//		}
//	}
//	public void position2(boolean Right){
//		arm1.set(-1);
//		arm2.set(-1);
//		Timer.delay(1);
//		arm1.set(0);
//		arm2.set(0);
//	}
//	public void armyBack(double rightSpeed){
//		arm1.set(rightSpeed*-.25);
//	}
//	public double normalize(double Right){
//		double V = (Right * 0.5) + 0.5;
//		return V;
//	}
//
//	public void operatorControl(){
//		while (isOperatorControl() && isEnabled()){
//			
//			double lefty = p4.getRawAxis(5);
//			//Sr1.set(y)
//			
//			double righty = p4.getRawAxis(1);
//			driveControl(lefty,righty);
//			
//			double L2 = p4.getRawAxis(3);
//			//grabs the boulder(L2);
//			
//			boolean L1 = p4.getRawButton(6);
//			//raise(L1);
//			
//			boolean r1 = p4.getRawButton(5);
//			//lower
//			
//			double r2 = p4.getRawAxis(4);
//			//shoots boulder(R2);
//			
//			boolean square = p4.getRawButton(1);
//			//Solenoid arms close
//			
//			boolean circle = p4.getRawButton(3);
//			//Solenoid arms open
//			
//			boolean triangle = p4.getRawButton(4);
//			//Solenoid claws close
//			
//			boolean x = p4.getRawButton(2);
//			//Solenoid claws open
//			
//		
//			if(p4.getRawButton(7)){
//				clawUp( normalize(L2) );
//			}
//			else if(p4.getRawButton(8)){
//				clawDown( normalize(r2) );
//			}
//			else{
//				clawUp(0);
//			
//			}
//			
//			if(r1){
//				clawUp(1);
//			}
//			else if(L1){
//				clawDown(1);
//			}
//			else{
//				arm1.set(0);
//			}
//			if(triangle){
//				pickup(true);
//			}
//			else if(x){
//				pickdown(true);
//			}
//			else{
//				//pickup.set(0);
//			}
//			if(square){
//				position1(true);
//				Timer.delay(0.25);
//				position2(true);
////			}
////		
////			else if(circle){
////				pickdown(true);
////			}
////		
////			else{
////				pickup.set(DoubleSolenoid.Value.kOff);
////			}
////			
////			if(triangle){
////				shifter(true);
////			}
////			else if(x){
////				reverseShifter(true);
////			}
////			else{
////				shifter.set(DoubleSolenoid.Value.kOff);
////			}
////			if(r1){
////				if(x){
////					dunnoyet(true);
////				}
////			}
////			else if(L1){
////				if(x){
////					duyesyet(true);
////			}
////			}
////			else{
////				dunnoyet.set(DoubleSolenoid.Value.kOff);
////			}
//		}
//	} 
// }
//}
package org.usfirst.frc369.HighVoltage;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;

class Autonomous{
	static RobotDrive train;
	static void run1(){
			train.tankDrive(-1,-1);
			Timer.delay(2.125);
			train.tankDrive(0,0);
			Timer.delay(0.25);
			train.tankDrive(1,-1);
			Timer.delay(0.25);
			train.tankDrive(-1,-1);
			Timer.delay(0.15);
			train.tankDrive(0,0);
		}
	}

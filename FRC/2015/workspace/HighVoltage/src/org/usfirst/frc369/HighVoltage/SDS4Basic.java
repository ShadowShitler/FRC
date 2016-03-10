package org.usfirst.frc369.HighVoltage;

import java.util.HashMap;
import edu.wpi.first.wpilibj.Joystick;

public class SDS4Basic extends Joystick {
	private HashMap<Integer, Boolean> past = new HashMap<Integer, Boolean>();
	
	public SDS4Basic(int port){
	super(port);
	}

	public void updateButtonsPast(int button){
		past.put(button, this.getRawButton(button));
	}
	
	public boolean getButtonClicked(int button){
		if(this.getRawButton(button) && !past.get(button)){
			return true;
		}
		return false;
	}
}

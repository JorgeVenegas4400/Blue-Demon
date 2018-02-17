package org.usfirst.frc.team4400.robot.subsystems;

import org.usfirst.frc.team4400.robot.RobotMap;
import org.usfirst.frc.team4400.robot.commands.box.BoxDefault;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Box extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private DoubleSolenoid box;
	private String estado;
	public Box(){
		super();
		box = new DoubleSolenoid(RobotMap.kdoublesolenoidbox2, RobotMap.kdoublesolenoidbox2);		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BoxDefault());
    }
    
    public void Torque(){
    	box.set(DoubleSolenoid.Value.kForward);
    	estado = "Torque";
    }
    
    public void Velocidad(){
    	box.set(DoubleSolenoid.Value.kReverse);
    	estado = "Velocidad";
    }
    
    public void Default(){
    	this.Velocidad();
    }
    
    public String getBoxStatus(){
    	return estado;
    }
    
   /* public void ShiftBox(boolean value){
    	value = status;
    	if(value == true){
    		status = false;
    	}
    	else if(value == false){
    		status = true;
    	}
    }
    
    public boolean isBoxShifted(){
    	return status;
    }
*/
}


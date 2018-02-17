package org.usfirst.frc.team4400.robot.subsystems;

import org.usfirst.frc.team4400.robot.RobotMap;
import org.usfirst.frc.team4400.robot.commands.elevator.ElevatorDefault;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private WPI_TalonSRX elevator;
	private DigitalInput LimitUp, LimitDown;
	public Elevator(){
		super();
		elevator = new WPI_TalonSRX(RobotMap.Kelevatormotor);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevatorDefault());
    }
    
    public void ElevatorUp(){
    	if(LimitUp.get() == false){
    		elevator.set(SmartDashboard.getNumber("Potencia Motores Elevador", 0.75));
    	}
    	if(LimitUp.get() == true){
    		this.Default();
    	}
    }
    
    public void ElevatorDown(){
    	if(LimitDown.get() == false){
    		elevator.set(SmartDashboard.getNumber("Potencia Motores Elevador", 0.75)*-1);
    	}
    	if(LimitDown.get() == true){
    		this.Default();
    	}
    }
    
    public void Default(){
    	elevator.set(0);
    }
}


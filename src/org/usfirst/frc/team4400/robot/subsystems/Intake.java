package org.usfirst.frc.team4400.robot.subsystems;

import org.usfirst.frc.team4400.robot.RobotMap;
import org.usfirst.frc.team4400.robot.commands.intake.IntakeDefault;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private WPI_TalonSRX leftMotor, rightMotor;
	private SpeedControllerGroup intake;
	private DoubleSolenoid intakeSolenoid;
	public Intake(){
		super();
		leftMotor = new WPI_TalonSRX(RobotMap.Kintakeleftmotor);
		rightMotor = new WPI_TalonSRX(RobotMap.Kintakerightmotor);
		
		intake =  new SpeedControllerGroup(leftMotor, rightMotor);
		
		leftMotor.setInverted(false);
		rightMotor.setInverted(!leftMotor.getInverted());
		
		intakeSolenoid = new DoubleSolenoid(RobotMap.kdoublesolenoidintake1, RobotMap.kdoublesolenoidintake2);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new IntakeDefault());
    }
    
    public void PickBox(){
    	intake.set(SmartDashboard.getNumber("Potenncia motores Intake", 1));
    }
    
    public void PutBox(){
    	intake.set(SmartDashboard.getNumber("Potenncia motores Intake", 1)*-1);
    }
    
    public void OpenClaw(){
    	intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void CloseClaw(){
    	intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void Default(){
    	intake.set(0);
    	this.CloseClaw();
    }
}


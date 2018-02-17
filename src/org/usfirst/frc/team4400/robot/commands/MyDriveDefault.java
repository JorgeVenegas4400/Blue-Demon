package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;
import org.usfirst.frc.team4400.robot.subsystems.MyDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MyDriveDefault extends Command {

	MyDrive drive;
	Joystick Joy1;
    public MyDriveDefault() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        drive = Robot.drive;
        Joy1 = Robot.oi.getJoyestick(0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.setMaxOutput(SmartDashboard.getNumber("Potencia Motores", 0.75) - Joy1.getRawAxis(2) *0.25 + Joy1.getRawAxis(3));
    	if(drive.getType() == "arcade"){
    		drive.unitArcadeDrive(Joy1.getRawAxis(1), Joy1.getRawAxis(4), SmartDashboard.getNumber("Tolerancia Joysticks", 0.1));
    	}
    	else if(drive.getType() == "tank"){
    		drive.unitTankDrive(Joy1.getRawAxis(1), Joy1.getRawAxis(5), SmartDashboard.getNumber("Tolerancia Joysticks", 0.1));
    	}
    	else {
    		drive.unitArcadeDrive(Joy1.getRawAxis(1), Joy1.getRawAxis(4), SmartDashboard.getNumber("Tolerancia Joysticks", 0.1));
    	}
    	SmartDashboard.putString("Box Status", Robot.box.getBoxStatus());
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

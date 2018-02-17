package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;
import org.usfirst.frc.team4400.robot.subsystems.MyDrive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InvertAxis extends Command {

	MyDrive drive;
    public InvertAxis() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        drive = Robot.drive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(drive.getType() == "arcade"){
    		drive.setInvertedAxisArcade(true, true);
    	}
    	if(drive.getType() == "tank"){
    		drive.setInvertedAxisTank(true);
    	}
    	
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

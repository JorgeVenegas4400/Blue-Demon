package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftBox extends InstantCommand {

    public ShiftBox() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.box);
    }

    // Called once when the command executes
    protected void initialize() {
	    if(Robot.box.getBoxStatus() == "Torque"){
			Robot.box.Velocidad();
		}
		else if (Robot.box.getBoxStatus() == "Velocidad"){
			Robot.box.Torque();
		}
    }
}


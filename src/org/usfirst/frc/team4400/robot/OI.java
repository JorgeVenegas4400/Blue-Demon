/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4400.robot;

import org.usfirst.frc.team4400.robot.commands.InvertAxis;
import org.usfirst.frc.team4400.robot.commands.ShiftBox;
import org.usfirst.frc.team4400.robot.commands.elevator.ElevatorDown;
import org.usfirst.frc.team4400.robot.commands.elevator.ElevatorUp;
import org.usfirst.frc.team4400.robot.commands.intake.CloseClaw;
import org.usfirst.frc.team4400.robot.commands.intake.OpenClaw;
import org.usfirst.frc.team4400.robot.commands.intake.PickBox;
import org.usfirst.frc.team4400.robot.commands.intake.PutBox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
		Joystick Joystick1 = new Joystick(0);
		Joystick  Joystick2 = new Joystick(1);
		Button a = new JoystickButton(Joystick1, 1);
		Button b = new JoystickButton(Joystick1, 2);
		Button x = new JoystickButton(Joystick1, 3);
		Button y = new JoystickButton(Joystick1, 4);
		Button lb = new JoystickButton(Joystick1, 5);
		Button rb = new JoystickButton(Joystick1, 6);
		Button izq = new JoystickButton(Joystick1, 7);
		Button der = new JoystickButton(Joystick1, 8);
		Button joyd = new JoystickButton(Joystick1, 9);
		Button joyi = new JoystickButton(Joystick1, 10);
		public OI(){
			a.whileHeld(new ElevatorDown());
			b.whileHeld(new PutBox());
			x.whileHeld(new PickBox());
			y.whileHeld(new ElevatorUp());
			lb.whenPressed(new OpenClaw());
			rb.whenPressed(new CloseClaw());
			izq.whenReleased(new InvertAxis());
			der.whenReleased(new ShiftBox());
		}
		public Joystick getJoystick(int i){
			return i == 0 ? Joystick1 : i == 1 ? Joystick2 : null;
		}
}

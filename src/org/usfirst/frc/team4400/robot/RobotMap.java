/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4400.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static final byte Kfrontleftmotor = 3;
	public static final byte Kfrontrightmotor = 4;
	public static final byte Krearleftmotor = 5;
	public static final byte Krearrightmotor = 6;
	public static final byte Kmidleftmotor = 7;
	public static final byte Kmidrightmotor = 8;
	
	public static final byte Kintakerightmotor = 9;
	public static final byte Kintakeleftmotor = 10;
	
	public static final byte Kelevatormotor = 1;
	
	public static final byte kdoublesolenoidbox1 = 1;
	public static final byte kdoublesolenoidbox2 = 2;
	
	public static final byte kdoublesolenoidintake1 = 3;
	public static final byte kdoublesolenoidintake2 = 4;
}

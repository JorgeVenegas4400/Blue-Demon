package org.usfirst.frc.team4400.robot.subsystems;

import org.usfirst.frc.team4400.robot.RobotMap;
import org.usfirst.frc.team4400.robot.commands.MyDriveDefault;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MyDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private WPI_TalonSRX leftFront, leftMid, leftRear, rightFront, rightMid, rightRear;
	private SpeedControllerGroup leftGroup, rightGroup;
	private AHRS navx;
	private Encoder coder;
	public static final double kMaxGain = 0.05, kPulsesPerTurn = 40, kDiameter = 4*2.54, kDistancePerPulse = kPulsesPerTurn / kDiameter; 
	private double maxOutput = 1;
	private double lastTurnPower = 0, lastPower = 0; 
	private double lastLeftPower = 0, lastRightPower = 0; 
	private boolean InvertedYAxis = false, InvertedXAxis = false;
	private boolean AxisInverted = false;
	private String type = SmartDashboard.getString("Tipo Drive", "Arcade");
	
	public MyDrive(){
		super();
		leftFront = new WPI_TalonSRX(RobotMap.Kfrontleftmotor);
		leftMid = new WPI_TalonSRX(RobotMap.Kmidleftmotor);
		leftRear = new WPI_TalonSRX(RobotMap.Krearleftmotor);
		rightFront = new WPI_TalonSRX(RobotMap.Kfrontrightmotor);
		rightMid = new WPI_TalonSRX(RobotMap.Kmidrightmotor);
		rightRear = new WPI_TalonSRX(RobotMap.Krearrightmotor);
	
		leftFront.setInverted(false);
		leftMid.setInverted(!leftFront.getInverted());
		leftRear.setInverted(leftFront.getInverted());
		rightFront.setInverted(!leftFront.getInverted());
		rightMid.setInverted(leftFront.getInverted());
		rightRear.setInverted(!leftFront.getInverted());
		
		leftGroup = new SpeedControllerGroup(leftFront, leftMid, leftRear);
		rightGroup = new SpeedControllerGroup(rightFront, rightMid, rightRear);
		
		navx = new AHRS(SerialPort.Port.kUSB1);
		navx.reset();
		navx.enableLogging(true);
		
		coder = new Encoder(RobotMap.kencoderchannelA, RobotMap.kencoderchannelB);
		coder.reset();
		coder.setDistancePerPulse(kDistancePerPulse);
		
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MyDriveDefault());
    }
    
    public double getNavxAngle(){
    	return navx.getAngle();
    }
    
	public Encoder getCoder() {
		return coder;
	}
	
	public double getDistance(){
		return coder.getDistance();
	}
	
	public void resetCoder(){
		coder.reset();
	}
	
	public void resetNavx(){
		navx.reset();
	}
	
	public String getType(){
		return type;
    }
    
    public void setInvertedAxisArcade(boolean YAxis, boolean XAxis){
    	InvertedYAxis = YAxis;
    	InvertedXAxis = XAxis;
    }
    
    public void setInvertedAxisTank(boolean InvertedAxis) {
    	AxisInverted = InvertedAxis;
    }
    
    public boolean IsYAxisInverted(){
    	return InvertedYAxis;
    }
    
    public boolean IsXAxisInverted(){
    	return InvertedXAxis;
    }
    
    public boolean AreAxesInverted(){
    	return AxisInverted;
    }
    
    public void setMaxOutput(double MaxOut){
    	maxOutput = MaxOut;
    }
    
    public void setLeft(double power){
    	if(power>1)
    		power=1;
    	if(power<-1)
    		power=-1;
    	rightGroup.set(power*maxOutput);
    }
    
    public void setRight(double power){
    	if(power>1)
    		power=1;
    	if(power<-1)
    		power=-1;
    	leftGroup.set(power*maxOutput);
    }
    
    /**
     * Simple algorithm for an arcade drive
     * @param movePower Usually an Y Axis
     * @param rotateValue Usually an X Axis
     */
    public void arcadeDrive(double movePower, double rotatePower){
    	setLeft(movePower + rotatePower);
    	setRight(movePower - rotatePower);
    	lastTurnPower = rotatePower;
    	lastPower = movePower;
    }
    
    /**
     * Simple algorithm for a tank drive
     * @param left Usually an Y Axis
     * @param right Usually an X Axis
     */
    public void tankDrive(double left, double right){
    	setLeft(left);
    	setRight(right);
    	lastLeftPower = left;
    	lastRightPower = right;
    }
    
	public void unitArcadeDrive(double movePower, double turnPower, double deadzone) {
		unitArcadeDrive(Math.abs(movePower)> deadzone ? movePower : 0, Math.abs(turnPower)> deadzone ? turnPower: 0);
	}
	
	public void unitArcadeDrive(double movePower, double turnPower) {
		movePower *= InvertedYAxis ? -1 : 1;
		if(Math.abs(turnPower - lastTurnPower)> kMaxGain)
			turnPower = turnPower >= lastTurnPower ? lastTurnPower + kMaxGain : lastTurnPower - kMaxGain;
		if(Math.abs(movePower - lastPower)> kMaxGain)
			movePower = movePower >= lastPower ? lastPower +kMaxGain : lastPower - kMaxGain;
		arcadeDrive(movePower, turnPower);
	}

	public void unitTankDrive(double leftPower, double rightPower, double deadzone) {
		unitTankDrive(Math.abs(leftPower)>deadzone ? leftPower : 0, Math.abs(rightPower)>deadzone ? rightPower : 0);
	}

	public void unitTankDrive(double leftPower, double rightPower) {
		leftPower *= AxisInverted ? -1 :1;
		rightPower *= AxisInverted ? -1 :1;
		if(Math.abs(leftPower - lastLeftPower)>kMaxGain)
			leftPower = leftPower >= lastLeftPower ? lastLeftPower + kMaxGain : lastLeftPower - kMaxGain;
		if(Math.abs(rightPower-lastRightPower)>kMaxGain)
			rightPower = rightPower >= lastRightPower ? lastRightPower + kMaxGain : lastRightPower - kMaxGain;
	}    
}


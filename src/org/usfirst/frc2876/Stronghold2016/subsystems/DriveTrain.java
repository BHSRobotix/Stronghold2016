// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2876.Stronghold2016.subsystems;


import org.usfirst.frc2876.Stronghold2016.Robot;
import org.usfirst.frc2876.Stronghold2016.RobotMap;
import org.usfirst.frc2876.Stronghold2016.commands.*;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Encoder.IndexingType;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon frontRightTalon = RobotMap.driveTrainFrontRightTalon;
    private final CANTalon rearRightTalon = RobotMap.driveTrainRearRightTalon;
    private final CANTalon frontLeftTalon = RobotMap.driveTrainFrontLeftTalon;
    private final CANTalon rearLeftTalon = RobotMap.driveTrainRearLeftTalon;
    private final Encoder leftEncoder = RobotMap.driveTrainLeftEncoder;
    private final Encoder rightEncoder = RobotMap.driveTrainRightEncoder;
    private final AnalogAccelerometer accel = RobotMap.driveTrainAccel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
//	public RobotDrive myRobot = new RobotDrive(frontLeftTalon, rearLeftTalon, frontRightTalon, rearRightTalon);

    
    public RobotDrive myRobot = new RobotDrive(frontLeftTalon, frontRightTalon);
	
	private boolean isTankDrive = false;
	
    public boolean getIsTankDrive() {
		return isTankDrive;
	}

	public void setIsTankDrive(boolean isTankDrive) {
		this.isTankDrive = isTankDrive;
	}
	
	public void toggleIsTankDrive(){
		this.isTankDrive = !this.isTankDrive;
	}
	
	
	private double sensitivity = .65;

	public double getSensitivity(){
		return sensitivity;
	}
	
	public void setSensitivity(double sensitivity){
		this.sensitivity = sensitivity;
	}
	
	
	public Encoder getLeftEncoder() {
		return leftEncoder;
	}
	
	
	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	public void initDefaultCommand() {
		
		rearLeftTalon.changeControlMode(TalonControlMode.Follower);
		rearLeftTalon.set(frontLeftTalon.getDeviceID());
		
		rearRightTalon.changeControlMode(TalonControlMode.Follower);
		rearRightTalon.set(frontRightTalon.getDeviceID());
		
		frontRightTalon.configEncoderCodesPerRev(100);
		frontLeftTalon.configEncoderCodesPerRev(100);
		
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveControl());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
	public void updateSmartDashboard(){
//		boolean sensorPluggedIn = (frontRightTalon.isSensorPresent(FeedbackDevice.QuadEncoder) == FeedbackDeviceStatus.FeedbackStatusPresent);
//		SmartDashboard.putBoolean("frencoder isSensorPresent ", sensorPluggedIn);
		SmartDashboard.putData("Left Encoder Distance", getLeftEncoder());
    	SmartDashboard.putData("Right Encoder Distance", getRightEncoder());
    	SmartDashboard.putNumber("srx encoder r", frontRightTalon.getEncPosition());
    	SmartDashboard.putNumber("srx encoder l", frontLeftTalon.getEncPosition());
    	SmartDashboard.putNumber("X Accel NavX", Robot.navX.getRawAccelX());
    	SmartDashboard.putNumber("Y Accel NavX", Robot.navX.getRawAccelY());
    	SmartDashboard.putNumber("X Gyro NavX", Robot.navX.getRawGyroX());
    	SmartDashboard.putNumber("Accel RoboRio", accel.getAcceleration());

	}
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public void initializeDistancePerPulse() {
//		double wheelDiameter = 8;
//		double pulsePerRevolution = 2048;
//		double gearRatio = 10.71;
//		rightEncoder.setDistancePerPulse(Math.PI * wheelDiameter / pulsePerRevolution / gearRatio);
//		leftEncoder.setDistancePerPulse(Math.PI * wheelDiameter / pulsePerRevolution / gearRatio);
	}
}


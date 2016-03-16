// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2876.Stronghold2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2876.Stronghold2016.commands.*;
import org.usfirst.frc2876.Stronghold2016.subsystems.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Arm arm;
    public static Intake intake;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static AHRS navX;
    
    
    public static final boolean IS_PRACTICE_BOT = true;
    
    SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
        navX = new AHRS(SPI.Port.kMXP);
    	

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        arm = new Arm();
        intake = new Intake();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain.initializeCamera();        
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        setupAuto();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	driveTrain.setBrakeMode(true);
        // schedule the autonomous command (example)S
    	autonomousCommand = (Command) autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	arm.updateSmartDashboard();
    	driveTrain.updateSmartDashboard();
    	
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	driveTrain.setBrakeMode(false);
        if (autonomousCommand != null) autonomousCommand.cancel();
        driveTrain.resetEncoders();
        Command pollXBox = new PollXBox();
        pollXBox.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	arm.updateSmartDashboard();
    	driveTrain.updateSmartDashboard();
    	intake.updateSmartDashboard();
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	arm.updateSmartDashboard();
    	driveTrain.updateSmartDashboard();
    	intake.updateSmartDashboard();
        LiveWindow.run();
    }
    
    public void setupAuto(){
    	autoChooser = new SendableChooser();
    	
    	autoChooser.addDefault("Cross Low Bar", new AutoCGCrossLowBar());
    	autoChooser.addObject("Cross Rough Terrain", new AutoCGCrossRoughTerrain());
    	autoChooser.addObject("Cross Moat", new AutoCGCrossMoat());
    	autoChooser.addObject("Cross Ramparts", new AutoCGCrossRamparts());
    	autoChooser.addObject("Cross Rock Wall", new AutoCGCrossRockWall());
    	autoChooser.addObject("Do Nothing", new AutoDoNothing());
    	
    	SmartDashboard.putData("Auto Choice", autoChooser);

    }
}

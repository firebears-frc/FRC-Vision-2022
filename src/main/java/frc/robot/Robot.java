// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.XboxController;
=======
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
>>>>>>> parent of e535186 (Updated Driver Controller)
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveController;
import frc.robot.subsystems.Vision;

import java.io.Console;

import edu.wpi.first.math.controller.PIDController;

//

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
<<<<<<< HEAD
  XboxController stick = new XboxController(0);
=======
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  Joystick stick = new Joystick(0);
>>>>>>> parent of e535186 (Updated Driver Controller)

  /* OLD Robot ID's
  final int frontLeftID = 4;
  final int rearLeftID = 5;
  final int frontRightID = 3;
  final int rearRightID = 2;
  */

  final int frontLeftID = 2;
  final int rearLeftID = 3;
  final int frontRightID = 5;
  final int rearRightID = 4;

  final double camHeight = Units.inchesToMeters(17);
  final double targetHeight = Units.inchesToMeters(48);

<<<<<<< HEAD
  final double camHeight = Units.inchesToMeters(19);
  final double targetHeight = Units.inchesToMeters(19);

  private DriveController dc = new DriveController(frontLeftID,frontRightID,rearLeftID,rearRightID);
  private Vision vs = new Vision("gloworm");

  private PIDController pid = new PIDController(0.75, 0, 0);
=======
  final double ANGULAR_P = 0.1;
  final double ANGULAR_D = 0.0;
  PIDController turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);

  private DriveController dc = new DriveController(frontLeftID,frontRightID,rearLeftID,rearRightID);
  private Vision vs = new Vision("gloworm",camHeight,targetHeight,0);
>>>>>>> parent of e535186 (Updated Driver Controller)

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
<<<<<<< HEAD
    pid.setTolerance(0.15);
=======
    m_robotContainer = new RobotContainer();
>>>>>>> parent of e535186 (Updated Driver Controller)
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    dc.Break(false);
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
<<<<<<< HEAD
    dc.Break(false);
=======
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
>>>>>>> parent of e535186 (Updated Driver Controller)
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //System.out.println(vs.getTargetDistence() + " out of " + vs.getTargetYaw());
    //dc.setDriveV(-vs.getTargetDistence(),vs.getTargetYaw(),0.75);
  }

  @Override
  public void teleopInit() {
    dc.Break(true);
  }


  double lastYaw = 0;
  double lastSpeed = 0;
  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
<<<<<<< HEAD
    // give the yaw position of the robot
    vs.getRobotYaw(0);
    dc.setDrive(0,-0.75);
=======
    double speed = Math.min(0.05 * vs.getTargetDistence(), 0.1);
    // Calculate angular turn power
    // -1.0 required to ensure positive PID controller effort _increases_ yaw
    double rotation = -turnController.calculate(vs.getTargetYaw(),0);

    SmartDashboard.putNumber("VisionTarget_Distence", vs.getTargetDistence());

    // set speed faster or slower based on joysticks Left Stick's Position
    speed = stick.getY() * 0.25;
    System.out.printf("Speed : " + speed + " Distence:  " + vs.getTargetDistence());

    if(vs.getTargetDistence() <= 10){
      //speed = 0;
    }

    if(vs.getBestTarget() != null){
      dc.Drive(speed, vs.getTargetYaw());
    }
    else{
      dc.Drive(0,0);
    }
>>>>>>> parent of e535186 (Updated Driver Controller)
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}

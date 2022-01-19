// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.*;

import java.io.Console;

import javax.naming.spi.DirStateFactory.Result;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(new PWMSparkMax(0), new PWMSparkMax(1));
  private final Joystick m_stick = new Joystick(0);

  private final Timer m_timer = new Timer();
  private final CameraInfo c_Info = new CameraInfo();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  PhotonCamera pc = new PhotonCamera("gloworm");

  @Override
  public void robotInit() {

  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    findDistenceOfTarget();
  }

  // test function whenever XBOX [Button A] is pressed
  // check distence from camera and print to console how many [UNITS]
  public void findDistenceOfTarget(){
    var result = pc.getLatestResult();

    if(result.hasTargets()){
      //get the range
      double range = 
        PhotonUtils.calculateDistanceToTargetMeters(c_Info.CAMERA_HEIGHT_METERS, 
        c_Info.TARGET_HEIGHT_METERS, 
        c_Info.CAMERA_PITCH_RADIANS, 
        Units.degreesToRadians(result.getBestTarget().getPitch()));
      
      System.out.println("Target Distence Is About: " + range + "m");
    }
  }

  public void pickUpBall(){
    // find ball position
    // if cant find ball cancel all code
    // stop all movement
    // drive towards ball
    // use arms to 'pick up ball'

    //change pipeline to the 'ball pipeline'
    pc.setPipelineIndex(c_Info.BlueCamera);
    var result = pc.getLatestResult();

    if(result.hasTargets()){
      //get the range
      double range = 
        PhotonUtils.calculateDistanceToTargetMeters(c_Info.CAMERA_HEIGHT_METERS, 
        c_Info.TARGET_HEIGHT_METERS, 
        c_Info.CAMERA_PITCH_RADIANS, 
        Units.degreesToRadians(result.getBestTarget().getPitch()));

        var t = result.getBestTarget();

        double yaw = t.getYaw();
        
        //turn left and right if the yaw is not correct
        if(yaw != 0) TurnRobot(yaw);

        if(Math.abs(range) > 2.5f){
          pickUpBall();
        }
        else{
          //pick up the ball if and when its in range
          System.out.println("Able To Pick Up Ball");
        }
    }
  }

  private void TurnRobot(double yaw){
    // yaw > 0 = Turn Robot Right : Left Wheel -1 , Right Wheel +1
    // yaw < 0 = Turn Robot Left : Left Wheen +1 , Right Wheel -1

    m_robotDrive.arcadeDrive(2.5, yaw);
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    pickUpBall();
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  public class CameraInfo{
    // How high the robot's camera is at
    final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(24);
    // Avarage Height Of Target 
    final double TARGET_HEIGHT_METERS = Units.feetToMeters(5);
    // Angle between horizontal and the camera.
    final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);

    final int MainCamera = 0;
    final int BlueCamera = 1;
    final int RedCamera = 2;
  }

  
}
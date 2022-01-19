// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.util.Units;
//import edu.wpi.first.math.*;

//import java.io.Console;

//import javax.naming.spi.DirStateFactory.Result;

import org.photonvision.PhotonCamera;
//import org.photonvision.PhotonUtils;

//import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private TalonSRX srx = new TalonSRX(25);
  //private final CameraInfo c_Info = new CameraInfo();
  private PhotonCamera pc = new PhotonCamera("gloworm");

  @Override
  public void robotInit() {

  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // yaw > 0 = Turn Robot Right : Left Wheel -1 , Right Wheel +1
    // yaw < 0 = Turn Robot Left : Left Wheen +1 , Right Wheel -1
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    // check if target exists and turn robot
    var result = pc.getLatestResult();

    if(result.hasTargets()){
      // move wheel
      srx.set(TalonSRXControlMode.PercentOutput,0.1);
    }
    else{
      srx.set(TalonSRXControlMode.PercentOutput,0);
    }
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
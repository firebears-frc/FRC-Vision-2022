// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  Basic Subsystem Made For 2022 Robot Code
  Basic Driving And Turning Made For Robot Vision
*/

// Importing all the Packages
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// DriveController Class extending Subsystem to be called in main robotcode
public class DriveController extends SubsystemBase {
  // Creates a new DriveController.
  CANSparkMax frontL;
  CANSparkMax frontR;
  CANSparkMax backL;
  CANSparkMax backR;

  // differentialDrive to make it control 4 wheels simultaniusly
  private final DifferentialDrive robotDrive;

  public DriveController(int fl, int fr, int bl, int br) {
    frontL = new CANSparkMax(fl,MotorType.kBrushless);
    frontR = new CANSparkMax(fr,MotorType.kBrushless);
    backL = new CANSparkMax(bl,MotorType.kBrushless);
    backR = new CANSparkMax(br,MotorType.kBrushless);

    //make back wheels follow front wheels
    backL.follow(frontL);
    backR.follow(backR);

    // create a drifferentialDrive and give it our CANSparkMax's
    robotDrive = new DifferentialDrive(frontL, frontR);
  }

  public void Drive(double speed,double rotation){
    //Drive the robot using Speed & Rotation
    robotDrive.arcadeDrive(speed,rotation);
  }
}

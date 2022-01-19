// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveController extends SubsystemBase {
  /** Creates a new DriveController. */
  CANSparkMax frontL;
  CANSparkMax frontR;
  CANSparkMax backL;
  CANSparkMax backR;
  private final DifferentialDrive robotDrive;


  public DriveController(int fl, int fr, int bl, int br) {
    frontL = new CANSparkMax(fl,MotorType.kBrushless);
    frontR = new CANSparkMax(fr,MotorType.kBrushless);
    backL = new CANSparkMax(bl,MotorType.kBrushless);
    backR = new CANSparkMax(br,MotorType.kBrushless);

    backL.follow(frontL);
    backR.follow(backR);


    robotDrive = new DifferentialDrive(frontL, frontR);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Drive(double speed,double rotation){
    robotDrive.arcadeDrive(speed,rotation);
  }
}

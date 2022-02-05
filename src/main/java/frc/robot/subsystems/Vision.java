// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  SubSystem for Vision Code 2022
  Vision Will Return Best Target , Target Data , Ect
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.photonvision.*;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.math.util.Units;

public class Vision extends SubsystemBase {
  /** Creates a new Vision Subsystem. */

  // photon camera is used for 
  private PhotonCamera photoncam;
  PhotonTrackedTarget PhotonTarget;


  private double camHeight;
  private double targetHeight;
  private double camPitch;
  public Vision(String CameraName) {
    photoncam = new PhotonCamera(CameraName);
    // set the hight width and pitch (Y) of 
    camHeight = Units.inchesToMeters(30);
    targetHeight = Units.inchesToMeters(50);
    camPitch = Units.degreesToRadians(0);
  }

  // every update we get the best target for the Vision Subsystem
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    var result = photoncam.getLatestResult();

    if(result.hasTargets()){
      PhotonTarget = photoncam.getLatestResult().getBestTarget();
      updateTargetYaw();
    }
    else{
      PhotonTarget = null;
    }
  }

  /*      Functions That Return Values To Main.Java           */

  // return the best target [BIGGEST / BEST IN CENTER / ECT]
  public PhotonTrackedTarget getBestTarget(){
    return PhotonTarget;
  }

  // Returns Yaw (-1,1) : Edited For Wheels & Other
  // Driving Functionality
  public double getTargetYaw(){
    if(PhotonTarget != null){
      return PhotonTarget.getYaw()/30;
    }
    else{
      return 0;
    }
  }


  // return the area of the target
  // being larger is closer to the target
  public double getTargetArea(){
    if(PhotonTarget != null){
      return PhotonTarget.getArea();
    }
    else{
      return 0;
    }
  }


  // returns distences in meters
  // Currently Not Working
  public double getTargetDistence(){
    return PhotonUtils.calculateDistanceToTargetMeters(
            camHeight,
            targetHeight,
            camPitch,
            Units.degreesToRadians(PhotonTarget.getPitch()))/5;
  }

  double robotYaw;
  double targetYaw;

  public void getRobotYaw(double y){
    robotYaw = y;

  }

  void updateTargetYaw(){
    targetYaw = PhotonTarget.getYaw();
  }

  public double returnCircularYaw(){
    return targetYaw + robotYaw;
  }
}

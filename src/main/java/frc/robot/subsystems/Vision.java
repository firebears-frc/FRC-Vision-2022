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

  private PhotonCamera photoncam;
  PhotonTrackedTarget PhotonTarget;


  private double camHeight;
  private double targetHeight;
  private double camPitch;
  public Vision(String CameraName, int cH, int tH, int cP) {
    photoncam = new PhotonCamera(CameraName);
    camHeight = cH;
    targetHeight = tH;
    camPitch = cP;
  }

  // every update we get the best target for the Vision Subsystem
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    var result = photoncam.getLatestResult();

    if(result.hasTargets()){
      PhotonTarget = photoncam.getLatestResult().getBestTarget();
    }
    else{
      PhotonTarget = null;
    }
  }

  /*      Functions That Return Values To Main.Java           */

  public PhotonTrackedTarget getBestTarget(){
    return PhotonTarget;
  }

  // returns yaw
  public double getTargetYaw(){
    if(PhotonTarget != null){
      return PhotonTarget.getYaw();
    }
    else{
      return 0;
    }
  }


  // returns distences in meters
  public double getTargetDistence(){
    return PhotonUtils.calculateDistanceToTargetMeters(
            camHeight,
            targetHeight,
            camPitch,
            Units.degreesToRadians(PhotonTarget.getPitch()));
  }
}

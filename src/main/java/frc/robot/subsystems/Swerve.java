// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.hal.AllianceStationID;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Field;
import frc.team696.lib.Util;
import frc.team696.lib.Camera.LimeLightCam;
import frc.team696.lib.Swerve.SwerveDriveSubsystem;

public class Swerve extends SwerveDriveSubsystem {
  public final LimeLightCam shooterCam;
  public final LimeLightCam noteCam;
  private static Swerve swerve;
  public static Swerve get(){
    if (swerve==null) swerve=new Swerve();
    return swerve;
  }
  public Swerve() {
    shooterCam=new LimeLightCam("limelight-shooter");
    // don't do anything with this yet
    noteCam=new LimeLightCam("limelight-note");


  }
  public void onUpdate(){
      shooterCam.addVisionEstimate(this::addVisionMeasurement, (latestResult)->{
      // AprilTag reads while the robot is rotating quickly may be inaccurate
      if(latestResult.ambiguity>0.4||getState().angularVelocity()>2.5){
        return false;
      }
      if(latestResult.pose.getX()<0||latestResult.pose.getX()>8.3||latestResult.pose.getY()<0||latestResult.pose.getY()>16.3)
        return false;// robot is literally off the field
      double deviationRatio=(Math.pow((latestResult.distToTag),2)/12);
      shooterCam.setStdDeviations(deviationRatio/5, deviationRatio/5, deviationRatio/5);
      return true;
    });
  }
  public double distToSpeaker(){
    return distTo(Util.getAlliance()==Alliance.Red?Field.RED.Speaker:Field.BLUE.Speaker);
  }
  public Rotation2d angleToSpeaker(){
    return angleTo(Field.getSide().Speaker);
  }
  public double distToCorner(){
    return distTo(Field.getSide().Corner);
  }
  public Rotation2d angleToCorner(){
    return angleTo(Field.getSide().Corner);
  }
}

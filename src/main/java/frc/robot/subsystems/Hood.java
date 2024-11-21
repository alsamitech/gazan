// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.team696.lib.HardwareDevices.TalonFactory;
import com.ctre.phoenix6.controls.*;
public class Hood extends SubsystemBase implements Sendable {
  private static Hood hood;
  public static Hood get(){
    if(hood==null) hood=new Hood();
    return hood;
  }
  /** Creates a new Hood. */
  TalonFactory left, right;
  private PositionVoltage m_PositionReq;
  public Hood() {
    left=new TalonFactory(10, Constants.CANivoreName,Constants.configs.hood.left, "Left Hood");
    right=new TalonFactory(10, Constants.CANivoreName,Constants.configs.hood.right, "Left Right");
    right.Follow(left, true);
    left.setPosition(0);
  }
  /**
   * 
   * @return Angle of the Hood (in degrees)
   * @see frc.robot.Configs
   */
  public double getAngle(){
    return Units.rotationsToDegrees(left.getPosition());
  }
  /**
   * 
   * @param degrees Desired position of the Hood (in degrees) [0-110]
   */
  public void setAngle(double degrees){
    if(degrees>0&&degrees<120)
    left.setPosition(Units.degreesToRotations(degrees));
  }
  /**
   * 
   * @param angle Desired angle (in degrees)
   * @return True if hood is within tolerance of the angle, false if it is off
   */
  public boolean atAngle(double angle){
    return Math.abs(angle-getAngle())<3;
  }

  @Override
  public void initSendable(SendableBuilder b){
    b.addDoubleProperty("angle", left::getPosition, null);
    b.addDoubleProperty("left angle motor current", left::getCurrent, null);
    b.addDoubleProperty("right angle motor current", right::getCurrent, null);

  }

  @Override
  public void periodic() {
  }
}

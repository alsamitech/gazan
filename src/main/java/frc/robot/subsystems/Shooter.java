// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.interpolation.Interpolatable;
import edu.wpi.first.units.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.team696.lib.Util;
import frc.team696.lib.Datatypes.InterpolatingTable;
import frc.team696.lib.HardwareDevices.TalonFactory;
import com.ctre.phoenix6.controls.*;
public class Shooter extends SubsystemBase {
  private static Shooter shooter;
  public static Shooter get(){
    if(shooter==null) shooter=new Shooter();
    return shooter;
  }
  public static InterpolatingTable<State> shootTable=new InterpolatingTable<State>();
  public static InterpolatingTable<State> passTable=new InterpolatingTable<State>();
  public static class State implements Interpolatable<State>{
    public double leftVelocity;
    public double rightVelocity;
    public double angle;
    public boolean beamBreak;
    public State(double leftVelocity, double rightVelocity, double angle, boolean beamBreak){
      this.leftVelocity=leftVelocity;this.rightVelocity=rightVelocity;this.angle=angle;this.beamBreak=beamBreak;
    }
    public State(double leftVelocity, double rightVelocity, double angle){
      this.leftVelocity=leftVelocity;this.rightVelocity=rightVelocity;this.angle=angle;
    }
    @Override 
    public State interpolate(State endValue, double t){
      return new State(
        Util.lerp(t, this.angle, endValue.angle),
        Util.lerp(t, this.rightVelocity, endValue.rightVelocity),
        Util.lerp(t, this.leftVelocity, endValue.leftVelocity));
    }
  }
  private TalonFactory m_leftMotor, m_rightMotor;
  private VelocityVoltage m_leftVV, m_rightVV;
  private DigitalInput beamBreak;
  public Shooter() {
    m_leftMotor=new TalonFactory(0, Constants.CANivoreName, Constants.configs.shooter.left,"Left Shooter");
    m_rightMotor=new TalonFactory(0, Constants.CANivoreName, Constants.configs.shooter.right,"Right Shooter");
    beamBreak=new DigitalInput(7);
  }

  public boolean upToSpeed(State s){
    return s.leftVelocity-m_leftMotor.getVelocity()>200||s.rightVelocity-m_rightMotor.getVelocity()>200;
  }
  public void setState(State s){
    m_leftMotor.setControl(m_leftVV.withVelocity(s.leftVelocity));
    m_rightMotor.setControl(m_rightVV.withVelocity(s.rightVelocity));

  }

  public State getState(){
    return new State(m_leftMotor.getVelocity(), m_rightMotor.getVelocity(), Hood.get().getAngle());
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

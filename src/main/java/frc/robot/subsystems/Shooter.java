// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.units.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.team696.lib.HardwareDevices.TalonFactory;
import com.ctre.phoenix6.controls.*;
public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public class State{
    double leftVelocity;
    double rightVelocity;
    boolean beamBreak;
    public State(double leftVelocity, double rightVelocity, double beamBreak){
      this.leftVelocity=leftVelocity;this.rightVelocity=rightVelocity;this.beamBreak=beamBreak;
    }
    public State(double leftVelocity, double rightVelocity){
      this.leftVelocity=leftVelocity;this.rightVelocity=rightVelocity;
    }
  }
  public TalonFactory m_leftMotor, m_rightMotor;
  public VelocityVoltage m_leftVV, m_rightVV;
  public Shooter() {
    //m_leftMotor=new TalonFactory(0, Constants.CANivoreName, "Left Shooter")

  }
  public void setState(State s){
    m_leftMotor.setControl(m_leftVV.withVelocity(s.leftVelocity));
    m_rightMotor.setControl(m_rightVV.withVelocity(s.rightVelocity));

  }
  public State getState(){
    return new State(m_leftMotor.getVelocity(), m_rightMotor.getVelocity());
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team696.lib.HardwareDevices.TalonFactory;

public class Intake extends SubsystemBase {
  private static Intake intake;
  public static Intake get(){
    if(intake!=null)intake=new Intake();
    return intake;
  }
  TalonFactory m_Serializer;
  DigitalInput beamBreak;
  /** Creates a new Intake. */
  public Intake() {
    m_Serializer=new TalonFactory(10, Constants.CANivoreName, Constants.configs.intake.serializer, "Intake Serializer");
    beamBreak=new DigitalInput(8);
  }
  public void stop(){
    m_Serializer.stop();
  }
  /**
   * Gets the status of the intake beam break
   * @return True if the beam break is unbroken, false if it is broken 
   */
  public boolean getBeamBreak(){
    return beamBreak.get();
  }
  /**
   * Sets the speed of the intake serializer
   * @param speed [0-1] Speed of the serializer motor
   */
  public void setSpeed(double speed){
    m_Serializer.VoltageOut(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

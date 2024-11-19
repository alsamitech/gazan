// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team696.lib.HardwareDevices.TalonFactory;

public class Intake extends SubsystemBase {
  TalonFactory m_IntakeMotor=new TalonFactory(10, "vore", Constants.configs.intake.intake, "Intake");
  /** Creates a new Intake. */
  public Intake() {
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

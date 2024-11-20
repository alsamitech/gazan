// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class GroundIntake extends Command {
  /** Creates a new GroundIntake. */
  public GroundIntake() {
    addRequirements(Intake.get());
    addRequirements(Hood.get());
    addRequirements(Shooter.get());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Intake.get().setSpeed(0.3);
    Hood.get().setAngle(45);
    Shooter.get().serializerSpeed(0.2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!Shooter.get().getBeamBreak()){
      // beam break is broken, stop spinning the rollers
      Intake.get().stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !Intake.get().getBeamBreak()&&!Shooter.get().getBeamBreak();
  }
}

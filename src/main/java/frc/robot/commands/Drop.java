// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class Drop extends Command {
  /** Creates a new Drop. */
  public Drop() {
    addRequirements(Shooter.get());
    addRequirements(Intake.get());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Intake.get().setSpeed(-0.4); // spin in reverse
    Shooter.get().setState(new Shooter.State(1200, 1200,0));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Intake.get().stop();
    Shooter.get().stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

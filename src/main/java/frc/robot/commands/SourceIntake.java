// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;

public class SourceIntake extends Command {
  Shooter.State source=new Shooter.State(-1000, -1000, 30);
  /** Creates a new SourceIntake. */
  public SourceIntake() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Hood.get());
    addRequirements(Shooter.get());
    Shooter.get().serializerSpeed(-0.3);

  }
  private void setState(Shooter.State s){
    Shooter.get().setState(s);
    Hood.get().setAngle(s.angle);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setState(source);
    Shooter.get().serializerSpeed(-0.2);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!Shooter.get().getBeamBreak())
    // the shooter beam break is broken, the note is in, stop the serializer
      Shooter.get().serializerSpeed(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

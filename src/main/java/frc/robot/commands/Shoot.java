// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;

public class Shoot extends Command {
  /** Creates a new Shoot. */
  
  public Shoot() {
    addRequirements(Shooter.get());
    addRequirements(Hood.get());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}


  public void setState(Shooter.State s){
    Shooter.get().setState(s);
    Hood.get().setAngle(s.angle);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Shooter.State s=Shooter.shootTable.getValue(Swerve.get().distToSpeaker());
    setState(s);
    if(Shooter.get().upToSpeed(s)){
      // spin serializer
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

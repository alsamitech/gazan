// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;

public class Amp extends Command {
  /** Creates a new Amp. */
  public Amp() {
    addRequirements(Hood.get());
    addRequirements(Shooter.get());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Hood.get().setAngle(Shooter.ampState.angle);
    Shooter.get().setState(Shooter.ampState);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean didSeeFront=false;
    // check if the robot is aligned 
    // check if the shooter rollers are up to speed
    // if it is, spin the serializer to shoot the note
    if(Shooter.get().upToSpeed(Shooter.ampState)&&didSeeFront){
      Shooter.get().serializerSpeed(0.3);
    }
    if(Shooter.get().getBeamBreak()){
      Shooter.get().serializerSpeed(0);
      didSeeFront=true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Hood.get().stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

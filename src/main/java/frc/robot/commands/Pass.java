// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;

public class Pass extends Command {
  private boolean shouldFinish=false;
  private boolean didSeeFront=false;

  /** Creates a new Pass. */
  public Pass() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Hood.get());
    addRequirements(Shooter.get());
  }
  private void setState(Shooter.State s){
    Shooter.get().setState(s);
    Hood.get().setAngle(s.angle);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // we're assuming the note is actually in the robot therefore we're assuming that the beambreak is broken 
    Shooter.State s=Shooter.passTable.getValue(Swerve.get().distToCorner());
    setState(s);
    // TODO: make sure the robot is actually pointed in the direction of the corner
    if(Shooter.get().upToSpeed(s)&&Hood.get().atAngle(s.angle)&&!didSeeFront){
      // you can shoot now!!!
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
    return shouldFinish?didSeeFront:false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;

public class Shoot extends Command {
  private boolean shouldFinish=false;
  private boolean didSeeFront=false;

  /** Creates a new Shoot. */
  
  public Shoot() {
    addRequirements(Shooter.get());
    addRequirements(Hood.get());
  }
  public Shoot(boolean shouldFinish) {
    addRequirements(Shooter.get());
    addRequirements(Hood.get());
    this.shouldFinish=shouldFinish;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}


  private void setState(Shooter.State s){
    Shooter.get().setState(s);
    Hood.get().setAngle(s.angle);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Shooter.State s=Shooter.shootTable.getValue(Swerve.get().distToSpeaker());
    setState(s);
    // wait for spinup 
    if(Shooter.get().upToSpeed(s)&&Hood.get().atAngle(s.angle)){
      // spin serializer to shoot
      Shooter.get().serializerSpeed(0.2);
    }
    if(Shooter.get().getBeamBreak()){
      // stop the serializer once the note is no longer there
      Shooter.get().stopSerializer();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.get().stopSerializer();
    Hood.get().stop();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shouldFinish?didSeeFront:false;
  }
}

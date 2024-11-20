// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve;
import frc.team696.lib.Swerve.SwerveConstants;

public class Rotate extends Command {
  private double goalDegrees;
  private boolean shouldFinish=false;
  /** Creates a new Rotate. */
  public Rotate(Rotation2d goal) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Swerve.get());
    goalDegrees=goal.getDegrees();
  }
  public Rotate(Rotation2d goal, boolean shouldFinish) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Swerve.get());
    goalDegrees=goal.getDegrees();
    this.shouldFinish=shouldFinish;
  }
  PIDController thetaController;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    thetaController=new PIDController(0.001, 0, 0);
    thetaController.enableContinuousInput(-180, 180);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      double rPower=0;
      Swerve.get().Drive(new ChassisSpeeds(0,0, rPower*SwerveConstants.maxAngularVelocity));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // stop the drivetrain
    Swerve.get().Drive(new ChassisSpeeds(0,0,0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shouldFinish?Math.abs(Swerve.get().getYaw().getDegrees()-goalDegrees)<2.5:false;
  }
}

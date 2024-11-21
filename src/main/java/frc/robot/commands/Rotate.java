// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve;
import frc.team696.lib.Swerve.SwerveConstants;

public class Rotate extends Command {
  private boolean shouldFinish=false;
  private Supplier<Rotation2d> goalSupplier;
  PIDController thetaController;
  /** Creates a new Rotate. */
  public Rotate(Supplier<Rotation2d> goalSupplier) {
    addRequirements(Swerve.get());
    this.goalSupplier=goalSupplier;
    thetaController=new PIDController(0.001, 0, 0);
    thetaController.enableContinuousInput(-180, 180);
  }
  public Rotate(Supplier<Rotation2d> supplier, boolean shouldFinish) {
    addRequirements(Swerve.get());
    this.goalSupplier=goalSupplier;
    thetaController=new PIDController(0.001, 0, 0);
    thetaController.enableContinuousInput(-180, 180);
    this.shouldFinish=shouldFinish;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      double rPower=0;
      rPower=thetaController.calculate(Swerve.get().getYaw().getDegrees(), goalSupplier.get().getDegrees());
      rPower=rPower*rPower;
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
    return shouldFinish?Swerve.get().getYaw().minus(goalSupplier.get()).getDegrees()<2.5:false;
  }
}

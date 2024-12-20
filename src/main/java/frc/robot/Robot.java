// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;

import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Rotate;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;
import frc.team696.lib.Auto;
import frc.team696.lib.Util;
import frc.team696.lib.Auto.NamedCommand;
import frc.team696.lib.Dashboards.ShuffleDashboard;
import frc.team696.lib.Swerve.Commands.TeleopSwerve;

public class Robot extends LoggedRobot {
  private Command m_autonomousCommand;

  private PowerDistribution PDH;

  @Override
  public void robotInit() {
    // Set up logging and telemetry 
    if(isReal()){
      // Log to a USB stick
      Logger.addDataReceiver(new WPILOGWriter("/u/logs"));
    }
    Logger.addDataReceiver(new NT4Publisher()); // Log to Network Tables
    try{
      Logger.recordMetadata("Robot Mac Address", Util.getMacAddresses().toString());
    }catch(IOException e){}
    // Log build information
    switch(BuildConstants.DIRTY){
      case 0:{
        Logger.recordMetadata("Dirty", "All changes commited");
      }
      case 1:{
        Logger.recordMetadata("Dirty", "Uncommited changes");
      }
      case 2:{
        Logger.recordMetadata("Dirty","Unknown");
      }
    }
    Logger.recordMetadata("ProjectName", BuildConstants.MAVEN_NAME);
    Logger.recordMetadata("BuildDate", BuildConstants.BUILD_DATE);
    Logger.recordMetadata("GitSHA", BuildConstants.GIT_SHA);
    Logger.recordMetadata("GitBranch", BuildConstants.GIT_BRANCH);
    Logger.recordMetadata("GitDate", BuildConstants.GIT_DATE);
    Logger.recordMetadata("RuntimeType", getRuntimeType().toString());

    // Log the state of the command scheduler
    SmartDashboard.putData(CommandScheduler.getInstance());
    // Log which commands are running on which subsystems
    SmartDashboard.putData(Hood.get());
    SmartDashboard.putData(Swerve.get());
    SmartDashboard.putData(Shooter.get());
    SmartDashboard.putData(Swerve.get());
    SmartDashboard.putData(Intake.get());
    ShuffleDashboard.initialize();
    DriverStation.silenceJoystickConnectionWarning(true);
    Logger.start();

    // Initialize subsystems
    Shooter.get();
    Intake.get();
    Hood.get();
    Swerve.get();

    
    // Initialize common library components
    Auto.Initialize(Swerve.get(), 
      new NamedCommand("Shoot", (new Shoot().asProxy()).deadlineWith(new Rotate(Swerve.get()::angleToSpeaker)))

    ); 

    // Initialize and confiugre controllers 
    RobotController.setEnabled5V(true); // beambreaks need these
    RobotController.setEnabled6V(false);
    RobotController.setEnabled3V3(false);


    // Set up controls
    Controls.EmilDriverStation.setup();
    // make sure teleopswerve is initialized after controllers are set up
    Swerve.get().setDefaultCommand(TeleopSwerve.New().withRotationGoal(/* yo oscar this is juicy!!!! :) */Swerve.get()::angleToSpeaker));


  }

  @Override
  public void robotPeriodic() {
    double start=System.currentTimeMillis();
    // run the command scheduler
    CommandScheduler.getInstance().run();
    double end=System.currentTimeMillis();
    Logger.recordOutput("Times/CommandSchedulerMs", end-start);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = Auto.get().Selected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}

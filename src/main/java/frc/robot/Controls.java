// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.GroundIntake;
import frc.robot.subsystems.Swerve;
import frc.team696.lib.Swerve.Commands.TeleopSwerve;

/** Add your docs here. */
public class Controls {
    public class EmilDriverStation{
        public static CommandJoystick driverPanel=new CommandJoystick(0);
        public static CommandJoystick operatorPanel=new CommandJoystick(1);
        public static CommandJoystick operatorPanelB=new CommandJoystick(2);
        public static Trigger leftJoyB=driverPanel.button(1);
        public static Trigger rightJoyB=driverPanel.button(2);
        public static DoubleSupplier leftJoyY=()->-driverPanel.getRawAxis(1);
        public static DoubleSupplier leftJoyX=()->driverPanel.getRawAxis(0);
        public static DoubleSupplier rightJoyX=()->driverPanel.getRawAxis(2);
        public static DoubleSupplier rightJoyY=()->driverPanel.getRawAxis(3);

        public static final Trigger Shoot=operatorPanel.button(1);
        public static final Trigger Amp=operatorPanel.button(2);
        public static final Trigger Ground=operatorPanel.button(7);
        public static final Trigger Drop=operatorPanel.button(11);
        public static void setup(){
            TeleopSwerve.config(Swerve.get(), leftJoyX, leftJoyY, rightJoyX, leftJoyB, 0.07);
            Shoot.whileTrue(new frc.robot.commands.Shoot());
            Amp.whileTrue(new frc.robot.commands.Amp());
            Ground.whileTrue(new GroundIntake());
            Drop.whileTrue(new frc.robot.commands.Drop());
        }
    }
    public class XboxController{
        public static CommandXboxController xbox=new CommandXboxController(0);
        public static DoubleSupplier translationX=()->-xbox.getRawAxis(0);
        public static DoubleSupplier translationY=()->-xbox.getRawAxis(1);
        public static DoubleSupplier rotation=()->-xbox.getRawAxis(4);
        public static Trigger rotationTrigger=xbox.button(8);
        public static Trigger translationTrigger=xbox.button(9);
        public static Trigger shoot=xbox.leftBumper();
        
        public static void setup(){
            TeleopSwerve.config(Swerve.get(), translationX, translationY, rotation, rotationTrigger, 0.03);
        }
        //public DoubleSupplier ;
    }
    public class TwoXboxControllers{
        public static CommandXboxController Driver=new CommandXboxController(0);
        public static CommandXboxController Operator=new CommandXboxController(1);
        public static DoubleSupplier translationX=()->-Driver.getRawAxis(0);
        public static DoubleSupplier translationY=()->-Driver.getRawAxis(1);
        public static DoubleSupplier rotation=()->-Driver.getRawAxis(4);
        public static Trigger rotationTrigger=Driver.button(8); // TODO: set these to their actual values
        public static Trigger translationTrigger=Driver.button(9); // TODO: set these to their actual values

        public static Trigger Ground=Operator.rightBumper();
        public static Trigger Shoot=Operator.leftBumper();
        public static Trigger Pass=Operator.b();
        public static Trigger Amp=Operator.a();
        public static Trigger Drop=Operator.b().and(Operator.a());
        public static void setup(){
            TeleopSwerve.config(Swerve.get(), translationX, translationY, rotation, rotationTrigger, 0.03);
            Shoot.whileTrue(new frc.robot.commands.Shoot());
            Amp.whileTrue(new frc.robot.commands.Amp());
            Ground.whileTrue(new GroundIntake());
            Drop.whileTrue(new frc.robot.commands.Drop());
        }
    }
}

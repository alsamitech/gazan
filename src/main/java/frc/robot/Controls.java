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
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Swerve;
import frc.team696.lib.Swerve.Commands.TeleopSwerve;

/** Add your docs here. */
public class Controls {
    public class EmilDriverStation{
        public static CommandJoystick driverPanel=new CommandJoystick(0);
        public static CommandJoystick operator1=new CommandJoystick(1);
        public static CommandJoystick operator2=new CommandJoystick(2);
        public static CommandJoystick operator3=new CommandJoystick(3);
        public static Trigger leftJoy;
        public static void setup(){

        }
    }
    public class XboxController{
        public static CommandXboxController xbox=new CommandXboxController(0);
        public static DoubleSupplier translationX=()->-xbox.getRawAxis(0);
        public static DoubleSupplier translationY=()->-xbox.getRawAxis(1);
        public static DoubleSupplier rotation=()->-xbox.getRawAxis(4);
        public static Trigger rotationTrigger=xbox.button(8);
        
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

        public static Trigger GroundIntake=Operator.rightBumper();
        public static Trigger Shoot=Operator.leftBumper();
        public static Trigger Pass=Operator.b();
        public static Trigger Amp=Operator.a();
        public static void setup(){

        }
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/** Add your docs here. */
public class Controls {
    public class EmilDriverStation{
        public CommandJoystick driverPanel=new CommandJoystick(0);
        public static void setup(){

        }
    }
    public class XboxController{
        public CommandXboxController xbox=new CommandXboxController(0);
    }
    public class TwoXboxControllers{
        public CommandXboxController Driver=new CommandXboxController(0);
        public CommandXboxController Operator=new CommandXboxController(1);
        
    }
}

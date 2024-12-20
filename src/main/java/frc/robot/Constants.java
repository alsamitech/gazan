// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.team696.lib.Util;

/** Add your docs here. */
public final class Constants {
    public static Configs configs=new Configs();
    public static String CANivoreName="vore";
    public static final class Field {
        public static class alliance{
		    public static final Translation2d Speaker = new Translation2d(0, 0);
            public static final Pose2d Amp = new Pose2d(0,0,Rotation2d.fromDegrees(0));
            public static final Pose2d Source = new Pose2d(0,0,Rotation2d.fromDegrees(0));
			public static final Translation2d Corner = new Translation2d(0,0);
        }
        public static final class RED extends alliance{
		    public static final Translation2d Speaker = new Translation2d(16.57, 5.54);
            public static final Pose2d Amp = new Pose2d(14.7, 7.8, new Rotation2d(Math.PI/2));
            public static final Pose2d Source = new Pose2d(1, 0.5, Rotation2d.fromDegrees(-135));
			public static final Translation2d Corner = new Translation2d(14.57, 7.);

        }
        public static final class BLUE extends alliance{
            public static final Translation2d Speaker = new Translation2d(-0.04, 5.54);
            public static final Pose2d Amp = new Pose2d(1.7, 7.8, new Rotation2d(Math.PI/2));
            public static final Pose2d Source = new Pose2d(15.15, 1.5, Rotation2d.fromDegrees(-45)); 
			public static final Translation2d Corner = new Translation2d(2., 7.);

        }
        /**
         * 
         * @return The field constant set for the alliance the robot is on
         */
        private static alliance aRed=new RED();
        private static alliance aBlue=new BLUE();
        public static alliance getSide(){
            return Util.getAlliance()==Alliance.Red?aRed:aBlue;
        }
        /**
         * 
         * @return The field set for the alliance opposite the alliance of the robot
         */
        public static alliance getOppositeSide(){
            return Util.getAlliance()==Alliance.Red?aBlue:aRed;
        }
	}
	    
}

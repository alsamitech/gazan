// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

/** Add your docs here. */
public class Configs {
    public final class Hood{
        public TalonFXConfiguration left;
        public TalonFXConfiguration right;
    }
    public final class Shooter{
        public TalonFXConfiguration serializer;
        public TalonFXConfiguration left;
        public TalonFXConfiguration right;
    }
    public class Intake{
        public TalonFXConfiguration serializer;
    }
    public Hood hood=new Hood();
    public Shooter shooter=new Shooter();
    public Intake intake = new Intake();
    public Configs() {
        hood.left = new TalonFXConfiguration();
        hood.right = new TalonFXConfiguration();
    
        shooter.left = new TalonFXConfiguration();
        shooter.right = new TalonFXConfiguration();
        shooter.serializer = new TalonFXConfiguration();

        intake.serializer = new TalonFXConfiguration();

        hood.left.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        hood.left.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        hood.left.CurrentLimits.StatorCurrentLimitEnable = true;
        hood.left.CurrentLimits.StatorCurrentLimit = 80;
        hood.left.Slot0.kP = 6.; 
        hood.left.Slot0.kS = 0.6;
        hood.left.Slot0.kV = 0;
        hood.left.Slot0.kA = 0;
        hood.left.Feedback.SensorToMechanismRatio=
        hood.left.MotionMagic.MotionMagicCruiseVelocity = 80;
        hood.left.MotionMagic.MotionMagicAcceleration = 100;

        hood.right.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        hood.right.MotorOutput.NeutralMode = hood.left.MotorOutput.NeutralMode;
        hood.right.Slot0.kP = hood.left.Slot0.kP;
        hood.right.CurrentLimits.StatorCurrentLimitEnable = true;
        hood.right.CurrentLimits.StatorCurrentLimit = 80;
        hood.right.Slot0.kP = hood.left.Slot0.kP; 
        hood.right.Slot0.kS = hood.left.Slot0.kS;
        hood.right.Slot0.kV = hood.left.Slot0.kV;
        hood.right.Slot0.kA = hood.left.Slot0.kA;
        hood.right.MotionMagic.MotionMagicCruiseVelocity = hood.left.MotionMagic.MotionMagicCruiseVelocity;
        hood.right.MotionMagic.MotionMagicAcceleration = hood.left.MotionMagic.MotionMagicAcceleration;

        shooter.left.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        shooter.left.Slot0.kP = 0.35;
        shooter.left.Slot0.kV = 0.13;
        shooter.left.Slot0.kS = 0.14;
        shooter.left.CurrentLimits.StatorCurrentLimitEnable = true;
        shooter.left.CurrentLimits.StatorCurrentLimit = 80;

        shooter.left.Voltage.PeakForwardVoltage = 12.;
        shooter.left.Voltage.PeakReverseVoltage = -12.;

        shooter.right.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        shooter.right.Slot0.kP = shooter.left.Slot0.kP;
        shooter.right.Slot0.kV = shooter.left.Slot0.kV;
        shooter.right.Slot0.kS = shooter.left.Slot0.kS;
        shooter.right.CurrentLimits.StatorCurrentLimitEnable = true;
        shooter.right.CurrentLimits.StatorCurrentLimit = 80;

        shooter.right.Voltage.PeakForwardVoltage = 12.;
        shooter.right.Voltage.PeakReverseVoltage = -12.;

        intake.serializer.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        intake.serializer.CurrentLimits.StatorCurrentLimitEnable = false;
        intake.serializer.CurrentLimits.StatorCurrentLimit = 80;
        intake.serializer.Voltage.PeakForwardVoltage = 12.;
        intake.serializer.Voltage.PeakReverseVoltage = -12.;
        shooter.serializer.Slot0.kP = 12.;
    }
}

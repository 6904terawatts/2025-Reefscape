package frc.robot;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSTalonFXSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.2;

    public static final class Swerve {
        public static final int pigeonID = 0;

        public static final COTSTalonFXSwerveConstants chosenModule = 
        COTSTalonFXSwerveConstants.SDS.MK4.KrakenX60(COTSTalonFXSwerveConstants.SDS.MK4.driveRatios.L3);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(28); 
        public static final double wheelBase = Units.inchesToMeters(28); 
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics */
        public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final InvertedValue angleMotorInvert = chosenModule.angleMotorInvert;
        public static final InvertedValue driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final SensorDirectionValue cancoderInvert = chosenModule.cancoderInvert;

        /* Swerve Current Limiting */
        public static final int angleCurrentLimit = 25;
        public static final int angleCurrentThreshold = 40;
        public static final double angleCurrentThresholdTime = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveCurrentLimit = 35;
        public static final int driveCurrentThreshold = 60;
        public static final double driveCurrentThresholdTime = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.12; //TODO: This must be tuned t specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values From SYSID */
        public static final double driveKS = 0.32; //TODO: This must be tuned to specific robot
        public static final double driveKV = 1.51;
        public static final double driveKA = 0.27;

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 4.5; //TODO: This must be tuned to specific robot
        /** Radians per Second */
        public static final double maxAngularVelocity = 10.0; //TODO: This must be tuned to specific robot

        /* Neutral Modes */
        public static final NeutralModeValue angleNeutralMode = NeutralModeValue.Coast;
        public static final NeutralModeValue driveNeutralMode = NeutralModeValue.Brake;


        
  /* Front Left Module - Module 0 */
        public static final class Mod0 { //TODO: This must be tuned to specific robot
          public static final int driveMotorID = 2;
          public static final int angleMotorID = 1;
          public static final int canCoderID = 10;
          public static final Rotation2d angleOffset = Rotation2d.fromRotations(0.4831542968750);// angle offset
          public static final SwerveModuleConstants constants = 
              new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
      }

      /* Front Right Module - Module 1 */
      public static final class Mod1 { //TODO: This must be tuned to specific robot
          public static final int driveMotorID = 4;
          public static final int angleMotorID = 3;
          public static final int canCoderID = 12;
          public static final Rotation2d angleOffset = Rotation2d.fromRotations(0.4814453125); // angle offset
          public static final SwerveModuleConstants constants = 
              new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
      }
      
      /* Back Left Module - Module 2 */
      public static final class Mod2 { //TODO: This must be tuned to specific robot
          public static final int driveMotorID = 6;
          public static final int angleMotorID = 5;
          public static final int canCoderID = 11;
          public static final Rotation2d angleOffset = Rotation2d.fromRotations(-0.1572265625);// angle offset
          public static final SwerveModuleConstants constants = 
              new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
      }

      /* Back Right Module - Module 3 */
      public static final class Mod3 { //TODO: This must be tuned to specific robot
          public static final int angleMotorID = 8;
          public static final int driveMotorID = 7;
          public static final int canCoderID = 13;
          public static final Rotation2d angleOffset = Rotation2d.fromRotations(-0.04296875);//angle offset
          public static final SwerveModuleConstants constants = 
              new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
      }
    }

    public static class Elevator {
      public static final int kElevatorLeftMotorId = 9;
      public static final int kElevatorRightMotorId = 10;
  
      public static final double kP = 0.15;
      public static final double kI = 0;
      public static final double kD = 0.0;
      public static final double kIZone = 5.0;
      public static final double kG = 0.5;
  
      public static final double kMaxVelocity = 65;
      public static final double kMaxAcceleration = 200;
  
      public static final int kMaxCurrent = 40;
      public static final double kMaxPowerUp = -0.1;
      public static final double kMaxPowerDown = 0.1;
  
      public static final double kStowHeight = -5.0;
      public static final double kL2Height = 9.0;
      public static final double kL3Height = 52.14;
      public static final double kL4Height = 110.0;
      public static final double kMaxHeight = 115.2;
      public static final double kGroundAlgaeHeight = 0.0;
      public static final double kScoreAlgaeHeight = 0.0;
      public static final double kLowAlgaeHeight = 24.8;
      public static final double kHighAlgaeHeight = 42.5;
    }
  
    public static class Coral {
      public static final int kLeftMotorId = 11;
      public static final int kRightMotorId = 12;
  
      public static final int kLaserId = 15;
      public static final int kColorId = 16;
  
      public static final double kMaxCurrent = 20;
  
      public static final double kP = 0.0;
      public static final double kI = 0.0;
      public static final double kD = 0.0;
      public static final double kIZone = 0;
  
      public static final double kIntakeSpeed = 0.3;
      public static final double kReverseSpeed = -0.3;
      public static final double kL1Speed = 0.4;
      public static final double kL24Speed = 0.6;
      public static final double kIndexSpeed = 0.1;
      public static final double kSpeedDifference = kL1Speed * 0.5;
    }
  
    // public static class Algae {
    //   // WRIST
    //   public static final int kWristMotorId = 13;
    //   public static final int kIntakeMotorId = 14;
  
    //   public static final int kWristEncoderId = 9;
  
    //   public static final int kMaxWristCurrent = 10;
  
    //   public static final double kWristP = 0.01;
    //   public static final double kWristI = 0.0;
    //   public static final double kWristD = 0.0;
  
    //   public static final double kWristKS = 0.0;
    //   public static final double kWristKG = 0.0;
    //   public static final double kWristKV = 0.100;
    //   public static final double kWristKA = 0.0;
  
    //   public static final double kWristOffset = 141.0;
  
    //   public static final double kWristMaxVelocity = 690.0;
    //   public static final double kWristMaxAcceleration = 1380.0;
  
    //   public static final double kStowAngle = 233.0;
    //   public static final double kDeAlgaeAngle = 215.0;
    //   public static final double kGroundIntakeAngle = 162.0;
  
    //   // INTAKE
    //   public static final int kMaxIntakeCurrent = 20;
  
    //   public static final double kIntakeSpeed = 0.6;
    //   public static final double kEjectSpeed = -0.3;
    //   public static final double kGroundIntakeSpeed = -0.3;
    // }
  
    public static class Intake {
      // Motors
      public static final int kIntakeMotorId = 9;
      public static final int kPivotMotorId = 10;
  
      // DIO
      public static final int k_pivotEncoderId = 0;
      public static final int k_intakeLimitSwitchId = 2;
  
      // Absolute encoder offset
      public static final double k_pivotEncoderOffset = 0.166842; // Straight up, sketchy to reset to "up"
  
      // Pivot set point angles
      public static final double k_pivotAngleGround = 60;
      public static final double k_pivotAngleSource = 190;
      public static final double k_pivotAngleAmp = k_pivotAngleSource;
      public static final double k_pivotAngleStow = 275;
  
      // Intake speeds
      public static final double k_intakeSpeed = 0.7;
      public static final double k_ejectSpeed = -0.45;
      public static final double k_feedShooterSpeed = -0.5;
    }
  
   
  
    // DIO
  
    // Shooter
    public static final int kShooterLeftMotorId = 12;
    public static final int kShooterRightMotorId = 13;
  
    public static final double kShooterP = 0.00005;
    public static final double kShooterI = 0.0;
    public static final double kShooterD = 0.0;
    public static final double kShooterFF = 0.0002;
  
    public static final double kShooterMinOutput = 0;
    public static final double kShooterMaxOutput = 1;
  
    // Climber
    public static final int kClimberLeftMotorId = 14;
    public static final int kClimberRightMotorId = 15;
    public static final double kClimberClimbSpeed = 600.0; // RPM
    public static final double kClimberReleaseSpeed = -600.0; // RPM
  
    public static final double kClimberGearRatio = 1.0 / 12.0;
  
    public static final double kClimberP = 0.001;
    public static final double kClimberI = 0.0;
    public static final double kClimberD = 0.0;
    public static final double kClimberMinOutput = -0.5;
  
    public static final double kClimberMaxOutput = 0.5;
    public static class Field {
      public static final double k_width = Units.feetToMeters(54.0);
      public static final double k_length = Units.feetToMeters(27.0);
  
      // TODO: Add left and right subwoofer starting poses
      public static final Pose2d redCenterPose2d = new Pose2d(15.19, 5.50, new Rotation2d(Units.degreesToRadians(180.0)));
      public static final Pose2d blueCenterPose2d = new Pose2d(1.27, 5.50, new Rotation2d(0));
    }
  
    public static class LEDs {
      public static final int k_PWMId = 9;
      public static final int k_totalLength = 300;
    }  

    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
      public static final double kMaxSpeedMetersPerSecond = 3;
      public static final double kMaxAccelerationMetersPeecondSquared = 3;
      public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
      public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
  
      public static final double kPXController = 1;
      public static final double kPYController = 1;
      public static final double kPThetaController = 1;
  
      /* Constraint for the motion profilied robot angle controller */
      public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
          new TrapezoidProfile.Constraints(
              kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }
}

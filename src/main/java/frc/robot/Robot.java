// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Rotation;

import java.util.ArrayList;
import java.util.List;

import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Swerve;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.controls.controllers.DriverController;
import frc.robot.controls.controllers.OperatorController;
import frc.robot.simulation.Field;
import frc.robot.subsystems.Coral;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Subsystem;
import frc.robot.subsystems.leds.LEDs;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends LoggedRobot {
  public static final CTREConfigs ctreConfigs = new CTREConfigs();
  private final Swerve s_Swerve = new Swerve();
  private final DriverController m_driverController = new DriverController(0, true, true);
  private final OperatorController m_operatorController = new OperatorController(1, true, true);
  private final GenericHID sysIdController = new GenericHID(2);

  private final SlewRateLimiter m_speedLimiter = new SlewRateLimiter(Constants.Swerve.maxSpeed);
  private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(Math.PI * 8);

  // Robot subsystems
  private List<Subsystem> m_allSubsystems = new ArrayList<>();
  // private final Intake m_intake = Intake.getInstance();
  private final Swerve m_drive = Swerve.getInstance();
  private final Coral m_coral = Coral.getInstance();
  // private final Algae m_algae = Algae.getInstance();
  // private final Shooter m_shooter = Shooter.getInstance();
  private final Elevator m_elevator = Elevator.getInstance();

  public final LEDs m_leds = LEDs.getInstance();

  // Simulation stuff
  private final Field m_field = Field.getInstance();

  private void initializeSubSystems() {
    // Drivetrain will execute this command periodically
    m_drive.setDefaultCommand(
        new TeleopSwerve(
            m_drive,
            m_driverController::getForwardAxis,
            m_driverController::getStrafeAxis,
            m_driverController::getTurnAxis,
            m_driverController::getWantsRobotCentric
        )
    );
  }

  /**
   * This function is run when the robot is first started up.
   */
  @Override
  public void robotInit() {
    //CanandEventLoop.getInstance();
    //TalonFX testMotor = new TalonFX(60, "TEST");
    Timer.delay(1);
    setupLogging();
    CameraServer.startAutomaticCapture();



    // Add all subsystems to the list
    // m_allSubsystems.add(m_compressor);
    m_allSubsystems.add(m_drive);
    m_allSubsystems.add(m_coral);
    // m_allSubsystems.add(m_algae);
    m_allSubsystems.add(m_elevator);

    m_allSubsystems.add(m_leds);

    // Set up the Field2d object for simulation
    SmartDashboard.putData("Field", m_field);

    // Initialize subsystems
    initializeSubSystems();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    m_allSubsystems.forEach(subsystem -> subsystem.periodic());
    m_allSubsystems.forEach(subsystem -> subsystem.writePeriodicOutputs());
    m_allSubsystems.forEach(subsystem -> subsystem.outputTelemetry());
    m_allSubsystems.forEach(subsystem -> subsystem.writeToLog());

    updateSim();

    // Used by sysid
    if (this.isTestEnabled()) {
      


      
    }
  }

  @Override
  public void autonomousInit() {
   
  }

  @Override
  public void autonomousPeriodic() {
   //Swerve.drive()
  }

  @Override
  public void teleopInit() {
    m_leds.breathe();
  }

  double speed = 0;
  boolean scorePressed = false;
  @Override
  public void teleopPeriodic() {
    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    double maxSpeed = m_driverController.getWantsSpeedMode() ? Constants.Swerve.maxSpeed : Constants.Swerve.maxSpeed;
    double xSpeed = m_speedLimiter.calculate(m_driverController.getForwardAxis() * maxSpeed);

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.

    // m_drive.slowMode(m_driverController.getWantsSlowMode());
    // m_drive.speedMode(m_driverController.getWantsSpeedMode());
    double rot = m_rotLimiter.calculate(m_driverController.getTurnAxis() * Constants.Swerve.maxAngularVelocity);

    // Temp controls ignoring elevator and sensor
    if (m_operatorController.getLeftTrigger()) {
      m_coral.scoreL1();
    } else if (m_operatorController.getRightTrigger()) {
      m_coral.scoreL24();
    } else {
      m_coral.stopCoral();
    }

    if (m_driverController.getWantsScoreCoral()) {
      scorePressed = true;

      if (m_elevator.getState() == Elevator.ElevatorState.STOW) {
        m_coral.scoreL1();
      } else {
        m_coral.scoreL24();
      }
    } else if (scorePressed) {
      scorePressed = false;}

  

    // FINAL OPERATOR CONTROLS
    // if (m_operatorController.getWantsElevatorStow()) {
    //   m_elevator.goToElevatorStow();
      
    // } else if (m_operatorController.getWantsElevatorL2()) {
    //   m_elevator.goToElevatorL2();
     
    // } else if (m_operatorController.getWantsElevatorL3()) {
    //   m_elevator.goToElevatorL3();
      
    // } else if (m_operatorController.getWantsElevatorL4()) {
    //   m_elevator.goToElevatorL4();
    
    // } else if (m_operatorController.getWantsCoralIntake()) {
    //   m_coral.intake();
    // }
    // if (m_operatorController.getWantsElevatorReset() || m_driverController.getWantsElevatorReset()) {
    //   m_elevator.reset();
    // }
  }
  

  @Override
  public void disabledInit() {
    // m_leds.rainbow();
    m_leds.setColor(Color.kRed);

    speed = 0;
    m_allSubsystems.forEach(subsystem -> subsystem.stop());
  };

  @Override
  public void disabledPeriodic() {
  };

  @Override
  public void disabledExit() {
  };

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  };

  @Override
  public void testPeriodic() {
 
  };

  @Override
  public void simulationInit() {
  };

  @Override
  public void simulationPeriodic() {
  };

  private void updateSim() {
    // Update the odometry in the sim.
    m_field.setRobotPose(m_drive.getPose());
  };

  @SuppressWarnings("resource")
  private void setupLogging() {
    Logger.recordMetadata("ProjectName", "Flipside"); // Set a metadata value

    if (isReal()) {
      new WPILOGWriter(); // Log to the RoboRIO

      // TODO: Add the next line back with a USB stick
      // Logger.addDataReceiver(new WPILOGWriter()); // Log to a USB stick ("/U/logs")
      Logger.addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables
      new PowerDistribution(1, ModuleType.kCTRE); // Enables power distribution logging
    }
  }
}

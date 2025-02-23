package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.*;
import frc.robot.controls.controllers.DriverController;
import frc.robot.subsystems.*;

public class RobotContainer {
    /* Controllers */
    private final DriverController m_driverController = new DriverController(0, false, false);

    /* Subsystems */
    private final Swerve m_drive = Swerve.getInstance();

    public RobotContainer() {
        m_drive.setDefaultCommand(
            new TeleopSwerve(
                m_drive,
                m_driverController::getForwardAxis,
                m_driverController::getStrafeAxis,
                m_driverController::getTurnAxis,
                m_driverController::getWantsRobotCentric
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        // Zero gyro button
        new JoystickButton(m_driverController, XboxController.Button.kY.value)
            .onTrue(new InstantCommand(() -> m_drive.zeroHeading()));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}

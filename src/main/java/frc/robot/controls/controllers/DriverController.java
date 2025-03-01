package frc.robot.controls.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

public class DriverController extends FilteredController {
  private String m_smartDashboardKey = "DriverInput/";

  public DriverController(int port) {
    super(port, false, false);
  }

  public DriverController(int port, boolean useDeadband, boolean useSquaredInput) {
    super(port, useDeadband, useSquaredInput);
  }

  // Axis
  private final double k_triggerActivationThreshold = 0.5;

  // Swerve Drive Controls
  private final int translationAxis = XboxController.Axis.kLeftY.value; // Left stick Y-axis
  private final int strafeAxis = XboxController.Axis.kLeftX.value; // Left stick X-axis
  private final int rotationAxis = XboxController.Axis.kRightX.value; // Right stick X-axis

  public double getTranslationAxis() {
    return -this.getFilteredAxis(translationAxis); // Inverted for forward movement
  }

  public double getStrafeAxis() {
    return -this.getFilteredAxis(strafeAxis); // Inverted for right movement
  }

  public double getRotationAxis() {
    return -this.getFilteredAxis(rotationAxis); // Inverted for rotation
  }

  public double getForwardAxis() {
    return getTranslationAxis(); // Use translation for forward movement
  }

  public double getTurnAxis() {
    return getRotationAxis();
  }

  public boolean getWantsRobotCentric() {
    return this.getRawButton(1); // Use Xbox button Start for robot-centric mode
  }

  public boolean getWantsSpeedMode() {
    return this.getFilteredAxis(2) > k_triggerActivationThreshold; // Right trigger
  }

  public boolean getWantsScoreCoral() {
    return this.getFilteredAxis(2) > k_triggerActivationThreshold; // Left trigger
  }

  public boolean getLeftTrigger() {
    return this.getFilteredAxis(2) > k_triggerActivationThreshold; // Left Trigger
  }

  public boolean getWantsIntakeCoral() {
    return this.getFilteredAxis(3) > k_triggerActivationThreshold; // Right trigger
  }

  public boolean getRightTrigger() {
    return this.getFilteredAxis(3) > k_triggerActivationThreshold; // Right trigger
  }
 

  

 

  public void outputTelemetry() {
    SmartDashboard.putNumber(m_smartDashboardKey + "Forward", getForwardAxis());
    SmartDashboard.putNumber(m_smartDashboardKey + "Turn", getTurnAxis());
  }
}

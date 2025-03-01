package frc.robot.controls.controllers;

public class OperatorController extends FilteredController {

  public OperatorController(int port) {
    super(port, false, false);
  }

  public OperatorController(int port, boolean useDeadband, boolean useSquaredInput) {
    super(port, useDeadband, useSquaredInput);
  }

  // Axis
  private final double k_triggerActivationThreshold = 0.5;

  public double getElevatorAxis() {
    return -this.getFilteredAxis(1); // Left stick Y-axis
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
  public boolean getWantsCoralL1() {
    return this.getRawButton(4); // Xbox button Y
  }

  public boolean getWantsCoralL24() {
    return this.getRawButton(5); // Xbox button LB
  }

   // ELEVATOR
   public boolean getWantsElevatorReset() {
    return this.getRawButton(7);
  }

  public boolean getWantsElevatorStow() {
    return this.getRawButton(1);
  }

  public boolean getWantsElevatorL2() {
    return this.getRawButton(3);
  }

  public boolean getWantsElevatorL3() {
    return this.getRawButton(2);
  }

  public boolean getWantsElevatorL4() {
    return this.getRawButton(4);
  }
  public boolean getWantsCoralIntake() {
    return this.getRawButton(6);
  }
}

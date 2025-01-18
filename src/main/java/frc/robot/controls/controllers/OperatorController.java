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

  public boolean getWantsCoralIntake() {
    return this.getRawButton(1); // Xbox button A
  }

  public boolean getWantsCoralReverse() {
    return this.getRawButton(2); // Xbox button B
  }

  public boolean getWantsCoralIndex() {
    return this.getRawButton(3); // Xbox button X
  }

  public boolean getWantsCoralL1() {
    return this.getRawButton(4); // Xbox button Y
  }

  public boolean getWantsCoralL24() {
    return this.getRawButton(5); // Xbox button LB
  }

  public boolean getWantsElevatorReset() {
    return this.getRawButton(6); // Xbox button RB
  }

  public boolean getWantsElevatorStow() {
    return this.getHatDown(); // DPad down
  }

  public boolean getWantsElevatorL2() {
    return this.getHatLeft(); // DPad left
  }

  public boolean getWantsElevatorL3() {
    return this.getHatRight(); // DPad right
  }

  public boolean getWantsElevatorL4() {
    return this.getHatUp(); // DPad up
  }
}

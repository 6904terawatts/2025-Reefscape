package frc.robot.autonomous;

import frc.robot.RobotTelemetry;
import frc.robot.autonomous.AutoModeBase;
import frc.robot.autonomous.modes.DefaultMode;
import frc.robot.autonomous.modes.DoNothingMode;
import frc.robot.autonomous.modes.SwerveScore2Corals;
import frc.robot.autonomous.modes.SwerveScore3Corals;
import frc.robot.autonomous.tasks.Task;

public class AutoRunner {
  private static AutoRunner m_autoRunner = null;
  private AutoChooser m_autoChooser = null;
  private AutoModeBase m_autoMode;
  private AutoMode m_selectedAuto;

  public enum AutoMode {
    DO_NOTHING,
    DEFAULT,
    SWERVE_SCORE_2_CORALS,
    SWERVE_SCORE_3_CORALS
  }

  public static AutoRunner getInstance() {
    if (m_autoRunner == null) {
      m_autoRunner = new AutoRunner();
    }
    return m_autoRunner;
  }

  private AutoRunner() {
    // Use this to set the default auto mode
    AutoMode defaultAuto = AutoMode.SWERVE_SCORE_2_CORALS;

    m_autoChooser = AutoChooser.getInstance();

    // Sets the default auto to the mode from above, then listens for changes
    // to the auto chooser and updates the selected auto mode
    onAutoChange(defaultAuto.toString());
    m_autoChooser.setDefaultOption(defaultAuto.toString());
    m_autoChooser.setOnChangeCallback(this::onAutoChange);
  }

  public Task getNextTask() {
    return m_autoMode.getNextTask();
  }

  public AutoMode getSelectedAuto() {
    return m_selectedAuto;
  }

  private void onAutoChange(String newAuto) {
    RobotTelemetry.print("AUTO CHANGED: " + newAuto);
    m_selectedAuto = AutoMode.valueOf(newAuto);

    switch (m_selectedAuto) {
      case DO_NOTHING:
        m_autoMode = new DoNothingMode();
        break;
      case DEFAULT:
        m_autoMode = new DefaultMode();
        break;
      case SWERVE_SCORE_2_CORALS:
        m_autoMode = new SwerveScore2Corals();
        break;
      case SWERVE_SCORE_3_CORALS:
        m_autoMode = new SwerveScore3Corals();
        break;
      default:
        RobotTelemetry.print("Invalid auto mode selected. Defaulting to do nothing.");
        m_autoMode = new DoNothingMode();
        break;
    }

    m_autoMode.queueTasks();
    m_autoMode.setStartingPose();
  }
}

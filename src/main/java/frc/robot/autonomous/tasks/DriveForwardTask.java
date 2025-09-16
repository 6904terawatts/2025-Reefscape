package frc.robot.autonomous.tasks;

import frc.robot.subsystems.Swerve;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.geometry.Translation2d;

public class DriveForwardTask implements Task {
    private final Swerve m_swerve = Swerve.getInstance();
    private final double m_speed;
    private final double m_duration;
    private double m_startTime;
    private boolean m_isFinished = false;

    public DriveForwardTask(double speed, double duration) {
        m_speed = speed;
        m_duration = duration;
    }

    @Override
    public void start() {
        m_startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void update() {
        // Drive forward at the specified speed
        m_swerve.drive(new Translation2d(m_speed, 0), 0, true, false);

        // Check if the duration has elapsed
        if (Timer.getFPGATimestamp() - m_startTime >= m_duration) {
            m_isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return m_isFinished;
    }

    @Override
    public void done() {
        // Stop the robot when the task is complete
        m_swerve.drive(new Translation2d(), 0, true, false);
    }
}

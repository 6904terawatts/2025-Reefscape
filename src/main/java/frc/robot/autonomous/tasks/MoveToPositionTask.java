package frc.robot.autonomous.tasks;

import frc.robot.subsystems.Swerve;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class MoveToPositionTask implements Task {
    private final Swerve m_swerve = Swerve.getInstance();
    private final Pose2d m_targetPose;
    private boolean m_isFinished = false;

    public MoveToPositionTask(Pose2d targetPose) {
        m_targetPose = targetPose;
    }

    @Override
    public void start() {
        // Initialize any necessary controllers or state
    }

    @Override
    public void update() {
        // Calculate the required movement
        Pose2d currentPose = m_swerve.getPose();
        Translation2d translation = m_targetPose.getTranslation().minus(currentPose.getTranslation());
        double rotation = m_targetPose.getRotation().minus(currentPose.getRotation()).getRadians();

        // Move towards the target position
        m_swerve.drive(translation, rotation, true, false);

        // Check if we've reached the target position
        if (translation.getNorm() < 0.1 && Math.abs(rotation) < 0.1) {
            m_isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return m_isFinished;
    }

    @Override
    public void done() {
        m_swerve.drive(new Translation2d(), 0, true, false);
    }
}

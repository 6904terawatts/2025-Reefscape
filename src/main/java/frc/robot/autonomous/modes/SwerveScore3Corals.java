package frc.robot.autonomous.modes;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Coral;
import frc.robot.autonomous.AutoModeBase;
import frc.robot.autonomous.tasks.Task;
import frc.robot.autonomous.tasks.MoveToPositionTask;
import frc.robot.autonomous.tasks.ScoreCoralTask;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveScore3Corals extends AutoModeBase {
    private final Swerve m_swerve;
    private final Coral m_coral;

    public SwerveScore3Corals() {
        m_swerve = Swerve.getInstance();
        m_coral = Coral.getInstance();
    }

    @Override
    public void queueTasks() {
        // Move to first coral position
        addTask(new MoveToPositionTask(new Pose2d(2.0, 3.0, new Rotation2d())));
        
        // Score first coral
        addTask(new ScoreCoralTask());
        
        // Move to second coral position
        addTask(new MoveToPositionTask(new Pose2d(4.0, 1.5, new Rotation2d())));
        
        // Score second coral
        addTask(new ScoreCoralTask());
        
        // Move to third coral position
        addTask(new MoveToPositionTask(new Pose2d(6.0, 3.0, new Rotation2d())));
        
        // Score third coral
        addTask(new ScoreCoralTask());
    }

    @Override
    public Task getNextTask() {
        return null;
    }

    @Override
    public void setStartingPose() {
        // Set starting pose
    }

    @Override
    public Pose2d getStartingPose() {
        return new Pose2d();
    }
}

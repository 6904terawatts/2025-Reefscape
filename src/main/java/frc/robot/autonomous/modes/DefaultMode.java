package frc.robot.autonomous.modes;

import frc.robot.autonomous.AutoModeBase;
import frc.robot.autonomous.tasks.Task;
import edu.wpi.first.math.geometry.Pose2d;

public class DefaultMode extends AutoModeBase {
    @Override
    public void queueTasks() {
        // Add default tasks here
    }

    @Override
    public Task getNextTask() {
        return null;
    }

    @Override
    public void setStartingPose() {
        // Default starting pose
    }

    @Override
    public Pose2d getStartingPose() {
        return new Pose2d();
    }
}

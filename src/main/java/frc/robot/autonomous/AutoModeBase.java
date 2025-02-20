package frc.robot.autonomous;

import frc.robot.autonomous.tasks.Task;
import edu.wpi.first.math.geometry.Pose2d;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AutoModeBase {
    private final Queue<Task> m_taskQueue = new LinkedList<>();

    public abstract void queueTasks();
    public abstract void setStartingPose();
    public abstract Pose2d getStartingPose();

    protected void addTask(Task task) {
        m_taskQueue.add(task);
    }

    public Task getNextTask() {
        return m_taskQueue.poll();
    }
}

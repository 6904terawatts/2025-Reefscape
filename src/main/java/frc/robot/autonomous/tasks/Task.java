package frc.robot.autonomous.tasks;

public interface Task {
    void start();
    void update();
    boolean isFinished();
    void done();
}

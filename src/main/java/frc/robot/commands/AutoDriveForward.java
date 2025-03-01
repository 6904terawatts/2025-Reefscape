package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class AutoDriveForward extends Command {
    private final Swerve m_swerve;
    private double startTime;
    private final double duration;
    private final double speed;

    public AutoDriveForward(double durationSeconds, double speedMetersPerSecond) {
        m_swerve = Swerve.getInstance();
        this.duration = durationSeconds;
        this.speed = speedMetersPerSecond;
        addRequirements(m_swerve);
    }

    @Override
    public void initialize() {
        if (m_swerve == null) {
            System.err.println("Swerve subsystem is null!");
            cancel();
            return;
        }
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        if (m_swerve != null) {
            m_swerve.drive(new Translation2d(speed, 0), 0, false, false);
        }
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime) >= duration;
    }

    @Override
    public void end(boolean interrupted) {
        if (m_swerve != null) {
            m_swerve.drive(new Translation2d(0, 0), 0, false, false);
        }
    }
}

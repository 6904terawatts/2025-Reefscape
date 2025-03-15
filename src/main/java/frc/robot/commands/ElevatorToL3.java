package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

public class ElevatorToL2 extends Command {
    private final Elevator m_elevator;
    private long startTime;

    public ElevatorToL2(Elevator elevator) {
        m_elevator = elevator;
        addRequirements(m_elevator);
    }

    @Override
    public void initialize() {
        m_elevator.goToElevatorL2();
        startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        // Use a 2 second timeout
        return (System.currentTimeMillis() - startTime) > 2000;
    }

    @Override
    public void end(boolean interrupted) {
        m_elevator.stop();
    }
}

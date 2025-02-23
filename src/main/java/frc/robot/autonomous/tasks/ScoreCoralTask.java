package frc.robot.autonomous.tasks;

import frc.robot.subsystems.Coral;

public class ScoreCoralTask implements Task {
    private final Coral m_coral = Coral.getInstance();
    private boolean m_isFinished = false;

    @Override
    public void start() {
        m_coral.scoreL24();
    }

    @Override
    public void update() {
        // Check if scoring is complete
        if (!m_coral.isHoldingCoralViaLaserCAN()) {
            m_isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return m_isFinished;
    }

    @Override
    public void done() {
        m_coral.stopCoral();
    }
}

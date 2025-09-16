package frc.robot.controls.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class KeyboardController extends FilteredController implements KeyListener {
    private Set<Integer> pressedKeys = new HashSet<>();
    private String m_smartDashboardKey = "KeyboardInput/";

    public KeyboardController() {
        super(-1, false, false); // No port for keyboard
        // Setup a JFrame to capture keyboard input
        JFrame frame = new JFrame("Keyboard Input Capture");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    private double getAxisValue(int positiveKey, int negativeKey) {
        boolean positive = pressedKeys.contains(positiveKey);
        boolean negative = pressedKeys.contains(negativeKey);
        if (positive && !negative) {
            return 1.0;
        } else if (!positive && negative) {
            return -1.0;
        } else {
            return 0.0;
        }
    }

    public double getForwardAxis() {
        // W is forward, S is backward
        return getAxisValue(KeyEvent.VK_W, KeyEvent.VK_S);
    }

    public double getStrafeAxis() {
        // D is right, A is left
        return getAxisValue(KeyEvent.VK_D, KeyEvent.VK_A);
    }

    public double getTurnAxis() {
        // Optional: Q and E for rotation
        return getAxisValue(KeyEvent.VK_E, KeyEvent.VK_Q);
    }

    public boolean getWantsRobotCentric() {
        // Default to false or map to a key, e.g., Shift
        return pressedKeys.contains(KeyEvent.VK_SHIFT);
    }

    public void outputTelemetry() {
        SmartDashboard.putNumber(m_smartDashboardKey + "Forward", getForwardAxis());
        SmartDashboard.putNumber(m_smartDashboardKey + "Strafe", getStrafeAxis());
        SmartDashboard.putNumber(m_smartDashboardKey + "Turn", getTurnAxis());
    }
}

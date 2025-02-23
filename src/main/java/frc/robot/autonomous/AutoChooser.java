package frc.robot.autonomous;

import java.util.function.Consumer;

public class AutoChooser {
    private static AutoChooser m_instance = null;
    private String m_selectedAuto;
    private Consumer<String> m_onChangeCallback;

    private AutoChooser() {}

    public static AutoChooser getInstance() {
        if (m_instance == null) {
            m_instance = new AutoChooser();
        }
        return m_instance;
    }

    public void setDefaultOption(String defaultAuto) {
        m_selectedAuto = defaultAuto;
    }

    public void setOnChangeCallback(Consumer<String> callback) {
        m_onChangeCallback = callback;
    }

    public String getSelectedAuto() {
        return m_selectedAuto;
    }
}

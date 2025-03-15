package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Coral;
import frc.robot.subsystems.Elevator;

public class ScoreCoralL2 extends SequentialCommandGroup {
    public ScoreCoralL2(Elevator elevator, Coral coral) {
        addCommands(
            new ElevatorToL2(elevator),
            new ScoreCoral(coral)
        );
    }
}

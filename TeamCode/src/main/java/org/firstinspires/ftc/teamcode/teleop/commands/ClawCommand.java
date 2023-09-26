package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;

public class ClawCommand extends CommandBase {
    private ClawSubsystem clawSubsystem;
    private boolean clawOpen;
    public ClawCommand(ClawSubsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
        clawSubsystem.close();
        clawOpen = false;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        if (clawOpen) {
            clawSubsystem.close();
        } else {
            clawSubsystem.open();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

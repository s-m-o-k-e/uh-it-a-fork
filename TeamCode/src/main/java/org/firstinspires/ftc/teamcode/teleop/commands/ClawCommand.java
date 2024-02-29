package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;

import java.util.concurrent.TimeUnit;

public class ClawCommand extends CommandBase {
    private ClawSubsystem clawSubsystem;
    private Timing.Timer timer;
    public ClawCommand(ClawSubsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
        clawSubsystem.close();
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        timer = new Timing.Timer(TeleOpConfig.CLAW_TIMER, TimeUnit.MILLISECONDS);
        timer.start();
        clawSubsystem.open();
    }

    @Override
    public void end(boolean interrupted) {
        clawSubsystem.close();
    }


    @Override
    public boolean isFinished() {
        return timer.done();
    }
}

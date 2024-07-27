package org.firstinspires.ftc.teamcode.teleop.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.LauncherSubsystem;

import java.util.concurrent.TimeUnit;

public class LauncherCommand extends CommandBase {
    private LauncherSubsystem launchSubsystem;
    private boolean clawOpen;


    public LauncherCommand(LauncherSubsystem launchSubsystem) {
        this.launchSubsystem = launchSubsystem;
        //launchSubsystem.hold();
        //clawOpen = false;
        addRequirements(launchSubsystem);
    }

    @Override
    public void initialize() {
        if (clawOpen) {
            launchSubsystem.hold();
            clawOpen = !clawOpen;
        } else {
            launchSubsystem.fire();

        }
        clawOpen = !clawOpen;



    }


    @Override
    public boolean isFinished() {
        return true;
    }
}

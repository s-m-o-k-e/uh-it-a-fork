package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.teleop.subsystems.SlideSubsystem;

public class SlideStupid extends CommandBase {

    private SlideSubsystem slideSubsystem;
    public SlideStupid (SlideSubsystem slideSubsystem) {
        this.slideSubsystem = slideSubsystem;
        addRequirements(slideSubsystem);
    }


}

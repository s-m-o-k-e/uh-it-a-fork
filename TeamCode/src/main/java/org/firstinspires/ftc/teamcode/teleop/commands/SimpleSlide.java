package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.SlideSubsystem;

import java.util.function.DoubleSupplier;

public class SimpleSlide extends CommandBase {
    private SlideSubsystem slideSubsystem;
    private DoubleSupplier power;

    public SimpleSlide (SlideSubsystem slideSubsystem, DoubleSupplier power) {
        this.slideSubsystem = slideSubsystem;
        this.power =power;
        addRequirements(slideSubsystem);
    }
    @Override
    public void initialize() {
        slideSubsystem.slidePower(0);

    }

    @Override
    public void execute() {
        slideSubsystem.slidePower(power.getAsDouble());
    }


    @Override
    public void end(boolean interrupted) {
        slideSubsystem.slidePower(0);
    }
}
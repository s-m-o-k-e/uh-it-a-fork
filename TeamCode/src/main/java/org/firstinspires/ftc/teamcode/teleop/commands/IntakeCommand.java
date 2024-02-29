package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.SlideSubsystem;

import java.util.function.DoubleSupplier;

public class IntakeCommand extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private DoubleSupplier power;


    public IntakeCommand (IntakeSubsystem slideSubsystem, DoubleSupplier power) {
        this.intakeSubsystem = slideSubsystem;
        this.power =power;
        addRequirements(intakeSubsystem);
    }
    @Override
    public void initialize() {
        intakeSubsystem.stopIntake();

    }

    @Override
    public void execute() {
        intakeSubsystem.move(power.getAsDouble() * TeleOpConfig.INTAKE_SPEED);
    }


    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stopIntake();
    }
}
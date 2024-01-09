package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import org.firstinspires.ftc.teamcode.teleop.subsystems.SlideSubsystem;

public class SlideCommand extends CommandBase {
    private SlideSubsystem slideSubsystem;
    private Positions pos;
    private PIDFController pidfController;
    private int setPoint;
    public enum Positions {
        BOTTOM,
        MIDDLE,
        TOP
    }
    public SlideCommand (SlideSubsystem slideSubsystem, Positions pos) {
        this.slideSubsystem = slideSubsystem;
        this.pos = pos;
        this.pidfController = new PIDFController(0, 0, 0, 0);
        pidfController.setTolerance(20);
        addRequirements(slideSubsystem);
    }
    @Override
    public void initialize() {
        switch (pos) {
            case BOTTOM:
                setPoint = 0;
                break;
            case MIDDLE:
                setPoint = 1;
                break;
            case TOP:
                setPoint = 2;
                break;
            default:
                setPoint = 0;
                break;
        }
    }

    @Override
    public void execute() {
        slideSubsystem.slidePower(pidfController.calculate(slideSubsystem.getMotorPosition()[0], setPoint));
    }

    @Override
    public void end(boolean interrupted) {
        slideSubsystem.slidePower(0);
    }

    @Override
    public boolean isFinished() {
        return pidfController.atSetPoint();
    }
}
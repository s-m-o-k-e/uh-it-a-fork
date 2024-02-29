package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultDrive extends CommandBase {
    private final DriveSubsystem drive;

    private final DoubleSupplier strafeSpeed;
    private final DoubleSupplier forwardSpeed;
    private final DoubleSupplier turnSpeed;

    private final BooleanSupplier leftBumper;
    private final BooleanSupplier rightBumper;

    public DefaultDrive(DriveSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, BooleanSupplier left, BooleanSupplier right) {
        drive = subsystem;
        strafeSpeed = strafe;
        forwardSpeed = forward;
        turnSpeed = turn;
        leftBumper = left;
        rightBumper = right;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        double strafe = strafeSpeed.getAsDouble();
        double forward = forwardSpeed.getAsDouble();
        double turn = turnSpeed.getAsDouble();
        if (leftBumper.getAsBoolean() || rightBumper.getAsBoolean()) {
            strafe *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
            forward *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
            turn *= TeleOpConfig.PRECISION_TURN_MULTIPLIER;
        }
        drive.drive(strafe, forward, -turn);
    }

}

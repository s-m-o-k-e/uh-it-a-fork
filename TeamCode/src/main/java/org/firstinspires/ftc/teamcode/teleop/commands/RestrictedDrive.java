package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.OdometrySubsystem;

import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class RestrictedDrive extends CommandBase {
    private final DriveSubsystem drive;

    private final DoubleSupplier strafeSpeed;
    private final DoubleSupplier forwardSpeed;
    private final DoubleSupplier turnSpeed;

    private final BooleanSupplier leftBumper;
    private final BooleanSupplier rightBumper;

    private final double restrictedX;
    private final double restrictedY;

    private final OdometrySubsystem odo;

    public RestrictedDrive(DriveSubsystem subsystem, OdometrySubsystem odo, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, BooleanSupplier left, BooleanSupplier right, double restrictedX1, double restrictedY1) {
        drive = subsystem;
        strafeSpeed = strafe;
        forwardSpeed = forward;
        turnSpeed = turn;
        leftBumper = left;
        rightBumper = right;
        addRequirements(subsystem);
        restrictedX = restrictedX1;
        restrictedY = restrictedY1;
        this.odo = odo;
        addRequirements(odo);

    }

    @Override
    public void execute() {
        double strafe = strafeSpeed.getAsDouble();
        double forward = forwardSpeed.getAsDouble();
        double turn = 0;
        if (leftBumper.getAsBoolean() || rightBumper.getAsBoolean()) {
            strafe *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
            forward *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
            turn *= TeleOpConfig.PRECISION_TURN_MULTIPLIER;
        }

        drive.drive(strafe, forward, turn);

        if (Math.abs(odo.getPose().getX()) >= restrictedX || Math.abs(odo.getPose().getY()) >= restrictedY){
            drive.drive(0,0,0);
        }
        else{
            drive.drive(strafe, forward, turn);

        }
    }


}

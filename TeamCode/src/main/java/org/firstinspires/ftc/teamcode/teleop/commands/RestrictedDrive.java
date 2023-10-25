package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class RestrictedDrive extends CommandBase {
    private final DriveSubsystem drive;

    private final IMU imu;

    private final DoubleSupplier runtime;

    private final DoubleSupplier strafeSpeed;
    private final DoubleSupplier forwardSpeed;
    private final DoubleSupplier turnSpeed;

    private final BooleanSupplier leftBumper;
    private final BooleanSupplier rightBumper;

    private final Telemetry telemtry;

    private final double restrictedX;
    private final double restrictedY;

    private double posX;
    private double posY;

    private double prevTime;


    public RestrictedDrive(DriveSubsystem subsystem, IMU imu, Telemetry telemetry, DoubleSupplier runtime, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, BooleanSupplier left, BooleanSupplier right, double restrictedX1, double restrictedY1) {
        drive = subsystem;
        this.imu = imu;
        this.runtime = runtime;
        this.telemtry = telemetry;
        strafeSpeed = strafe;
        forwardSpeed = forward;
        turnSpeed = turn;
        leftBumper = left;
        rightBumper = right;
        addRequirements(subsystem);
        restrictedX = restrictedX1;
        restrictedY = restrictedY1;

    }

    public void updateLocalization() {
        // thx: https://ftc-code.gitbook.io/tech-toolbox/this-domain-is-now-depreciated-and-is-no-longer-updated-1/mecanum-no-deadwheels
        // backRight, backLeft, frontRight, frontLeft
        double[] wheelVelocities = drive.getMotorVelocities();
        double vX = Arrays.stream(wheelVelocities).sum() * 0.5;
        double vY = (-wheelVelocities[3] + wheelVelocities[2] + wheelVelocities[1] - wheelVelocities[0]) * 0.5;

        double turnAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double nx = (vX*Math.cos(turnAngle)-vY*Math.sin(turnAngle));
        double ny = (vX*Math.sin(turnAngle)+vY*Math.cos(turnAngle));

        vX = nx; vY = ny;

        posY += (vY*(runtime.getAsDouble()-prevTime))/TeleOpConfig.TICKS_TO_INCHES;
        posX += (vX*(runtime.getAsDouble()-prevTime))/TeleOpConfig.TICKS_TO_INCHES;
        prevTime = runtime.getAsDouble();
    }

    @Override
    public void execute() {
        double strafe = strafeSpeed.getAsDouble();
        double forward = forwardSpeed.getAsDouble();
        double turn = 0;
        strafe *= 0.5;
        forward *= 0.5;
        turn *= 0.5;
        updateLocalization();
        telemtry.addData("x", posX);
        telemtry.addData("y", posY);
        telemtry.update();
        if (Math.abs(posX) >= restrictedX || Math.abs(posY) >= restrictedY){
            drive.drive(strafe * 0.2,forward * 0.2,turn * 0.2);
        } else {
            drive.drive(strafe, forward, turn);
        }
    }


}

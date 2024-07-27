package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.kinematics.DifferentialOdometry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleop.commands.ClawCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.commands.RestrictedDrive;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@TeleOp(name="SchoolChildren TeleOp", group = "Apex Robotics 3916")
public class SchoolChildrenTeleOp extends CommandOpMode {

    private GamepadEx driver, codriver;
    private OverflowEncoder par, perp;
    private DriveSubsystem drive;
    private RestrictedDrive driveCommand;
    private ClawSubsystem claw;
    private ClawCommand clawCommand;

    private IMU imu;

    private DifferentialOdometry odom;
    @Override
    public void initialize() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashTelemetry = dashboard.getTelemetry();

        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(parameters);

        drive = new DriveSubsystem(hardwareMap, "rightBack", "leftBack", "rightFront", "leftFront");
        driveCommand = new RestrictedDrive(drive, imu, telemetry, this::getRuntime, driver::getLeftX, driver::getLeftY, driver::getRightX,
                () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),  () -> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));

        register(drive);
        drive.setDefaultCommand(driveCommand);

        //claw = new ClawSubsystem(hardwareMap, "clawServo", 0, 360);

        //clawCommand = new ClawCommand(claw);

        //new GamepadButton(driver, GamepadKeys.Button.A).whenPressed(clawCommand);

        //schedule(clawCommand);
    }
}

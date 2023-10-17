package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.kinematics.DifferentialOdometry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

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

    private OdometrySubsystem odometrySubsystem;
    private DifferentialOdometry odom;
    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);

        par = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "par")));
        perp = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "perp")));

        odom = new DifferentialOdometry(
                () -> par.getPositionAndVelocity().position,
                () -> perp.getPositionAndVelocity().position,
                TeleOpConfig.TRACKWIDTH
        );

        drive = new DriveSubsystem(hardwareMap, "rightBack", "leftBack", "rightFront", "leftFront");
        odometrySubsystem = new OdometrySubsystem(odom);
        driveCommand = new RestrictedDrive(drive, odometrySubsystem, driver::getLeftX, driver::getLeftY, driver::getRightX,
                () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),  () -> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER), 10, 10);

        register(drive);
        drive.setDefaultCommand(driveCommand);

        claw = new ClawSubsystem(hardwareMap, "clawServo", 0, 360);

        clawCommand = new ClawCommand(claw);

        new GamepadButton(driver, GamepadKeys.Button.A).whenPressed(clawCommand);

        schedule(clawCommand);
    }
}

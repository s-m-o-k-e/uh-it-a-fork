package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.teleop.commands.ClawCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@TeleOp(name="Main TeleOp", group = "Apex Robotics 3916")
public class MainTeleOp extends CommandOpMode {

    private GamepadEx driver, codriver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;
    private ClawSubsystem claw;
    private ClawCommand clawCommand;
    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);

        drive = new DriveSubsystem(hardwareMap, "backRight", "backLight", "frontRight", "frontLeft");

        driveCommand = new DefaultDrive(drive, driver::getLeftX, driver::getLeftY, driver::getRightX,
                () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),  () -> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));

        register(drive);
        drive.setDefaultCommand(driveCommand);

        claw = new ClawSubsystem(hardwareMap, "clawServo", 0, 360);

        clawCommand = new ClawCommand(claw);

        new GamepadButton(driver, GamepadKeys.Button.A).whenPressed(clawCommand);

        schedule(clawCommand);
    }
}

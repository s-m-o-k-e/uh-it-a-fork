package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.teleop.commands.ClawCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.SimpleSlide;
import org.firstinspires.ftc.teamcode.teleop.commands.SlideCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.SlideSubsystem;

@TeleOp(name="Just Drive TeleOp", group = "Apex Robotics 3916")
public class JustDrive extends CommandOpMode {

    private GamepadEx driver, codriver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;

    private IntakeSubsystem intake;

    private IntakeCommand incommand;
    private IntakeCommand outcommand;
    private IntakeCommand nomove;

    private SlideSubsystem slide;
    private SimpleSlide up;
    private SimpleSlide down;
    private SimpleSlide stop;
    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);

        drive = new DriveSubsystem(hardwareMap, "rightBack", "leftBack", "rightFront", "leftFront");

        driveCommand = new DefaultDrive(drive, driver::getLeftX, driver::getLeftY, driver::getRightX,
                () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),  () -> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));

        register(drive);
        drive.setDefaultCommand(driveCommand);

        intake = new IntakeSubsystem(new MotorEx(hardwareMap, "inmotor"));

        incommand = new IntakeCommand(intake, 1);
        outcommand = new IntakeCommand(intake, -1);
        nomove = new IntakeCommand(intake, 0);

        new GamepadButton(driver, GamepadKeys.Button.A).whenPressed(incommand, true);
        new GamepadButton(driver, GamepadKeys.Button.B).whenPressed(outcommand, true);
        new GamepadButton(driver, GamepadKeys.Button.X).whenPressed(nomove, true);
        intake.setDefaultCommand(nomove);

        slide = new SlideSubsystem(hardwareMap, "left", "right");

        up = new SimpleSlide(slide, 0.5);
        down = new SimpleSlide(slide, -0.5);
        stop = new SimpleSlide(slide, 0);

        new GamepadButton(driver, GamepadKeys.Button.DPAD_DOWN).whenPressed(down, true);
        new GamepadButton(driver, GamepadKeys.Button.DPAD_UP).whenPressed(up, true);
        new GamepadButton(driver, GamepadKeys.Button.DPAD_RIGHT).whenPressed(stop, true);
        slide.setDefaultCommand(stop);
    }
}

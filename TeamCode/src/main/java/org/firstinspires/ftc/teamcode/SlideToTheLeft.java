package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@Autonomous(group = "drive", name="Slide to the Left")
public class SlideToTheLeft extends SlideToTheRight {
    private int xDirection = -1;
}

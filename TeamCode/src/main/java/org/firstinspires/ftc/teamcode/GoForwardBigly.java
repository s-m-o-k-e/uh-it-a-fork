package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@Autonomous(group = "drive", name="Go forward bigly")
public class GoForwardBigly extends OpMode {
    private static double START_TIME = 15;
    public static double STOP_TIME = 17.4;
    private double forwardDirection = .1;
    private DriveSubsystem ds;

    @Override
    public void init() {
        ds = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront");
    }

    @Override
    public void loop() {
        if ((getRuntime() > (START_TIME * 1000)) && (getRuntime() > (STOP_TIME * 1000))) {
            ds.drive(0, forwardDirection, 0);
        } if (getRuntime() > (STOP_TIME * 1000)) {
            stop();
        }
    }
}
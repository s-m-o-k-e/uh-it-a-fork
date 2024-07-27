package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@Autonomous(group = "drive", name="Slide Far to the Left")
public class SlideFarToTheLeft extends OpMode {
    public static double TIME = 5;
    private double xDirection = -1;
    private double forwardDirection = 0.1;
    private DriveSubsystem ds;
    @Override
    public void init() {
        ds = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront");
    }

    @Override
    public void loop() {
        ds.drive(xDirection, forwardDirection, 0);
        if (getRuntime() > (TIME * 1000)) {
            stop();
        } else if (getRuntime() > 400) {
            forwardDirection = 0;
        }
    }
}

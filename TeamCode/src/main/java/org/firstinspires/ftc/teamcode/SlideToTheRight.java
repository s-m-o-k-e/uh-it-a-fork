package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@Autonomous(group = "drive", name="Slide to the Right")
public class SlideToTheRight extends OpMode {
    public static double TIME = 1;
    private double xDirection = 1;//.5
    private double forwardDirection = 0;
    private DriveSubsystem ds;
    @Override
    public void init() {
        ds = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront");
    }

    @Override
    public void loop() {
        ds.drive(xDirection, forwardDirection, 0);
        if (getRuntime() > (TIME)) {
            stop();
        } else if (getRuntime() > 4) {
            forwardDirection = 0;
        }
    }
}

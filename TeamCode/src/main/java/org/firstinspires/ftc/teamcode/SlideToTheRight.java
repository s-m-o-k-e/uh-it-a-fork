package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@Autonomous(group = "drive", name="Slide to the Right")
public class SlideToTheRight extends OpMode {
    public static double TIME = 4;
    private int xDirection = 1;
    private DriveSubsystem ds;
    @Override
    public void init() {
        ds = new DriveSubsystem(hardwareMap, "rightBack", "leftBack", "rightFront", "leftFront");
    }k

    @Override
    public void loop() {
        ds.drive(xDirection, 0, 0);
        if (getRuntime() > (TIME * 1000)) {
            stop();
        }
    }
}

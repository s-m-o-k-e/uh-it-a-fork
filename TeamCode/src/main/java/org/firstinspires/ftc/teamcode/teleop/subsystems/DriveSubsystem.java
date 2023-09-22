package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {

    private final MecanumDrive drive;

    public DriveSubsystem(MotorEx backRight, MotorEx backLeft, MotorEx frontRight, MotorEx frontLeft) {
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public DriveSubsystem(HardwareMap hw, String backRight, String backLeft, String frontRight, String frontLeft) {
        MotorEx bR = new MotorEx(hw, backRight);
        MotorEx bL = new MotorEx(hw, backLeft);
        MotorEx fR = new MotorEx(hw, frontRight);
        MotorEx fL = new MotorEx(hw, frontLeft);
        drive = new MecanumDrive(fL, fR, bL, bR);
    }

    public void drive(double strafe, double forward, double turn) {
        drive.driveRobotCentric(strafe, forward, turn);
    }

    public void drive(double strafe, double forward, double turn, double heading) {
        drive.driveFieldCentric(strafe, forward, turn, heading);
    }

}

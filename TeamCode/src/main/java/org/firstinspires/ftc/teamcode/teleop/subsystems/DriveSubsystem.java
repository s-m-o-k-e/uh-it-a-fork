package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {

    private final Me canumDrive drive;
    private final MotorEx backRight;
    private final MotorEx backLeft;
    private final MotorEx frontRight;
    private final MotorEx frontLeft;


    public DriveSubsystem(MotorEx backRight, MotorEx backLeft, MotorEx frontRight, MotorEx frontLeft) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }


    public DriveSubsystem(HardwareMap hw, String backRight, String backLeft, String frontRight, String frontLeft) {
        this.backRight = new MotorEx(hw, backRight);
        this.backLeft = new MotorEx(hw, backLeft);
        this.frontRight = new MotorEx(hw, frontRight);
        this.frontLeft = new MotorEx(hw, frontLeft);
        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);
    }

    public void drive(double strafe, double forward, double turn) {
        drive.driveRobotCentric(strafe, forward, turn);
    }

    public void drive(double strafe, double forward, double turn, double heading) {
        drive.driveFieldCentric(strafe, forward, turn, heading);
    }

    public double[] getMotorVelocities(){
        return new double[]{backRight.encoder.getCorrectedVelocity(), backLeft.encoder.getCorrectedVelocity(), frontRight.encoder.getCorrectedVelocity(), frontLeft.encoder.getCorrectedVelocity()};
    }

}

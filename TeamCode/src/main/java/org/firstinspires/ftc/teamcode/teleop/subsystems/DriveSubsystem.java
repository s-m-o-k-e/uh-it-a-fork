package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {

    private final MecanumDrive drive;
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
//current correct config:
    //port 0 = leftBack
    //port 1 = leftFront
    //port 2 = rightFront
    //port 3 = rightBack

    public DriveSubsystem(HardwareMap hw, String backRight, String backLeft, String frontRight, String frontLeft) {
        this.backRight = new MotorEx(hw, backRight);
        this.backLeft = new MotorEx(hw, backLeft);
        this.frontRight = new MotorEx(hw, frontRight);
        this.frontLeft = new MotorEx(hw, frontLeft);

        //correct:
        //this.backLeft = new MotorEx(hw, backRight);
        //this.backRight = new MotorEx(hw, backLeft);
        //this.frontLeft = new MotorEx(hw, frontRight);
        //this.frontRight = new MotorEx(hw, frontLeft);
        //or

        //this.backLeft = new MotorEx(hw, backLeft);
        //this.backRight = new MotorEx(hw, frontRight);
        //this.frontRight = new MotorEx(hw, frontLeft);
        //this.frontLeft = new MotorEx(hw, backRight);

        //adjust as needed
        this.backLeft.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backRight.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        //this.frontLeft.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontRight.motor.setDirection(DcMotorSimple.Direction.REVERSE);


        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);

    }

    //public void drive(double strafe, double forward, double turn) {
      //  drive.driveRobotCentric(forward, strafe, turn);
    //}
    //possible fix:
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

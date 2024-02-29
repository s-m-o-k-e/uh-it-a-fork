package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/***
* @author Jude Naramor
 * @author Vikram K
 * */
public class SlideSubsystem extends SubsystemBase {
    private final MotorEx frontMotor;
    private final MotorEx backMotor;
    public SlideSubsystem(HardwareMap hw, String frontMotor, String backMotor) {
        this.frontMotor = new MotorEx(hw, frontMotor);
        this.backMotor = new MotorEx(hw, backMotor);
        this.backMotor.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        this.backMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        this.frontMotor.setRunMode(MotorEx.RunMode.RawPower);
        this.backMotor.setRunMode(MotorEx.RunMode.RawPower);
    }

    public void slidePower(double velocity) {
        frontMotor.set(velocity);
        backMotor.set(velocity);
    }

    public double[] getMotorVelocities(){
        return new double[]{frontMotor.encoder.getCorrectedVelocity(), backMotor.encoder.getCorrectedVelocity()};
    }

    public double[] getMotorPosition(){
        return new double[]{frontMotor.encoder.getPosition(), backMotor.encoder.getPosition()};
    }
}

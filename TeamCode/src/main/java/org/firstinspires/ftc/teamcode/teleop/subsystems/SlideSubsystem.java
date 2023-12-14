package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideSubsystem extends SubsystemBase {
    private final MotorEx frontMotor;
    private final MotorEx backMotor;
    public SlideSubsystem(HardwareMap hw, String frontMotor, String backMotor) {
        this.frontMotor = new MotorEx(hw, frontMotor);
        this.backMotor = new MotorEx(hw, backMotor);
    }
    public void raiseSlide() {
//        frontMotor.setRunMode(MotorEx.RunMode.RawPower);
//        backMotor.setRunMode(MotorEx.RunMode.RawPower);
        frontMotor.set(.20);
        backMotor.set(.20);
    }
    public void lowerSlide() {
//        frontMotor.setRunMode(MotorEx.RunMode.RawPower);
//        backMotor.setRunMode(MotorEx.RunMode.RawPower);
        frontMotor.set(-.20);
        backMotor.set(-.20);
    }

    public void stopSlide() {
        frontMotor.stopMotor();
        backMotor.stopMotor();
    }

    public double[] getMotorVelocities(){
        return new double[]{frontMotor.encoder.getCorrectedVelocity(), backMotor.encoder.getCorrectedVelocity(),frontMotor.encoder.getPosition(),backMotor.encoder.getPosition()};
    }
}

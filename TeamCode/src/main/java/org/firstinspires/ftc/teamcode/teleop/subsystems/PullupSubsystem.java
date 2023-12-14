package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PullupSubsystem extends SubsystemBase {
    private final MotorEx motor;
    public PullupSubsystem(HardwareMap hw, String motor) {
        this.motor = new MotorEx(hw, motor);
    }
}

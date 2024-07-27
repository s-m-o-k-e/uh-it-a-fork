package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.TeleOpConfig;

 public class LauncherSubsystem extends SubsystemBase {
    private SimpleServo servo;

    public LauncherSubsystem(HardwareMap hw, String servoName, double minDegree, double maxDegree) {
        servo = new SimpleServo(hw, servoName, minDegree, maxDegree);
    }

    public void hold() {
        servo.setPosition(TeleOpConfig.LAUNCHER_HOLD_DEGREES);
    }

    public void fire() {
        servo.setPosition(TeleOpConfig.LAUNCHER_FIRE_DEGREES);
    }
}
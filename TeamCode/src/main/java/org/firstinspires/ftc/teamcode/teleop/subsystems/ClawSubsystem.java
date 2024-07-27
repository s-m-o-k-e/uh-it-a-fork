package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.TeleOpConfig;

public class ClawSubsystem extends SubsystemBase {
    private SimpleServo servo;
    //private boolean isOpen = false;

    public ClawSubsystem(HardwareMap hw, String servoName, double minDegree, double maxDegree) {
        servo = new SimpleServo(hw, servoName, minDegree, maxDegree);
    }

    public void close() {

        servo.setPosition(TeleOpConfig.CLAW_CLOSE_DEGREES);
    }

    public void open() {

        servo.setPosition(TeleOpConfig.CLAW_OPEN_DEGREES);
    }
}

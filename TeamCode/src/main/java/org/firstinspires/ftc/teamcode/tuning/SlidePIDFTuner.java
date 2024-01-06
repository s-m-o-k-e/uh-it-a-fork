package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
@Config
public class SlidePIDFTuner extends OpMode {
    private PIDFController pidfController;
    private MotorEx leftMotor;
    private MotorEx rightMotor;
    private FtcDashboard dashboard;
    private Telemetry telemetry;

    public static double p = 0, i = 0, d = 0, f = 0;
    public static int target = 0;

    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        pidfController = new PIDFController(p, i, d, f);
        leftMotor = new MotorEx(hardwareMap, "leftSlide");
        rightMotor = new MotorEx(hardwareMap, "rightSlide");
        leftMotor.setRunMode(Motor.RunMode.RawPower);
        rightMotor.setRunMode(Motor.RunMode.RawPower);
    }

    @Override
    public void loop() {
        pidfController.setPIDF(p, i, d, f);
        int pos = leftMotor.getCurrentPosition();
        double power = pidfController.calculate(pos, target);

        leftMotor.set(power);
        rightMotor.set(power);

        telemetry.addData("pos", pos);
        telemetry.addData("target", target);
        telemetry.update();
    }
}

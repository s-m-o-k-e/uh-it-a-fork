package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.ColorProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

public class AutoCamTest extends OpMode {
    ColorProcessor processor;
    VisionPortal portal;
    FtcDashboard dash;
    Telemetry telemetry;
    @Override
    public void init() {
        processor = new ColorProcessor();
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "webcam"))
                .addProcessor(processor)
                .build();
        dash = FtcDashboard.getInstance();
        telemetry = dash.getTelemetry();
    }

    @Override
    public void loop() {
        telemetry.addData("Position", processor.getPropPosition());
        telemetry.update();
    }
}

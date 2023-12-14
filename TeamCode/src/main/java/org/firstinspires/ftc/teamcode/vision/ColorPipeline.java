package org.firstinspires.ftc.teamcode.vision;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Core;
import org.opencv.core.Point;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.util.*;

public class ColorPipeline extends OpenCvPipeline {

    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);

    private Mat hslMat       = new Mat();
    private Mat binaryMat      = new Mat();
    private Mat maskedInputMat = new Mat();

    Telemetry telemetry;

    public ColorPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hslMat, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hslMat, lower, upper, binaryMat);
        maskedInputMat.release();

        Core.bitwise_and(input, input, maskedInputMat, binaryMat);

        int countX = 0;
        int countY = 0;
        int count = 0;
        for (int i = 0; i < binaryMat.rows(); i++){
            for (int j = 0; j < binaryMat.cols(); j++){
                double[] pixel = binaryMat.get(i, j);
                if (pixel[0] > 0) {
                    countX += j;
                    countY += i;
                    count++;
                }
            }
        }

        if (count != 0) {
            telemetry.addData("X", countX/count);
            telemetry.addData("Y", countY/count);
            telemetry.update();
            maskedInputMat.put(countY/count, countX/count, 0.0, 0.0, 255.0, 0.0);
        }
        return maskedInputMat;
    }
}
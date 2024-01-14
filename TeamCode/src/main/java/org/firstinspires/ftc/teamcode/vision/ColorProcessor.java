package org.firstinspires.ftc.teamcode.vision;

import android.graphics.Canvas;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ColorProcessor implements VisionProcessor {

    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);

    private Mat hslMat       = new Mat();
    private Mat binaryMat      = new Mat();
    private Mat maskedInputMat = new Mat();

    public enum PropPos {
        LEFT,
        MIDDLE,
        RIGHT
    }

    private PropPos position;
    @Override
    public void init(int width, int height, CameraCalibration calibration) {

    }

    @Override
    public Object processFrame(Mat input, long captureTimeNanos) {
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
            maskedInputMat.put(countY/count, countX/count, 0.0, 0.0, 255.0, 0.0);
        }

        int x = countX/count;

        if (x < 200) {
            position = PropPos.LEFT;
        } else if (x < 400) {
            position = PropPos.MIDDLE;
        } else {
            position = PropPos.RIGHT;
        }

        return maskedInputMat;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {

    }

    public PropPos getPropPosition() {
        return position;
    }
}

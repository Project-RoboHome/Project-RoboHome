package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class Pipeline extends OpenCvPipeline {

    public Pipeline() {}

    @Override
    public Mat processFrame(Mat input) {
        return input;
    }
}
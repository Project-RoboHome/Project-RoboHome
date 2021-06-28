package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class Vision {

    private OpenCvCamera cam;

    public Vision(LinearOpMode op) {
        int cameraMonitorViewId = op.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", op.hardwareMap.appContext.getPackageName());
        cam = OpenCvCameraFactory.getInstance().createWebcam(op.hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
        cam.setPipeline(new Pipeline());
    }

    public void start() {
        cam.openCameraDeviceAsync(() -> cam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT));
        FtcDashboard.getInstance().startCameraStream(cam, 0);
    }

    public void stop() {
        cam.stopStreaming();
        cam.closeCameraDevice();
        FtcDashboard.getInstance().stopCameraStream();
    }
}
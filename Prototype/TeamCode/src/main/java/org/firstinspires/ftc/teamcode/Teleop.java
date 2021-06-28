package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "1 Teleop")
@Config
public class Teleop extends LinearOpMode {
    private DcMotor motorRight;
    private DcMotor motorLeft;

    private double rightPower;
    private double leftPower;

    private FtcDashboard dashboard;
    private TelemetryPacket packet;

    public static double velocityGain = 0.5;

    @Override
    public void runOpMode() {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");

        dashboard = FtcDashboard.getInstance();
        packet = new TelemetryPacket();

        Vision detector = new Vision(this);
        detector.start();

        waitForStart();

        while (opModeIsActive()) {
            rightPower = velocityGain * (gamepad1.right_trigger - gamepad1.left_trigger + gamepad1.left_stick_x);
            leftPower = velocityGain * (gamepad1.right_trigger - gamepad1.left_trigger - gamepad1.left_stick_x);

            motorRight.setPower(rightPower);
            motorLeft.setPower(leftPower);

            packet.put("Velocity Gain", velocityGain);
            packet.put("Right Power", rightPower);
            packet.put("Left Power", leftPower);
            dashboard.sendTelemetryPacket(packet);
            packet = new TelemetryPacket();

            telemetry.addData("Velocity Gain", velocityGain);
            telemetry.addData("Right Power", rightPower);
            telemetry.addData("Left Power", leftPower);
            telemetry.update();
        }

        detector.stop();
    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MecanumOpMode", group = "TeleOpModes")
public class TeleOpMode extends LinearOpMode {
    Robot robot;

    public void init(Gamepad gamepad1, Gamepad gamepad2, HardwareMap hardwareMap) {
        robot = new Robot(gamepad1, gamepad2, hardwareMap.get(Servo.class, "intake"), hardwareMap.get(DcMotor.class, "lift"), hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"), hardwareMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void runOpMode() {
        robot = new Robot(gamepad1, gamepad2, hardwareMap.get(Servo.class, "intake"), new DcMotor[]{hardwareMap.get(DcMotor.class, "lift1"), hardwareMap.get(DcMotor.class, "lift2")}, hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"), hardwareMap.get(BNO055IMU.class, "imu"), hardwareMap);

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive() && !gamepad2.x) {
                robot.run();

                //telemetry.addData("Servo Position", robot.intake.claw.getPosition());
                //telemetry.update();
            }
        }
    }
}

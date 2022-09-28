package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MecanumOpMode", group = "TeleOpModes")
public class MecanumOpMode extends OpMode {
    MecanumDrive drive;

    public void init() {
        drive = new MecanumDrive(hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"), gamepad1, hardwareMap.get(BNO055IMU.class, "imu"));
    }

    public void loop() {
        drive.fieldCentricDrive();
    }
}

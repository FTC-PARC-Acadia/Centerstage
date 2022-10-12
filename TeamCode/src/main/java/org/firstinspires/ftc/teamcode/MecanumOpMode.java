package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MecanumOpMode", group = "TeleOpModes")
public class MecanumOpMode extends OpMode {
    Intake drive;
    Servo servo;

    public void init() {
        drive = new Intake(hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"), hardwareMap.get(DcMotor.class, "lift"), hardwareMap.get(CRServo.class, "intake"), gamepad1, gamepad2, hardwareMap.get(BNO055IMU.class, "imu"));
    }

    public void loop() {
        drive.fieldCentricDrive();
        drive.lift();
    }
}

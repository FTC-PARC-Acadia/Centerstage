package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Direction.FORWARD;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MecanumOpMode", group = "TeleOpModes")
public class MecanumOpMode extends OpMode {
    Robot robot;

    public void init() {
        robot = new Robot(gamepad1, gamepad2, hardwareMap.get(Servo.class, "intake"), hardwareMap.get(DcMotor.class, "lift"), hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"), hardwareMap.get(BNO055IMU.class, "imu"));
    }

    public void loop() {
       robot.run(FORWARD);
    }
}

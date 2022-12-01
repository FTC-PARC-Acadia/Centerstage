package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoOpMode", group = "AutoOpModes")
public class AutoOpMode extends LinearOpMode {
    Robot robot;

    public void runOpMode() {
        robot = new Robot(hardwareMap.get(Servo.class, "intake"), hardwareMap.get(DcMotor.class, "lift"), hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"), hardwareMap.get(BNO055IMU.class, "imu"));

        waitForStart();

        robot.drive.move(Direction.FORWARD, 10);
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

public class Robot {

    Intake intake;
    Lift lift;
    MecanumDrive drive;
    
    public Robot(Gamepad gamepad1, Gamepad gamepad2, Servo claw, DcMotor[] lifts, DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, BNO055IMU imu, HardwareMap hardwareMap) {
        intake = new Intake(gamepad2, claw);
        drive = new MecanumDrive(gamepad1, frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, imu, hardwareMap);
        lift = new Lift(gamepad2, lifts);
    }

    public Robot(Servo claw, DcMotor[] lifts) {
        intake = new Intake(claw);
        lift = new Lift(lifts);
    }
    
    public void run() {
        drive.fieldCentricDrive();
        intake.grab();
        lift.liftByPush();
        lift.adjust();
    }
}

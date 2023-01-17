package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {

    Intake intake;
    Lift lift1;
    Lift lift2;
    MecanumDrive drive;
    
    public Robot(Gamepad gamepad1, Gamepad gamepad2, Servo claw, DcMotor[] lifts, DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, BNO055IMU imu) {
        intake = new Intake(gamepad2, claw);
        drive = new MecanumDrive(gamepad1, frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, imu);
        lift1 = new Lift(gamepad2, lifts[0]);
        lift2 = new Lift(gamepad2, lifts[1]);
    }

    public Robot(Servo claw, DcMotor[] lifts, DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, BNO055IMU imu) {
        intake = new Intake(claw);
        lift1 = new Lift(lifts[0]);
        lift2 = new Lift(lifts[1]);
        drive = new MecanumDrive(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive);
    }
    
    public void run() {
        drive.fieldCentricDrive();
        intake.grab();
        lift1.liftByPush();
        lift2.liftByPush();
        lift1.adjust();
        lift2.adjust();
    }
}

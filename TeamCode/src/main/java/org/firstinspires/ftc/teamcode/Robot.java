package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {

    Intake intake;
    Lift lift;
    MecanumDrive drive;
    
    public Robot(Gamepad gamepad1, Gamepad gamepad2, Servo claw, DcMotor lift, DcMotor frontLeftDrive, DcMotor backLeftDrive, DcMotor frontRightDrive, DcMotor backRightDrive, BNO055IMU imu) {
        intake = new Intake(gamepad2, claw);
        this.lift = new Lift(gamepad2, lift);
        drive = new MecanumDrive(gamepad1, frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, imu);
    }
    
    public void run() {
        drive.fieldCentricDrive();
        intake.grab();
        lift.liftByLevel();
        lift.liftByPush();
    }
}

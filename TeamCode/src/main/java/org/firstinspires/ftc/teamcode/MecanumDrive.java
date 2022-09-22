package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive {
    //Motor Variables
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    private Gamepad gamepad1;

    //Power Variables
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    public MecanumDrive(DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, Gamepad gamepad1) {
        this.frontLeftDrive = frontLeftDrive;
        this.frontRightDrive = frontRightDrive;
        this.backLeftDrive = backLeftDrive;
        this.backRightDrive = backRightDrive;

        this.gamepad1 = gamepad1;
    }

    public void robotCentricDrive() {
        double joystickAngle;
        double joystickMagnitude;

        //Use joystick data to get vector of joystick
        joystickAngle = gamepad1.left_stick_x < 0 ? Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) + Math.PI : Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
        joystickMagnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));

        //Mecanum math, joystick angle and magnitude --> motor power
        frontLeftPower = Range.clip((Math.sin(joystickAngle + Math.PI/4) * joystickMagnitude), -1, 1);
        backRightPower = Range.clip(-(Math.sin(joystickAngle + Math.PI/4) * joystickMagnitude), -1, 1);
        frontRightPower = Range.clip(-(Math.sin(joystickAngle - Math.PI/4) * joystickMagnitude), -1, 1);
        backLeftPower = Range.clip((Math.sin(joystickAngle - Math.PI/4) * joystickMagnitude), -1, 1);

        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        //Set motor power
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }

    public void fieldCentricDrive() {

    }

}

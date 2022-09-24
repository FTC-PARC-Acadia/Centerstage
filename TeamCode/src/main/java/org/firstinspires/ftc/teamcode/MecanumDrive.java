package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive {
    //Motor Variables
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    //Turn Variables
    private DcMotor frontLeftTurn;
    private DcMotor frontRightTurn;
    private DcMotor backLeftTurn;
    private DcMotor backRightTurn;

    private Gamepad gamepad1;
    private Gamepad gamepad2;

    //Power Variables
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    //More turn variables
    private double frontLeftTurnValue;
    private double frontRightTurnValue;
    private double backLeftTurnValue;
    private double backRightTurnValue;


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
        double turn;

        //Calculating magnitude of joystick vector
        joystickAngle = gamepad1.left_stick_x < 0 ? Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) + Math.PI : Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
        joystickMagnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
        turn = gamepad1.right_stick_x;

        //Mecanum math, joystick angle and magnitude --> motor power
        frontLeftPower = Range.clip((Math.sin(joystickAngle) * joystickMagnitude) - Math.cos(joystickAngle) * joystickMagnitude, -1, 1);
        backRightPower = Range.clip(-(Math.sin(joystickAngle) * joystickMagnitude - Math.cos(joystickAngle) * joystickMagnitude + turn), -1, 1);
        frontRightPower = Range.clip(-(Math.sin(joystickAngle) * joystickMagnitude) - Math.cos(joystickAngle) * joystickMagnitude, -1, 1);
        backLeftPower = Range.clip(-(-Math.sin(joystickAngle) * joystickMagnitude - Math.cos(joystickAngle) * joystickMagnitude + turn), -1, 1);

        //Set motor power
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }

    public void fieldCentricDrive() {

    }

}

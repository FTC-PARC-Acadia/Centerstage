package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive {
    //Motor Variables
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    //Power Variables
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    public MecanumDrive(String frontLeftDrive, String frontRightDrive, String backLeftDrive, String backRightDrive) {

        this.frontLeftDrive = hardwareMap.get(DcMotor.class, frontLeftDrive);
        this.frontRightDrive = hardwareMap.get(DcMotor.class, frontRightDrive);
        this.backLeftDrive = hardwareMap.get(DcMotor.class, backLeftDrive);
        this.backRightDrive = hardwareMap.get(DcMotor.class, backRightDrive);

    }

    public void robotCentricDrive() {
        double joystickAngle;
        double joystickMagnitude;

        //Use joystick data to get vector of joystick
        joystickAngle = Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
        joystickMagnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));

        //Mecanum math, joystick angle and magnitude --> motor power
        frontLeftPower = Range.clip(-(Math.sin(joystickAngle + Math.PI/4) * joystickMagnitude), -1, 1);
        backRightPower = Range.clip((Math.sin(joystickAngle + Math.PI/4) * joystickMagnitude), -1, 1);
        frontRightPower = Range.clip((Math.sin(joystickAngle - Math.PI/4) * joystickMagnitude), -1, 1);
        backLeftPower = Range.clip(-(Math.sin(joystickAngle - Math.PI/4) * joystickMagnitude), -1, 1);

        //Set motor power
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }

    public void fieldCentricDrive() {

    }

}

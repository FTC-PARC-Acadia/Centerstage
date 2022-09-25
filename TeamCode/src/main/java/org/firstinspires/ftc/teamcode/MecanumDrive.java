package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

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

    //IMU Variables
    BNO055IMU imu;
    Orientation orientation;

    public MecanumDrive(DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, Gamepad gamepad1, BNO055IMU imu) {
        this.frontLeftDrive = frontLeftDrive;
        this.frontRightDrive = frontRightDrive;
        this.backLeftDrive = backLeftDrive;
        this.backRightDrive = backRightDrive;

        this.gamepad1 = gamepad1;

        //Should pass this through from main class.
        this.imu = imu;
    }

    public void robotCentricDrive() {
        double joystickAngle;
        double joystickMagnitude;
        double turn;

        //Calculating magnitude of joystick vector
        joystickAngle = gamepad1.left_stick_x < 0 ? Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) + Math.PI : Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
        joystickMagnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
        turn = gamepad1.right_stick_x;

        drive(joystickAngle, joystickMagnitude, turn);
    }

    public void fieldCentricDrive() {
        //I think we just add angle to the joystickAngle of robotCentricDrive
        double joystickAngle;
        double joystickMagnitude;
        double turn;
        double newAngle;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        //Calculating magnitude of joystick vector
        joystickAngle = gamepad1.left_stick_x < 0 ? Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) + Math.PI : Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
        joystickMagnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
        turn = gamepad1.right_stick_x;



        drive(joystickAngle, joystickMagnitude, turn);
    }

    public void drive(double joystickAngle, double joystickMagnitude, double turn)
    {
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
}

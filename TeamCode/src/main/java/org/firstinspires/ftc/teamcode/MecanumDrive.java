package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
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

    //Joystick Variables
    private double joystickAngle;
    private double joystickMagnitude;
    private double turn;

    private float forwardAngle;

    //IMU Variables
    BNO055IMU imu;
    Orientation orientation;

    public MecanumDrive(DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, Gamepad gamepad1, BNO055IMU imu) {
        this.frontLeftDrive = frontLeftDrive;
        this.frontRightDrive = frontRightDrive;
        this.backLeftDrive = backLeftDrive;
        this.backRightDrive = backRightDrive;

        this.gamepad1 = gamepad1;
        
        this.imu = imu;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);
        orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        forwardAngle = orientation.firstAngle;
    }

    public void robotCentricDrive() {
        drive(0);
    }

    public void fieldCentricDrive() {
        orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        drive(forwardAngle - orientation.firstAngle);
    }

    public void drive(double adjustmentAngle)
    {
        //Vector Math
        joystickAngle = gamepad1.left_stick_x < 0 ? Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) + Math.PI : Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
        joystickAngle -= adjustmentAngle;
        joystickMagnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
        turn = gamepad1.right_stick_x;

        //Mecanum math, joystick angle and magnitude --> motor power
        double powerFrontLeftBackRight = (Math.sin(joystickAngle) - Math.cos(joystickAngle)) * joystickMagnitude;
        double powerFrontRightBackLeft = (-Math.sin(joystickAngle) - Math.cos(joystickAngle)) * joystickMagnitude;

        if (Double.isNaN(powerFrontLeftBackRight))
        {
            powerFrontLeftBackRight = 0D;
        }
        if (Double.isNaN(powerFrontRightBackLeft))
        {
            powerFrontRightBackLeft = 0D;
        }
        if (gamepad1.x)
        {
            forwardAngle = orientation.firstAngle;
        }

        //Combining power and turn
        frontLeftPower = Range.clip(powerFrontLeftBackRight - turn, -1, 1);
        backRightPower = Range.clip(-(powerFrontLeftBackRight + turn), -1, 1);
        frontRightPower = Range.clip(powerFrontRightBackLeft - turn, -1, 1);
        backLeftPower = Range.clip(-(powerFrontRightBackLeft + turn), -1, 1);

        //Set motor power
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }
}

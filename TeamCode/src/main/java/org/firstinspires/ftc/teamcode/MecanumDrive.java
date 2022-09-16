package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive {
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    private double frontRightBackLeftPower;
    private double frontLeftBackRightPower;

    public MecanumDrive(DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive) {
        this.frontLeftDrive = frontLeftDrive;
        this.frontRightDrive = frontRightDrive;
        this.backLeftDrive = backLeftDrive;
        this.backRightDrive = backRightDrive;
    }

    private void robotCentric() {
        frontRightBackLeftPower = Range.clip(Math.sin(Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x)-0.25*Math.PI)*Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1.0, 1.0);
        frontLeftBackRightPower = Range.clip(Math.sin(Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x)+0.25*Math.PI)*Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1.0, 1.0);

        frontLeftDrive.setPower(-frontLeftBackRightPower);
        frontRightDrive.setPower(frontRightBackLeftPower);
        backLeftDrive.setPower(-frontRightBackLeftPower);
        backRightDrive.setPower(frontLeftBackRightPower);
    }
}

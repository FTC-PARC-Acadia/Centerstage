package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
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

    }

    public void fieldCentricDrive() {

    }

}


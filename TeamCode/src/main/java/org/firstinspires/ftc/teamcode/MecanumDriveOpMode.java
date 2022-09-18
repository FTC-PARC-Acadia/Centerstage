package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Mecanum Drive OpMode", group = "Drive OpMode")
public class MecanumDriveOpMode extends OpMode {
    public MecanumDrive drive;

    public void init() {
        DcMotor frontLeftDrive;
        DcMotor frontRightDrive;
        DcMotor backLeftDrive;
        DcMotor backRightDrive;

        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        drive = new MecanumDrive(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive);
    }

    public void loop() {
        drive.robotCentric();
    }
}

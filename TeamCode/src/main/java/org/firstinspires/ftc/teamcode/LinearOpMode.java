package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "LinearOpMode", group = "TeleOpModes")
public class LinearOpMode extends OpMode {
    MecanumDrive drive;

    public void init() {
        drive = new MecanumDrive(hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"));
    }

    public void loop() {
        drive.robotCentricDrive();
    }
}

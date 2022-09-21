package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "LinearOpMode", group = "TeleOpModes")
public class LinearOpMode extends OpMode {
    MecanumDrive drive;

    public void init() {
        drive = new MecanumDrive("frontLeftDrive", "frontRightDrive", "backLeftDrive", "backRightDrive");
    }

    public void loop() {
        drive.robotCentricDrive();
    }
}

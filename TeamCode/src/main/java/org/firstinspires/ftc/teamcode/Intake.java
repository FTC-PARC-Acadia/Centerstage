package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    public DcMotor intake;
    public Gamepad gamepad2;
    public HardwareMap hardwareMap;

    private double power = 0.5;
    private boolean stopped = false;

    public Intake(Gamepad gamepad2, DcMotor intake) {
        this.gamepad2 = gamepad2;
        this.intake = intake;
    }

    public Intake(DcMotor intake) {
        this.intake = intake;
    }

    public void stop() {
        if (gamepad2.a && !stopped) {
            intake.setPower(0);
            stopped = true;
        }
        else if (gamepad2.a && stopped) {
            intake.setPower(power);
            stopped = false;
        }
    }

    public void flip() {
        if (gamepad2.y && !stopped) {
            power = - power;
            intake.setPower(power);
        }
    }

}
<<<<<<< HEAD
=======

>>>>>>> 277396966b52ce86115fe9fb13de76ab8cbc52a8

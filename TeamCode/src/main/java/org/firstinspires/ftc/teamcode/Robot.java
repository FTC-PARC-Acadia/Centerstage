package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot implements Intake2, Lift {
    final double max = 1;
    final double min = .5;

    Servo claw;
    DcMotor lift;
    Gamepad gamepad1;
    Gamepad gamepad2;

    public Robot(Gamepad gamepad1, Gamepad gamepad2,Servo claw, DcMotor lift) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.lift = lift;
        this.claw = claw;
    }

    public void grab(){
        if(gamepad1.left_bumper) {
            if(claw.getPosition() != max) {
                claw.setPosition(max);
            }
            else {
                claw.setPosition(min);
            }
        }
    }


}

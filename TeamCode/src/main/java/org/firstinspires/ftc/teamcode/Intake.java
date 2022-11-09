package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static final double MIN = 0;
    static final double MAX = 0.25;

    Gamepad gamepad2;
    Servo claw;
    
    public Intake(Gamepad gamepad2, Servo claw) {
        this.gamepad2 = gamepad2;
        this.claw = claw;
    }
    
    public void grab(){
        if(gamepad2.left_bumper) {
            if(claw.getPosition() < MAX) {
                claw.setDirection(Servo.Direction.FORWARD);
                claw.setPosition(MAX);
            }
            else {
                claw.setDirection(Servo.Direction.REVERSE);
                claw.setPosition(MIN);
            }
        }
    }
}

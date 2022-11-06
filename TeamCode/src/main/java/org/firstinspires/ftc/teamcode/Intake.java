package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static final double MAX = 1;
    static final double MIN = .5;

    Gamepad gamepad2;
    Servo claw;
    
    public Intake(Gamepad gamepad2, Servo claw) {
        this.gamepad2 = gamepad2;
        this.claw = claw;
    }
    
    public void grab(){
        if(gamepad2.left_bumper) {
            if(claw.getPosition() != MAX) {
                claw.setPosition(MAX);
            }
            else {
                claw.setPosition(MIN);
            }
        }
    }
}

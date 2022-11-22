package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static final double MIN = 0.02;
    static final double MAX = 0.08;

    Gamepad gamepad2;
    Servo claw;

    double position;
    
    public Intake(Gamepad gamepad2, Servo claw) {
        this.gamepad2 = gamepad2;
        this.claw = claw;
    }
    
    public void grab(){
        if(gamepad2.left_trigger > 0 && claw.getPosition() < MAX) {
            position += 0.01;
        } else if (gamepad2.left_bumper && claw.getPosition() > MIN) {
            position -= 0.01;
        }

        claw.setPosition(position);
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static final double MIN = 0.02;
    static final double MAX = 0.055;

    Gamepad gamepad2;
    public Servo claw;

    double position = MAX;
    
    public Intake(Gamepad gamepad2, Servo claw) {
        this.gamepad2 = gamepad2;
        this.claw = claw;
    }

    public Intake(Servo claw) {
        this.claw = claw;
    }
    
    public void grab(){
        if(gamepad2.left_bumper && claw.getPosition() < MAX) {
            position += 0.01;
        } else if (gamepad2.left_trigger > 0 && claw.getPosition() > MIN) {
            position -= 0.01;
        }

        claw.setPosition(position);
    }

    public void grasp(boolean open) {
        if (open) {
            position = MAX;
        }
        else {
            position = MIN;
        }

        while (claw.getPosition() != position) {
            claw.setPosition(position);
        }
    }
}

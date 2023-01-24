package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static final double MAX = 0.105;
    static final double MIN = 0.08;

    private Gamepad gamepad2;
    public Servo claw;

    double position = MAX;

    int count = 0;
    
    public Intake(Gamepad gamepad2, Servo claw) {
        this.gamepad2 = gamepad2;
        this.claw = claw;
    }

    public Intake(Servo claw) {
        this.claw = claw;
    }
    
    public void grab(){
        if (gamepad2.a && count > 20) {
            if (position < MAX) {
                position = MAX;
            } else {
                position = MIN;
            }

            count = 0;
        }

        count++;

        claw.setPosition(position);
    }

//    public void grab() {
//        if(gamepad2.left_bumper) {
//            position = MAX;
//        } else if (gamepad2.left_trigger > 0) {
//            position = MIN;
//        }
//
//        claw.setPosition(position);
//    }

    public void grasp(boolean open) {
        if (open) {
            position = MAX;
        }
        else {
            position = MIN;
        }

        for (int i = 0; i < 30; i++) {
            claw.setPosition(position);
        }
    }
}

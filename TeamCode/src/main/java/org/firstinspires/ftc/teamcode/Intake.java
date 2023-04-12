package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static final double CLOSED = 0.49;
    static final double OPEN = 0.52;

    private Gamepad gamepad2;
    public Servo claw;

    double position = CLOSED;

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
            if (position > CLOSED) {
                position = CLOSED;
            } else {
                position = OPEN;
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
            position = OPEN;
        }
        else {
            position = CLOSED;
        }

        for (int i = 0; i < 30; i++) {
            claw.setPosition(position);
        }
    }

    public boolean ifOpen() {
        return (position == CLOSED);
    }
}

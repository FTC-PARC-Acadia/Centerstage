package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift {
    Gamepad gamepad2;
    DcMotor[] lifts;

    private int pos = 0;
    private int min = 50;

    public Lift(Gamepad gamepad2, DcMotor[] lifts) {
        this.gamepad2 = gamepad2;
        this.lifts = lifts;

        lifts[0].setDirection(DcMotorSimple.Direction.FORWARD);
        lifts[1].setDirection(DcMotorSimple.Direction.REVERSE);

        for (DcMotor lift : lifts) {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setTargetPosition(0);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public Lift(DcMotor[] lifts) {
        this.lifts = lifts;

        lifts[0].setDirection(DcMotorSimple.Direction.FORWARD);
        lifts[1].setDirection(DcMotorSimple.Direction.REVERSE);

        for (DcMotor lift : lifts) {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setTargetPosition(0);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

//    public void liftByLevel() {
//        int level = pos / 1250;
//        if (gamepad2.dpad_up && (level < 4) && lift.getCurrentPosition() >= pos - 100) {
//            pos += 1250;
//            lift.setTargetPosition(pos);
//            lift.setPower(1);
//        } else if (gamepad2.dpad_down && (level > 0) && lift.getCurrentPosition() <= pos + 100) {
//            pos -= 1250;
//            lift.setTargetPosition(pos);
//            lift.setPower(-1);
//        }
//    }

    public void liftByPush() {
        if (gamepad2.dpad_up && pos < 6000) {

            pos += 50;
            for (DcMotor lift : lifts) {
                lift.setTargetPosition(pos);
                lift.setPower(1);
            }
        } else if (gamepad2.dpad_down && pos > min) {
            pos -= 50;
            for (DcMotor lift : lifts) {
                lift.setTargetPosition(pos);
                lift.setPower(1);
            }
        }

        if (!lifts[0].isBusy() || !lifts[1].isBusy()) {
            lifts[0].setPower(0);
            lifts[1].setPower(0);
        }

        if(gamepad2.b){
            //reset minimum position
            min -= 50;
        }
    }

    public void adjust() {
        if (lifts[0].getCurrentPosition() < pos || lifts[1].getCurrentPosition() < pos) {
            lifts[0].setPower(1);
            lifts[1].setPower(1);
        }
    }

    public void lift(int level) {
        pos = level*1000;

        for (DcMotor lift : lifts) {
            lift.setTargetPosition(pos);
            lift.setPower(0.75);
        }

        while (lifts[0].isBusy() && lifts[1].isBusy()) {

        }

        if (!lifts[0].isBusy() && !lifts[1].isBusy()) {
            lifts[0].setPower(0);
            lifts[1].setPower(0);
        }
    }
}

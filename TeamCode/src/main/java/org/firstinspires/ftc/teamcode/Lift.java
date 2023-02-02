package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift {
    Gamepad gamepad2;
    DcMotor[] lifts;

    private int pos = 0;
    private int min = 50;
    private final double stepsPerInch = 122.2055;
    private int maxPos = (int)(33.5 * stepsPerInch);


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

   public void liftByLevel() {
       int minLevel = (int)(stepsPerInch * 13.5);
       int interval = (int)(stepsPerInch * 10);
       int level = (2 * (pos - minLevel) / (maxPos - minLevel));

       for(DcMotor lift : lifts){
           int currentPos = lift.getCurrentPosition();
           if(gamepad2.dpad_up && (pos < minLevel) && currentPos >= pos - 100){
               pos = minLevel - currentPos;
               lift.setTargetPosition(pos);
               lift.setPower(1);

           }
           else if (gamepad2.dpad_up && (level < 2) && currentPos >= pos - 100) {
              pos = minLevel + interval*(level+1);
              lift.setTargetPosition(pos);
              lift.setPower(1);
           }
           else if (gamepad2.dpad_down && (level > 0) && currentPos <= pos + 100) {
              pos -= minLevel + interval*(level-1);
              lift.setTargetPosition(pos);
              lift.setPower(-1);
           }
       }
   }

    public void liftInches(double inches){
        int steps = (int)(inches * stepsPerInch);
        pos += steps;

        for(DcMotor lift : lifts){
            lift.setTargetPosition(pos);
            lift.setPower((steps > 0) ? 1 : -1);
//             if(steps > 0)
//                 lift.setPower(1);
//             else
//                 lift.setPower(-1);
        }
    }

    public void liftByPush() {
        if (gamepad2.dpad_up && pos < 6000) {

            pos += 50;
            for (DcMotor lift : lifts) {
                lift.setTargetPosition(pos);
                lift.setPower(1);
            }
        }

        else if (gamepad2.dpad_down && pos > min) {
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

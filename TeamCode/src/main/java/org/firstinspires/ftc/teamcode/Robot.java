package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot implements Intake, Lift {
    final double max = 1;
    final double min = .5;
    
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 1.5;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    Servo claw;
    DcMotor lift;
    Gamepad gamepad1;
    Gamepad gamepad2;

    public Robot(Gamepad gamepad1, Gamepad gamepad2,Servo claw, DcMotor lift) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.lift = lift;
        this.claw = claw;
        
        lift.setDirection(DcMotor.Direction.FORWARD);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void grab(){
        if(gamepad2.left_bumper) {
            if(claw.getPosition() != max) {
                claw.setPosition(max);
            }
            else {
                claw.setPosition(min);
            }
        }
    }
    
    public void liftByLevel() {
        if (gamepad2.dpad_up) {
            encoderDrive(9, 1);
        } else if (gamepad2.dpad_down) {
            encoderDrive(9, -1);
        }
    }
    
    public void liftByPush() {

    }

    public void encoderDrive(double inches, double speed) {
        int target = (int) ((inches * COUNTS_PER_INCH) - lift.getCurrentPosition());

        lift.setTargetPosition(target);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(speed);

        if (!lift.isBusy()) {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    } 
}

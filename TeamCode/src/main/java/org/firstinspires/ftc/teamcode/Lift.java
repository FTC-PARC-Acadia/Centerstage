package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift {
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 1.5;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    Gamepad gamepad2;
    DcMotor lift;

    public Lift(Gamepad gamepad2, DcMotor lift) {
        this.gamepad2 = gamepad2;
        this.lift = lift;

        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void liftByLevel() {
        if (gamepad2.dpad_up) {
            encoderDrive(9, 0.1);
        } else if (gamepad2.dpad_down) {
            encoderDrive(9, -0.1);
        }
    }

    public void liftByPush() {
        if (gamepad2.right_bumper) {
            encoderDrive(1, 0.1);
        } else {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.setPower(0);
        }
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

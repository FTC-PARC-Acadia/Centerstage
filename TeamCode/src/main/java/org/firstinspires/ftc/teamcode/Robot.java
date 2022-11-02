package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Robot implements Intake2, Lift {
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 1.5;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    private DcMotor liftMotor;

    private Gamepad gamepad2;

    public Robot(DcMotor liftMotor, Gamepad gamepad2) {
        this.liftMotor = liftMotor;
        this.gamepad2 = gamepad2;

        liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        int target = (int) ((inches * COUNTS_PER_INCH) - liftMotor.getCurrentPosition());

        liftMotor.setTargetPosition(target);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(speed);

        if (!liftMotor.isBusy()) {
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}

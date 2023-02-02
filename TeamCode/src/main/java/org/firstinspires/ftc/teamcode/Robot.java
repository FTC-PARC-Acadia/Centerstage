package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.AutoLeft.backward;
import static org.firstinspires.ftc.teamcode.AutoLeft.forward;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.ArrayList;

public class Robot {
    final double MOVE_X = 0;
    final double MOVE_Y = 0;
    final double CONE_HEIGHT = 5;
    Gamepad gamepad2;

    Intake intake;
    Lift lift;
    MecanumDrive drive;
    SampleMecanumDrive sampleDrive;
    
    public Robot(Gamepad gamepad1, Gamepad gamepad2, Servo claw, DcMotor[] lifts, DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, BNO055IMU imu, HardwareMap hardwareMap) {
        intake = new Intake(gamepad2, claw);
        drive = new MecanumDrive(gamepad1, frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, imu, hardwareMap);
        lift = new Lift(gamepad2, lifts);
    }

    public Robot(Servo claw, DcMotor[] lifts) {
        intake = new Intake(claw);
        lift = new Lift(lifts);
    }
    
    public void run() {
        drive.fieldCentricDrive();
        intake.grab();
        lift.liftByPush();
        lift.adjust();
    }

    public void getCone() {
        // opening claw
        if (!intake.ifOpen()) {
            intake.grasp(true);
        }

        //moving forward
        sampleDrive.followTrajectory(forward(sampleDrive, MOVE_X));

        //moving down
        lift.liftInches(-MOVE_Y);
    
        //closing claw
        intake.grasp(false);
        
        //moving up
        lift.liftInches(MOVE_Y + 5);

        //moving backward
        sampleDrive.followTrajectory(backward(sampleDrive, MOVE_X));
        
        //turning 180˚
        sampleDrive.turn(180);
    }

}

/*
BEFORE AUTO
- align position + level

AFTER AUTO
- open claw (or check if claw is open/closed and move accordingly)
- move forward x inches
- go down y inches
- close claw
- go up y + z inches
- back x inches
- turn 180˚

DURING AUTO
- emergency stop
- pause

VARIABLES
- clawOpen (boolean)
- double moveX
- double liftY
- double coneHeight = 5 inches

METHODS
- getClawPosition()
- emergencyStop()
- pause()
- move(), lift() -> already in use for auto
- getCone()

 */

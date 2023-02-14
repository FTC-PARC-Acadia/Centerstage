package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.AutoLeft.forward;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "AutonomousLeft", group = "AutoOpModes")
public class AutonomousLeft extends LinearOpMode {
    AutoState state;
    ElapsedTime runtime;
    SampleMecanumDrive drive;

    public void runOpMode() {
        state = AutoState.RUNNING;
        drive = new SampleMecanumDrive(hardwareMap);
        runtime = new ElapsedTime();

        Thread runThread = new RunThread();
        Thread checkStateThread = new CheckStateThread();

        waitForStart();
        runtime.reset();

        if (opModeIsActive()) {
            checkStateThread.start();
            runThread.start();
        }
    }

    private void runAuto() {
        drive.followTrajectory(forward(drive, 70));
    }

    private class RunThread extends Thread {
        public void run() {
            while (opModeIsActive()) {
                if (runtime.time() > 4) {
                    state = AutoState.STOPPED;
                }
            }
        }
    }

    private class CheckStateThread extends Thread {
        public void run() {
            switch (state) {
                case RUNNING:
                    runAuto();
                case STOPPED:
                    break;
            }
        }
    }
}


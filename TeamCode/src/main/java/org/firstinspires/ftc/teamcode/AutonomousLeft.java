package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.AutoLeft.forward;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "AutonomousLeft", group = "AutoOpModes")
public class AutonomousLeft extends LinearOpMode {
    ElapsedTime runtime;
    SampleMecanumDrive drive;

    boolean state;

    public void runOpMode() {
        state = true;
        drive = new SampleMecanumDrive(hardwareMap);
        runtime = new ElapsedTime();

        Thread autoThread = new AutoThread();

        waitForStart();
        runtime.reset();

        if (opModeIsActive()) {
            autoThread.start();
        }

        while (opModeIsActive()) {
            if (runtime.seconds() > 1) {
                autoThread.interrupt();
            }
        }
    }

    private void runAuto() {
        drive.followTrajectory(forward(drive, 70));
    }

    private class AutoThread extends Thread {
        public void run() {
            while (opModeIsActive() && state) {
                runAuto();
            }
        }
    }
}


package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.advanced.SampleMecanumDriveCancelable;

public class TrajectoryBuilder {
    public static Trajectory forward(SampleMecanumDrive drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .forward(inches)
                .build();
    }

    public static Trajectory backward(SampleMecanumDrive drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .back(inches)
                .build();
    }

    public static Trajectory left(SampleMecanumDrive drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeLeft(inches)
                .build();
    }

    public static Trajectory right(SampleMecanumDrive drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeRight(inches)
                .build();
    }
}

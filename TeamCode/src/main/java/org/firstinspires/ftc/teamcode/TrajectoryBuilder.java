package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.drive.advanced.SampleMecanumDriveCancelable;

public class TrajectoryBuilder {
    public static Trajectory forward(SampleMecanumDriveCancelable drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .forward(inches)
                .build();
    }

    public static Trajectory backward(SampleMecanumDriveCancelable drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .back(inches)
                .build();
    }

    public static Trajectory left(SampleMecanumDriveCancelable drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeLeft(inches)
                .build();
    }

    public static Trajectory right(SampleMecanumDriveCancelable drive, double inches) {
        return drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeRight(inches)
                .build();
    }
}

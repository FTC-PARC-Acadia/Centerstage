package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "ImageRecognition", group = "AutoOpModes")
public class Autonomous extends LinearOpMode {
    public int label;

    private static final String TFOD_MODEL_ASSET = "NewConeSleeveDetection.tflite";

    private static final String[] LABELS = {
            "1 green",
            "2 orange",
            "3 purple"
    };

    private static final String VUFORIA_KEY = "AZ6GcV7/////AAABmfVKnUPVmEYUptmw3QQGPrYoGakkYwWd0zW/qAD6t6xQnIEP0joqQnwSb97A4E1E8uNf4f3VeF+KpfB01M2h/NKfHZYkaZMwQPMe0NWZAMJJpeZptIh2B8kD9aLrAyQxb8Mr9oyb5W8D99jkiCecECXqOtkNvC4cvo8iT9c1qtWmuUbOCct4kLPTuQ/SW3VlsjRsruuzOiW9yoo4/XtEZsts0YVdN255mU7xQU9+M8MXbog790+rK4GKwl2JuSpPCC6LhxuvoaX1K2XEJSHR/0OfzsItcNsBh+7lD9NA02EUObjohybJscDPQ8wfCLWgo9BH+KrKM3ZVx3+s42FsXUCYuybMR7um9Xn/pyzKCPvF";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    Robot robot = new Robot(hardwareMap.get(Servo.class, "intake"), new DcMotor[]{hardwareMap.get(DcMotor.class, "lift1"), hardwareMap.get(DcMotor.class, "lift2")});

    Trajectory goToCone = drive.trajectoryBuilder(new Pose2d())
            .forward(24)
            .build();

    Trajectory goToTower = drive.trajectoryBuilder(new Pose2d())
            .forward(3)
            .strafeLeft(12)
            .forward(2)
            .build();

    Trajectory placeCone = drive.trajectoryBuilder(new Pose2d())
            .forward(1.5)
            .build();

    Trajectory backUp = drive.trajectoryBuilder(new Pose2d())
            .back(1.5)
            .build();

    Trajectory goToCycleSpot = drive.trajectoryBuilder(new Pose2d())
            .lineToLinearHeading(new Pose2d(24,29,Math.toRadians(90)))
            .strafeLeft(12)
            .build();

    Trajectory goToParkSpot = drive.trajectoryBuilder(new Pose2d())
            .back(12)
            .forward((label - 1)*24)
            .build();


    public void runOpMode() {
        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1.0, 16.0/9.0);
        }

        waitForStart();

        if (opModeIsActive()) {
            drive.followTrajectory(goToCone);

            while (tfod.getUpdatedRecognitions().size() == 0) {

            }

            label = labelToInt(tfod.getUpdatedRecognitions().get(0).getLabel());
            telemetry.addData("Color Detected", tfod.getUpdatedRecognitions().get(0).getLabel());
            telemetry.update();

            drive.followTrajectory(goToTower);

            robot.lift.lift(4);

            drive.followTrajectory(placeCone);

            robot.intake.grasp(true);

            drive.followTrajectory(backUp);

            robot.lift.lift(0);

            drive.followTrajectory(goToCycleSpot);
            drive.followTrajectory(goToParkSpot);
        }
    }

    public int labelToInt(String label) {
        return Integer.parseInt(label.substring(0,0));
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "webcam");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}

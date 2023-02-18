package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.TrajectoryBuilder.backward;
import static org.firstinspires.ftc.teamcode.TrajectoryBuilder.forward;
import static org.firstinspires.ftc.teamcode.TrajectoryBuilder.left;
import static org.firstinspires.ftc.teamcode.TrajectoryBuilder.right;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.ArrayList;

@Autonomous(name = "AutoRight", group = "AutoOpModes")
public class RightAuto extends LinearOpMode {
    public int label;

    private static final String TFOD_MODEL_ASSET = "ImageRecognition.tflite";

    private static final String[] LABELS = {
            "1 green",
            "2 orange",
            "3 purple"
    };

    private static final String VUFORIA_KEY = "AV9eXtT/////AAABmYHJ5b+iqUHJikZQ1S4jEZV2+BslbZDe7HxZll8H1AcFbWuFyoYARyV/hpU/maiPSylq1iP1RrLFe9F5bV0Y7PQmdo/mnU8RYV3BClJHAWith4j0FY5dB6xC1EHKmiOPg/z8dpQqQPeeOB1sjuw3vlmI9olcK7obEDHhmtHomACdN4jBa2GPdOLnt2C0qlOwR77QnG2IxKCoeOmUsTH/RL/sA5SpMmegiNWczswGUrzBvhHqrm/7mNu+UZQfPEfhTn+DuS15B9z5Fgai9XkjgYwpR639uAMcS0pRr88l94efch5fIM0zL+qgAOTmtGCREB5DhMltSVnLqdZYU50f/6K8FhgoFkzUhoc99KdDijhs";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    ElapsedTime runtime;
    SampleMecanumDrive drive;

    boolean state;

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        state = true;
        drive = new SampleMecanumDrive(hardwareMap);
        runtime = new ElapsedTime();
        robot = new Robot(hardwareMap.get(Servo.class, "intake"), new DcMotor[]{hardwareMap.get(DcMotor.class, "lift1"), hardwareMap.get(DcMotor.class, "lift2")});

        initVuforia();
        initTfod();

        waitForStart();

        robot.intake.grasp(false);

        //Sleeve Detection
        while (opModeIsActive() && tfod.getRecognitions().isEmpty()) {
            telemetry.addLine("Hi");
            telemetry.update();
        }

        ArrayList<Recognition> recognitions = (ArrayList<Recognition>) tfod.getRecognitions();

        if (recognitions != null) {
            telemetry.addData("Recognitions", recognitions);
            telemetry.update();

            label = labelToInt(recognitions.get(0).getLabel());
            telemetry.addData("Color Detected", recognitions.get(0).getLabel());
            telemetry.update();
        }
        drive.followTrajectory(left(drive, 24));
        drive.followTrajectory(forward(drive, 51));
        drive.followTrajectory(right(drive, 12));

        robot.lift.lift(4);
        drive.followTrajectory(forward(drive, 5));
        robot.intake.grasp(true);
        drive.followTrajectory(backward(drive, 5));
        robot.lift.lift(0);
        robot.intake.grasp(false);

        drive.followTrajectory(left(drive, 12));
        drive.followTrajectory(right(drive, (label - 1)*24));
    }

    public int labelToInt(String label) {
        return Integer.parseInt(label.substring(0,1));
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
        tfodParameters.minResultConfidence = 0.5f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}

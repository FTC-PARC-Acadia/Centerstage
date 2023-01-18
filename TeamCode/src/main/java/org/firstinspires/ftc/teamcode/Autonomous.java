package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "ImageRecognition", group = "AutoOpModes")
public class Autonomous extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "NewConeSleeveDetection.tflite";

    private static final String[] LABELS = {
            "1 green",
            "2 orange",
            "3 purple"
    };

    private static final String VUFORIA_KEY = "AZ6GcV7/////AAABmfVKnUPVmEYUptmw3QQGPrYoGakkYwWd0zW/qAD6t6xQnIEP0joqQnwSb97A4E1E8uNf4f3VeF+KpfB01M2h/NKfHZYkaZMwQPMe0NWZAMJJpeZptIh2B8kD9aLrAyQxb8Mr9oyb5W8D99jkiCecECXqOtkNvC4cvo8iT9c1qtWmuUbOCct4kLPTuQ/SW3VlsjRsruuzOiW9yoo4/XtEZsts0YVdN255mU7xQU9+M8MXbog790+rK4GKwl2JuSpPCC6LhxuvoaX1K2XEJSHR/0OfzsItcNsBh+7lD9NA02EUObjohybJscDPQ8wfCLWgo9BH+KrKM3ZVx3+s42FsXUCYuybMR7um9Xn/pyzKCPvF";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public void runOpMode() {
        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1.0, 16.0/9.0);
        }

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

                    if (updatedRecognitions != null) {
                        telemetry.addData("# Objects Detected", updatedRecognitions.size());

                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData("Color Detected", "%s", recognition.getLabel());
                        }

                        telemetry.update();
                    }
                }
            }
        }
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

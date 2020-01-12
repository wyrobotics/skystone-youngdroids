package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.SkyStone.AutonFunctions;

@Autonomous(name = "VuforiaLeft", group = "Vuforia")
public class TestVuforiaAutonLeft extends LinearOpMode {

    AutonFunctions auton;

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    int SkyStoneNum = 0;

    @Override public void runOpMode() throws InterruptedException{

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AaAxlWn/////AAABmStCtjq0tEuwuSqRrqDYwgk1Fta1+4mcKAD6f4X1jyU+/6ADzeidbtuCRkCGiMMiKkeSIbXefClAhcqx1UYvkLd6Yz3bYAsJXKmdERas6pvXmgbl0//xRvX47XbPNXNKRsOs/irAEghpUTN9APmROEMNfiSn/ZbdFU5sRnND0watljGL8rafAqmGE7Dhk3u96lM4LP72FnLK+Djz9cprjBXSK8nlY/e06lZx95mxf/2qDv1pYa4R3LIrVsIxnbLoKenuUgl3+S86ubyMMo3xaMNVNazoSUaJ5wmL2yoF2RpZYI7rQhGoNZoW891YOj6DlyXV3AQRLT6RBELvdK7kcTt4stZvB4NoM/7DWhtk1cSe";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        /**
         * Instantiate the Vuforia engine
         */
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        VuforiaTrackables skyStones = this.vuforia.loadTrackablesFromAsset("Skystone");

        //i believe this gets the first item in that list, and there should only be one item bc all skystone markers are the same
        // don't quote me on this one either
        VuforiaTrackable skyStone = skyStones.get(0);

        //i used the vuforia trackables listener interface which specifically looks for instances of certain objects
        VuforiaTrackableDefaultListener skyStoneFinder = new VuforiaTrackableDefaultListener();

        //make it go both ways
        skyStone.setListener(skyStoneFinder);

        //addTrackable tells the listener to look for instances of a type of object- here it's the skystone
        skyStoneFinder.addTrackable(skyStone);

        waitForStart();

        skyStones.activate();

        auton.MoveForward(1.3,opModeIsActive());
        for(int i= 0; i < 3 ;i++){
            wait(100);
            if(!skyStoneFinder.isVisible()){
                auton.Strafe(1/3,opModeIsActive());
            } else {
                i = 3;
                auton.InL.setPower(1);
                auton.InR.setPower(1);
                auton.MoveForward(1,opModeIsActive());
                auton.MoveForward(-1,opModeIsActive());
            }
            SkyStoneNum++;
        }
        auton.Rotate90(-.6,opModeIsActive());
        auton.MoveForward(3 + (SkyStoneNum * (1/3)),opModeIsActive());
        auton.Rotate90(-.6,opModeIsActive());
        auton.MoveForward(-.5,opModeIsActive());
        auton.MoveForward(1.5,opModeIsActive());
        auton.Rotate90(-.6,opModeIsActive());
        auton.Strafe(-.5,opModeIsActive());
        auton.MoveForward(2.2,opModeIsActive());
    }
}

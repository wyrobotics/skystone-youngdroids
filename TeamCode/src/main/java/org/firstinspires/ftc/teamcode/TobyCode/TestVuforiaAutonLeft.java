package org.firstinspires.ftc.teamcode.TobyCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.SkyStone.AutonFunctions;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "VuforiaLeft2019", group = "Vuforia")
@Disabled
public class TestVuforiaAutonLeft extends LinearOpMode {

    AutonFunctions auton;

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    int SkyStoneNum = 0;

    public final double RotationsPerTileForward = 1850, RotationsPer90 = 1225, RotationsPerStafe = 3000;

    @Override
    public void runOpMode() throws InterruptedException{

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

        MoveForward(1.3,opModeIsActive());
        for(int i= 0; i < 3 ;i++){
            wait(100);
            if(!skyStoneFinder.isVisible()){
                //auton.Strafe(1/3,opModeIsActive());
            } else {
                i = 3;
                //auton.InL.setPower(1);
                //auton.InR.setPower(1);
                MoveForward(1,opModeIsActive());
                MoveForward(-1,opModeIsActive());
            }
            SkyStoneNum++;
        }
        Rotate90(-.6,opModeIsActive());
        MoveForward(3 + (SkyStoneNum * (1/3)),opModeIsActive());
        Rotate90(-.6,opModeIsActive());
        MoveForward(-.5,opModeIsActive());
        MoveForward(1.5,opModeIsActive());
        Rotate90(-.6,opModeIsActive());
        //auton.Strafe(-.5,opModeIsActive());
        MoveForward(2.2,opModeIsActive());
    }
    public void MoveForward(double tiles, boolean opActive){ // pow is how fast it moves, + is CW, - is CCW
        if (!opActive) {return;}
        auton.resetMotorEncoder();
        if ( tiles < 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition() +
                    auton.fl.getCurrentPosition() + auton.bl.getCurrentPosition()) / 4 >= RotationsPerTileForward * tiles && opModeIsActive()) {
                auton.setPowers(-.875,-1.0,-.875,-1.0);
            }
            auton.setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition() +
                    auton.fl.getCurrentPosition() + auton.bl.getCurrentPosition()) / 4 <= RotationsPerTileForward * tiles && opModeIsActive()) {
                auton.setPowers(.9,1.0,.9,1.0);
            }
            auton.setPowers(0,0,0,0);
        }
        auton.resetMotorEncoder();
    }
    public void Rotate90(double tiles, boolean opActive) {
        if (!opActive) {return;}
        auton.resetMotorEncoder();
        if ( tiles < 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition()) / 2 >=  tiles * RotationsPer90 && opModeIsActive()) {
                auton.setPowers(1,-1,1,-1);
            }
            auton.setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition()) / 2 <= tiles * RotationsPer90 && opModeIsActive()) {
                auton.setPowers(-1,1,-1,1);
            }
            auton.setPowers(0,0,0,0);
        }
        auton.resetMotorEncoder();
    }
}

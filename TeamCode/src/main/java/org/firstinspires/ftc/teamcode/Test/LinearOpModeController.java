package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;


@Disabled
public abstract class LinearOpModeController extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode(){
        Drive.init(hardwareMap);
        telemetry.addData("Say: ","Before OpMode");
        telemetry.update();
        waitForStart();

        boolean a, b, x, y;
        boolean dpadUp, dpadDown, dpadLeft, dpadRight;
        boolean rightBumper, leftBumper, rightTrigger, LeftTrigger;

        double jsLX, jsRX, jsLY, jsRY;

    }

    abstract public void OpModeEvents();

}

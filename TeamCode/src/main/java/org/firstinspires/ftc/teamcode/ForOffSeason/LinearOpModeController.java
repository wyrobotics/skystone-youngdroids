package org.firstinspires.ftc.teamcode.ForOffSeason;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;


@Disabled
public abstract class LinearOpModeController extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();

    boolean a, b, x, y;
    boolean dPadUp, dPadDown, dPadLeft, dPadRight;
    boolean rightBumper, leftBumper, rightTrigger, leftTrigger;
    boolean start, guide, back;

    double jsLX, jsRX, jsLY, jsRY;


    @Override
    public void runOpMode(){
        Drive.init(hardwareMap);
        gamepad1.reset();
        start = guide = back = false;

        telemetry.addData("Say: ","Before OpMode");
        telemetry.update();

        waitForStart();


        while(opModeIsActive()) {
            if (gamepad1.right_bumper) {
                rightBumper = true;
                leftBumper = false;
            } else if (gamepad1.left_bumper) {
                leftBumper = true;
                rightBumper = false;
            } else {
                rightBumper = leftBumper = false;
            }
            if(gamepad1.dpad_up){
                dPadUp = true;
                dPadDown = false;
            } else if (gamepad1.dpad_down){
                dPadUp = false;
                dPadDown = true;
            } else {
                dPadUp = dPadDown = false;
            }
            if(gamepad1.dpad_left){
                dPadLeft = true;
                dPadRight = false;
            } else if (gamepad1.dpad_right){
                dPadRight = true;
                dPadLeft = false;
            } else {
                dPadRight = dPadLeft = false;
            }
            if(gamepad1.a){
                a = true;
                x = false;
                y = false;
                b = false;
            } else if (gamepad1.x){
                a = false;
                x = true;
                y = false;
                b = false;
            } else if (gamepad1.y){
                a = false;
                x = false;
                y = true;
                b = false;
            } else if (gamepad1.b){
                a = false;
                x = false;
                y = false;
                b = true;
            } else {
                a = x = y = b = false;
            }
            if (gamepad1.left_trigger > 0.8) {
                leftTrigger = true;
                rightTrigger = false;
            } else if (gamepad1.right_trigger > 0.8) {
                leftTrigger = false;
                rightTrigger = true;
            } else {
                leftTrigger = rightTrigger = false;
            }

            //Might need to add variable to slow this process down;
            if(gamepad1.guide) {
                guide = !guide;
            }
            if(gamepad1.start) {
                start = !start;
            }
            if(gamepad1.back) {
                back = !back;
            }

            jsLX = gamepad1.left_stick_x;
            jsRX = gamepad1.right_stick_x;
            jsLY = gamepad1.left_stick_y;
            jsRY = gamepad1.right_stick_y;

            //gamepad1.setJoystickDeadzone();


            OpModeEvents();


        }
    }

    abstract public void OpModeEvents();

}

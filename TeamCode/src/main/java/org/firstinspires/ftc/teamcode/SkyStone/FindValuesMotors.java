package org.firstinspires.ftc.teamcode.SkyStone;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;

@TeleOp(name="FindValuesMotors2019", group = "Test")
public class FindValuesMotors extends LinearOpMode {
    
    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode() {

        Drive.init(hardwareMap);
        telemetry.addData("say", "before testmode");
        telemetry.update();
        waitForStart();


        boolean aPressed = false;
        boolean yPressed = false;
        boolean xPressed = false;
        boolean bPressed = false;
        boolean dPadUp = false;
        boolean dPadDown = true;
        boolean rightBumper = false, leftBumper = false;
        boolean dPadLeft = false, dPadRight = false;
        boolean rightTrigger = false, leftTrigger = false;


        while (opModeIsActive()) {

            // Controller Stuff
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
                aPressed = true;
                xPressed = false;
                yPressed = false;
                bPressed = false;
            } else if (gamepad1.x){
                aPressed = false;
                xPressed = true;
                yPressed = false;
                bPressed = false;
            } else if (gamepad1.y){
                aPressed = false;
                xPressed = false;
                yPressed = true;
                bPressed = false;
            } else if (gamepad1.b){
                aPressed = false;
                xPressed = false;
                yPressed = false;
                bPressed = true;
            } else {
                aPressed = xPressed = yPressed = bPressed = false;
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

            // Motor and servo Stuff
            if (rightBumper) {
                Drive.LifterPower = 1;
            } else if (leftBumper) {
                Drive.LifterPower = -1;
            } else {
                Drive.LifterPower = 0;
            }
            if  (leftTrigger) {
                Drive.GrabberPos += .1;
            } else if (rightTrigger) {
                Drive.GrabberPos -= .1;
            }
            if (dPadUp) {
                Drive.PlateGrabLPos += .1;
                Drive.PlateGrabRPos += .1;
            } else if (dPadDown) {
                Drive.PlateGrabLPos -= .1;
                Drive.PlateGrabRPos -= .1;
            }
            if (xPressed) {
                Drive.releaseInPos -= .1;
            } else if (bPressed) {
                Drive.releaseInPos += .1;
            }
            if (yPressed) {
                Drive.InLPower += 1;
                Drive.InRPower += 1;
            } else if (xPressed) {
                Drive.InRPower -= 1;
                Drive.InLPower += 1;
            }

            Drive.SetMotorPower();

            // Phone Data collecty Stuff
            telemetry.addData("InLPower: ",Drive.InLPower);
            telemetry.addData("InRPower: ",Drive.InRPower);
            telemetry.addData("PlateGrabLPos: ",Drive.PlateGrabLPos);
            telemetry.addData("PlateGrabRPos: ",Drive.PlateGrabRPos);
            telemetry.addData("LifterPower: ",Drive.LifterPower);
            telemetry.addData("GrabberPos: ",Drive.GrabberPos);
            telemetry.addData("releaseInPos: ",Drive.releaseInPos);

            telemetry.update();
        }
    }
}
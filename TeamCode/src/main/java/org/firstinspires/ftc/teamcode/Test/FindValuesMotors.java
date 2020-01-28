package org.firstinspires.ftc.teamcode.Test;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;
import java.lang.Math.*;

@TeleOp(name="FindValuesMotors2020", group = "Test")
public class FindValuesMotors extends LinearOpMode {
    
    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode() {
        // Plate Grabbers: 0.4
        // Intake On::     IntakeCtrL Pos: 0.62100 IntakeCtrlR: 0.67500
        // Grabber Out:    IntakeCtrL Pos: 0.16200 IntakeCtrlR: 0.13400

        // Grabber Close Pos: 0.57700 Grabber Open Pos: 0.42600


        Drive.init(hardwareMap);
        telemetry.addData("say", "before testmode");
        telemetry.update();
        Drive.inCtrlLPos = 1;
        Drive.inCtrlRPos = 1;
        Drive.SetMotorPower();

        waitForStart();


        boolean aPressed = false;
        boolean yPressed = false;
        boolean xPressed = false;
        boolean bPressed = false;
        boolean dPadUp = false;
        boolean dPadDown = false;
        boolean rightBumper = false, leftBumper = false;
        boolean dPadLeft = false, dPadRight = false;
        boolean rightTrigger = false, leftTrigger = false;
        boolean start = false, back = false;

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
                Drive.GrabberPos += .001;
            } else if (rightTrigger) {
                Drive.GrabberPos -= .001;
            }
            if (dPadUp) {
                Drive.inCtrlLPos += .001;
            } else if (dPadDown) {
                Drive.inCtrlLPos -= .001;
            }
            if(dPadLeft) {
                Drive.inCtrlRPos += .001;
            } else if(dPadRight){
                Drive.inCtrlRPos -= .001;
            }


            if (xPressed) {
                Drive.PlateGrabLPos += .001;
                Drive.PlateGrabRPos += .001;
            } else if (bPressed) {
                Drive.PlateGrabLPos -= .001;
                Drive.PlateGrabRPos -= .001;
            }
            if (yPressed) {
                Drive.InLPower += .01;
                Drive.InRPower = Drive.InRPower * 4;
            } else if (aPressed) {
                Drive.InLPower -= .01;
                Drive.InRPower = Drive.InRPower * 4;
            }

            if (start) {
                Drive.DriveTrain(5);
            } else if (back) {
                Drive.DriveTrain(-5);
            } else {
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            }


            Drive.inCtrlLPos = Drive.limServo(Drive.inCtrlLPos);
            Drive.inCtrlRPos = Drive.limServo(Drive.inCtrlRPos);;
            Drive.GrabberPos = Drive.limServo(Drive.GrabberPos);;
            Drive.PlateGrabLPos = Drive.limServo(Drive.PlateGrabLPos);;
            Drive.PlateGrabRPos = Drive.limServo(Drive.PlateGrabRPos);;

            Drive.InLPower = Math.max(-10, (Math.min(Drive.InLPower, 10)));
            Drive.InRPower = Math.max(-10, (Math.min(Drive.InRPower, 10)));


            Drive.SetMotorPower();

            // Phone Data collecty Stuff
            telemetry.addData("InLPower: ",Drive.InLPower);
            telemetry.addData("InRPower: ",Drive.InRPower);
            telemetry.addData("PlateGrabLPos: ",Drive.PlateGrabLPos);
            telemetry.addData("PlateGrabRPos: ",Drive.PlateGrabRPos);

            telemetry.addData("LifterPower: ",Drive.LifterPower);

            int lifterPos = Drive.Lifter.getCurrentPosition();
            telemetry.addData("LifterPos: ", lifterPos);

            telemetry.addData("GrabberPos: ",Drive.GrabberPos);

            telemetry.addData("IntakeControl Left: ",Drive.inCtrlLPos);
            telemetry.addData("IntakeControl Right: ", Drive.inCtrlRPos);
            telemetry.addData("LFMotorPower",Drive.LFWheelPower);
            telemetry.addData("LBMotorPower",Drive.LBWheelPower);
            telemetry.addData("RFMotorPower",Drive.RFWheelPower);
            telemetry.addData("RBMotorPower",Drive.RBWheelPower);

            telemetry.addData("LF Position: ", Drive.fl.getCurrentPosition());
            telemetry.addData("LR Position: ", Drive.bl.getCurrentPosition());
            telemetry.addData("RF Position: ", Drive.fr.getCurrentPosition());
            telemetry.addData("RB Position: ", Drive.br.getCurrentPosition());
            telemetry.addData("GrabL Current Pos: ", Drive.PlateGrabL.getPosition());
            telemetry.addData("GrabL Target Pos: ", Drive.PlateGrabLPos);
            telemetry.addData("GrabR Current Pos: ", Drive.PlateGrabR.getPosition());
            telemetry.addData("GrabR Target Pos: ", Drive.PlateGrabRPos);
            telemetry.update();



            try {
                wait(500);
            } catch( Exception e){
                e.getStackTrace();
            }

        }
    }
}
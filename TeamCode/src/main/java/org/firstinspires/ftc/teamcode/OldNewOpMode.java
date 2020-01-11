package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.ftdi.eeprom.FT_EEPROM_232H;

@TeleOp(name="OldNewMainOpModeNew")
public class OldNewOpMode extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode() {
        //Robot.init(hardwareMap);
        Drive.init(hardwareMap);
        DcMotor InL, InR;
        Servo PlateGrabberL, PlateGrabberR, Grabber;

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();
        Drive.intakeRPos = 0.5;
        boolean aPressed = false;
        boolean yPressed = false;
        boolean xPressed = false;
        boolean bPressed;
        boolean dPadUp = false;
        boolean dPadDown = true;
        boolean rightBumper = false, leftBumper = false;
        boolean dPadLeft = false, dPadRight = false;

        while (opModeIsActive()) {
            if (rightBumper) {
                Drive.DriveTrain(5);
            } else if (leftBumper) {
                Drive.DriveTrain(-5);
            } else {
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            }


            if(aPressed){
                Drive.InLPower = 5;
                Drive.InRPower = 5;
            } else if (xPressed){
                Drive.InLPower = 0;
                Drive.InRPower = 0;
            } else if (yPressed){
                Drive.InLPower = -.5;
                Drive.InRPower = -.5;
            }

            if(gamepad1.b) {
                Drive.intakeRPos -= 1;
            }

            if(dPadDown){
                Drive.PlateGrabLPos = .6;
                Drive.PlateGrabRPos = .6;
            } else if (dPadUp){
                Drive.PlateGrabLPos = .1;
                Drive.PlateGrabRPos = .1;
            }
            if(dPadLeft){
                Drive.PlateGrabLPos += .1;
                Drive.PlateGrabRPos += .1;
            } else if(dPadRight){
                Drive.PlateGrabLPos -= .1;
                Drive.PlateGrabRPos -= .1;
            }

            Drive.SetMotorPower();
            if(gamepad1.dpad_up){
                dPadUp = true;
                dPadDown = false;
            } else if (gamepad1.dpad_down){
                dPadUp = false;
                dPadDown = true;
            }
            if(gamepad1.dpad_left){
                dPadLeft = true;
                dPadRight = false;
            } else if (gamepad1.dpad_right){
                dPadRight = false;
                dPadLeft = true;
            } else {
                dPadRight = dPadLeft = false;
            }
            if(gamepad1.a){
                aPressed = true;
                xPressed = false;
                yPressed = false;
            } else if (gamepad1.x){
                aPressed = false;
                xPressed = true;
                yPressed = false;
            } else if (gamepad1.y){
                aPressed = false;
                xPressed = false;
                yPressed = true;
            } if (gamepad1.right_bumper) {
                rightBumper = true;
                leftBumper = false;
            } else if (gamepad1.left_bumper) {
                leftBumper = true;
                rightBumper = false;
            } else {
                rightBumper = leftBumper = false;
            }



            //TODO: Something to use strafe (Not required)
            //we have four motors for the drive base, and two at the front of the robot for intake. We have two rev servos at the back that control the clamps for pulling out the building spot. we are planning on adding a servo in the middle of the robot too hold back the intake at the beginning of the game. i think we should have that rotate up to release the intake in auton, or if it doesnt get released during auton we should have the X button release it
            //for the servos maybe we should use the dpad up and down and for the intake um idk maybe the left and right bumper?

            telemetry.addData("LFMotorPower",Drive.LFWheelPower);
            telemetry.addData("LBMotorPower",Drive.LBWheelPower);
            telemetry.addData("RFMotorPower",Drive.RFWheelPower);
            telemetry.addData("RBMotorPower",Drive.RBWheelPower);
            //telemetry.addData("Grabber Open?",Robot.GrabOpen);
            telemetry.addData("LF Position: ", Drive.fl.getCurrentPosition());
            telemetry.addData("LR Position: ", Drive.bl.getCurrentPosition());
            telemetry.addData("RF Position: ", Drive.fr.getCurrentPosition());
            telemetry.addData("RB Position: ", Drive.br.getCurrentPosition());
            telemetry.addData("Average Pos: ", AverageRotation());
            telemetry.addData("GrabL Current Pos: ", Drive.PlateGrabL.getPosition());
            telemetry.addData("GrabL Target Pos: ", Drive.PlateGrabLPos);
            telemetry.addData("GrabR Current Pos: ", Drive.PlateGrabR.getPosition());
            telemetry.addData("GrabR Target Pos: ", Drive.PlateGrabRPos);
            telemetry.update();
            //

        }






    }
    public double AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (Drive.fl.getCurrentPosition() + Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition() + Drive.br.getCurrentPosition()) / 4;
    }
}
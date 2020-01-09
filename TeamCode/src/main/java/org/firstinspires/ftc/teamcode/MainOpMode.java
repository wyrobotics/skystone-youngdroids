package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.lang.Math;

@TeleOp(name="MainOpModeNew", group="Robot")
public class MainOpMode extends LinearOpMode {

    MecanumDrive Drive = new MecanumDrive();

    @Override
    public void runOpMode() {
        Drive.init(hardwareMap); // Map and initialize our hardware

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();

        boolean aToggle = false; // Initialize Button Variables
        boolean yToggle = false;
        boolean xToggle = false;
        boolean bPressed = false;
        boolean startToggle = false;

        boolean dPadUp = false, dPadDown = false; // Initialize dPad and Bumper Variables
        boolean rightBumper = false, leftBumper = false;


        

        while (opModeIsActive()) {


            // Moves the robot depending on which variables are on
            if (rightBumper) {
                Drive.DriveTrain(5);
            } else if (leftBumper) {
                Drive.DriveTrain(-5);
            }
            else {
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            }
            if(startToggle) {
                Drive.releaseInPos = .5;
            } else {
                Drive. releaseInPos = 0;
            }

            if(aToggle && yToggle){
                Drive.InLPower = -1;
                Drive.InRPower = -1;
            } else if (aToggle){
                Drive.InLPower = 1;
                Drive.InRPower = 1;
            } else {
                Drive.InLPower = 0;
                Drive.InRPower = 0;
            }

            if(xToggle){
                Drive.PlateGrabLPos = .45;
                Drive.PlateGrabRPos = .45;
            } else {
                Drive.PlateGrabLPos = 0;
                Drive.PlateGrabRPos = 0;
            }
            Drive.SetMotorPower();


            // Changes the variables accordingly with the controller
  
            if(gamepad1.dpad_up){
                dPadUp = true;
                dPadDown = false;
            } else if (gamepad1.dpad_down){
                dPadUp = false;
                dPadDown = true;
            } else {
                dPadUp = dPadDown = false;
            }

            if(gamepad1.a) {
                aToggle = !aToggle;
            }
            if (gamepad1.y) {
                yToggle = !yToggle;
            }
            if (gamepad1.start) {
                startToggle = !startToggle;
            }
            if (gamepad1.x) {
                xToggle = !xToggle;
            }

            if (gamepad1.left_bumper) {
                leftBumper = true;
                rightBumper = false;
            } else if (gamepad1.right_bumper) {
                rightBumper = true;
                leftBumper = false;
            } else {
                rightBumper = leftBumper = false;
            }


            // Tells phone what information to display
            telemetry.addData("LFMotorPower",Drive.fl.getPower());
            telemetry.addData("LBMotorPower",Drive.bl.getPower());
            telemetry.addData("RFMotorPower",Drive.fr.getPower());
            telemetry.addData("RBMotorPower",Drive.br.getPower());
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
        }
    }
    public double AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (Drive.fl.getCurrentPosition() + Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition() + Drive.br.getCurrentPosition()) / 4;
    }
}
package org.firstinspires.ftc.teamcode.Test;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;
import org.firstinspires.ftc.teamcode.SkyStone.GamePadControls;

@TeleOp(name="OpMode", group = "Test")
public class OpModeTestController extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode() {
        //Robot.init(hardwareMap);
        Drive.init(hardwareMap);

        GamePadControls gp1;
        gp1 = new GamePadControls(gamepad1, new Object[][] {
                {"a", "button", "false"},
                {"b", "button", "false"},
                {"x", "button", "false"},
                {"y", "button", "false"},
                {"dpUp", "button", "false"},
                {"dpDown", "button", "false"},
                {"dpLeft", "button", "false"},
                {"dpRight", "button", "false"},
                {"rightBumper", "button", "false"},
                {"leftBumper", "button", "false"}});

        telemetry.addData("say", "before opmode");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            //Driving

            if (gp1.getVal("rightBumper")) {
                Drive.DriveTrain(5);
            } else if (gp1.getVal("leftBumper")) {
                Drive.DriveTrain(-5);
            } else {
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            }

            Drive.SetMotorPower();


            //grabber
            if(gp1.getVal("dpUp")){
                Drive.PlateGrabLPos = .6;
                Drive.PlateGrabRPos = .6;
            } else if (gp1.getVal("dpDown")){
                Drive.PlateGrabLPos = .1;
                Drive.PlateGrabRPos = .1;
            }
            //grabber fine adjustment

            if(gp1.getVal("dpLeft")){
                Drive.PlateGrabLPos += .1;
                Drive.PlateGrabRPos += .1;
            } else if(gp1.getVal("dpRight")){
                Drive.PlateGrabLPos -= .1;
                Drive.PlateGrabRPos -= .1;
            }
            //intake all controls

            if(gp1.getVal("a")){
                Drive.InLPower = 5;
                Drive.InRPower = 5;
            } else if (gp1.getVal("x")){
                Drive.InLPower = 0;
                Drive.InRPower = 0;
            } else if (gp1.getVal("y")){
                Drive.InLPower = -.5;
                Drive.InRPower = -.5;
            }

            gp1.updateController(gamepad1);
            

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

        }
    }
    public double AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (Drive.fl.getCurrentPosition() + Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition() + Drive.br.getCurrentPosition()) / 4;
    }
}
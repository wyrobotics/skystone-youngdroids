package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.ftdi.eeprom.FT_EEPROM_232H;

@TeleOp(name="MainOpMode", group="Robot")
public class MainOpMode extends LinearOpMode {

    //MainRobot Robot = new MainRobot();

    MecanumDrive Drive = new MecanumDrive();

    private static double LFMotorPower = 0.0, LBMotorPower = 0.0, RFMotorPower = 0.0, RBMotorPower = 0.0;

    @Override
    public void runOpMode() {
        //Robot.init(hardwareMap);
        Drive.init(hardwareMap);
        DcMotor InL, InR;
        Servo PlateGrabberL, PlateGrabberR, Grabber;

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();

        boolean aPressed = false;
        boolean yPressed = false;
        boolean xPressed = true;
        boolean bPressed;
        boolean dPadUp = true;
        boolean dPadDown = false;
        boolean rightTrigger, leftTrigger;

        while (opModeIsActive()) {
            Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            Drive.SetMotorPower();

            if(aPressed){
                Drive.InLPower = .5;
                Drive.InRPower = .5;
            } else if (xPressed){
                Drive.InLPower = 0;
                Drive.InRPower = 0;
            } else if (yPressed){
                Drive.InLPower = -.5;
                Drive.InRPower = -.5;
            }
            if(dPadUp){
                Drive.PlateGrabLPos = 1;
                Drive.PlateGrabRPos = 1;
            } else if (dPadDown){
                Drive.PlateGrabLPos = 0;
                Drive.PlateGrabRPos = 0;
            }



            if(gamepad1.dpad_up){
                dPadUp = true;
                dPadDown = false;
            } else if (gamepad1.dpad_down){
                dPadUp = false;
                dPadDown = true;
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
            telemetry.update();
            //

        }





    }
}
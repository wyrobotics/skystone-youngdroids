package org.firstinspires.ftc.teamcode.SkyStone;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;

@TeleOp(name="CoOpMode2020", group = "SkyStone")
public class CoOpMode extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();

    boolean[] a, b, x, y;

    boolean[] dPadUp, dPadDown, dPadLeft, dPadRight;
    boolean[] rightBumper, leftBumper;
    boolean[] leftTrigger, rightTrigger;

    boolean[] start, back, guide;

    boolean[] jsLeftButton, jsRightButton;

    double[] jsLeftX, jsLeftY, jsRightX, jsRightY;

    @Override
    public void runOpMode() {

        Drive.init(hardwareMap);
        telemetry.addData("say", "before CoOpMode");
        telemetry.update();


        waitForStart();


        a=b=x=y=dPadDown=dPadUp=dPadLeft=dPadRight=jsRightButton=jsLeftButton = butInit();
        rightBumper=leftBumper=leftTrigger=rightTrigger=start=back=guide = butInit();
        jsLeftX=jsLeftY=jsRightX=jsRightY = new double[]{0,0};



        while (opModeIsActive()) {
            updateControllers(new Gamepad[]{gamepad1,gamepad2});

           if (rightBumper[0]) {
               Drive.DriveTrain(4);
           } else if(leftBumper[0]) {
               Drive.DriveTrain(-4);
           } else {
               Drive.DriveTrain(jsLeftX[0],jsLeftY[0], jsRightX[0]);
           }

           //TODO: Choose code



            telemetry.addData("LFMotorPower",Drive.LFWheelPower);
            telemetry.addData("LBMotorPower",Drive.LBWheelPower);
            telemetry.addData("RFMotorPower",Drive.RFWheelPower);
            telemetry.addData("RBMotorPower",Drive.RBWheelPower);
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
    public boolean[] butInit() {
        return new boolean[]{false,false};
    }
    public void updateControllers(Gamepad[] gamepads) {
        for (int i = 0; i <= 1; i ++) {
            if(gamepads[i].right_bumper) {
                rightBumper[i] = true;
                leftBumper[i] = false;
            } else if(gamepads[i].left_bumper) {
                rightBumper[i] = false;
                leftBumper[i] = true;
            } else {
                rightBumper[i] = leftBumper[i] = false;
            }

            if(gamepads[i].right_trigger > .9) {
                rightTrigger[i] = true;
                leftTrigger[i] = false;
            } else if(gamepads[i].left_trigger > .9) {
                rightTrigger[i] = false;
                leftTrigger[i] = true;
            } else {
                rightTrigger[i] = leftTrigger[i] = false;
            }

            if(gamepads[i].dpad_up) {
                dPadUp[i] = true;
                dPadDown[i] = false;
            } else if(gamepads[i].dpad_down) {
                dPadUp[i] = false;
                dPadDown[i] = true;
            } else {
                dPadUp[i] = dPadDown[i] = false;
            }

            if(gamepads[i].dpad_left) {
                dPadLeft[i] = true;
                dPadRight[i] = false;
            } else if(gamepads[i].dpad_right) {
                dPadLeft[i] = false;
                dPadRight[i] = true;
            } else {
                dPadLeft[i] = dPadRight[i] = false;
            }

            if(gamepads[i].left_stick_button) {
                jsLeftButton[i] = true;
            } else {
                jsLeftButton[i] = false;
            }
            if(gamepads[i].right_stick_button) {
                jsRightButton[i] = true;
            } else {
                jsRightButton[i] = false;
            }

            if (gamepads[i].start) {
                start[i] = true;
            }
            if (gamepads[i].back) {
                back[i] = true;
            }
            if (gamepads[i].guide) {
                guide[i] = true;
            }
            jsLeftX[i] = gamepads[i].left_stick_x;
            jsLeftY[i] = gamepads[i].left_stick_y;
            jsRightX[i] = gamepads[i].right_stick_x;
            jsRightY[i] = gamepads[i].right_stick_y;

        }
    }
}
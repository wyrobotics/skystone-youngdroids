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
            //Driving
            if (gamepad1.right_bumper) {
                rightBumper = true;
                leftBumper = false;
            } else if (gamepad1.left_bumper) {
                leftBumper = true;
                rightBumper = false;
            } else {
                rightBumper = leftBumper = false;
            }
            if (rightBumper) {
                Drive.DriveTrain(5);
            } else if (leftBumper) {
                Drive.DriveTrain(-5);
            } else {
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            }




            Drive.SetMotorPower();
            //grabber
            if(gamepad1.dpad_up){
                dPadUp = true;
                dPadDown = false;
            } else if (gamepad1.dpad_down){
                dPadUp = false;
                dPadDown = true;
            }
            if(dPadDown){
                Drive.PlateGrabLPos = .6;
                Drive.PlateGrabRPos = .6;
            } else if (dPadUp){
                Drive.PlateGrabLPos = .1;
                Drive.PlateGrabRPos = .1;
            }
            //grabber fine adjustment
            if(gamepad1.dpad_left){
                dPadLeft = true;
                dPadRight = false;
            } else if (gamepad1.dpad_right){
                dPadRight = true;
                dPadLeft = false;
            } else {
                dPadRight = dPadLeft = false;
            }
            if(dPadLeft){
                Drive.releaseInPos += 5;
            } else if(dPadRight){
                Drive.releaseInPos -= 5;
            }
            //intake all controls
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
                Drive.releaseInPos -= 1;
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


        }
    }
}
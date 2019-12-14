package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.internal.ftdi.eeprom.FT_EEPROM_232H;

@TeleOp(name="MainOpModeNew", group="Robot")
public class MainOpMode extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode() {
        //Robot.init(hardwareMap);
        Drive.init(hardwareMap); // Maps our hardware
        Drive.zeroALL(); // Sets all of our positions and powers to 0

        telemetry.addData("say", "before opmode");
        telemetry.update();

        waitForStart();

        boolean aPressed = false, bPressed = false; // Variables for Buttons
        boolean yPressed = false, xPressed = false;

        boolean dPadUp = false, dPadDown = false; // Variables for dPad

        boolean rightTrigger = false, leftTrigger = false; // Variables for our triggers

        while (opModeIsActive()) {

            if (rightTrigger) { // Strafes if either Trigger is pressed
                Drive.DriveTrain(5);
            } else if (leftTrigger) {
                Drive.DriveTrain(-5);
            }
            else { // Drives normally otherwise
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            }

            if(bPressed) { // Lifts release when pressed
                Drive.SetMotorPower();
                Drive.releaseInPos = .5;
            }

            if(aPressed){ // Intake power
                Drive.InLPower = Drive.InRPower = 1;
            } else if (xPressed){
                Drive.InLPower = Drive.InRPower = 0;
            } else if (yPressed){
                Drive.InLPower  = Drive.InRPower = -1;
            }

            if(dPadUp){ // PlateGrabber power
                Drive.PlateGrabLPos = .45; Drive.PlateGrabRPos = .45;
            } else if (dPadDown){
                Drive.PlateGrabLPos = 0; Drive.PlateGrabRPos = 0;
            }



            if(gamepad1.dpad_up){ // If One of them is pressed, the other is off
                dPadUp = true;
                dPadDown = false;
            } else if (gamepad1.dpad_down){
                dPadUp = false;
                dPadDown = true;
            } else { // Else, turn both off
                dPadUp = dPadDown = false;
            }

            if(gamepad1.a){ // Same mechanism here, but with 3 values
                aPressed = true;
                xPressed = yPressed = false;
            } else if (gamepad1.x){
                xPressed = true;
                aPressed = yPressed = false;
            } else if (gamepad1.y){
                yPressed = true;
                aPressed = xPressed = false;
            } else {
                aPressed = xPressed = yPressed = false;
            }

            if (gamepad1.left_trigger > 0) { // Unsure how Triggers work, might give negative value instead of positive
                rightTrigger = false; leftTrigger = true;
            } else if (gamepad1.right_trigger > 0) {
                rightTrigger = true; leftTrigger = false;
            } else {
                rightTrigger = leftTrigger = false;
            }


            // Places info on our phone for our reference
            telemetry.addData("LFMotorPower",Drive.flWheelPower);
            telemetry.addData("LBMotorPower",Drive.blWheelPower);
            telemetry.addData("RFMotorPower",Drive.frWheelPower);
            telemetry.addData("RBMotorPower",Drive.brWheelPower);
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
        Drive.zeroALL(); // Sets everything to zero after it ends
    }
    public double AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (Drive.fl.getCurrentPosition() + Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition() + Drive.br.getCurrentPosition()) / 4;
    }
}
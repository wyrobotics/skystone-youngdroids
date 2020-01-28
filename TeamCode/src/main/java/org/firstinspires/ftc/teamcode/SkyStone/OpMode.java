package org.firstinspires.ftc.teamcode.SkyStone;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;

@TeleOp(name="OpMode2020", group = "SkyStone")
public class OpMode extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode() {

        Drive.init(hardwareMap); //Sets everything up

        telemetry.addData("say", "before testmode");
        telemetry.update();

        Drive.inCtrlLPos = 1; Drive.inCtrlRPos = 1;
        Drive. GrabberPos = 0.426;
        Drive.SetMotorPower();

        waitForStart(); // Waits for start

        Drive.inCtrlLPos = 0.621; Drive.inCtrlRPos = 0.675;
        Drive.SetMotorPower();
        Drive.InLPower = 5; Drive.InRPower = 10;


        // Declares all the variables
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
            // Sets all the variables to their respective Controller
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



            // Controller Code
            Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);

            if (rightBumper) { // Controls Lifter
                Drive.LifterPower = 1;
            } else if (leftBumper) {
                Drive.LifterPower = -1;
            } else {
                Drive.LifterPower = 0;
            }
            if (aPressed) { // Grabs Block, Stops intake, and opens intake
                Drive.GrabberPos = 0.577;
                Drive.inCtrlLPos = 0.621;
                Drive.inCtrlRPos = 0.675;
                Drive.InRPower = 0;
                Drive.InLPower = 0;
                Drive.SetMotorPower();
            }
            if (bPressed) { // Opens Grabber to drop block, then closes again
                Drive.GrabberPos = 0.426;
                Drive.SetMotorPower();
                Drive.GrabberPos = 0.577;
            }
            if (xPressed) { // Opens Grabber, Closes Intake, Turns on intake
                Drive.GrabberPos = 0.426;
                Drive.inCtrlLPos = 0.621;
                Drive.inCtrlRPos = 0.675;
                Drive.SetMotorPower();
                Drive.InLPower = 5;
                Drive.InRPower = 10;
            }
            if (dPadUp) {
                Drive.PlateGrabRPos = 0;
                Drive.PlateGrabLPos = 0;
            }
            if (dPadDown) {
                Drive.PlateGrabRPos = .4;
                Drive.PlateGrabLPos = .4;
            }



            Drive.SetMotorPower();



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
}
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="MainOpMode", group="Robot")
public class MainOpMode extends LinearOpMode {

    //MainRobot Robot = new MainRobot();

    MecanumDrive Drive = new MecanumDrive();

    private static double LFMotorPower = 0.0, LBMotorPower = 0.0, RFMotorPower = 0.0, RBMotorPower = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Robot.init(hardwareMap);
        Drive.init(hardwareMap);
        DcMotor InL, InR;
        Servo PlateGrabberL, PlateGrabberR, Grabber;

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();

        boolean xPressed, yPressed, aPressed, bPressed, dPadUp, dPadDown, rightTrigger, leftTrigger;

        while (opModeIsActive()) {
            Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            Drive.SetMotorPower();

            if (!this.gamepad1.dpad_up == this.gamepad1.dpad_down) { //TODO: Copy and paste code,
                                                                    // but make the robot strafe instead of moving the release
                if (!this.gamepad1.dpad_up) {
                    Drive.releaseInPower = 0.2;
                }
                else {
                    Drive.releaseInPower = -0.2;
                }
            } else {
                Drive.releaseInPower = 0;
            }
            //TODO: Make the X button turn on/off the intakes
            //TODO: Something to use strafe (Not required)
            //TODO: Controls for the Plate Grabbers


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
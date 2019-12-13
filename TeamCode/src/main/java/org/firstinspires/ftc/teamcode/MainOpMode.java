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
        DcMotor InL;
        DcMotor InR;
        Servo PlateGrabberL;
        Servo PlateGrabberR;
        Servo Grabber;

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            Drive.SetMotorPower();
            /*if(gamepad1.y){
                Robot.GrabPlate();
            }
            if(gamepad1.x) {
                Robot.ReleasePlate();
            }*/
            /*if(gamepad1.a){
                if(Robot.GrabOpen){
                    Robot.GrabBlock();
                }
                else{
                    Robot.ReleaseBlock();
                }
            }*/


            telemetry.addData("LFMotorPower",Drive.LFWheelPower);
            telemetry.addData("LBMotorPower",Drive.LBWheelPower);
            telemetry.addData("RFMotorPower",Drive.RFWheelPower);
            telemetry.addData("RBMotorPower",Drive.RBWheelPower);
            //telemetry.addData("Grabber Open?",Robot.GrabOpen);
            telemetry.addData("LF Position: ", Drive.LF.getCurrentPosition());
            telemetry.addData("LR Position: ", Drive.LB.getCurrentPosition());
            telemetry.addData("RF Position: ", Drive.RF.getCurrentPosition());
            telemetry.addData("RB Position: ", Drive.RB.getCurrentPosition());
            telemetry.update();
        }





    }
}
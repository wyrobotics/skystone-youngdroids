package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.android.dx.command.Main;
import org.firstinspires.ftc.robotcore.internal.ui.GamepadUser;

@TeleOp(name="MainOpMode", group="Robot")
public class MainOpMode extends LinearOpMode {

    //MainRobot Robot = new MainRobot();

    MecanumDrive Drive = new MecanumDrive();



    private static double LFMotorPower = 0.0;
    private static double LBMotorPower = 0.0;
    private static double RFMotorPower = 0.0;
    private static double RBMotorPower = 0.0;


    @Override
    public void runOpMode() throws InterruptedException {
        //Robot.init(hardwareMap);
        Drive.init(hardwareMap);
        //DcMotor UltraSlideV = Robot.UltraSlideMotorV;
        //DcMotor UltraSlideH = Robot.UltraSlideMotorH;
        //Servo PlateGrabberL = Robot.PlateGrabberL;
        //Servo PlateGrabberR = Robot.PlateGrabberR;
        //Servo Grabber = Robot.GrabServo;

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
            telemetry.update();
        }





    }
}
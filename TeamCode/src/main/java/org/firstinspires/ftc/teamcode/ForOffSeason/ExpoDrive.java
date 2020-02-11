package org.firstinspires.ftc.teamcode.ForOffSeason;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ExpoDrive")
public class ExpoDrive extends LinearOpMode {

    public DcMotor fl, bl, fr, br; // 4 Drive Motors
    public Servo PlateGrabL, PlateGrabR; // Plate Servos + Release
    public Servo Grabber;
    public DcMotor Lifter;
    public double LFWheelPower, LBWheelPower, RFWheelPower, RBWheelPower; // Power/Position of m/s
    public double LGrabOpen = 0;
    public double RGrabOpen = 0;
    public double LGrabClose = .4;
    public double RGrabClose = .4;
    public double GrabberOpenPos = 0.819;
    public double GrabberClosePos = 1.0;
    public double LiftBottom = 0;
    public double LiftTop = 30000;
    public boolean GrabOpen = false;


    @Override
    public void runOpMode(){

        fl = hardwareMap.dcMotor.get("fl"); bl = hardwareMap.dcMotor.get("bl"); // Maps all our motors/servos
        fr = hardwareMap.dcMotor.get("fr"); br = hardwareMap.dcMotor.get("br");
        Lifter = hardwareMap.dcMotor.get("Lifter"); Grabber = hardwareMap.servo.get("Grabber");
        PlateGrabL = hardwareMap.servo.get("LGrab"); PlateGrabR = hardwareMap.servo.get("RGrab");

        PlateGrabL.setPosition(LGrabOpen); PlateGrabR.setPosition(RGrabOpen);
        Grabber.setPosition(GrabberOpenPos);


        waitForStart();

        while(opModeIsActive()){

            LFWheelPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x);
            LBWheelPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x);
            RFWheelPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x);
            RBWheelPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);
            fl.setPower(LFWheelPower); bl.setPower(LBWheelPower);
            fr.setPower(RFWheelPower); br.setPower(RBWheelPower);

            if(GrabOpen){
                PlateGrabR.setPosition(RGrabOpen); PlateGrabL.setPosition(LGrabOpen);
            }
            else{
                PlateGrabR.setPosition(RGrabClose); PlateGrabL.setPosition(LGrabClose);
            }
            if(gamepad1.a){
                GrabOpen = !GrabOpen;
            }
            if(gamepad1.dpad_up || Lifter.getCurrentPosition() < LiftTop){
                Lifter.setPower(1);
            }
            if(gamepad1.dpad_down || Lifter.getCurrentPosition() > LiftBottom){
                Lifter.setPower(-1);
            }
            if(gamepad1.b){
                Grabber.setPosition(GrabberOpenPos);
            }
            if(gamepad1.x){
                Grabber.setPosition(GrabberClosePos);
            }
            telemetry.addData("Lift", Lifter.getCurrentPosition());

        }
    }
}

package org.firstinspires.ftc.teamcode.ForOffSeason;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ExpoDrive Motor Vals")
public class FindValuesMotorsEXPO extends LinearOpMode {

    public DcMotor fl, bl, fr, br; // 4 Drive Motors
    public Servo Grabber;
    public DcMotor Lifter;
    public DigitalChannel tSensor;
    public double LFWheelPower, LBWheelPower, RFWheelPower, RBWheelPower; // Power/Position of m/s
    public double GrabberOpenPos = 0.180;
    public double GrabberClosePos = 0.0020;
    public double LiftBottom = 0;
    public double LiftTop = 2700;

    public double grabberPos = 0;


    @Override
    public void runOpMode(){

        fl = hardwareMap.dcMotor.get("fl"); bl = hardwareMap.dcMotor.get("bl"); // Maps all our motors/servos
        fr = hardwareMap.dcMotor.get("fr"); br = hardwareMap.dcMotor.get("br");
        Lifter = hardwareMap.dcMotor.get("Lifter"); Grabber = hardwareMap.servo.get("Grabber");

        tSensor = hardwareMap.get(DigitalChannel.class,"tSensor");

        Lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Grabber.setDirection(Servo.Direction.REVERSE);

        fl.setDirection(DcMotorSimple.Direction.REVERSE); bl.setDirection(DcMotorSimple.Direction.REVERSE);
        tSensor.setMode(DigitalChannel.Mode.INPUT);

        //Grabber.setPosition(GrabberOpenPos);
        Grabber.setPosition(GrabberClosePos);
        while (tSensor.getState()) {
            Lifter.setPower(-1);
        }
        try {
            sleep(250);
        } catch (Exception e){
            e.getStackTrace();
        }
        Lifter.setPower(0);
        try {
            sleep(250);
        } catch (Exception e){
            e.getStackTrace();
        }
        Lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        Grabber.setPosition(GrabberOpenPos);

        while(opModeIsActive()){

            LFWheelPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x);
            LBWheelPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x);
            RFWheelPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x);
            RBWheelPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);
            fl.setPower(LFWheelPower); bl.setPower(LBWheelPower);
            fr.setPower(RFWheelPower); br.setPower(RBWheelPower);


            if(gamepad1.dpad_up && Lifter.getCurrentPosition() < LiftTop){
                Lifter.setPower(1);
            } else if(gamepad1.dpad_down && Lifter.getCurrentPosition() > LiftBottom){
                Lifter.setPower(-1);
            } else {
                Lifter.setPower(0);
            }
            if(gamepad1.b){
                grabberPos += 0.001;
                Grabber.setPosition(grabberPos);
            }
            if(gamepad1.x){
                grabberPos -= 0.001;
                Grabber.setPosition(grabberPos);
            }
            if(gamepad1.y) {
                Grabber.setPosition(GrabberOpenPos);
            }
            if (gamepad1.a) {
                Grabber.setPosition(GrabberClosePos);
            }
            telemetry.addData("Lift", Lifter.getCurrentPosition());
            telemetry.addData("tSensor", tSensor.getState());
            telemetry.addData("grabber: ", grabberPos);
            telemetry.update();
        }

    }


}

package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class MecanumDrive{

    public void init(HardwareMap HM) {
        fl = HM.dcMotor.get("fl"); bl = HM.dcMotor.get("bl"); // Maps all our motors/servos
        fr = HM.dcMotor.get("fr"); br = HM.dcMotor.get("br");
        InL = HM.dcMotor.get("InL"); InR = HM.dcMotor.get("InR");

        releaseIn = HM.servo.get("releaseIn");
        PlateGrabL = HM.servo.get("PlateGrabL"); PlateGrabR = HM.servo.get("PlateGrabR");
        //releaseIn = HM.servo.get("intakeR");


        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //sLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //Reverse Direction of certain motors/servos
        fl.setDirection(DcMotorSimple.Direction.REVERSE); bl.setDirection(DcMotorSimple.Direction.REVERSE);
        InL.setDirection(DcMotorSimple.Direction.REVERSE);
        PlateGrabL.setDirection(Servo.Direction.REVERSE);
    }

    public DcMotor fl, bl, fr, br; // 4 Drive Motors
    public DcMotor InL, InR; // Intake Motors
    public Servo releaseIn, PlateGrabL, PlateGrabR; // Plate Servos + Release
    

    public double LFWheelPower, LBWheelPower, RFWheelPower, RBWheelPower; // Power/Position of m/s
    public double releaseInPos, PlateGrabLPos, PlateGrabRPos, InLPower, InRPower; // (motors/servos)
    public double intakeRPos;


    public void DriveTrain(double left_stick_x,double left_stick_y, double right_stick_x) {
        // the math for the mecanum wheel
        LFWheelPower = (-left_stick_y + right_stick_x + left_stick_x) * 2;
        LBWheelPower = (-left_stick_y + right_stick_x - left_stick_x) * 2;
        RFWheelPower = (-left_stick_y - right_stick_x - left_stick_x) * 2;
        RBWheelPower = (-left_stick_y - right_stick_x + left_stick_x) * 2;
    }
    public void DriveTrain(double x) { // For Strafing rather than normal movement
        LFWheelPower = RFWheelPower = x;
        LBWheelPower = RBWheelPower = -x;
    }

    public void SetMotorPower(){
        // Sets the power/pos of all our hardware to what it should be
        fl.setPower(LFWheelPower); bl.setPower(LBWheelPower);//
        fr.setPower(RFWheelPower); br.setPower(RBWheelPower);
        //Grabber.setPosition(GrabberPos); sLift.setPower(sLiftPower);

        releaseIn.setPosition(releaseInPos);
        PlateGrabL.setPosition(PlateGrabLPos); PlateGrabR.setPosition(PlateGrabRPos);
        InL.setPower(InLPower); InR.setPower(InLPower);
        releaseIn.setPosition(releaseInPos);

    }

}
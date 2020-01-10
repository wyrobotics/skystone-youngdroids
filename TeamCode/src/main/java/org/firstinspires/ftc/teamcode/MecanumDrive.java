package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MecanumDrive{

    public void init(HardwareMap HM) {
        fl = HM.dcMotor.get("fl"); bl = HM.dcMotor.get("bl"); // Maps all our motors/servos
        fr = HM.dcMotor.get("fr"); br = HM.dcMotor.get("br");
        InL = HM.dcMotor.get("InL"); InR = HM.dcMotor.get("InR");
        releaseIn = HM.servo.get("releaseIn");
        PlateGrabL = HM.servo.get("PlateGrabL"); PlateGrabR = HM.servo.get("PlateGrabR");

        Grabber = HM.servo.get("Grabber"); sLift = HM.dcMotor.get("sLift");


        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Reverse Direction of certain motors/servos
        fl.setDirection(DcMotorSimple.Direction.REVERSE); bl.setDirection(DcMotorSimple.Direction.REVERSE);
        InL.setDirection(DcMotorSimple.Direction.REVERSE);
        PlateGrabL.setDirection(Servo.Direction.REVERSE);
    }

    DcMotor fl, bl, fr, br; // 4 Drive Motors
    DcMotor InL, InR; // Intake Motors
    DcMotor sLift;
    Servo Grabber;
    Servo releaseIn, PlateGrabL, PlateGrabR; // Plate Servos + Release
    double LFWheelPower, LBWheelPower, RFWheelPower, RBWheelPower; // Power/Position of m/s
    double releaseInPos, PlateGrabLPos, PlateGrabRPos, InLPower, InRPower; // (motors/servos)
    double GrabberPos, sLiftPower;


    public void DriveTrain(double left_stick_x,double left_stick_y, double right_stick_x) {
        // the math for the mecanum wheel
        LFWheelPower = (-left_stick_y + right_stick_x + left_stick_x) * 4;
        LBWheelPower = (-left_stick_y + right_stick_x - left_stick_x) * 4;
        RFWheelPower = (-left_stick_y - right_stick_x - left_stick_x) * 4;
        RBWheelPower = (-left_stick_y - right_stick_x + left_stick_x) * 4;
    }
    public void DriveTrain(double x) { // For Strafing rather than normal movement
        LFWheelPower = RFWheelPower = x;
        LBWheelPower = RBWheelPower = -x;
    }

    public void SetMotorPower(){
        // Sets the power/pos of all our hardware to what it should be
        fl.setPower(LFWheelPower); bl.setPower(LBWheelPower);//
        fr.setPower(RFWheelPower); br.setPower(RBWheelPower);
        Grabber.setPosition(GrabberPos); sLift.setPower(sLiftPower);

        releaseIn.setPosition(releaseInPos);
        PlateGrabL.setPosition(PlateGrabLPos); PlateGrabR.setPosition(PlateGrabRPos);
        InL.setPower(InLPower); InR.setPower(InLPower);


    }

}
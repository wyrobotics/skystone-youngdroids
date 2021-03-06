package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.lang.Math.*;

@Disabled
public class MecanumDrive{

    public void init(HardwareMap HM) {
        tSensor = HM.get(DigitalChannel.class,"tSensor");


        fl = HM.dcMotor.get("fl"); bl = HM.dcMotor.get("bl"); // Maps all our motors/servos
        fr = HM.dcMotor.get("fr"); br = HM.dcMotor.get("br");
        InL = HM.dcMotor.get("InL"); InR = HM.dcMotor.get("InR");
        Lifter = HM.dcMotor.get("Lifter"); Grabber = HM.servo.get("Grabber");

        inCtrlL = HM.servo.get("inCtrlL"); inCtrlR = HM.servo.get("inCtrlR");
        PlateGrabL = HM.servo.get("PlateGrabL"); PlateGrabR = HM.servo.get("PlateGrabR");


        Lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tSensor.setMode(DigitalChannel.Mode.INPUT);


        Lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        fl.setDirection(DcMotorSimple.Direction.REVERSE); bl.setDirection(DcMotorSimple.Direction.REVERSE);
        InL.setDirection(DcMotorSimple.Direction.REVERSE);
        PlateGrabL.setDirection(Servo.Direction.REVERSE);
        inCtrlL.setDirection(Servo.Direction.REVERSE);
    }

    public DcMotor fl, bl, fr, br; // 4 Drive Motors
    public DcMotor InL, InR; // Intake Motors
    public Servo PlateGrabL, PlateGrabR; // Plate Servos + Release
    public Servo Grabber;
    public DcMotor Lifter;
    public Servo inCtrlL, inCtrlR;
    public DigitalChannel tSensor;

    public double LFWheelPower, LBWheelPower, RFWheelPower, RBWheelPower; // Power/Position of m/s
    public double PlateGrabLPos, PlateGrabRPos, InLPower, InRPower; // (motors/servos)
    public double LifterPower, GrabberPos;
    public double inCtrlLPos, inCtrlRPos;


    public synchronized void DriveTrain(double left_stick_x,double left_stick_y, double right_stick_x, double rt, double lt) {
        // the math for the mecanum wheel
        LFWheelPower = (-left_stick_y + right_stick_x + left_stick_x) * 2 + (rt - lt) * 2;
        LBWheelPower = (-left_stick_y + right_stick_x - left_stick_x) * 2 + (rt - lt) * 2;
        RFWheelPower = (-left_stick_y - right_stick_x - left_stick_x) * 2 + (lt - rt) * 2;
        RBWheelPower = (-left_stick_y - right_stick_x + left_stick_x) * 2 + (lt - rt) * 2;
    }
    public synchronized  void DriveTrain(double lsx, double lsy, double rsx) {
        DriveTrain(lsx,lsy,rsx,0,0);
    }

    public void SetMotorPower(){
        synchronized (this) {
            Grabber.setPosition(GrabberPos);
        }
        Lifter.setPower(LifterPower);

        // Sets the power/pos of all our hardware to what it should be
        fl.setPower(LFWheelPower); bl.setPower(LBWheelPower);//
        fr.setPower(RFWheelPower); br.setPower(RBWheelPower);



        inCtrlL.setPosition(inCtrlLPos); inCtrlR.setPosition(inCtrlRPos);
        PlateGrabL.setPosition(PlateGrabLPos); PlateGrabR.setPosition(PlateGrabRPos);
        InL.setPower(InLPower); InR.setPower(InLPower);

    }

}
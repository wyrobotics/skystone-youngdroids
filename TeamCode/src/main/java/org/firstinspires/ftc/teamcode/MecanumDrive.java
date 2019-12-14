package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class MecanumDrive{

    public void init(HardwareMap HM) {
        fl = HM.dcMotor.get("fl"); bl = HM.dcMotor.get("bl"); // map the Front wheels
        fr = HM.dcMotor.get("fr"); br = HM.dcMotor.get("br"); // Map the Back wheels
        InL = HM.dcMotor.get("InL"); InR = HM.dcMotor.get("InR");
        releaseIn = HM.servo.get("releaseIn");
        PlateGrabL = HM.servo.get("PlateGrabL"); PlateGrabR = HM.servo.get("PlateGrabR");


        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fr.setDirection(DcMotorSimple.Direction.REVERSE); br.setDirection(DcMotorSimple.Direction.REVERSE);// reverse the left wheels direction
        InR.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    DcMotor fl, bl, fr, br; // 4 Drive Motors
    DcMotor InL, InR; // Intake Motors
    Servo releaseIn, PlateGrabL, PlateGrabR; // Plate Servos + Release
    double LFWheelPower, LBWheelPower, RFWheelPower, RBWheelPower, releaseInPos, PlateGrabLPos, PlateGrabRPos, InLPower, InRPower; // Power of Wheels


    /*public void DriveTrain(double left_stick_x,double left_stick_y, double right_stick_x) {// the math for the mecanum wheel
        double r = Math.hypot(left_stick_x, left_stick_y);
        double robotAngle = Math.atan2(left_stick_y, left_stick_x) - Math.PI / 4;
        double rightX = right_stick_x;

        LFWheelPower = r * Math.cos(robotAngle) + rightX;
        RFWheelPower = r * Math.sin(robotAngle) - rightX;
        LBWheelPower = r * Math.sin(robotAngle) + rightX;
        RBWheelPower = r * Math.cos(robotAngle) - rightX;
    }
     */
    public void DriveTrain(double left_stick_x,double left_stick_y, double right_stick_x) {// the math for the mecanum wheel
        LFWheelPower = -left_stick_y - right_stick_x + left_stick_x;
        LBWheelPower = -left_stick_y - right_stick_x - left_stick_x;
        RFWheelPower = -left_stick_y + right_stick_x - left_stick_x;
        RBWheelPower = -left_stick_y + right_stick_x + left_stick_x;
    }

    public void SetMotorPower(){
        fl.setPower(LFWheelPower); bl.setPower(LBWheelPower);// set the wheel power to what it should be
        fr.setPower(RFWheelPower); br.setPower(RBWheelPower);

        releaseIn.setPosition(releaseInPos);
        PlateGrabL.setPosition(PlateGrabLPos); PlateGrabR.setPosition(PlateGrabRPos);
        InL.setPower(InLPower); InR.setPower(InLPower);
        // The strafing makes the front go forward and back go backward when controller goes to the right
    }

}
/*
lf
lb
rf
rb




 */

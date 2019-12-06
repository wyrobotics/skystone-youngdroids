package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class MecanumDrive{

    public void init(HardwareMap HM) {
        LFWheel = HM.dcMotor.get("lf");// map the wheel
        LBWheel = HM.dcMotor.get("lb");// map the wheel
        RFWheel = HM.dcMotor.get("rf");// map the wheel
        RBWheel = HM.dcMotor.get("rb");// map the wheel

        RFWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RBWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LFWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LBWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RFWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LFWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RFWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LFWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LFWheel.setDirection(DcMotorSimple.Direction.REVERSE);// reverse the left wheel direction
        LBWheel.setDirection(DcMotorSimple.Direction.REVERSE);// reverse the left wheel direction
    }

    DcMotor LFWheel; // Left Front Wheel
    DcMotor LBWheel; // Left Back Wheel
    DcMotor RFWheel; // Right Front Wheel
    DcMotor RBWheel; // Right Back Wheel

    double LFWheelPower;// left front wheel power
    double LBWheelPower;// left back wheel power
    double RFWheelPower;// right front wheel power
    double RBWheelPower;// right back wheel power



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
        LFWheel.setPower(LFWheelPower);// set the wheel power to what it should be
        LBWheel.setPower(LBWheelPower);// set the wheel power to what it should be
        RFWheel.setPower(RFWheelPower);// set the wheel power to what it should be
        RBWheel.setPower(RBWheelPower);// set the wheel power to what it should be
        // The strafing makes the front go forward and back go backward when controller goes to the right
    }

}
/*
lf
lb
rf
rb




 */

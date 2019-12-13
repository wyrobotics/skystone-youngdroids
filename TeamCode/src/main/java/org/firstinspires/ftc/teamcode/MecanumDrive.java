package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class MecanumDrive{

    public void init(HardwareMap HM) {
        LF = HM.dcMotor.get("lf"); LB = HM.dcMotor.get("lb"); // map the Front wheels
        RF = HM.dcMotor.get("rf"); RB = HM.dcMotor.get("rb"); // Map the Back wheels

        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER); RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER); LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LF.setDirection(DcMotorSimple.Direction.REVERSE); LB.setDirection(DcMotorSimple.Direction.REVERSE);// reverse the left wheels direction
    }

    DcMotor LF, LB, RF, RB; // Wheels

    double LFWheelPower, LBWheelPower, RFWheelPower, RBWheelPower; // Power of Wheels


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
        LF.setPower(LFWheelPower); LB.setPower(LBWheelPower);// set the wheel power to what it should be
        RF.setPower(RFWheelPower); RB.setPower(RBWheelPower);
        // The strafing makes the front go forward and back go backward when controller goes to the right
    }

}
/*
lf
lb
rf
rb




 */

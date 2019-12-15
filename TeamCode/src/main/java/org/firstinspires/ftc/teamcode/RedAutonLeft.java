package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.sql.Driver;


@Autonomous(
        name = "RedAutonLeft"
)
public class RedAutonLeft extends LinearOpMode {

    AutonFunctions auton;

    @Override
    public void runOpMode() throws InterruptedException {

        auton.init(hardwareMap);

        waitForStart();

        if(opModeIsActive()){

            auton.releaseIn.setPosition(.5);
            auton.MoveForward(1, opModeIsActive());
            auton.Rotate90(.6, opModeIsActive());
            auton.MoveForward(1, opModeIsActive());

            /*InR.setPower(.75); InL.setPower(.75);

            MoveForward(1.5);
            MoveForward(-.2);
            Rotate90(.6);
            Rotate90(.6);
            InR.setPower(-.75); InL.setPower(-.75);
            MoveForward(1.3);
            MoveForward(-1.7);*/
            /*InR.setPower(.75); InL.setPower(.75);

            MoveForward(1.5); MoveForward(-.4);

            Rotate90(.5); MoveForward(3);
            Rotate90(.5); MoveForward(-.4);

            PlateGrabL.setPosition(1); PlateGrabR.setPosition(1);

            Rotate90(.5); Strafe(-1.5);
            wait(500);

            PlateGrabL.setPosition(0); PlateGrabR.setPosition(0);
            Strafe(1.5);
            MoveForward(2.3);*/
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.sql.Driver;


@Autonomous(
        name = "BlueAutonRight"
)
public class BlueAutonRight extends LinearOpMode {

    AutonFunctions auton;

    @Override
    public void runOpMode() throws InterruptedException {

        auton.init(hardwareMap);

        waitForStart();

        if (opModeIsActive()){

            auton.releaseIn.setPosition(.5);
            auton.MoveForward(1, opModeIsActive());
            auton.Rotate90(-.6, opModeIsActive());
            auton.MoveForward(1, opModeIsActive());

            /*InR.setPower(.75); InL.setPower(.75);

            MoveForward(1.5);
            MoveForward(-.2);
            Rotate90(-.6);
            InR.setPower(-.75); InL.setPower(-.75);
            MoveForward(1.5);
            MoveForward(-1.7);*/
            /*MoveForward(1.5); MoveForward(-.4);

            Rotate90(-.6); MoveForward(3);
            Rotate90(-.6); MoveForward(-.4);

            PlateGrabL.setPosition(1); PlateGrabR.setPosition(1);

            Rotate90(-.6); Strafe(1.5);

            PlateGrabL.setPosition(0); PlateGrabR.setPosition(0);

            Strafe(-1.5);
            MoveForward(2.3);*/
        }
    }
}

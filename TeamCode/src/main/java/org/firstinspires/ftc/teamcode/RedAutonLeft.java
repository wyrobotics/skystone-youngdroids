package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



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

            auton.InR.setPower(.75); auton.InL.setPower(.75);

            auton.MoveForward(1.5,opModeIsActive()); auton.MoveForward(-.4,opModeIsActive());

            auton.Rotate90(.5,opModeIsActive()); auton.MoveForward(3,opModeIsActive());
            auton.Rotate90(.5,opModeIsActive()); auton.MoveForward(-.4,opModeIsActive());

            auton.PlateGrabL.setPosition(1); auton.PlateGrabR.setPosition(1);

            auton.Rotate90(.5,opModeIsActive()); auton.Strafe(-1.5,opModeIsActive());
            wait(500);

            auton.PlateGrabL.setPosition(0); auton.PlateGrabR.setPosition(0);
            auton.Strafe(1.5, opModeIsActive());
            auton.MoveForward(2.3, opModeIsActive());
        }
    }
}

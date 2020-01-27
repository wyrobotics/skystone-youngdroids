package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.SkyStone.AutonFunctions;

@Autonomous(name = "BlueAutonRight2019", group = "SkyStone")
public class BlueAutonRight extends LinearOpMode {

    AutonFunctions auton = new AutonFunctions();

    @Override
    public void runOpMode(){


        waitForStart();

        auton.init(hardwareMap);

        if (opModeIsActive()){

            //move forward with start orientation facing tape
            //FOR ROBOPHINS
            // ////////auton.releaseIn.setPosition(.5);
            auton.MoveForward(.5,opModeIsActive());

            //grab a SINGLE block and park on the duct tape
            //start in front of the last block (1 tiles from tape)
            //FOR OTHER
            /*auton.releaseIn.setPosition(.5);
            auton.InR.setPower(.75); auton.InR.setPower(.75);

            auton.MoveForward(2,opModeIsActive());
            auton.MoveForward(-1.6,opModeIsActive());
            auton.Rotate90(-.5,opModeIsActive());
            auton.MoveForward(2,opModeIsActive());
            auton.MoveForward(-1,opModeIsActive());*/

            /*auton.releaseIn.setPosition(.5);

            auton.InR.setPower(.75); auton.InL.setPower(.75);

            auton.MoveForward(1.5,opModeIsActive()); auton.MoveForward(-.4,opModeIsActive());

            auton.Rotate90(-.5,opModeIsActive()); auton.MoveForward(3,opModeIsActive());

            auton.InR.setPower(.2); auton.InL.setPower(-.2);

            auton.Rotate90(.5,opModeIsActive()); auton.Rotate90(.5,opModeIsActive());
            auton.MoveForward(.4,opModeIsActive());

            auton.Rotate90(-.5,opModeIsActive());


            auton.InR.setPower(.75); auton.InL.setPower(.75);

            auton.MoveForward(1,opModeIsActive()); auton.MoveForward(-1,opModeIsActive());
            auton.Rotate90(-.5,opModeIsActive()); auton.MoveForward(2.0,opModeIsActive());
            auton.MoveForward(1.0,opModeIsActive());
             */
        }
    }
}

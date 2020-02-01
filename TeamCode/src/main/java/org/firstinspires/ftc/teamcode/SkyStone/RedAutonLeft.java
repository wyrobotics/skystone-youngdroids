package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// Next to plate
@Autonomous(name = "RedAutonLeft 2020", group = "SkyStone")
public class RedAutonLeft extends LinearOpMode {


    AutonFunctions auton = new AutonFunctions();

    public void rest(int a) {
        try {
            sleep(a);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    @Override
    public void runOpMode(){


        waitForStart();

        auton.init(hardwareMap);

        if (opModeIsActive()){
            auton.inCtrlLPos = auton.inCtrlRPos = 1;
            auton.SetMotorPower();


            auton.MoveForward(1, opModeIsActive());
            auton.Rotate90(2.5, opModeIsActive());
            auton.MoveForward(-.5, opModeIsActive());

            rest(250);

            auton.PlateGrabLPos = auton.PlateGrabRPos = .4;
            auton.SetMotorPower();

            rest(500);

            auton.MoveForward(1, opModeIsActive());
            auton.Rotate90(-4, opModeIsActive());

            auton.MoveForward(-1, opModeIsActive());

            auton.PlateGrabLPos = auton.PlateGrabRPos = 0;
            auton.SetMotorPower();

            auton.MoveForward(1.7, opModeIsActive());
        }
    }
}

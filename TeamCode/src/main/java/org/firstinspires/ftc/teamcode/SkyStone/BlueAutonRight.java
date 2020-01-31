package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TobyCode.AutonFunctions;


@Autonomous(name = "BlueAutonRight2020", group = "SkyStone")
public class BlueAutonRight extends LinearOpMode {




    AutonFunctions auton = new AutonFunctions();
    int t = 1;
    public void pause() {
        while (!gamepad1.start) {
            t ++;
        }
    }
    @Override
    public void runOpMode(){


        waitForStart();

        auton.init(hardwareMap);

        if (opModeIsActive()){
            auton.MoveForward(1, opModeIsActive());
            pause();
            auton.MoveForward(-1, opModeIsActive());
            pause();
            auton.Rotate90( 5, opModeIsActive());
            pause();
            auton.Rotate90(-5, opModeIsActive());
            pause();
            auton.Strafe(1, opModeIsActive());
            pause();
            auton.Strafe(-1, opModeIsActive());
        }
    }
}

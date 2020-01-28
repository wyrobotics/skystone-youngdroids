package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.SkyStone.AutonFunctions;

@Autonomous( name = "RedAutonLeft2020", group = "SkyStone"
)
public class RedAutonLeft extends LinearOpMode {

    AutonFunctions auton = new AutonFunctions();

    @Override
    public void runOpMode(){


        waitForStart();

        auton.init(hardwareMap);

        if (opModeIsActive()){

            ////////auton.releaseIn.setPosition(0);
            auton.InR.setPower(-1); auton.InR.setPower(-1);
            auton.MoveForward(2,opModeIsActive());
            auton.InL.setPower(1); auton.InR.setPower(1);
            auton.MoveForward(-1.6,opModeIsActive());
            auton.Rotate90(.5,opModeIsActive());
            auton.MoveForward(2,opModeIsActive());
            auton.MoveForward(-1,opModeIsActive());
        }
    }
}

package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//Next to plate
@Autonomous(name = "Just Park", group = "SkyStone")
public class justPark extends LinearOpMode {

    AutonFunctions auton = new AutonFunctions();

    public void rest(int a) {
        try {
            sleep(a);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    String a = "Out"; // closer to middle bridge
    //String a = "In" // closer to wall (Posititon facing bridge)


    @Override
    public void runOpMode(){


        waitForStart();

        auton.init(hardwareMap);

        if (opModeIsActive()){
            if ( a == "Out") {
                auton.MoveForward(1, opModeIsActive());
                auton.Rotate90(1, opModeIsActive());
                auton.MoveForward(1,opModeIsActive());
            } else if (a == "In") {
                auton.MoveForward(1,opModeIsActive());
            }
        }
    }
}

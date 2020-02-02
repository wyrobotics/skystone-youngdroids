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

    String a = "In"; // closer to middle bridge
    //String a = "In" // closer to wall (Posititon facing bridge)


    @Override
    public void runOpMode(){


        waitForStart();

        auton.init(hardwareMap);

        if (opModeIsActive()){
            if ( a == "Out") {
                MoveForward(0.9, opModeIsActive());
                Rotate90(-1.5, opModeIsActive());
                MoveForward(1.2,opModeIsActive());
            } else if (a == "In") {
                MoveForward(1.4,opModeIsActive());
            }
        }
    }
    public final double RotationsPerTileForward = 1850, RotationsPer90 = 1225, RotationsPerStafe = 3000;

    public void MoveForward(double tiles, boolean opActive){ // pow is how fast it moves, + is CW, - is CCW
        if (!opActive) {return;}
        auton.resetMotorEncoder();
        if ( tiles < 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition() +
                    auton.fl.getCurrentPosition() + auton.bl.getCurrentPosition()) / 4 >= RotationsPerTileForward * tiles && opModeIsActive()) {
                auton.setPowers(-.875,-1.0,-.875,-1.0);
            }
            auton.setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition() +
                    auton.fl.getCurrentPosition() + auton.bl.getCurrentPosition()) / 4 <= RotationsPerTileForward * tiles && opModeIsActive()) {
                auton.setPowers(.9,1.0,.9,1.0);
            }
            auton.setPowers(0,0,0,0);
        }
        auton.resetMotorEncoder();
    }
    public void Rotate90(double tiles, boolean opActive) {
        if (!opActive) {return;}
        auton.resetMotorEncoder();
        if ( tiles < 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition()) / 2 >=  tiles * RotationsPer90 && opModeIsActive()) {
                auton.setPowers(1,-1,1,-1);
            }
            auton.setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((auton.fr.getCurrentPosition() + auton.br.getCurrentPosition()) / 2 <= tiles * RotationsPer90 && opModeIsActive()) {
                auton.setPowers(-1,1,-1,1);
            }
            auton.setPowers(0,0,0,0);
        }
        auton.resetMotorEncoder();
    }
}

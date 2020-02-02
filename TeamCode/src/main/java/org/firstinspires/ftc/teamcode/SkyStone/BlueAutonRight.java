package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//Next to plate
@Autonomous(name = "BlueAutonRight 2020", group = "SkyStone")
public class BlueAutonRight extends LinearOpMode {

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


            MoveForward(1, opModeIsActive());
            Rotate90(2.5, opModeIsActive());
            MoveForward(-.5, opModeIsActive());

            rest(250);

            auton.PlateGrabLPos = auton.PlateGrabRPos = .4;
            auton.SetMotorPower();

            rest(500);

            MoveForward(1, opModeIsActive());
            Rotate90(4, opModeIsActive());

            MoveForward(-1, opModeIsActive());

            auton.PlateGrabLPos = auton.PlateGrabRPos = 0;
            auton.SetMotorPower();

            MoveForward(1.7, opModeIsActive());

            return;
        }
        return;
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

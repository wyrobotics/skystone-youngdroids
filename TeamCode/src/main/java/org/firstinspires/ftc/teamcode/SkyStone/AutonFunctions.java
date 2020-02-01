package org.firstinspires.ftc.teamcode.SkyStone;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class AutonFunctions extends MecanumDrive {

    public final double RotationsPerTileForward = 1850, RotationsPer90 = 1225, RotationsPerStafe = 3000;

    public void MoveForward(double tiles, boolean opActive){ // pow is how fast it moves, + is CW, - is CCW
        if (!opActive) {return;}
        resetMotorEncoder();
        if ( tiles < 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition() +
                    fl.getCurrentPosition() + bl.getCurrentPosition()) / 4 >= RotationsPerTileForward * tiles) {
                setPowers(-.875,-1.0,-.875,-1.0);
            }
            setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition() +
                    fl.getCurrentPosition() + bl.getCurrentPosition()) / 4 <= RotationsPerTileForward * tiles) {
                setPowers(.9,1.0,.9,1.0);
            }
            setPowers(0,0,0,0);
        }
        resetMotorEncoder();
    }
    public void Rotate90(double tiles, boolean opActive) {
        if (!opActive) {return;}
        resetMotorEncoder();
        if ( tiles < 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition()) / 2 >=  tiles * RotationsPer90) {
                setPowers(1,-1,1,-1);
            }
            setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition()) / 2 <= tiles * RotationsPer90) {
                setPowers(-1,1,-1,1);
            }
            setPowers(0,0,0,0);
        }
        resetMotorEncoder();
    }
    public void resetMotorEncoder(){
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setPowers(double a, double b, double c, double d) {
        LFWheelPower = a; RFWheelPower = b;
        LBWheelPower = c; RBWheelPower = d;
        SetMotorPower();
    }


}
